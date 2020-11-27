/**
 * Title: T410.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日上午10:38:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:T4102020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日上午10:38:58
 */
public class T410 extends Computer {
	private String graphicCard;//显卡
	
	/**
	 * 
	 */
	public T410() {
		// TODO Auto-generated constructor stub
		this.setType("ThinkPad T410i");
	}

	public String getGraphicCard() {
		return graphicCard;
	}

	public void setGraphicCard(String graphicCard) {
		this.graphicCard = graphicCard;
	}

	@Override
	public String toString() {
		return "T410 [graphicCard=" + graphicCard + ", getType()=" + getType() + ", getCpu()=" + getCpu()
				+ ", getRam()=" + getRam() + ", getHardDisk()=" + getHardDisk() + ", getMonitor()=" + getMonitor()
				+ ", getOs()=" + getOs() + "]";
	}
		
}
