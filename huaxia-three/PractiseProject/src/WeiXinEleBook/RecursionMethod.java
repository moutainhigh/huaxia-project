/**
 * Title: RecursionMethod.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午4:53:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

/**
 * @class_name:RecursionMethod2020年9月11日
 * @comments: 练习程序
 * @param: 
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午4:53:46
 */
public class RecursionMethod {

	/**
	 * Constructor
	 */
	public RecursionMethod() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午4:53:46
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecursionMethod test = new RecursionMethod();
		int result = test.addNonrecursion(10);
		System.out.println("非递归方法计算"+result);
		result = test.addRecursion(10);
		System.out.println("递归方式计算"+result);
		System.out.println();
	}
	/**
	 * @Title: addNonrecursion
	 *@Description: TODO 非递归计算函数
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午4:54:56
	 */
	public int addNonrecursion(int n){
		int result = 0;
		for(int i=1;i<= n;i++)
		{
			result += i;
		}
		return result;
	}
	/**
	 * @Title: addRecursion
	 *@Description: TODO
	 *addRecursion 递归方式计算累加
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午4:56:05
	 */
	public int addRecursion(int n){
		if(n <= 1) 
			return n;
		return n+ addRecursion(n-1);
	}
}
