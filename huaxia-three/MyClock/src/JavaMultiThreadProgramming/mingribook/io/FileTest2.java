/**
 * Title: FileTest2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日下午3:12:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.io;

import java.io.File;

/**
 * @class_name:FileTest22019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日下午3:12:26
 */
public class FileTest2 {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日下午3:12:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("word.txt");
		if(file.exists()){
			String name = file.getName();
			long length = file.length();
			boolean  hidden = file.isHidden();//文件是否为隐藏文件
			System.out.println("文件名称"+name);
			System.out.println("文件长度"+length);
			System.out.println("改文件是隐藏文件么？"+hidden);
		}else{
			System.out.println("该文件不存在");
		}
	}
}
