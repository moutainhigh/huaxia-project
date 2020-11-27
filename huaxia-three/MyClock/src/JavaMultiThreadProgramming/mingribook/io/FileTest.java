/**
 * Title: FileTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日下午3:07:59
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.io;

import java.io.File;

/**
 * @class_name:FileTest2019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日下午3:07:59
 */
public class FileTest {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日下午3:07:59
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("word.txt");
		if(file.exists()){
			file.delete();
			System.out.println("文件已删除");
		}else{
			try{
				file.createNewFile();
				System.out.println("文件已经创建");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
