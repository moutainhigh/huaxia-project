/**
 * Title: BuilderString.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日下午5:29:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:BuilderString2020年9月18日
 * @comments: StringBuilder最快线程不安全，使用StringBuffer也快，线程安全
 * String追加字符3万个。
用时：363.4337毫秒
StringBuilder追加字符3万个。
用时：0.9322毫秒
StringBuffer追加字符3万个。
用时：1.7824毫
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日下午5:29:13
 */
public class BuilderString {
	
	/**
	 * Constructor
	 */
	public BuilderString() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日下午5:29:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String appendStr = "";
		long startTime = System.nanoTime();
		for(int i= 20000;i<50000;i++){
			appendStr += (char)i;
		}
		long endTime =  System.nanoTime();
		System.out.println("String追加字符3万个。");
		System.out.println("用时："+(endTime-startTime)/1000000d+"毫秒");
		StringBuilder strBUilder = new StringBuilder();
		startTime = System.nanoTime();
		for(int i= 20000;i<50000;i++){
			strBUilder.append((char)i);
		}
		endTime = System.nanoTime();
		System.out.println("StringBuilder追加字符3万个。");
		System.out.println("用时："+(endTime-startTime)/1000000d+"毫秒");
		StringBuffer strBuffer = new StringBuffer();
		startTime = System.nanoTime();
		for(int i= 20000;i<50000;i++){
			strBuffer.append((char)i);
		}
		endTime = System.nanoTime();
		System.out.println("StringBuffer追加字符3万个。");
		System.out.println("用时："+(endTime-startTime)/1000000d+"毫秒");
	}
}
