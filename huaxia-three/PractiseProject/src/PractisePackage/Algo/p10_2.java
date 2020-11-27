/**
 * Title: p10_2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月17日上午10:51:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:p10_22020年8月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月17日上午10:51:48
 */
public class p10_2 {

	/**
	 * Constructor
	 */
	public p10_2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月17日上午10:51:48
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] len1 = {0};
		int[] len2 = {0};
		int[] len3 = {0};
		int[] len4 = {0};
		int[] len5 = {0};
		int[] len ={0};
		 WJGJ(len1,len2, len3, len4, len5,len);
		 System.out.printf("五家共井问题求解结果如下：\n");
		 System.out.printf("甲家井绳长度为：%d\n", len1[0]);
		 System.out.printf("乙家井绳长度为：%d\n", len2[0]);
		 System.out.printf("丙家井绳长度为：%d\n", len3[0]);
		 System.out.printf("丁家井绳长度为：%d\n", len4[0]);
		 System.out.printf("戌家井绳长度为：%d\n", len5[0]);
		 System.out.printf("井深为：%d\n", len[0]);
		 
	}
	/**
	 * @Title: WJGJ
	 *@Description: TODO
	 *@param len1
	 *@param len2
	 *@param len3
	 *@param len4
	 *@param len5
	 *@param len
	 *@author: LiuWei
	 *@Date: 2020年8月17日上午10:55:32
	 */
	static void WJGJ(int[] len1,int[] len2,int[] len3,int[] len4,int[] len5,int[] len)
	{
		for(len5[0] = 4;;len5[0]+=4)
		{
			for(len1[0] = 5;;len1[0]+= 5)
			{
				len4[0] = len5[0] + len1[0]/5;
				len3[0] = len4[0] + len5[0]/4;
				if(len3[0] %2 != 0 || len4[0] %3 !=0){
					continue;
				}
				len2[0] = len3[0] +len4[0]/3;
				if(len2[0] + len3[0]/2<len1[0])
					break;
				if(len2[0]+len3[0]/2 == len1[0])
				{
					len[0] = 2*(len1[0])+(len2[0]);
					return;
				}
			}
		}
	}
}
