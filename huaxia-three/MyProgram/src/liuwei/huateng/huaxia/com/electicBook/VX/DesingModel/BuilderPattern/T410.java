/**
 * Title: T410.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������10:38:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:T4102020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������10:38:58
 */
public class T410 extends Computer {
	private String graphicCard;//�Կ�
	
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
