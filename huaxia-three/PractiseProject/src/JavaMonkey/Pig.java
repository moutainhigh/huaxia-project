/**
 * Title: Pg.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��14������9:30:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Pg2020��9��14��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��14������9:30:21
 */
public class Pig {
	String name;
	/**
	 * Constructor
	 */
	public Pig(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��14������9:30:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pig pig = new Pig("��˽�");
		pig.eat(); 
	}
	/**
	 * @Title: eat
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��14������9:33:35
	 */
	public void eat(){
		System.out.println("���ױ��������ߣ�����������������ϸ���������ĳ�����л����������");
	}
}
