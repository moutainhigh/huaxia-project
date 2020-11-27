package SimpleBook;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
/**
 *  查找系统代理设置
 * @author liuwei
 *
 */
public class NewClass {
	public static void main(String args[]){
		try{
			System.setProperty("java.net.useSystemProxies","true");
			List<Proxy> l= ProxySelector.getDefault().select(new URI("http://www.google.com/"));
			for(Iterator<Proxy> iter = l.iterator();iter.hasNext();){
				Proxy proxy = iter.next();
				System.out.println("proxy hostname:"+proxy.type());
				InetSocketAddress add = (InetSocketAddress)proxy.address();
			if(add == null){
				System.out.println("No prosy");
			}else{
				System.out.println("proxy hostname :" +add.getHostName());
				System.out.println("proxy port:"+add.getPort());
			}
			
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
