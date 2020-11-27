/**
 * Title: BinaryTreeNode.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月30日下午2:41:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:BinaryTreeNode2019年10月30日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月30日下午2:41:27
 */
public class BinaryTreeNode {
	int m_nValue;
	BinaryTreeNode m_pLeft;
	BinaryTreeNode m_pRight;
	/**
	 * 构造方法
	 */
	public BinaryTreeNode(){
		super();
	}
	/**
	 * 构造法方法二
	 * @param m_nValue
	 * @param m_pLefet
	 * @param m_pRight
	 */
	public BinaryTreeNode(int m_nValue,BinaryTreeNode m_pLefet,BinaryTreeNode m_pRight){
		this.m_nValue = m_nValue;
		this.m_pLeft = m_pLefet;
		this.m_pRight = m_pRight;
	}
	public int getM_nValue() {
		return m_nValue;
	}
	public BinaryTreeNode getM_pLeft() {
		return m_pLeft;
	}
	public BinaryTreeNode getM_pRight() {
		return m_pRight;
	}
	public void setM_nValue(int m_nValue) {
		this.m_nValue = m_nValue;
	}
	public void setM_pLeft(BinaryTreeNode m_pLeft) {
		this.m_pLeft = m_pLeft;
	}
	public void setM_pRight(BinaryTreeNode m_pRight) {
		this.m_pRight = m_pRight;
	}
}
