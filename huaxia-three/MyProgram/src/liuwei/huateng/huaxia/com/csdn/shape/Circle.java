/**
 * Title: Circle.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��2������11:04:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.csdn.shape;

/**
 * @class_name:Circle2020��1��2��
 * @comments:���Բ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��2������11:04:13
 */
public class Circle {

	/**
	 * 
	 */
	public Circle() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��2������11:04:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		circle();
	}
	/**
	 * @Title: circle
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��1��2������11:09:07
	 */
	public static void circle()
	{
		int r=15;//Բ�εİ뾶
		for(int y = 0;y<=2*r;y+=2)//y�Ĳ�����2���ı�y�Ĳ������Խ�Բ�α����Բ
		{
			int x = (int)Math.round(r-Math.sqrt(2*r*y-y*y));
			int len = 2*(r-x);
			for(int i=0;i<=x;i++)
			{
				System.out.print(" ");
			}
			System.out.print("*");
			for(int j=0;j<=len;j++){
//				System.out.print(" ");
				System.out.print("*");
			}
			System.out.println('*');
		}
	}
}
