/**
 * Title: TestInteger.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��12������5:16:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestInteger2020��10��12��
 * @comments:
 * @param:�������ҵ�����
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��12������5:16:52
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
	 *@Date: 2020��10��12������5:16:52
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
