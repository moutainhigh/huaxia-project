/**
 * Title: Grape.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��3������4:49:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

/**
 * @class_name:Grape2020��1��3��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��3������4:49:55
 */
public class Grape implements Fruit {
	private boolean seedless;
	/**
	 * 
	 */
	public Grape() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Fruit#grow()
	 */
	@Override
	public void grow() {
		// TODO Auto-generated method stub
		System.out.println("������������...");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Fruit#harvest()
	 */
	@Override
	public void harvest() {
		// TODO Auto-generated method stub
		System.out.println("�ջ�����");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Fruit#plant()
	 */
	@Override
	public void plant() {
		// TODO Auto-generated method stub
		System.out.println("��ֲ����");
	}

	public boolean isSeedless() {
		return seedless;
	}

	public void setSeedless(boolean seedless) {
		this.seedless = seedless;
	}

}
