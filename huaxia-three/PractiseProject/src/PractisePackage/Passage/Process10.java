/**
 * Title: Process10.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������1:48:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Process102020��8��18��
 * @comments:1,2,3,4����ɶ��ٸ���ͬ����λ��������������⣬�ö���ѭ������ʵ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������1:48:48
 */
public class Process10 {

	public static void arrange(){
		int i = 0;
		int j = 0;
		int k = 0;
		int t = 0;
		for(i = 1;i<=4 ;i++)
		{
			for(j = 1;j<=4;j++){
				for(k = 1;k<=4;k++){
					if(i != j && j!=k && k!=i){
						t+=1;
						System.out.print(i*100+j*10+k+"--");
					}
				}
			}
		}
		System.out.println();
		System.out.println("t="+t);
	}
	/**
	 * Constructor
	 */
	public Process10() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��18������1:48:48
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		arrange();
	}

}
