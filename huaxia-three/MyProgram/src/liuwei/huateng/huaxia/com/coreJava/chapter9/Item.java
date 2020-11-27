/**
 * Title: Item.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月3日上午9:05:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter9;

/**
 * @class_name:Item2020年1月3日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月3日上午9:05:26
 */
public class Item implements Comparable<Item> {
	private String description;
	private int partNumber;
	/**
	 * 
	 */
	public Item(String aDescripton,int aPartNumber) {
		// TODO Auto-generated constructor stub
		this.description = aDescripton;
		this.partNumber = aPartNumber;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月3日上午9:05:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(Item other) {
		// TODO Auto-generated method stub
		int diff = Integer.compare(partNumber, other.partNumber);
		return diff != 0?diff:description.compareTo(other.description);
	}

	public String getDescription() {
		return description;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	@Override
	public String toString() {
		return "Item [description=" + description + ", partNumber=" + partNumber + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + partNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (partNumber != other.partNumber)
			return false;
		return true;
	}

}
