/**
 * Title: DeepEnum.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月27日下午1:57:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;
/**
 * 
 * @class_name:NewColor2020年8月27日
 * @comments: 测试枚举的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日下午2:39:23
 */
enum NewColor{
	RED("红色",4),GREEN("绿色",5),BLUE("蓝色",6);
	private String name;
	private int index;
	private NewColor(String name,int index){
		this.name = name;
		this.index = index;
	}
	public static String getName(int index) {
		for(NewColor c:NewColor.values()){
				if(c.getIndex() == index){
					return c.name;
				}
			}
		return null;
	}
	public String getName() {
	return name;
	}
	public int getIndex() {
		return index;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @Title: setName
	 *@Description: TODO
	 *@param index 根据索引设置姓名
	 *@param name
	 *@author: LiuWei
	 *@Date: 2020年8月27日下午2:32:50
	 */
	public static void setName(int index,String name){
		for(NewColor c:NewColor.values()){
			if(c.getIndex() == index){
				c.name = name;
				return;
			}
		}
	}
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @Title: setIndex
	 *@Description: TODO 根据姓名设置索引
	 *@param index
	 *@param name
	 *@author: LiuWei
	 *@Date: 2020年8月27日下午2:34:06
	 */
	public static void setIndex(int index,String name){
		for(NewColor c:NewColor.values()){
			if(c.getName() == name){
				c.index = index;
				return;
			}
		}
	}
}
/**
 * @class_name:DeepEnum2020年8月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日下午1:57:15
 */
public class DeepEnum {

	/**
	 * Constructor
	 */
	public DeepEnum() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月27日下午1:57:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("---------------输出枚举中的元素--------------");
		System.out.println(NewColor.RED.getIndex()+"---------->"+NewColor.RED.getName());
		System.out.println(NewColor.GREEN.getIndex()+"---------->"+NewColor.GREEN.getName());
		System.out.println(NewColor.BLUE.getIndex()+"---------->"+NewColor.BLUE.getName());
		System.out.println("------------在自定义编号和属性之后，测试-------------");
		NewColor.setName(4, "黑色");
		System.out.println("4----------->"+NewColor.getName(4));
		NewColor.setIndex(7, "黑色");
		System.out.println("7----------->"+NewColor.getName(7));
	}

}
