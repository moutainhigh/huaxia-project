/**
 * Title: C1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��23������10:21:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:C12020��9��23��
 * @comments: ���������أ���д�������͸��ǲ�ͬ
 * runResult:public Man��String n)
���ǣ����������꣺0��
public Man��int a)
���ǣ�null�����꣺12��
public Man��String n,int a)
���ǣ����ģ����꣺56��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��23������10:21:12
 */
public class C1 {

	/**
	 * Constructor
	 */
	public C1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��23������10:21:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Man2 man = new Man2("����");
		System.out.println(man.talk());
		System.out.println(new Man2(12).talk());
		System.out.println(new Man2("����",56).talk());
	}
}
class Man2{
	private String name;
	private int age;
	/**
	 * 
	 * @param n
	 * @param aConstructor
	 */
	public Man2(String n,int a){
		name = n;
		age = a;
		System.out.println("public Man��String n,int a)");
	}
	/**
	 * 
	 * @param nConstructor
	 */
	public Man2(String n){
		name = n;
		System.out.println("public Man��String n)");
	}
	/**
	 * 
	 * @param aConstructor
	 */
	public Man2(int a){
		age = a;
		System.out.println("public Man��int a)");
	}
	/**
	 * @Title: talk
	 *@Description: TODO
	 *@return ˵������
	 *@author: LiuWei
	 *@Date: 2020��9��23������10:25:23
	 */
	public String talk(){
		return "���ǣ�"+name+"�����꣺"+age+"��";
	}
}
