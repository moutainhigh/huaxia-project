/**
 * Title: TestAdapter.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日下午3:03:10
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestAdapter2020年9月29日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日下午3:03:10
 */
public class TestAdapter {

	/**
	 * Constructor
	 */
	public TestAdapter() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月29日下午3:03:10
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sourceable source1 = new SourceSub1();
		Sourceable source2 = new SourceSub2();
		source1.editTextFile();
		source2.editWordFile();
	}

}
