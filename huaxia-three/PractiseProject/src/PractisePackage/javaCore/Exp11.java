/**
 * Title: Exp11.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��21������3:55:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;
/**
 * 
 * @class_name:Exp2020��8��21��
 * @comments: �����Զ����쳣���ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��21������3:57:03
 */
class Exp extends Exception{
	private int show;
	Exp(int a){
		show = a;
	}
	public String toString(){
		return "Exp<"+show+">";
	}
}
/**
 * @class_name:Exp112020��8��21��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��21������3:55:09
 */
public class Exp11 {

	/**
	 * Constructor
	 */
	public Exp11() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��21������3:55:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			caculate(1);
			caculate(1000);
		}catch(Exp e){
			System.out.println("�������쳣:"+e);
		}
	}
	/**
	 * @Title: caculate
	 *@Description: TODO�����Զ����쳣��
	 *@param a
	 *@throws Exp
	 *@author: LiuWei
	 *@Date: 2020��8��21������3:59:57
	 */
	static void caculate(int a) throws Exp{
		System.out.println("��["+a+"]�Ѿ����й���Ӧ�Ĳ���");
		if(a>100)
			throw new Exp(a);
		System.out.println("ִ�и��㷨�����˳�");
	}
}
