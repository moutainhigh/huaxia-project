/**
 * Title: MyZip.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日下午4:32:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @class_name:MyZip2019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日下午4:32:06
 */
public class MyZip {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日下午4:32:06
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyZip book = new MyZip();
		try{
			book.zip("D:/hello/hello.zip", new File("D:/hello/hello"));
			System.out.println("压缩完成");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	 * @Title: zip
	 *@Description: TODO
	 *@param zipFileName
	 *@param inputFile
	 *@throws Exception
	 *@author: LiuWei
	 *@Date: 2019年10月15日下午4:52:16
	 */
	public void zip(String zipFileName,File inputFile)throws Exception{
		ZipOutputStream  out = new ZipOutputStream(new FileOutputStream(zipFileName));
		zip(out,inputFile,"");
		System.out.println("压缩中。。。");
		out.close();
	}
	/**
	 * @Title: zip
	 *@Description: TODO
	 *@param out
	 *@param f
	 *@param base
	 *@throws Exception
	 *@author: LiuWei
	 *@Date: 2019年10月15日下午4:52:23
	 */
	private void zip(ZipOutputStream out,File f,String base) throws Exception{
		if(f.isDirectory()){	   //判断是否是文件夹
			File[] fl = f.listFiles();
			if(base.length() !=0){
				out.putNextEntry(new ZipEntry(base+"/"));
			}
			for(int i=0;i<fl.length;i++){
				zip(out,fl[i],base+fl[i]);
			}
		}else{
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b ;
			System.out.println(base);
			while((b=in.read())!=-1){
				out.write(b);
			}
			in.close();
		}
	}
}
