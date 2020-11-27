/**
 * Title: Circle.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月2日上午11:04:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.csdn.shape;

/**
 * @class_name:Circle2020年1月2日
 * @comments:输出圆形
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月2日上午11:04:13
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
	 *@Date: 2020年1月2日上午11:04:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		circle();
	}
	/**
	 * @Title: circle
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年1月2日上午11:09:07
	 */
	public static void circle()
	{
		int r=15;//圆形的半径
		for(int y = 0;y<=2*r;y+=2)//y的步长是2，改变y的步长可以将圆形变成椭圆
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
