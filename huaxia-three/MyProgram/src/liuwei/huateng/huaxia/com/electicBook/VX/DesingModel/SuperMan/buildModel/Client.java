/**
 * Title: Client.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������5:18:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel;

/**
 * @class_name:Client2020��1��8��
 * @comments:������ģʽ��������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������5:18:22
 */
public class Client {

	/**
	 * 
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��8������5:18:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Director director =new Director();
		SuperMan adultSuperMan = director.buildSuperMan("adult");
		System.out.println("���곬�ˣ�"+adultSuperMan);
		SuperMan childSuperMan = director.buildSuperMan("child");
		System.out.println("δ���곬�ˣ�"+childSuperMan);
	}

}
