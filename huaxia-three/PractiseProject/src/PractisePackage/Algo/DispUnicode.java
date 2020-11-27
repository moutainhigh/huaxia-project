/**
 * Title: DispUnicode.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日上午10:36:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:DispUnicode2020年8月10日
 * @comments:打印出字符串中汉字的unicode编码，不是汉字的符号自动跳过
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日上午10:36:53
 */
public class DispUnicode {

	/**
	 * Constructor
	 */
	public DispUnicode() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午10:36:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		queryCoding("和谐包容，智慧诚信，务实创新。");
	}
	/**
	 * @Title: queryCoding
	 *@Description: TODO 打印出字符串中汉字的unicode编码，不是汉字的符号自动跳过
	 *@param sArg
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午10:37:22
	 */
	private static  void queryCoding(String sArg){
		System.out.println("用户传递的字符串是：");
		System.out.println(sArg);
		System.out.println("计算得到汉字的unicode编码是：");
		for(int i = 0;i<sArg.length();i++){
			char ch = sArg.charAt(i);
			if(ch<19968 || ch>40869) 
				continue;
			System.out.print((int)ch+" ");
		}
	}
}
