/**
 * Title: TestMultiton.java
 * Description: һ��ֻ�����߸�����
 * �Ա�ֻ�����������󡣶���ģʽ���
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������4:44:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;
class Sex{
	private String title;
	private static final Sex MALE = new Sex("��");
	private static final Sex FEMALE = new Sex("Ů");

	private Sex(String title){
		this.title = title;
	}
	/**
	 * @Title: getInstance
	 *@Description: TODO ����ģʽ
	 *@param ch
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��11������4:47:26
	 */
	public static Sex getInstance(int ch){
		switch(ch){
			case 0:
				return MALE;
			case 1:
				return FEMALE;
			default:
				return null;
		}
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.title;
	}
}
/**
 * @class_name:TestMultiton2020��9��11��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������4:44:26
 */
public class TestMultiton {

	/**
	 * Constructor
	 */
	public TestMultiton() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������4:44:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Sex.getInstance(0));
	}

}
