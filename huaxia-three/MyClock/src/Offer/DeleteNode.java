/**
 * Title: DeleteNode.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月11日上午10:00:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:DeleteNode2019年11月11日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月11日上午10:00:47
 */
public class DeleteNode {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月11日上午10:00:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode linkList = new ListNode();
		for(int i=1;i<10;i++){
			AddToTail(linkList,i);
		}
		printLinkList(linkList);
		ListNode delete = linkList.m_pNext.m_pNext.m_pNext;
		DeleteNode(linkList,delete);
		printLinkList(linkList);
	}
	/**
	 * @Title: DeleteNode
	 *@Description: TODO
	 *@param pListHead
	 *@param pToBeDeleted
	 *@author: LiuWei
	 *@Date: 2019年11月11日上午10:02:18
	 */
	public static void DeleteNode(ListNode pListHead,ListNode pToBeDeleted){
		if(pListHead == null || pToBeDeleted == null)
			return ;
		if(pToBeDeleted.getM_pNext() != null)
		{
			ListNode pNext = pToBeDeleted.m_pNext;
			pToBeDeleted.m_nValue = pNext.m_nValue;
			pToBeDeleted.m_pNext = pNext.m_pNext;
			pNext = null;
		}
		else if(pListHead == pToBeDeleted)
		{
			pToBeDeleted = null;
		}
		else
		{
			ListNode pNode = pListHead;
			while(pNode.m_pNext != pToBeDeleted)
			{
				pNode = pNode.m_pNext;
			}
			pNode.m_pNext = null;
			pToBeDeleted = null;
		}
	}
	/**
	 * @Title: printLinkList
	 *@Description: TODO
	 *@param pHead
	 *@author: LiuWei
	 *@Date: 2019年11月11日上午10:17:52
	 */
	static void printLinkList(ListNode pHead){
		ListNode pNode;
		pNode = pHead;
		while(pNode != null){
			System.out.print(pNode.m_nValue+" ");
			pNode = pNode.m_pNext;
		}
		System.out.println();
	}
	/**
	 * @Title: AddToTail
	 *@Description: TODO
	 *@param pHead
	 *@param value
	 *@author: LiuWei
	 *@Date: 2019年11月11日上午10:21:16
	 */
	static void AddToTail(ListNode pHead,int value){
		ListNode pNew = new ListNode();
		pNew.m_nValue = value;
		pNew.m_pNext = null;
		
		if(pHead == null){
			pHead = pNew;
		}
		else{
			ListNode pNode = pHead;
			while(pNode.m_pNext != null){
				pNode = pNode.m_pNext;
			}
			pNode.m_pNext = pNew;
		}
	}
}
