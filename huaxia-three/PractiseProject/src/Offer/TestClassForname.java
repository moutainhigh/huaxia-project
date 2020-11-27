/**
 * Title: TestClassForname.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日下午3:06:56
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @class_name:TestClassForname2020年9月23日
 * @comments: Class.froname方法的使用
 * @param:
 * @return: 通过反射动态生辰类并且调用其方法
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日下午3:06:56
 */
public class TestClassForname {

	/**
	 * Constructor
	 */
	public TestClassForname() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日下午3:06:56
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class clazz  = Class.forName("Offer.Persion");
			Method[] method = clazz.getDeclaredMethods();
			for(Method m :method){
				System.out.println(m.toString());
			}
			Field[] field = clazz.getDeclaredFields();
			for(Field f:field){
				System.out.println(f.toString());
			}
			Constructor[] constructor = clazz.getDeclaredConstructors();
			for(Constructor f:constructor){
				System.out.println(f.toString());
			}
			try {
				Persion p = (Persion) clazz.newInstance();
				System.out.println(p.toString());
				Constructor c;
				try {
					c = clazz.getDeclaredConstructor(String.class,String.class);
					try {
						Persion p2 = (Persion)c.newInstance("李四","20");
						System.out.println(p2.toString());
						Method method2 = clazz.getMethod("setName", String.class);
						method2.invoke(p, "alex");
						System.out.println(p.toString());
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
