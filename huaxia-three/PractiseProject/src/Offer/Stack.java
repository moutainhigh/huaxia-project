package Offer;

public class Stack<E>{
	private  Object[] data = null;
	private int maxSize =0 ;//栈的容量
	private int top  = -1;//栈顶指针
	
	public Stack() {
		// TODO Auto-generated constructor stub
		this(10);
	}
	/**
	 * 
	 * @param initialSizeConstructor
	 */
	public Stack(int initialSize) {
		// TODO Auto-generated constructor stub
		if(initialSize >= 0){
			this.maxSize = initialSize;
			data = new Object[initialSize];
			top = -1;
		}else{
			throw new RuntimeException("初始化大小不能小于0"+initialSize);
		}
	}
	/**
	 * @Title: push
	 *@Description: TODO
	 *@param e 数据入栈
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月28日下午2:15:28
	 */
	public boolean push(E e){
		if(top == maxSize -1){
		 throw new RuntimeException("栈已经满了，无法将数据入栈！");	
		}else{
			data[++top] = e;
			return true;
		}
	}
	/**
	 * @Title: pop
	 *@Description: TODO
	 *@return弹出元素
	 *@author: LiuWei
	 *@Date: 2020年9月28日下午2:17:41
	 */
	public E pop(){
		if(top == -1){
			throw new RuntimeException("栈为空！");
		}else{
			return (E) data[top--];
		}
	}
	/**
	 * @Title: peek
	 *@Description: TODO
	 *@retur数据查询
	 *@author: LiuWei
	 *@Date: 2020年9月28日下午2:18:57
	 */
	public E peek(){
		if(top == -1){
			throw new RuntimeException("栈为空！");
		}else{
			return (E) data[top];
		}
	}
	/**
	 * @Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月28日下午2:22:31
	 */
	public static void main(String args[]){
		Stack<String> stack = new Stack<String>();
		for(int i=0;i<10;i++){
			stack.push(i+"0");
		}
		for(int i=0;i<10;i++){
			System.out.println("栈弹出的元素："+stack.pop());
		}
	}
}
