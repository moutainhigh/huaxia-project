/**
 * Title: ComputerTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������11:20:34
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:ComputerTest2020��1��6��
 * @comments:������ģʽ��23�����ģʽ֮һ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������11:20:34
 */
public class ComputerTest {

	/**
	 * 
	 */
	public ComputerTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��6������11:20:34
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComputerDirector director = new ComputerDirector();
		Computer t410 = director.constructT410();
		System.out.println(t410);
		System.out.println("------------------------------------");
		Computer x201= director.constructX201();
		System.out.println(x201);
	}

}
