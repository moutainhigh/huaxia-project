/**
 * Title: YanghuiTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������3:39:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:YanghuiTest2020��9��18��
 * @comments:���������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������3:39:07
 */
public class YanghuiTest {

	/**
	 * Constructor
	 */
	public YanghuiTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��18������3:39:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int triangle[][] = new int[10][];
		for(int i =0;i<triangle.length;i++){
			triangle[i] = new int[i+1];
			for(int j=0;j<=i;j++){
				if(i==0 ||j ==0 ||j ==i){
					triangle[i][j] = 1;
				}else{
					triangle[i][j] = triangle[i-1][j]+triangle[i-1][j-1];
				}
				System.out.print(triangle[i][j]+"\t");
			}
			System.out.println();
		}
	}

}
