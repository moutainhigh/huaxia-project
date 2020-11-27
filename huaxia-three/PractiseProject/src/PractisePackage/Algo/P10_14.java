/**
 * Title: P10_14.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月13日上午10:03:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P10_142020年8月13日
 * @comments:爱因斯坦阶梯问题
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月13日上午10:03:13
 */
public class P10_14 {
	/**
	 * @Title: jieti
	 *@Description: TODO 测试阶数
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日上午10:07:10
	 */
	static int jieti(){
		int i,res;
		int count = 7;
		for(i = 1;i<100;i++){
			if((count%2 == 1)&&(count%3 == 2) && (count % 5 == 4) && (count %6 == 5)){
				res = count;
				break;
			}
			count = 7*(i+1);
		}
		return count;
	}
	/**
	 * Constructor
	 */
	public P10_14() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月13日上午10:03:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num;
		System.out.println("爱因斯坦阶梯问题求解");
		num = jieti();
		System.out.printf("这个阶梯总共有%d个台阶\n",num);
	}

}
