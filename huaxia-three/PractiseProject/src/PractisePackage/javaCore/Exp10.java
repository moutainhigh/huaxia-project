/**
 * Title: Exp10.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��21������3:50:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;

import java.io.IOException;

/**
 * @class_name:Exp102020��8��21��
 * @comments:�����쳣��ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��21������3:50:13
 */
public class Exp10 {

	/**
	 * Constructor
	 */
	public Exp10() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��21������3:50:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			System.out.println("...�������г���....."	);
			throw new IOException("�û����в������쳣");
		}catch(IOException e){
			System.out.println("�Ѳ����˸��쳣��");
			e.printStackTrace();
		}
	}

}
