/**
 * Title: Process09.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������11:18:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Process092020��8��18��
 * @comments: ð������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������11:18:44
 */
public class Process09 {

	/**
	 * Constructor
	 */
	public Process09() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��18������11:18:44
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] score = {9,8,7,6,5};
		for(int i=0;i<score.length-1;i++)
			for(int j=0;j<score.length-i-1;j++){
				if(score[j] >score[j+1]){
					int temp = score[j];
					score[j] = score[j+1];
					score[j+1] = temp;
				}
			}
		for(int i = 0;i<score.length;i++){
			System.out.print(score[i]+" ");
		}
	}

}
