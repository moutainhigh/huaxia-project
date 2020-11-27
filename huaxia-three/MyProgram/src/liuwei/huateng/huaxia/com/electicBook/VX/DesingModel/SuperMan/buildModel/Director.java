/**
 * Title: Director.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午5:14:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel;

/**
 * @class_name:Director2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午5:14:47
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
