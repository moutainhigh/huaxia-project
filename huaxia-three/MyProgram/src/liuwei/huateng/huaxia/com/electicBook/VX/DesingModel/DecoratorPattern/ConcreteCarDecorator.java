/**
 * Title: ConcreteCarDecorator.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������4:17:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.DecoratorPattern;

/**
 * @class_name:ConcreteCarDecorator2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������4:17:12
 */
public class ConcreteCarDecorator extends CarDecorator {
	
	/**
	 * @param car
	 */
	public ConcreteCarDecorator(Car car) {
		super(car);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: print
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��1��6������4:18:11
	 */
	private void print(){
		System.out.println("�ڳ�β���ơ����֡���������ɫ����ɫϼ��");
	}
	/**
	 * @Title: setGps
	 *@Description: TODO��װGPS��λ����ϵͳ
	 *@author: LiuWei
	 *@Date: 2020��1��6������4:18:50
	 */
	private void setGps(){
		System.out.println("��װGPS��λ����ϵͳ");
	}
	/**
	 * ��дshow����
	 */
	@Override
	public void show(){
		super.show();
		this.print();
		this.setGps();
	}
}
