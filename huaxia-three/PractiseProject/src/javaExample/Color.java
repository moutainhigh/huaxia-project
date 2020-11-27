/**
 * Title: Color.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月27日上午10:44:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;

/**
 * @class_name:Color2020年8月27日
 * @comments:没有使用枚举类型的例子
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日上午10:44:32
 */
public class Color {
	private String name;
	public static final Color RED = new Color("红色");
	public static final Color GREEN = new Color("绿色");
	public static final Color BLUE =new Color("蓝色");
	/**
	 * Constructor
	 */
	private Color(String name) {
		// TODO Auto-generated constructor stub
		setName(name);
	}
	/**
	 * @Title: getInstance
	 *@Description: TODO 获取实例
	 *@param i
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月27日上午10:48:02
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
	 *@Date: 2020年8月27日上午10:44:32
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
