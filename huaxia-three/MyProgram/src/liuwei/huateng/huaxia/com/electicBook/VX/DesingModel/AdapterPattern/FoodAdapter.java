/**
 * Title: FoodAdapter.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午4:30:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.AdapterPattern;

/**
 * @class_name:FoodAdapter2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午4:30:54
 */
public class FoodAdapter extends ShuiJiao implements Hundun {

	/**
	 * 
	 */
	public FoodAdapter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.AdapterPattern.Hundun#makeHundun()
	 */
	@Override
	public void makeHundun() {
		// TODO Auto-generated method stub
		super.makeShujiao();
		System.out.println("混沌和水饺一样是以面包陷的食品");
	}

}
