package Test;

import java.io.File;

public class TestDeleteFile {

	public TestDeleteFile() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * ����ɾ���ļ����ļ��ķ���
	 * @param args
	 */
	public static void main(String args[]){
//		File fd = new File("/app/opas/lowristlist/");
//		//����ļ����������ļ�,Ȼ��д���ļ�
//		File[] files = fd.listFiles();
//		if(files != null){
//			for(File f : files){
//				System.out.println(f.delete());
//			}
//		}
		
		File file = new File("/app/opas/lowristlist/");
		File[] array = file.listFiles();
		//��ȡĿ¼�µ�ÿ���ļ�
		for(int i =0;i<array.length;i++){
			if(array[i].isFile()){
				System.out.println(array[i].getName());
				System.out.println(array[i]);
				System.out.println(array[i].getPath());
			}
		}
	}
}
