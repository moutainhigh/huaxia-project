/**
 * Title: TestInteger.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午5:16:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestInteger2020年10月12日
 * @comments:
 * @param:测试自我迭代器
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午5:16:52
 */
public class TestInteger {
	
	/**
	 * Constructor
	 */
	public TestInteger() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午5:16:52
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Collection collection = new ListCollection();
		for(int i=0;i<10;i++)
		collection.add("object"+i);
		Iterator it = collection.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

}
