package DataStructuresAndAlgorithm;
/**
 * 顺序栈的实现
 * 栈是一种先进先出的数据结构
 * 顺序栈是顺序结构存储的结构
 * @author liulwei
 *
 */
public class Sequentialstack {
	public static Integer StackSize;
	public static class SeqStack{
		Integer[] data;
		int top;
		public SeqStack(int size){
			StackSize = size;
			data = new Integer[StackSize];
		}
	}
	/**
	 * @Title: InitStack
	 *@Description: 初始化栈
	 *@param s
	 *@author: LiuWei
	 *@Date: 2019年7月12日上午9:25:30
	 */
	void InitStack(SeqStack s){
		s.top = -1;
	}
	/**
	 * @Title: Push
	 *@Description: 入栈方法
	 *@param s
	 *@param x
	 *@author: LiuWei
	 *@Date: 2019年7月12日上午9:28:31
	 */
	void Push(SeqStack s,Integer x){
		if(s.top == StackSize-1){
			System.out.println("上溢");
			System.exit(0);
		}
		s.data[++s.top] = x;
	}
	/**
	 * @Title: pop
	 *@Description: 弹出栈顶元素
	 *@param s
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月12日上午9:33:24
	 */
	Integer pop(SeqStack s){
		if(s.top == -1){
			System.out.println("下溢");
			System.exit(0);
		}
		return s.data[s.top--];
	}
	/**
	 * @Title: GetTop
	 *@Description: 取出栈顶元素
	 *@param s
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月12日上午9:34:38
	 */
	Integer GetTop(SeqStack s){
		if(s.top == -1){
			System.out.println("下溢");
			System.exit(0);
		}
		return s.data[s.top];
	}
	/**
	 * @Title: isEmpty
	 *@Description: 判断栈是否为空
	 *@param s
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月12日上午9:36:22
	 */
	boolean isEmpty(SeqStack s){
		if(s.top == -1)
			return true;
		return false;
	}
	public static void main(String args[]){
		Sequentialstack ss= new Sequentialstack();
		/*Sequentialstack.SeqStack s = new Sequentialstack.SeqStack(110);
		for(int i=0;i<100;i++)
			ss.Push(s, i+1);
		for(int i=0;i<100;i++)
			System.out.print(ss.pop(s)+" ");
		System.out.println();
		ss.Push(s, 100);
		System.out.println("取出栈顶元素"+ss.GetTop(s));
		System.out.println("栈是否为空"+ss.isEmpty(s));
		ss.InitStack(s);
//		System.out.println("取出栈顶元素"+ss.GetTop(s));
		System.out.println("栈是否为空"+ss.isEmpty(s));*/
		Sequentialstack.BothStack s = new Sequentialstack.BothStack(110);
		ss.InitStack(s);
		for(int i=0;i<10;i++)
			ss.BothPush(s,1, i+1);
		for(int i=0;i<10;i++)
			System.out.print(ss.BothPop(s,1)+" ");
		for(int i=0;i<10;i++)
			ss.BothPush(s,2, i+1);
		for(int i=0;i<10;i++)
			System.out.print(ss.BothPop(s,2)+" ");
		System.out.println();
		for(int i=0;i<ss.StackSize;i++)
			System.out.print(s.data[i]+" ");
	}
	/**
	 * 双向列表
	 * @author liuwei
	 */
	public static class BothStack{
		Integer[] data;
		int top1;
		int top2;
		public BothStack(int size){
			StackSize = size;
			data = new Integer[StackSize];
		}
	}
	/**
	 * @Title: InitStack
	 *@Description: 初始化双向列表
	 *@param s
	 *@author: LiuWei
	 *@Date: 2019年7月12日上午10:08:46
	 */
	void InitStack(BothStack s){
		s.top1 = -1;
		s.top2 = StackSize ;
	}
	/**
	 * @Title: BothPush
	 *@Description: 双向列表的两边插入
	 *@param s
	 *@param i
	 *@param x
	 *@author: LiuWei
	 *@Date: 2019年7月12日上午10:04:20
	 */
	void BothPush(BothStack s,int i,Integer x){
		if(s.top1 == s.top2){
			System.out.println("上溢");
			System.exit(0);
		}
		if(i == 1){
			s.data[++s.top1] = x;
		}
		if(i == 2){
			s.data[--s.top2] = x;
		}
	}
	/**
	 * @Title: BothPop
	 *@Description: 出栈算法
	 *@param s
	 *@param i
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月12日上午10:11:42
	 */
	Integer BothPop(BothStack s,int i){
		if(i ==1){
			if(s.top1 == -1){
				System.out.println("下溢");
				System.exit(0);
			}
			return s.data[s.top1--];
		}
		if(i ==2){
			if(s.top2 == StackSize){
				System.out.println("下溢");
				System.exit(0);
			}
			return s.data[s.top2++];
		}
		return -100;
	}
}
