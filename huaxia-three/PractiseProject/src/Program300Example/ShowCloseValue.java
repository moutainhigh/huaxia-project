/**
 * Title: ShowCloseValue.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日上午10:43:34
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:ShowCloseValue2020年9月18日
 * @comments:衣服的型号大小
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日上午10:43:34
 */
public class ShowCloseValue {

	/**
	 * Constructor
	 */
	public ShowCloseValue() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日上午10:43:34
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String type;
		Size size;
		boolean goon = true;
		System.out.println("请输入衣服型号,包括SMALL，MEDIUM，LARGE,EXTRA_LARGE");
		System.out.println("空行表示结束");
		while(goon){
			System.out.print("请输入型号：");
			type = in.nextLine().toUpperCase();
			if(type.length()>0){
				size = (Size)Enum.valueOf(Size.class, type);
				System.out.println(type+"价格为："+size.getValue());
			}else{
				goon = false;
			}
		}
		in.close();
	}

}
