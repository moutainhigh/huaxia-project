/**
 * Title: FileClass_1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日上午11:28:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

import java.io.File;

/**
 * @class_name:FileClass_12020年9月23日
 * @comments:
 * @param:文件的常用方法
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午11:28:18
 */
public class FileClass_1 {

	/**
	 * Constructor
	 */
	public FileClass_1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午11:28:18
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f =new File("D:\\abc","User.java");
		FileClass_1.fileInfo(f);
	}
	/**
	 * @Title: fileInfo
	 *@Description: TODO
	 *@param f
	 *@author: LiuWei
	 *@Date: 2020年9月23日下午2:07:20
	 */
	public static void fileInfo(File f){
		System.out.println("文件名："+f.getName());
		System.out.println("路径："+f.getPath());
		System.out.println("父路径："+f.getParent());
		System.out.println("绝对路径："+f.getAbsolutePath());
		if(f.exists()){
			System.out.println("文件存在");
			System.out.println("是目录？"+f.isDirectory());
			System.out.println("可读："+f.canRead());
			System.out.println("可写："+f.canWrite());
			System.out.println("文件的长度："+f.length());
			System.out.println("文件最后被修改的日期："+f.lastModified());
			if(f.delete()){
				System.out.println(f.exists()?"删除失败":"删除成功");
			}
		}
	}
}
