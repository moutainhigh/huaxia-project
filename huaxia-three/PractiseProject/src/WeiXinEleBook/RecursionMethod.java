/**
 * Title: RecursionMethod.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������4:53:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

/**
 * @class_name:RecursionMethod2020��9��11��
 * @comments: ��ϰ����
 * @param: 
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������4:53:46
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
	 *@Date: 2020��9��11������4:53:46
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecursionMethod test = new RecursionMethod();
		int result = test.addNonrecursion(10);
		System.out.println("�ǵݹ鷽������"+result);
		result = test.addRecursion(10);
		System.out.println("�ݹ鷽ʽ����"+result);
		System.out.println();
	}
	/**
	 * @Title: addNonrecursion
	 *@Description: TODO �ǵݹ���㺯��
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��11������4:54:56
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
	 *addRecursion �ݹ鷽ʽ�����ۼ�
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��11������4:56:05
	 */
	public int addRecursion(int n){
		if(n <= 1) 
			return n;
		return n+ addRecursion(n-1);
	}
}
