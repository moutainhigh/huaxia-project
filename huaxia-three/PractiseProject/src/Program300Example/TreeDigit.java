/**
 * Title: TreeDigit.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日下午3:56:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:TreeDigit2020年9月18日
 * @comments:1 2 3 4 能组成多少不同的三位数字
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日下午3:56:50
 */
public class TreeDigit {

	/**
	 * Constructor
	 */
	public TreeDigit() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日下午3:56:50
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,2,3,4};
		System.out.println("满足条件的三位数是：");
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a.length;j++){
				if(j == i)
					continue;
				for(int k= 0;k<a.length;k++){
					if(k == i || k==j)
						continue;
					for(int h=0;h<a.length;h++)
					{
						if(h == i || h== j|| h == k)
							continue;
						System.out.print(a[j]*100+a[k]*10+a[h]+" ");
					}
				}
			}
		}
	}
}
