/**
 * Title: Client.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日下午1:56:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.IneratorPattern;

/**
 * @class_name:Client2020年1月7日
 * @comments: 迭代器模式
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日下午1:56:38
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
	 *@Date: 2020年1月7日下午1:56:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Aggregate agg = new ConcreteAggregate();
		agg.add("张三");
		agg.add("李四");
		agg.add("王五");
		//遍历
		Iterator iterator = agg.createIterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
