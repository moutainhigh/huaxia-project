/**
 * Title: Director.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������5:14:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel;

/**
 * @class_name:Director2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������5:14:47
 */
public class Director {
	private Builder builder;
	public SuperMan buildSuperMan(String name){
		if(name.equals("child")){
			builder = new ChildSuperManBuilder();
		}else if(name.equals("adult")){
			builder = new AdultSuperManBuilder();
		}
		else{
			return null;
		}
		builder.setBody();
		builder.setSpecialSymbol();
		builder.setSpecialTalnent();
		return builder.getSuperMan();
	}
	/**
	 * 
	 */
	public Director() {
		// TODO Auto-generated constructor stub
	}

}
