/**
 * Title: waapon.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��14������9:50:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:waapon2020��9��14��
 * @comments: ��������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��14������9:50:55
 */
public class weapon {
	String name; // 
	double length; //����
	double weight;//����
	boolean isStretchAble;//�Ƿ��������
	String madeIn;//���� 
//	Monkey owner;//��ʾ����
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
	 *@Date: 2020��9��14������9:50:55
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		weapon  weapon = new weapon("�𹿰�",2,13500,true,"����");
		weapon.print();
		weapon = new weapon("���ǽ�",0.3,100.56,false,"����ɽ");
		weapon.print();
	}
	/**
	 * @Title: print
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��14������10:07:55
	 */
	public void print(){
		System.out.println("�������֣�"+name+"\n ����"+length+"��\n ����:"+weight+"ǧ��\n�ɷ�������"+isStretchAble+"\n���أ�"+madeIn+"\n");
	}
}
