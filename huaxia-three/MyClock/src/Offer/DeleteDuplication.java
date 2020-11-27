/**
 * Title: DeleteDuplication.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月11日下午1:58:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:DeleteDuplication2019年11月11日
 * @comments:删除重复节点
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月11日下午1:58:22
 */
public class DeleteDuplication {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月11日下午1:58:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode linkList = new ListNode();
		AddToTail(linkList,1);
		AddToTail(linkList,2);
		AddToTail(linkList,3);
		AddToTail(linkList,3);
		AddToTail(linkList,4);
		AddToTail(linkList,4);
		AddToTail(linkList,5);
		AddToTail(linkList,6);
		printLinkList(linkList);
		ListNode delete = linkList.m_pNext.m_pNext.m_pNext;
		DeleteDuplication(linkList);
		printLinkList(linkList);
	}
	/**
	 * @Title: DeleteDuplication
	 *@Description: TODO
	 *@param pHead
	 *@author: LiuWei
	 *@Date: 2019年11月11日下午1:59:04
	 */
	public static void DeleteDuplication(ListNode pHead){
		if(pHead == null){
			return ;
		}
		ListNode pPreNode = null;
		ListNode pNode = pHead;
		while(pNode != null)
		{
			ListNode pNext = pNode.m_pNext;
			boolean needDelete = false;
			if(pNext != null && pNext.m_nValue == pNode.m_nValue)
				needDelete = true;
			if(!needDelete)
			{
				pPreNode = pNode;
				pNode = pNode.m_pNext;
			}
			else
			{
				int value = pNode.m_nValue;
				ListNode pToBeDel = pNode;
				while(pToBeDel != null && pToBeDel.m_nValue == value){
					pNext = pToBeDel.m_pNext;
					pToBeDel = null;
					pToBeDel = pNext;
				}
				if(pPreNode == null)
					pHead = pNext;
				else
					pPreNode.m_pNext = pNext;
				pNode = pNext;
			}
		}
	}
	/**
	 * @Title: AddToTail
	 *@Description: TODO
	 *@param pHead
	 *@param value
	 *@author: LiuWei
	 *@Date: 2019年11月11日上午11:29:20
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
	/**
	 * @Title: printLinkList
	 *@Description: TODO
	 *@param pHead
	 *@author: LiuWei
	 *@Date: 2019年11月11日上午11:29:34
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
}
