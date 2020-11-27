/**
 * Title: MyStack.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月17日下午2:03:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:MyStack2020年9月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月17日下午2:03:36
 */
public class MyStack {
	private int capicity = 1;
	private int point = -1;
	private String[] buffer;
	/**
	 * Constructor
	 */
	public MyStack() {
		// TODO Auto-generated constructor stub
		this(1000);
	}
	public MyStack(int capicity) {
		// TODO Auto-generated constructor stub
		this.capicity = capicity;
		buffer = new String[capicity];
	}
	/**
	 * @Title: push
	 *@Description: TODO
	 *@param data
	 *@author: LiuWei
	 *@Date: 2020年9月17日下午2:06:19
	 */
	public void push(String data){
		if(point == capicity - 1){
			System.out.println("堆栈已经满了。。。");
			return;
		}
			if(data == null){
				System.out.println("不容许向堆栈加入null");
				return;
			}
		buffer[++point] = data;
	}
	/**
	 * @Title: pop
	 *@Description: TODO
	 *@return 弹出元素
	 *@author: LiuWei
	 *@Date: 2020年9月17日下午2:08:57
	 */
	public String pop(){
		if(point == -1){
			System.out.println("堆栈是空的。。");
			return null;
		}
		return buffer[point--];
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月17日下午2:03:36
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyStack stack =new MyStack();
		stack.push("Tom");
		stack.push("Mike");
		stack.push("Mary");
		stack.push("Rose");
		for(int i=0;i<4;i++){
			System.out.println(stack.pop());
		}
	}
}
