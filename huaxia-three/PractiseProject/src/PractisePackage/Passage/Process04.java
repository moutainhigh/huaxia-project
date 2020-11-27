/**
 * Title: Process04.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日上午10:37:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Process042020年8月18日
 * @comments: for循环的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日上午10:37:17
 */
public class Process04 {

	/**
	 * Constructor
	 */
	public Process04() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午10:37:17
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = 0;
		for(int i= 1;i<=100;i++)
		{
			sum+= i;
		}
		System.out.println(sum);
		String[] nameArr = {"Java","C++","C#"};
		for(String name:nameArr){
			System.out.println("name="+name);
		}
		int i =0;
		//i = 13;
		for(i++;i++<10;i++);
			System.out.println(++i);
		//j = 3 6 9
		int j =0;
		for(j++;j++<10;j++){
			System.out.println(++j);
		}
	}
	
}
