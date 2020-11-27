/**
 * Title: ReverseList.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月4日上午10:00:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:ReverseList2019年11月4日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月4日上午10:00:02
 */
public class ReverseList {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月4日上午10:00:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode pHead = new ListNode();
		ListNode p = pHead;
		
		pHead.m_nValue = 1;
		for(int i=2 ;i<=10;i++){
			ListNode ptemp = new ListNode();
			ptemp.m_nValue = i;
			p.m_pNext = ptemp;
			p = ptemp;
		}
		printLinkList(pHead);
		printLinkList(ReverseList(pHead));
	}
	/**
	 * @Title: ReverseList
	 *@Description: TODO
	 *@param pHead
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午1:43:58
	 */
	static ListNode ReverseList(ListNode pHead){
		ListNode pReversedHead = null;
		ListNode pNode = pHead;
		ListNode pPrev  = null;
		while(pNode != null){
			ListNode pNext = pNode.m_pNext;
			if(pNext == null)
				pReversedHead =pNode;
			pNode.m_pNext = pPrev;
			pPrev = pNode;
			pNode = pNext;
		}
		return pReversedHead;
	}
	/**
	 * @Title: printLinkList
	 *@Description: 输出链表
	 *@param pHead
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午1:48:29
	 */
	static void printLinkList(ListNode pHead){
		if(pHead == null)
			return;
		ListNode temp = pHead;
		while(temp.m_pNext != null){
			System.out.print(temp.m_nValue + " ");
			temp = temp.m_pNext;
		}
		System.out.print(temp.m_nValue + " ");
		System.out.println();
	}
}
