package PractisePackage;
/**
 * @class_name:StaticOrder2020��8��6��
 * @comments:��ʼ��˳��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������10:21:56
 */
public class StaticOrder {
	public static int X = 20;
	public static int Y= 2*X;
	static {
		X = 30;
	}
	public StaticOrder() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Y);
	}

}
