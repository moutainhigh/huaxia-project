/**
 * Title: StaticInnerClassTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月31日下午2:55:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter6;

/**
 * @class_name:StaticInnerClassTest2019年12月31日
 * @comments: 内部类的最大值和最小值
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日下午2:55:58
 */
public class StaticInnerClassTest {

	/**
	 * 
	 */
	public StaticInnerClassTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午2:55:58
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] d = new double[20];
		for(int i=0;i<d.length;i++)
			d[i] = 100*Math.random();
		ArrayAlg.Pair  p = ArrayAlg.minmax(d);
		for(int i=0;i<d.length;i++)
			System.out.print(d[i]+"\t");
		System.out.println();
		System.out.println("min="+p.getFirst());
		System.out.println("max="+p.getSecond());
	}

}
class ArrayAlg
{
	/**
	 * 
	 * @class_name:Pair2019年12月31日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2019年12月31日下午2:59:08
	 */
	public static class Pair
	{
		private double first;
		private double second;
		public Pair(double f,double s)
		{
			first = f;
			second = s;
		}
		public double getFirst() {
			return first;
		}
		public double getSecond() {
			return second;
		}
		public void setFirst(double first) {
			this.first = first;
		}
		public void setSecond(double second) {
			this.second = second;
		}
	}
	/**
	 * @Title: minmax
	 *@Description: TODO
	 *@param values
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午2:59:24
	 */
	public static Pair minmax(double[] values)
	{
		double min= Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		for(double v:values)
		{
			if(min>v) min = v;
			if(max<v) max = v;
		}
		return new Pair(min,max);
	}
}


