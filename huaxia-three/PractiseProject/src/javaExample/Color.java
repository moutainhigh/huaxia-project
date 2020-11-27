/**
 * Title: Color.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��27������10:44:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;

/**
 * @class_name:Color2020��8��27��
 * @comments:û��ʹ��ö�����͵�����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������10:44:32
 */
public class Color {
	private String name;
	public static final Color RED = new Color("��ɫ");
	public static final Color GREEN = new Color("��ɫ");
	public static final Color BLUE =new Color("��ɫ");
	/**
	 * Constructor
	 */
	private Color(String name) {
		// TODO Auto-generated constructor stub
		setName(name);
	}
	/**
	 * @Title: getInstance
	 *@Description: TODO ��ȡʵ��
	 *@param i
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��27������10:48:02
	 */
	public static Color getInstance(int i){
		if(i == 0)
			return RED;
		if(i == 1)
			return GREEN;
		if(i == 2)
			return BLUE;
	    return null;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��27������10:44:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Color c1 = Color.getInstance(0);
		System.out.println(c1.getName());
		Color c2 = Color.getInstance(1);
		System.out.println(c2.getName());
		Color c3 = Color.getInstance(2);
		System.out.println(c3.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
