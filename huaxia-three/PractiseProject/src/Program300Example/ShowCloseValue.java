/**
 * Title: ShowCloseValue.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������10:43:34
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:ShowCloseValue2020��9��18��
 * @comments:�·����ͺŴ�С
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������10:43:34
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
	 *@Date: 2020��9��18������10:43:34
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String type;
		Size size;
		boolean goon = true;
		System.out.println("�������·��ͺ�,����SMALL��MEDIUM��LARGE,EXTRA_LARGE");
		System.out.println("���б�ʾ����");
		while(goon){
			System.out.print("�������ͺţ�");
			type = in.nextLine().toUpperCase();
			if(type.length()>0){
				size = (Size)Enum.valueOf(Size.class, type);
				System.out.println(type+"�۸�Ϊ��"+size.getValue());
			}else{
				goon = false;
			}
		}
		in.close();
	}

}
