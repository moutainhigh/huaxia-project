/**
 * Title: StackTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日上午11:23:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:StackTest2020年9月18日
 * @comments:栈数据结构的测试
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日上午11:23:00
 */
public class StackTest {
	
	/**
	 * Constructor
	 */
	public StackTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日上午11:23:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack2<String> stack = new Stack2<String>();
		System.out.println("向栈中增加字符串");
		System.out.println("三国演义");
		System.out.println("西游记");
		System.out.println("水浒传");
		System.out.println("红楼梦");
		stack.push("三国演义");
		stack.push("西游记");
		stack.push("水浒传");
		stack.push("红楼梦");
		System.out.println("从栈里取出字符：");
		while(!stack.empty()){
			System.out.println((String)stack.pop());
		}
	}

}
