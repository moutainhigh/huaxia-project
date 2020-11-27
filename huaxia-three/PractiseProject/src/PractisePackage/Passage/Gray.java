/**
 * Title: Gray.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日下午2:18:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Gray2020年8月18日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日下午2:18:38
 */
public class Gray {

	/**
	 * Constructor
	 */
	public Gray() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月18日下午2:18:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String[] arr = getGray(4);
		 for(int i=0;i<arr.length;i++){
			 System.out.print(arr[i]+" ");
		 }
		 System.out.println();
	}
	/**
	 * @Title: getGray
	 *@Description: TODO
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月18日下午2:19:33
	 */
	public static String[] getGray(int n){
		String[] graycode = new String[(int)Math.pow(2,n)];
		if(n == 1){
			graycode[0]  = "0";
			graycode[1]  = "1";
			return graycode;
		}
		String[] last = getGray(n-1);
		for(int i=0;i<last.length;i++){
			graycode[i] = "0" + last[i];
			graycode[graycode.length-i-1] = "1"+last[i];
		}
		return graycode;
	}
}
