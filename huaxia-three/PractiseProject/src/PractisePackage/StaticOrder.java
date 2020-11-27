package PractisePackage;
/**
 * @class_name:StaticOrder2020年8月6日
 * @comments:初始化顺序
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日上午10:21:56
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
