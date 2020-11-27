/**
 * 
 */
package JavaMultiThreadProgramming.mingribook.javaStruct;

/**跳出带Loop标签的循环体
 * @author liuwei
 *
 */
public class BreakOutsideNested {
	

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月11日上午10:49:52
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Loop:for(int i=0;i<3;i++){
			for(int j=0;j<6;j++){
				if(j==4){
					break Loop;
				}
				System.out.println("i="+i+" j="+j);
			}
		}
	}
}
