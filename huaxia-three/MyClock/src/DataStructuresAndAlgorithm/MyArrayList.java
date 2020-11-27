package DataStructuresAndAlgorithm;
/**
 * 顺序表
 * @author liuwei
 *
 */
public class MyArrayList {
	private static final int DEFAULT_CAPACITY = 10;
	private int theSize;
	private Integer[] theItems;
	
	public MyArrayList(){
		theItems = new Integer[DEFAULT_CAPACITY];
	}
	public int size(){
		return theSize;
	}
	public boolean isEmpty(){
		return size()== 0;
	}
	/**
	 * @Title: ensureCapacity
	 *@Description: 扩充容量的函数，一般是两倍扩充
	 *@param newCapacity
	 *@author: LiuWei
	 *@Date: 2019年7月10日下午4:06:55
	 */
	public void ensureCapacity(int newCapacity){
		if(newCapacity<theSize){
			return ;
		}
		Integer[] old = theItems;
		theItems = (Integer[]) new Object[newCapacity];
		for(int i=0;i<size();i++){
			theItems[i] = old[i];
		}
	}
	/**
	 * @Title: add
	 *@Description: 链表中添加元素
	 *@param idx
	 *@param x
	 *@author: LiuWei
	 *@Date: 2019年7月10日下午4:13:42
	 */
	public void add(int idx,Integer x){
		if(theItems.length == size())
			ensureCapacity(size()*2+1);
		for(int i=theSize;i>idx;i--)
			theItems[i] = theItems[i-1];
		theItems[idx] = x;
		theSize++;
	}
	/**
	 * @Title: add
	 *@Description: 添加元素
	 *@param x
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月10日下午4:15:18
	 */
	public boolean add(Integer x){
		add(size(),x);
		return true;
	}
	/**
	 * @Title: remove
	 *@Description: 根据索引删除元素
	 *@param idx
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月10日下午4:19:18
	 */
	public Integer remove(int idx){
		Integer removedItem = theItems[idx];
		for(int i=idx;i<size()-1;i++)
			theItems[i] = theItems[i+1];
		theSize --;
		return removedItem;
	}
	MyArrayList.ArrayListIterator it= new ArrayListIterator();
	/**
	 * 遍历的类
	 * @author liuwei
	 *
	 */
	private  class ArrayListIterator implements java.util.Iterator<Integer>{
		private int current = 0;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current <size();
		}

		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}
			return theItems[current++];
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			MyArrayList.this.remove(--current);
		}
		
	}
	public static void main(String args[]){
		Integer[] a= new Integer[10];
		MyArrayList mylist = new MyArrayList();
		System.out.println("顺序表是否为空"+mylist.isEmpty());
		System.out.println("顺序表的大小为"+mylist.size());
		for(int i=0;i<a.length;i++)
			a[i]=(i+1);
		for(int i=0;i<a.length;i++)
			mylist.add(i+1);
		System.out.println("顺序表的数据");
		for(int i=0;i<mylist.size();i++)
			System.out.print(mylist.theItems[i]+" ");
		System.out.println();
		System.out.println("溢出第一个元素后");
		mylist.remove(0);
		System.out.println("顺序表的数据");
		for(int i=0;i<mylist.size();i++)
			System.out.print(mylist.theItems[i]+ " ");
		System.out.println();
		System.out.println("使用遍历方法遍历顺序表");
		while(mylist.it.hasNext()){
			System.out.print(mylist.it.next()+" ");
		}
	}
	
}
