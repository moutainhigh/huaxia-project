/**
 * Title: FoodAdapter.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������4:30:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.AdapterPattern;

/**
 * @class_name:FoodAdapter2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������4:30:54
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
		System.out.println("�����ˮ��һ����������ݵ�ʳƷ");
	}

}
