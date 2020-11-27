/**
 * Title: TestStringBuilder.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��25������4:33:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

/**
 * @class_name:TestStringBuilder2019��12��25��
 * @comments:
ʹ��+++�����ַ�����ʱ15
ʹ��StringBuilder�����ַ�����ʱ1
���Կ���ʹ��StringBuilder���Դ�����Ч��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��25������4:33:22
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
	 *@Date: 2019��12��25������4:33:22
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
		System.out.println("ʹ��+++�����ַ�����ʱ"+(end-start));
		StringBuilder builder = new StringBuilder();
		start = System.currentTimeMillis();
		for(int i=0;i<1000;i++){
			builder.append("Hello World! ");
		}
		end = System.currentTimeMillis();
		System.out.println("ʹ��StringBuilder�����ַ�����ʱ"+(end-start));
	}

}
