/**
 * Title: MirrorRecursively.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月30日下午2:43:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:MirrorRecursively2019年10月30日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月30日下午2:43:12
 */
public class MirrorRecursively {
	/**
	 * @Title: MirrorRecursively
	 *@Description: TODO
	 *@param pNode
	 *@author: LiuWei
	 *@Date: 2019年10月30日下午2:43:45
	 */
	static void MirrorRecursively(BinaryTreeNode pNode){
		if(pNode == null){
			return ;
		}
		if(pNode.m_pLeft == null & pNode.m_pRight == null){
			return;
		}
		BinaryTreeNode pTemp = pNode.m_pLeft;
		pNode.m_pLeft = pNode.m_pRight;
		pNode.m_pRight = pTemp;
		if(pNode.m_pLeft != null){
			MirrorRecursively(pNode.m_pLeft);
		}
		if(pNode.m_pRight != null){
			MirrorRecursively(pNode.m_pRight);
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月30日下午2:43:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeNode pNode5 = new BinaryTreeNode(5,null,null);
		BinaryTreeNode pNode7 = new BinaryTreeNode(7,null,null);
		BinaryTreeNode pNode9 = new BinaryTreeNode(9,null,null);
		BinaryTreeNode pNode11 = new BinaryTreeNode(11,null,null);
		BinaryTreeNode pNode6 = new BinaryTreeNode(6,pNode5,pNode7);
		BinaryTreeNode pNode10 = new BinaryTreeNode(10,pNode9,pNode11);
		BinaryTreeNode pNode8 = new BinaryTreeNode(8,pNode6,pNode10);
		printBinaryTree(pNode8);
		System.out.println();
		//镜像后树是
		MirrorRecursively(pNode8);
		printBinaryTree(pNode8);
		System.out.println();
	}
	/**
	 * @Title: printBinaryTree
	 *@Description: TODO 二叉树的中序排序输出
	 *@param pNode
	 *@author: LiuWei
	 *@Date: 2019年10月30日下午2:50:49
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
	
	static void CloneNodes(BinaryTreeNode pHead){
		BinaryTreeNode pNode = pHead;
		while(pNode != null){
			BinaryTreeNode pCloned = new BinaryTreeNode();
			pCloned.m_nValue = pNode.m_nValue;
			pCloned.m_pLeft = pNode.m_pLeft;
			pCloned.m_pRight = pNode.m_pRight;
		}
	}
}
