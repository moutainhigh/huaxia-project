/**
 * Title: BuilderString.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������5:29:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:BuilderString2020��9��18��
 * @comments: StringBuilder����̲߳���ȫ��ʹ��StringBufferҲ�죬�̰߳�ȫ
 * String׷���ַ�3�����
��ʱ��363.4337����
StringBuilder׷���ַ�3�����
��ʱ��0.9322����
StringBuffer׷���ַ�3�����
��ʱ��1.7824��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������5:29:13
 */
public class BuilderString {
	
	/**
	 * Constructor
	 */
	public BuilderString() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��18������5:29:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String appendStr = "";
		long startTime = System.nanoTime();
		for(int i= 20000;i<50000;i++){
			appendStr += (char)i;
		}
		long endTime =  System.nanoTime();
		System.out.println("String׷���ַ�3�����");
		System.out.println("��ʱ��"+(endTime-startTime)/1000000d+"����");
		StringBuilder strBUilder = new StringBuilder();
		startTime = System.nanoTime();
		for(int i= 20000;i<50000;i++){
			strBUilder.append((char)i);
		}
		endTime = System.nanoTime();
		System.out.println("StringBuilder׷���ַ�3�����");
		System.out.println("��ʱ��"+(endTime-startTime)/1000000d+"����");
		StringBuffer strBuffer = new StringBuffer();
		startTime = System.nanoTime();
		for(int i= 20000;i<50000;i++){
			strBuffer.append((char)i);
		}
		endTime = System.nanoTime();
		System.out.println("StringBuffer׷���ַ�3�����");
		System.out.println("��ʱ��"+(endTime-startTime)/1000000d+"����");
	}
}
