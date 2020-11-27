/**
 * Title: SuperMan.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������4:48:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel;

/**
 * @class_name:SuperMan2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������4:48:01
 */
public class SuperMan {
	private String body;
	private String specialTalent;
	private String specialSymbol;
	
	/**
	 * 
	 */
	public SuperMan() {
		// TODO Auto-generated constructor stub
	}

	public String getBody() {
		return body;
	}

	public String getSpecialTalent() {
		return specialTalent;
	}

	public String getSpecialSymbol() {
		return specialSymbol;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setSpecialTalent(String specialTalent) {
		this.specialTalent = specialTalent;
	}

	public void setSpecialSymbol(String specialSymbol) {
		this.specialSymbol = specialSymbol;
	}

	@Override
	public String toString() {
		return "SuperMan [body=" + body + ", specialTalent=" + specialTalent + ", specialSymbol=" + specialSymbol + "]";
	}
	
}
