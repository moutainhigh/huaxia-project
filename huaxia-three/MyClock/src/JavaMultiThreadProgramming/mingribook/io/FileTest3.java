/**
 * Title: FileTest3.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日下午3:20:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @class_name:FileTest32019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日下午3:20:19
 */
public class FileTest3 {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日下午3:20:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("word.txt");
		try{
			FileOutputStream out =  new FileOutputStream(file);
			byte buy[] = "我有一只小毛驴，我从来也不骑。".getBytes();
			out.write(buy);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			FileInputStream in = new FileInputStream(file);
			byte byt[]= new byte[1024];
			int len  = in.read(byt);
			System.out.println("文件中的信息是："+new String(byt,0,len));
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
