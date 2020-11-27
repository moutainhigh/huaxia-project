/**
 * Title: LinkedList.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月31日下午2:46:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:LinkedList2019年10月31日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月31日下午2:46:52
 */
public class LinkedList {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月31日下午2:46:52
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode linkList = new ListNode();
		for(int i=0;i<10;i++){
			AddToTail(linkList,i);
		}
		printLinkList(linkList);
	}
	/**
	 * @Title: AddToTail
	 *@Description: TODO
	 *@param pHead
	 *@param value
	 *@author: LiuWei
	 *@Date: 2019年10月31日下午2:47:25
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
	 *@Description: 输出链表
	 *@param pHead
	 *@author: LiuWsei
	 *@Date: 2019年10月31日下午3:02:39
	 */
	static void printLinkList(ListNode pHead){
		ListNode pNode;
		pNode = pHead;
		while(pNode != null){
			System.out.println(pNode.m_nValue);
			pNode = pNode.m_pNext;
		}
	}
}
