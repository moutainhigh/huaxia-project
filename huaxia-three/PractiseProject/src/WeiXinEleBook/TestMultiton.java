/**
 * Title: TestMultiton.java
 * Description: 一周只能由七个对象
 * 性别只能由两个对象。多例模式设计
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午4:44:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;
class Sex{
	private String title;
	private static final Sex MALE = new Sex("男");
	private static final Sex FEMALE = new Sex("女");

	private Sex(String title){
		this.title = title;
	}
	/**
	 * @Title: getInstance
	 *@Description: TODO 多例模式
	 *@param ch
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午4:47:26
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
 * @class_name:TestMultiton2020年9月11日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午4:44:26
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
	 *@Date: 2020年9月11日下午4:44:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Sex.getInstance(0));
	}

}
