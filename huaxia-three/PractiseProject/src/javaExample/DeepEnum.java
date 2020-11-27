/**
 * Title: DeepEnum.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��27������1:57:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;
/**
 * 
 * @class_name:NewColor2020��8��27��
 * @comments: ����ö�ٵ�ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������2:39:23
 */
enum NewColor{
	RED("��ɫ",4),GREEN("��ɫ",5),BLUE("��ɫ",6);
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
	 *@param index ����������������
	 *@param name
	 *@author: LiuWei
	 *@Date: 2020��8��27������2:32:50
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
	 *@Description: TODO ����������������
	 *@param index
	 *@param name
	 *@author: LiuWei
	 *@Date: 2020��8��27������2:34:06
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
 * @class_name:DeepEnum2020��8��27��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������1:57:15
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
	 *@Date: 2020��8��27������1:57:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("---------------���ö���е�Ԫ��--------------");
		System.out.println(NewColor.RED.getIndex()+"---------->"+NewColor.RED.getName());
		System.out.println(NewColor.GREEN.getIndex()+"---------->"+NewColor.GREEN.getName());
		System.out.println(NewColor.BLUE.getIndex()+"---------->"+NewColor.BLUE.getName());
		System.out.println("------------���Զ����ź�����֮�󣬲���-------------");
		NewColor.setName(4, "��ɫ");
		System.out.println("4----------->"+NewColor.getName(4));
		NewColor.setIndex(7, "��ɫ");
		System.out.println("7----------->"+NewColor.getName(7));
	}

}
