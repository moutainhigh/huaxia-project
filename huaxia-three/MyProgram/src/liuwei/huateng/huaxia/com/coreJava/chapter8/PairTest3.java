/**
 * Title: PairTest3.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月31日下午4:39:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter8;

/**
 * @class_name:PairTest32019年12月31日
 * @comments: 泛型的通配符
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日下午4:39:37
 */
public class PairTest3 {

	/**
	 * 
	 */
	public PairTest3() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:39:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager ceo = new Manager("Gus Greedy",80000,2003,12,15);
		Manager cfo = new Manager("Sid Sneaky",60000,2003,12,15);
		Pair<Manager> buddies = new Pair<>(ceo,cfo);
		printBuddies(buddies);
		ceo.setBonus(1000000);
		cfo.setBonus(500000);
		printBuddies(buddies);
		Manager[] managers = {ceo,cfo};
		Pair<Employee> result = new Pair<>();
		minmaxBouns(managers,result);
		System.out.println("first:"+result.getFirst().getName()+",second:"+result.getSecond().getName());
		maxminBonus(managers,result);
		System.out.println("first:"+result.getFirst().getName()+",second:"+result.getSecond().getName());
	}
	/**
	 * @Title: printBuddies
	 *@Description: TODO
	 *@param p
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:42:41
	 */
	public static void printBuddies(Pair<? extends Employee> p)
	{
			Employee first = p.getFirst();
			Employee second = p.getSecond();
			System.out.println(first.getName()+" and "+second.getName()+" are buddiest.");
			System.out.println(first.toString()+" and "+second.toString()+" are buddiest.");

	}
	/**
	 * @Title: minmaxBouns
	 *@Description: TODO
	 *@param a
	 *@param result
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:49:51
	 */
	public static void minmaxBouns(Manager[] a,Pair<? super Manager> result)
	{
		if(a.length == 0 )return ;
		Manager min = a[0];
		Manager max = a[0];
		for(int i=0;i<a.length;i++)
		{
			if(min.getBonus() > a[i].getBonus()) 
				min = a[i];
			if(max.getBonus() < a[i].getBonus())
				max = a[i];
			result.setFirst(min);
			result.setSecond(max);
		}
	}
	/**
	 * @Title: maxminBonus
	 *@Description: TODO
	 *@param a
	 *@param result
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:51:02
	 */
	public static void maxminBonus(Manager[] a,Pair<? super Manager> result)
	{
		minmaxBouns(a,result);
		PairAlg.swapHelper(result);
	}
}
class PairAlg
{
	/**
	 * @Title: hasNulls
	 *@Description: TODO
	 *@param p
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:49:40
	 */
	public static boolean hasNulls(Pair<?> p)
	{
		return p.getFirst()  == null || p.getSecond() == null;
	}
	/**
	 * @Title: swap
	 *@Description: TODO
	 *@param p
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:49:37
	 */
	public static void swap(Pair<?> p) {
		swapHelper(p);
	}
	/**
	 * @Title: swapHelper
	 *@Description: TODO
	 *@param p
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:49:32
	 */
	public static <T> void swapHelper(Pair<T> p)
	{
		T t = p.getFirst();
		p.setFirst(p.getSecond());
		p.setSecond(t);
	}
}