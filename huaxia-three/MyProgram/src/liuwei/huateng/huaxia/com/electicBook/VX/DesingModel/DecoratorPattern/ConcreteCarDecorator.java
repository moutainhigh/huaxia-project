/**
 * Title: ConcreteCarDecorator.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午4:17:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.DecoratorPattern;

/**
 * @class_name:ConcreteCarDecorator2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午4:17:12
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
	 *@Date: 2020年1月6日下午4:18:11
	 */
	private void print(){
		System.out.println("在车尾绘制‘新手’字样，颜色是紫色霞光");
	}
	/**
	 * @Title: setGps
	 *@Description: TODO安装GPS定位导航系统
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午4:18:50
	 */
	private void setGps(){
		System.out.println("安装GPS定位导航系统");
	}
	/**
	 * 重写show方法
	 */
	@Override
	public void show(){
		super.show();
		this.print();
		this.setGps();
	}
}
