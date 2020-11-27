/**
 * Title: Hoop.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��15������3:36:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Hoop2020��9��15��
 * @comments: һЩ��̬�Ĺ��̣�������ִ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��15������3:36:16
 */
public class Hoop {
	private Trainee trainee;//��������
	/**
	 * @Title: hitch
	 *@Description: TODO ��ס��������
	 *@param trainee
	 *@author: LiuWei
	 *@Date: 2020��9��15������3:37:36
	 */
	public void hitch(Trainee trainee){
		this.trainee = trainee;
	}
	/**
	 * Constructor
	 */
	public Hoop() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��15������3:36:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Monkey2 monkey = new Monkey2("�����");
		Bear bear =new Bear("���ܾ�");
		Hoop hoopForMonkey = new Hoop();
		hoopForMonkey.hitch(monkey);
		hoopForMonkey.useMagic();
		hoopForMonkey.hitch(bear);
		hoopForMonkey.useMagic();
	}
	/**
	 * @Title: useMagic
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��15������3:39:22
	 */
	public void useMagic(){
		System.out.println("��Ȧʩչ����");
		if(trainee  != null)
			trainee.haveHeadache();
	}
}
