/**
 * Title: Test16.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������3:34:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Test162020��8��18��
 * @comments:  һ����������100��һ����ȫƽ�������ټ���168����һ����ȫƽ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������3:34:02
 */
public class Test16 {

	/**
	 * Constructor
	 */
	public Test16() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��18������3:34:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = -100;i<10000;i++){
			if(Math.sqrt(i+100)%1 ==0 && Math.sqrt(i+268)%1 == 0){
				System.out.println(i+" "+(i+100)+" "+Math.sqrt(i+100)+" "+(i+268)+" "+Math.sqrt(i+268));
			}
		}
	}
}
