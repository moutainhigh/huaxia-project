package com.huaxia.opas.action.win;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.util.PadProperties;

public class ImageAction implements Action {
	
	public void findImageUrlByAppId(Context ctx) {
		// 15位条码
		String fileid = ctx.getData("fileid");
		StringBuilder imgurl = new StringBuilder();
		Socket sock = null;
		BufferedOutputStream oos = null;
		BufferedInputStream ois = null;
		try {
//			InetAddress addr = InetAddress.getByName("106.32.6.11");
			Map<String, String> paroperMap = PadProperties.getParoperMap();
			System.out.println(paroperMap+">>>>>>>>>>>>>>>>>>>>>>>>>>>");
			String imageUrl = paroperMap.get("imageUrl");
			String imagePort = paroperMap.get("imagePort");
			InetAddress addr = InetAddress.getByName(imageUrl);
			Integer port = Integer.parseInt(imagePort);
			SocketAddress sockaddr = new InetSocketAddress(addr, port);
			sock = new Socket();
			sock.connect(sockaddr, 20000);
			if (sock != null && sock.isConnected()) {
				try {
					// String str = "MWaps";
					String str = "CM" + fileid + "1";
					oos = new BufferedOutputStream(sock.getOutputStream());
					oos.write(str.getBytes("GBK"));
					oos.flush();
					ois = new BufferedInputStream(sock.getInputStream());
					byte[] chunk = new byte[1024];
					int len;
					while((len = ois.read(chunk))!=-1){
						if(len<1024){
							imgurl.append(new String(chunk, "GBK").trim().substring(0, len));
						}else{
							imgurl.append(new String(chunk, "GBK").trim());
						}
					};	
					System.out.println(imgurl);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sock != null && sock.isConnected()) {
				try {
					oos.close();
					ois.close();
					sock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		String imgeList = String.valueOf(imgurl);
		List list = new ArrayList();
		String[] url = imgeList.split("\\,");
		if (url.length > 0) {
			for (int i = 0; i < url.length; i++) {
				list.add(url[i]);
			}
		}
		// 返回影像地址
		ctx.setData("imgurl", list);
	}
	
}
