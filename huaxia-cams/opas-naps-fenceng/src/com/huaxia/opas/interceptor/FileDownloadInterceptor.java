package com.huaxia.opas.interceptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huateng.neofp.IcsException;
import com.huateng.neofp.IcsRuntimeException;
import com.huateng.neofp.channel.ChannelContext;
import com.huateng.neofp.channel.http.interceptors.ServletChannelInterceptor;
import com.huateng.neofp.core.ContextEx;
import com.huateng.neofp.support.TRACER;

public class FileDownloadInterceptor implements ServletChannelInterceptor {
	private static String FILE_DOWNLOADNAME = "FILE_DOWNLOAD_NAME";
	private String fileDownloadPath;

	public void setFileDownloadPath(String fileDownloadPath) {
		this.fileDownloadPath = fileDownloadPath;
	}

	public void onRequest(
			ChannelContext<HttpServletRequest, HttpServletResponse> chCtx,
			ContextEx ctx) throws IcsException {
	}

	@SuppressWarnings("static-access")
	public void onResponse(
			ChannelContext<HttpServletRequest, HttpServletResponse> chCtx,
			ContextEx ctx, Throwable error) {
		String processId = (String) ctx.getProcessId();
		if ("downloadFile".equals(processId)) {
			String downloadfileName = (String) ctx
					.getData(this.FILE_DOWNLOADNAME);
			try
		      {
		        byte[] dfn = downloadfileName.getBytes("ISO-8859-1");
		        downloadfileName = new String(dfn, "UTF-8");
		      } catch (UnsupportedEncodingException e1) {
		        throw new IcsRuntimeException("ICSHP8005", new String[] { downloadfileName }, "fileName Encoding error");
		      }
			
			File file = new File(fileDownloadPath+downloadfileName);
			if ((file.exists()) && (file.canRead()) && (file.isFile())) {
				FileInputStream stream = null;
				String fileName = file.getName();
				HttpServletResponse response = (HttpServletResponse) chCtx
						.getResponse();
				try {
					response.setContentType("application/x-download;charset=UTF-8");

					response.setHeader("Content-Disposition","attachment; filename="+ new String(fileName.getBytes("GBK"),"iso8859-1"));

					stream = new FileInputStream(file);
					byte[] bytes = new byte[(int) file.length()];
					stream.read(bytes);
					OutputStream out = response.getOutputStream();
					out.write(bytes);
					out.flush();

					ctx.setData("X-processType", "X-DownLoad");
				} catch (UnsupportedEncodingException e) {
					throw new IcsRuntimeException("ICSHP8005",
							new String[] { fileName },
							"fileName Encoding error");
				} catch (FileNotFoundException e) {
					TRACER.trace(
							"file not found error",
							new String[] { "check download file : " + fileName });
					throw new IcsRuntimeException("ICSHP8006",
							new String[] { fileName }, "file not found error");
				} catch (IOException localIOException1) {
					if (stream != null)
						try {
							stream.close();
						} catch (IOException e) {
							TRACER.trace("close file error",
									new String[] { e.getMessage() });
						}
				} finally {
					if (stream != null)
						try {
							stream.close();
						} catch (IOException e) {
							TRACER.trace("close file error",
									new String[] { e.getMessage() });
						}
				}
			}
		}
	}
}