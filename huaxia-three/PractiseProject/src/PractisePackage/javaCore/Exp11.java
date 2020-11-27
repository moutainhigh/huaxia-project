/**
 * Title: Exp11.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月21日下午3:55:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;
/**
 * 
 * @class_name:Exp2020年8月21日
 * @comments: 测试自定义异常类的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月21日下午3:57:03
 */
class Exp extends Exception{
	private int show;
	Exp(int a){
		show = a;
	}
	public String toString(){
		return "Exp<"+show+">";
	}
}
/**
 * @class_name:Exp112020年8月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月21日下午3:55:09
 */
public class Exp11 {

	/**
	 * Constructor
	 */
	public Exp11() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月21日下午3:55:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			caculate(1);
			caculate(1000);
		}catch(Exp e){
			System.out.println("捕获了异常:"+e);
		}
	}
	/**
	 * @Title: caculate
	 *@Description: TODO测试自定义异常类
	 *@param a
	 *@throws Exp
	 *@author: LiuWei
	 *@Date: 2020年8月21日下午3:59:57
	 */
	static void caculate(int a) throws Exp{
		System.out.println("对["+a+"]已经进行过相应的操作");
		if(a>100)
			throw new Exp(a);
		System.out.println("执行该算法正常退出");
	}
}
