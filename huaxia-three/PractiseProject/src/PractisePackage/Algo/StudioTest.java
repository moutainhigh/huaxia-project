/**
 * Title: StudioTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��17������10:23:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:StudioTest2020��8��17��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��17������10:23:22
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
	 *@Date: 2020��8��17������10:23:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=0;
		for(int h =0;h<24;h++)
		{
			for(int m = 0;m<60;m++)
			{
				if(m == (int)((float)m/12.0+(h%12*5))){
					System.out.println(h+"��"+m+"��"+m+"��  ʱ���������غ� ");
				}
			}
		}
	}

}
