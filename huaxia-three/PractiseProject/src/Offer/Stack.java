package Offer;

public class Stack<E>{
	private  Object[] data = null;
	private int maxSize =0 ;//ջ������
	private int top  = -1;//ջ��ָ��
	
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
			throw new RuntimeException("��ʼ����С����С��0"+initialSize);
		}
	}
	/**
	 * @Title: push
	 *@Description: TODO
	 *@param e ������ջ
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��28������2:15:28
	 */
	public boolean push(E e){
		if(top == maxSize -1){
		 throw new RuntimeException("ջ�Ѿ����ˣ��޷���������ջ��");	
		}else{
			data[++top] = e;
			return true;
		}
	}
	/**
	 * @Title: pop
	 *@Description: TODO
	 *@return����Ԫ��
	 *@author: LiuWei
	 *@Date: 2020��9��28������2:17:41
	 */
	public E pop(){
		if(top == -1){
			throw new RuntimeException("ջΪ�գ�");
		}else{
			return (E) data[top--];
		}
	}
	/**
	 * @Title: peek
	 *@Description: TODO
	 *@retur���ݲ�ѯ
	 *@author: LiuWei
	 *@Date: 2020��9��28������2:18:57
	 */
	public E peek(){
		if(top == -1){
			throw new RuntimeException("ջΪ�գ�");
		}else{
			return (E) data[top];
		}
	}
	/**
	 * @Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��28������2:22:31
	 */
	public static void main(String args[]){
		Stack<String> stack = new Stack<String>();
		for(int i=0;i<10;i++){
			stack.push(i+"0");
		}
		for(int i=0;i<10;i++){
			System.out.println("ջ������Ԫ�أ�"+stack.pop());
		}
	}
}
