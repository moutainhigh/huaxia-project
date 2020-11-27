/**
 * Title: CLInitDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月6日下午4:12:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

/**
 * @class_name:CLInitDemo2020年8月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午4:12:19
 */
public class CLInitDemo {
	public static class Hello{
		static{
			System.out.println("hello");
		}
	}
	/**
	 * Constructor
	 */
	public CLInitDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午4:12:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassLoader c1 = ClassLoader.getSystemClassLoader();
		String className = CLInitDemo.class.getName()+"$Hello";
		try{
//			Class<?> cls= c1.loadClass(className);
			Class<?> cls= Class.forName(className);

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

}
