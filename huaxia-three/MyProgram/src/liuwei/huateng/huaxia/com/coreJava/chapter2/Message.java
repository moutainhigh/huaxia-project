/**
 * Title: Message.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��26������11:03:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

/**
 * @class_name:Message2019��12��26��
 * @comments: ���������е�main��������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��26������11:03:29
 */
public class Message {

	/**
	 * 
	 */
	public Message() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��26������11:03:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 0 || args[0].equals("-h"))
			System.out.print("Hello,");
		else if(args[0].equals("-g"))
			System.out.print("Goodbye,");
		for(int i=1;i<args.length;i++)
			System.out.print(" "+args[i]);
		System.out.println("!");
	}

}
