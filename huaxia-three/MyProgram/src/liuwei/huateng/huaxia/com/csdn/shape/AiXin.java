/**
 * Title: AiXin.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��2������10:55:28
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.csdn.shape;

/**
 * @class_name:AiXin2020��1��2��
 * @comments: ��ӡ����
 * ���Ĺ�ʽ���й�ʽ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��2������10:55:28
 */
public class AiXin {

	/**
	 * 
	 */
	public AiXin() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: aiXin
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��1��2������10:58:58
	 */
	public static void aiXin(){
		for(float y = (float)1.5;y>-1.5;y-=0.1)
		{
			for(float x=(float)-1.5;x<1.5;x+=0.05)
			{
				float a = x*x + y*y -1;
				if((a*a*a -x*x*y*y*y)<=0.0){
					System.out.print("^");
				}
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��2������10:55:28
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		aiXin();
	}
}
