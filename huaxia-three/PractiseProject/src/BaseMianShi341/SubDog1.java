/**
 * Title: SubDog1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��23������10:11:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:SubDog12020��9��23��
 * @comments:
 * @param:���า����д����ķ���
 * @return:
 * runResult���ҵ�ë�Ǻ�ɫ��
 * @author ��ΰ/liuwei
 * @createtime:2020��9��23������10:11:27
 */
public class SubDog1 extends Dog1 {

	/**
	 * Constructor
	 */
	public SubDog1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��23������10:11:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str=new SubDog1().color();
		System.out.println(str);
	}
	/**
	 * ��д���Ǹ���ķ���
	 */
	public String color(){
		return "�ҵ�ë�Ǻ�ɫ��";
	}
}
