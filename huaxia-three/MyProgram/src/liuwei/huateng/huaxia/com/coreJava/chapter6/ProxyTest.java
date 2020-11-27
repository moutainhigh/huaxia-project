/**
 * Title: ProxyTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月31日下午3:24:45
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * @class_name:ProxyTest2019年12月31日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日下午3:24:45
 */
public class ProxyTest {

	/**
	 * 
	 */
	public ProxyTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午3:24:45
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object[] elements = new Object[1000];
		//fill elements with proxies for the integers 1....10000
		for(int i=0;i<elements.length;i++)
		{
			Integer value = i+1;
			InvocationHandler handler = new TraceHandler(value);
			Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
			elements[i] = proxy;
		}
		//construct a random integer
		Integer key = new Random().nextInt(elements.length)+1;
		int result = Arrays.binarySearch(elements , key);
		//print match if found
		if(result >=0) 
			System.out.println(elements[result]);
	}
}

class TraceHandler implements InvocationHandler
{
	private Object target;
	
	public TraceHandler(Object t)
	{
		this.target = t;
	}
	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		//print implicit argument
		System.out.print(target);
		//print metod  name
		System.out.print("."+m.getName()+"(");
		//printexplicit arguments
		if(args != null)
		{
			for(int i=0;i<args.length;i++)
			{
				System.out.print(args[i]);
				if(i<args.length-1) System.out.print(",");
			}	
		}
		System.out.println(")");
		//invoke actual method
		return m.invoke(target, args);
	}
	
}

