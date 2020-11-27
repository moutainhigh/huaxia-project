/**
 * Title: Monkey.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��14������3:09:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Monkey2020��9��14��
 * @comments:�ж���������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��14������3:09:07
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
	 *@Date: 2020��9��14������3:09:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Monkey wukong = new Monkey("�����","�������",9,true,"���ʯ��");
		Monkey wukongFalse = new Monkey("�����","�������",9,true,"����⨺�");
		System.out.println("��ֻ�����Ƿ���ͬ��"+wukong.equals(wukongFalse));
	}
	/**
	 * ���±ȽϷ���
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
