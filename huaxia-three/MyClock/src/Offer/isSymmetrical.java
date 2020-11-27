/**
 * Title: isSymmetrical.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月8日上午10:37:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:isSymmetrical2019年11月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月8日上午10:37:06
 */
public class isSymmetrical {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月8日上午10:37:06
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeNode binary8 = new BinaryTreeNode();
		BinaryTreeNode binary6 = new BinaryTreeNode();
		BinaryTreeNode binary61 = new BinaryTreeNode();
		BinaryTreeNode binary5 = new BinaryTreeNode();
		BinaryTreeNode binary7 = new BinaryTreeNode();
		BinaryTreeNode binary72 = new BinaryTreeNode();
		BinaryTreeNode binary52 = new BinaryTreeNode();
		binary8.m_nValue = 8;
		binary6.m_nValue = 6;
		binary61.m_nValue = 6;
		binary5.m_nValue = 5;
		binary7.m_nValue = 7;
		binary72.m_nValue = 7;
		binary52.m_nValue = 5;
		
		binary8.m_pLeft = binary6;
		binary8.m_pRight = binary61;
		binary6.m_pLeft = binary5;
		binary6.m_pRight = binary7;
		binary61.m_pLeft = binary72;
		binary61.m_pRight = binary52;

		printBinaryTree(binary8);
		System.out.println();
		System.out.println(isSymmetrical(binary8));
	}
	/**
	 * @Title: isSymmetrical
	 *@Description: TODO
	 *@param pRoot
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月8日上午10:38:35
	 */
	static boolean isSymmetrical(BinaryTreeNode pRoot){
		return isSymmetrical(pRoot,pRoot);
	}
	/**
	 * @Title: isSymmetrical
	 *@Description: TODO
	 *@param pRoot1
	 *@param pRoot2
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月8日上午10:38:46
	 */
	static boolean isSymmetrical(BinaryTreeNode pRoot1,BinaryTreeNode pRoot2){
		if(pRoot1 == null && pRoot2 == null)
			return true;
		if(pRoot1 == null || pRoot2 == null)
			return false;
		if(pRoot1.m_nValue != pRoot2.m_nValue)
			return false;
		return isSymmetrical(pRoot1.m_pLeft,pRoot2.m_pRight) && isSymmetrical(pRoot1.m_pRight,pRoot2.m_pLeft);
	}
	/**
	 * @Title: printBinaryTree
	 *@Description: TODO 深度遍历
	 *@param pNode
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午6:34:48
	 */
	public static void printBinaryTree(BinaryTreeNode pNode){
		if(pNode == null){
			return;
		}
		System.out.print(pNode.m_nValue+" ");
		if(pNode.m_pLeft != null){
			printBinaryTree(pNode.m_pLeft);
		}
		if(pNode.m_pRight != null){
			printBinaryTree(pNode.m_pRight);
		}
	}
}
