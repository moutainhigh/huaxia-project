/**
 * Title: ClientDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������4:20:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.DecoratorPattern;

/**
 * @class_name:ClientDemo2020��1��6��
 * @comments:װ��ģʽ�ʹ���ģʽ������ģʽ�ǽӿڣ�װ��ģʽ����
 * ����Щģʽ���൱����д�͸��ǣ���д����Ȼ������һЩ��а�������һ��ģʽ����ʵ���Ǽ򵥵�һ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������4:20:13
 */
public class ClientDemo {

	/**
	 * 
	 */
	public ClientDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��6������4:20:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car car = new Benz();
		car.show();
		//�Ա��۳�����װ��
		CarDecorator cd = new ConcreteCarDecorator(car);
		cd.show();
	}

}
