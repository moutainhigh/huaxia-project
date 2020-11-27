package Test;

import java.io.File;

public class TestDeleteFile {

	public TestDeleteFile() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 测试删除文件夹文件的方法
	 * @param args
	 */
	public static void main(String args[]){
//		File fd = new File("/app/opas/lowristlist/");
//		//清空文件夹下所有文件,然后写入文件
//		File[] files = fd.listFiles();
//		if(files != null){
//			for(File f : files){
//				System.out.println(f.delete());
//			}
//		}
		
		File file = new File("/app/opas/lowristlist/");
		File[] array = file.listFiles();
		//获取目录下的每个文件
		for(int i =0;i<array.length;i++){
			if(array[i].isFile()){
				System.out.println(array[i].getName());
				System.out.println(array[i]);
				System.out.println(array[i].getPath());
			}
		}
	}
}
