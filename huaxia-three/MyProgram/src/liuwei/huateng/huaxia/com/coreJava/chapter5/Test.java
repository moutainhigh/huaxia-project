/**
 * Title: Test.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��30������10:36:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

/**
 * @class_name:Test2019��12��30��
 * @comments: String ��hashcode�������ݵ����ģ�StringBuilder��hashCode����Ĭ�ϵ�hashCode������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��30������10:36:42
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
	 *@Date: 2019��12��30������10:36:42
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
