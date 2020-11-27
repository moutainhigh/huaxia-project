/**
 * Title: Monkey.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月14日下午3:09:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Monkey2020年9月14日
 * @comments:判断真假孙悟空
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月14日下午3:09:07
 */
public class Monkey implements Target{
	String name;
	String looks;
	int wushuGrade;
	boolean isSenstive;
	String origin;
	/**
	 * Constructor
	 */
	public Monkey() {
		// TODO Auto-generated constructor stub
	}
	public Monkey(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	/**
	 * 
	 * @param name
	 * @param looks
	 * @param wushuGrade
	 * @param isSenstive
	 * @param originConstructor
	 */
	public Monkey(String name,String looks,int wushuGrade,boolean isSenstive,String origin) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.looks = looks;
		this.wushuGrade = wushuGrade;
		this.isSenstive = isSenstive;
		this.origin = origin;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月14日下午3:09:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Monkey wukong = new Monkey("孙悟空","尖嘴猴腮",9,true,"天产石猴");
		Monkey wukongFalse = new Monkey("孙悟空","尖嘴猴腮",9,true,"六耳猕猴");
		System.out.println("两只猴子是否相同："+wukong.equals(wukongFalse));
	}
	/**
	 * 重新比较方法
	 */
	public boolean equals(Object o){
		if(this == o) return true;
		if(!(o instanceof Monkey))
			return false;
		final Monkey other = (Monkey) o;
		if(this.name.equals(other.name)&& this.looks.equals(other.looks) && this.wushuGrade==other.wushuGrade && this.isSenstive == other.isSenstive && this.origin.equals(other.origin))
			return true;
		else
			return false;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
