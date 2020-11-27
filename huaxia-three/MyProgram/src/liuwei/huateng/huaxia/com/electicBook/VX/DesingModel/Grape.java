/**
 * Title: Grape.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月3日下午4:49:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

/**
 * @class_name:Grape2020年1月3日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月3日下午4:49:55
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
		System.out.println("葡萄正在生长...");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Fruit#harvest()
	 */
	@Override
	public void harvest() {
		// TODO Auto-generated method stub
		System.out.println("收获葡萄");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Fruit#plant()
	 */
	@Override
	public void plant() {
		// TODO Auto-generated method stub
		System.out.println("种植葡萄");
	}

	public boolean isSeedless() {
		return seedless;
	}

	public void setSeedless(boolean seedless) {
		this.seedless = seedless;
	}

}
