/**
 * Title: LiftBook.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月15日下午2:42:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @class_name:LiftBook2020年9月15日
 * @comments:
 * @param: 生死簿不能被继承，只能有一个。
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月15日下午2:42:21
 */
public final class LiftBook {
	//存放所有的生灵信息  生死簿
	private Map<Integer,Creature> creatures = new  HashMap<Integer,Creature>();
	/**
	 * Constructor
	 */
	public LiftBook() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午2:42:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Creature creature = new Creature(1000,"猴类","智多星",300,new Date(),"花果山");
		LiftBook lifeBook = new LiftBook();
		lifeBook.add(creature);
		creature = lifeBook.get(1000);
		System.out.println("智多星的寿命是"+creature.getLifetime());
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param creature 生死簿中添加生灵
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午2:44:47
	 */
	public void add(Creature creature){
		creatures.put(creature.getNo(), creature);
	}
	/**
	 * @Title: get
	 *@Description: TODO
	 *@param no 获取生灵的信息
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午2:45:26
	 */
	public Creature get(int no){
		return creatures.get(no);
	}
}
