/**
 * Title: HashSubtree.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月7日下午5:29:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:HashSubtree2019年11月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月7日下午5:29:06
 */
public class HashSubtree {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午5:29:06
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeNode binary8 = new BinaryTreeNode();
		BinaryTreeNode binary82 = new BinaryTreeNode();
		BinaryTreeNode binary7 = new BinaryTreeNode();
		BinaryTreeNode binary9 = new BinaryTreeNode();
		BinaryTreeNode binary2 = new BinaryTreeNode();
		BinaryTreeNode binary4 = new BinaryTreeNode();
		BinaryTreeNode binary72 = new BinaryTreeNode();
		binary8.m_nValue = 8;
		binary82.m_nValue = 8;
		binary7.m_nValue = 7;
		binary9.m_nValue = 9;
		binary2.m_nValue = 2;
		binary4.m_nValue = 4;
		binary72.m_nValue = 7;
		
		binary8.m_pLeft = binary82;
		binary8.m_pRight = binary7;
		binary82.m_pLeft = binary9;
		binary82.m_pRight = binary2;
		binary2.m_pLeft = binary4;
		binary2.m_pRight = binary7;
		
		BinaryTreeNode bin8 = new BinaryTreeNode();
		BinaryTreeNode bin9 = new BinaryTreeNode();
		BinaryTreeNode bin2 = new BinaryTreeNode();
		bin8.m_nValue = 8;
		bin9.m_nValue = 9;
		bin2.m_nValue = 2;
		bin8.m_pLeft = bin9;
		bin8.m_pRight = bin2;
		printBinaryTree(binary8);
		System.out.println();
		printBinaryTree(bin8);
		System.out.println(HasSubtree(binary8,bin8));
	}
	/**
	 * @Title: HasSubtree
	 *@Description: TODO
	 *@param pRoot1
	 *@param pRoot2
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午5:30:30
	 */
	static boolean HasSubtree(BinaryTreeNode pRoot1,BinaryTreeNode pRoot2){
		boolean result = false;
		if(pRoot1 != null && pRoot2 != null)
		{
			if(pRoot1.m_nValue == pRoot2.m_nValue)
				result = DoesTree1HaveTree2(pRoot1,pRoot2);
			if(!result)
				result = HasSubtree(pRoot1.m_pLeft,pRoot2);
			if(!result)
				result = HasSubtree(pRoot1.m_pRight,pRoot2);
		}
		return result;
	}
	/**
	 * @Title: DoesTree1HaveTree2
	 *@Description: TODO
	 *@param pRoot1
	 *@param pRoot2
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午6:15:15
	 */
	static boolean DoesTree1HaveTree2(BinaryTreeNode pRoot1,BinaryTreeNode pRoot2){
		if(pRoot2 == null)
			return true;
		if(pRoot1 == null)
			return false;
		if(!(pRoot1.m_nValue == pRoot2.m_nValue))	
			return false;
		return DoesTree1HaveTree2(pRoot1.m_pLeft,pRoot2.m_pLeft)&&DoesTree1HaveTree2(pRoot1.m_pRight,pRoot2.m_pRight);
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
