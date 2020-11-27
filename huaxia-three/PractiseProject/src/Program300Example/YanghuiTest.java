/**
 * Title: YanghuiTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日下午3:39:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:YanghuiTest2020年9月18日
 * @comments:杨辉三角形
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日下午3:39:07
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
	 *@Date: 2020年9月18日下午3:39:07
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
