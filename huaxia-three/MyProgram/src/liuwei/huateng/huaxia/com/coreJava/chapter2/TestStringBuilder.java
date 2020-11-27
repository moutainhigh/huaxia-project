/**
 * Title: TestStringBuilder.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月25日下午4:33:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

/**
 * @class_name:TestStringBuilder2019年12月25日
 * @comments:
使用+++连接字符串耗时15
使用StringBuilder连接字符串耗时1
可以看出使用StringBuilder可以大大提高效率
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月25日下午4:33:22
 */
public class TestStringBuilder {

	/**
	 * 
	 */
	public TestStringBuilder() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月25日下午4:33:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str=null;
		long start = System.currentTimeMillis();
		for(int i=0;i<1000;i++){
			str+="Hello World! ";
		}
		System.out.println(str);
		long end = System.currentTimeMillis();
		System.out.println("使用+++连接字符串耗时"+(end-start));
		StringBuilder builder = new StringBuilder();
		start = System.currentTimeMillis();
		for(int i=0;i<1000;i++){
			builder.append("Hello World! ");
		}
		end = System.currentTimeMillis();
		System.out.println("使用StringBuilder连接字符串耗时"+(end-start));
	}

}
