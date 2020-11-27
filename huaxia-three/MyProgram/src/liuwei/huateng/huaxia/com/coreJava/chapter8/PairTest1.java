/**
 * Title: PairTest1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月31日下午4:22:35
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter8;

/**
 * @class_name:PairTest12019年12月31日
 * @comments: 测试泛型
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日下午4:22:35
 */
public class PairTest1 {
	
	/**
	 * 
	 */
	public PairTest1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:22:35
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String[] words = {"Marry","had","a","little","lamb"};
		Pair<String> mm = ArrayAlg.minmax(words);
		for(int i=0;i<words.length;i++)
			System.out.print(words[i]+" ");
		System.out.println();
		System.out.println("min="+mm.getFirst());
		System.out.println("max="+mm.getSecond());
	}
}
/**
 * 
 * @class_name:ArrayAlg2019年12月31日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日下午4:26:13
 */
class ArrayAlg
{
	/**
	 * @Title: minmax
	 *@Description: TODO
	 *@param a
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年12月31日下午4:26:17
	 */
	public static Pair<String> minmax(String[] a){
		if(a == null || a.length == 0)
			return null;
		String min = a[0];
		String max = a[0];
		for(int i=1;i<a.length;i++)
		{
			if(min.compareTo(a[i])>0)
				min = a[i];
			if(max.compareTo(a[i])<0)
				max = a[i];
		}
		return new Pair<>(min,max);
	}
}

