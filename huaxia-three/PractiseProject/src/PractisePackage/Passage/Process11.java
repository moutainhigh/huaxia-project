/**
 * Title: Process11.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������2:02:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Process112020��8��18��
 * @comments: �ݹ��㷨Ӧ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������2:02:46
 */
public class Process11 {

	/**
	 * Constructor
	 */
	public Process11() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��18������2:02:46
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getSumOne(100));
		System.out.println(getSumTwo(30));
		System.out.println(getSumThree(5));
	}
	/**
	 * @Title: getSumOne
	 *@Description: TODO ����1+2+3.....+100;
	 *@param i
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��18������2:04:25
	 */
	public static int getSumOne(int i){
		int sum;
		if(i == 1){
			return 1;
		}else{
			sum = i+getSumOne(i-1);
		}
		return sum;
	}
	/**
	 * @Title: getSumTwo
	 *@Description: TODO ����fibonacci���еĵ�30λ��Ŀ
	 *@param i
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��18������2:06:37
	 */
	public static int getSumTwo(int i){
		if(i <= 0){
			return 0;
		}else if(i == 1 ||i==2){
			return 1;
		}else{
			return getSumTwo(i-1)+getSumTwo(i-2);
		}
	}
	/**
	 * @Title: getSumThree
	 *@Description: TODO ����1*2*3*.....*100;
	 *@param i
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��18������2:08:16
	 */
	public static int getSumThree(int i){
		if(i == 1){
			return i;
		}else{
			return i*getSumThree(i-1);
		}
	}
}
