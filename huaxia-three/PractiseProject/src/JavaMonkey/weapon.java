/**
 * Title: waapon.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月14日上午9:50:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:waapon2020年9月14日
 * @comments: 武器的类
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月14日上午9:50:55
 */
public class weapon {
	String name; // 
	double length; //长度
	double weight;//重量
	boolean isStretchAble;//是否可以伸缩
	String madeIn;//产地 
//	Monkey owner;//表示主人
	/**
	 * Constructor
	 */
	public weapon() {
		// TODO Auto-generated constructor stub
	}
	public weapon(String name,double length,double weight,boolean isStretchAble,String madeIn) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.length = length;
		this.weight = weight;
		this.isStretchAble = isStretchAble;
		this.madeIn = madeIn;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月14日上午9:50:55
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		weapon  weapon = new weapon("金箍棒",2,13500,true,"东海");
		weapon.print();
		weapon = new weapon("流星剑",0.3,100.56,false,"花果山");
		weapon.print();
	}
	/**
	 * @Title: print
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月14日上午10:07:55
	 */
	public void print(){
		System.out.println("兵器名字："+name+"\n 长度"+length+"米\n 重量:"+weight+"千克\n可否伸缩："+isStretchAble+"\n产地："+madeIn+"\n");
	}
}
