/**
 * Title: Client.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������1:56:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.IneratorPattern;

/**
 * @class_name:Client2020��1��7��
 * @comments: ������ģʽ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������1:56:38
 */
public class Client {

	/**
	 * 
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��7������1:56:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Aggregate agg = new ConcreteAggregate();
		agg.add("����");
		agg.add("����");
		agg.add("����");
		//����
		Iterator iterator = agg.createIterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
