/**
 * Title: Merge.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月7日下午2:04:11
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Merge2019年11月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月7日下午2:04:11
 */
public class Merge {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午2:04:11
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ListNode pHead1 = new ListNode();
				ListNode p = pHead1;
				
				pHead1.m_nValue = 1;
				for(int i=3 ;i<=7;i=i+2){
					ListNode ptemp = new ListNode();
					ptemp.m_nValue = i;
					p.m_pNext = ptemp;
					p = ptemp;
				}
				ListNode pHead2 = new ListNode();
				ListNode p2 = pHead2;
				
				pHead2.m_nValue = 2;
				for(int i=4;i<=8;i=i+2){
					ListNode ptemp = new ListNode();
					ptemp.m_nValue = i;
					p2.m_pNext = ptemp;
					p2 = ptemp;
				}
				printLinkList(pHead1);
				printLinkList(pHead2);
				printLinkList(Merge(pHead1,pHead2));
	}
	/**
	 * @Title: Merge
	 *@Description: TODO
	 *@param pHead1
	 *@param pHead2
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午2:10:17
	 */
	static ListNode Merge(ListNode pHead1,ListNode pHead2){
		if(pHead1 == null)
			return pHead2;
		if(pHead2 == null)
			return pHead1;
		ListNode pMergedHead = null;
		if(pHead1.m_nValue <pHead2.m_nValue){
			pMergedHead = pHead1;
			pMergedHead.m_pNext = Merge(pHead1.m_pNext,pHead2);
		}
		else{
			pMergedHead = pHead2;
			pMergedHead.m_pNext = Merge(pHead1,pHead2.m_pNext);
		}
		return pMergedHead;
	}
	/**
	 * @Title: printLinkList
	 *@Description: TODO
	 *@param pHead
	 *@author: LiuWei
	 *@Date: 2019年11月7日下午2:10:13
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
