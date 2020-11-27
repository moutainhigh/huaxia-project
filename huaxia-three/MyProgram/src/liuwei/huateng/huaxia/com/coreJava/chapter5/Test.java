/**
 * Title: Test.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月30日上午10:36:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

/**
 * @class_name:Test2019年12月30日
 * @comments: String 的hashcode是由内容导出的，StringBuilder的hashCode是由默认的hashCode导出的
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月30日上午10:36:42
 */
public class Test {

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月30日上午10:36:42
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "OK";
		StringBuilder sb = new StringBuilder(s);
		System.out.println(s.hashCode()+" "+sb.hashCode());
		String t = new String("OK");
		StringBuilder tb = new StringBuilder(t);
		System.out.println(t.hashCode()+" "+tb.hashCode());
		
	}

}
