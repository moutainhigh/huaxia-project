/**
 * Title: StudioTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月17日上午10:23:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:StudioTest2020年8月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月17日上午10:23:22
 */
public class StudioTest {

	/**
	 * Constructor
	 */
	public StudioTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月17日上午10:23:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=0;
		for(int h =0;h<24;h++)
		{
			for(int m = 0;m<60;m++)
			{
				if(m == (int)((float)m/12.0+(h%12*5))){
					System.out.println(h+"点"+m+"分"+m+"秒  时分秒三针重合 ");
				}
			}
		}
	}

}
