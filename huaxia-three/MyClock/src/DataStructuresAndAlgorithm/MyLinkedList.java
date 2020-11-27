package DataStructuresAndAlgorithm;
/**
 * 链表的方法
 * @author liuwei
 *
 */
public class MyLinkedList {
	private int theSize;
	private int modCount = 0;
	private Node<Integer> beginMarker;
	private Node<Integer> endMarker;
	public MyLinkedList(){
		clear();
	}
	/**
	 * @Title: clear
	 *@Description:清除数据
	 *@author: LiuWei
	 *@Date: 2019年7月10日下午2:42:21
	 */
	public void clear(){
		beginMarker = new Node<Integer>(0,null,null);
		endMarker = new Node<Integer>(0,beginMarker,null);
		beginMarker.next = endMarker;
		theSize = 0;
		modCount++;
	}
	/**
	 * @Title: get
	 *@Description: 直接获取值
	 *@param idx
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月10日下午2:43:40
	 */
	public Integer get(int idx){
		return getNode(idx).data;
	}
	/**
	 * @Title: set
	 *@Description: 根据索引设定制定的值
	 *@param idx
	 *@param newVal
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月10日下午2:47:07
	 */
	public Integer set(int idx,Integer newVal){
		Node<Integer> p= getNode(idx);
		Integer oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}
	/**
	 * @Title: addBefore
	 *@Description: 前序插入法
	 *@param p
	 *@param x	
	 *@author: LiuWei
	 *@Date: 2019年7月10日上午11:26:54
	 */
	private void addBefore(Node<Integer> p,Integer x){
		Node<Integer> newNode = new Node<Integer>(x,p.prev,p);
		newNode.prev.next= newNode;
		p.prev = newNode;
		this.theSize++;
		modCount++;
	}
	public boolean next(){
		return false;
	}
	/**
	 * @Title: getNode
	 *@Description: 根据索引取出数据
	 *@param idx
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月10日下午2:37:54
	 */
	private Node<Integer> getNode(int idx){
		Node<Integer> p;
		if(idx<0 || idx>size()){
			throw new IndexOutOfBoundsException();
		}
		if(idx<size()/2){
			p= beginMarker.next;
			for(int i=0;i<idx;i++)
				p= p.next;
		}else{
			p= endMarker;
			for(int i=size();i>idx;i--)
				p=p.prev;
		}
		return p;
	}
	public int  size(){
		return theSize;
	}
	/**
	 * @Title: remove
	 *@Description: 删除表中的一个元素
	 *@param p
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月10日下午3:42:52
	 */
	private Integer remove(Node<Integer> p){
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize --;
		modCount++;
		return p.data;
	}
	public static void main(String args[]){
		MyLinkedList mylink = new MyLinkedList();
//		Node<Integer> p =new Node<Integer>(1,null,null);
		for(int i=2;i<=10;i++){
			mylink.addBefore(mylink.endMarker, i);
		}
		for(int i=0;i<=8;i++){
			System.out.print(mylink.getNode(i).data+ " ");
		}
		System.out.println();
		for(int i=0;i<=8;i++){
			System.out.print(mylink.get(i)+ " ");
		}
		mylink.set(2, 100);
		System.out.println();
		for(int i=0;i<=8;i++){
			System.out.print(mylink.get(i)+ " ");
		}
		System.out.println();
		System.out.println("文件的大小为"+mylink.size());
		System.out.println("删除了"+mylink.remove(mylink.getNode(2)));
		System.out.println("文件的大小为"+mylink.size());
		for(int i=0;i<mylink.size();i++){
			System.out.print(mylink.get(i)+ " ");
		}
	}
}
