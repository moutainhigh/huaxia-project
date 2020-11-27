/**
 * Title: FinkKthToTail.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月11日上午11:18:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:FinkKthToTail2019年11月11日
 * @comments:寻找倒数第k个元素
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月11日上午11:18:05
 */
public class FinkKthToTail {
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月11日上午11:18:05
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode linkList = new ListNode();
		for(int i=1;i<=6;i++){
			AddToTail(linkList,i);
		}
		printLinkList(linkList);
		ListNode delete = linkList.m_pNext.m_pNext.m_pNext;
		System.out.println(FindKthToTail(linkList,3).m_nValue);
	}
	/**
	 * @Title: FindKthToTail
	 *@Description: TODO
	 *@param pListHead
	 *@param k
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年11月11日上午11:18:48
	 */
	public static ListNode FindKthToTail(ListNode pListHead,int k){
		ListNode pAhead = pListHead;
		ListNode pBehind = null;
		
		for(int i=0;i<k-1;++i){
			pAhead = pAhead.m_pNext;
		}
		pBehind = pListHead;
		while(pAhead.m_pNext != null)
		{
			pAhead = pAhead.m_pNext;
			pBehind = pBehind.m_pNext;
		}
		return pBehind;
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
