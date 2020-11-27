/**
 * Title: FileClass_1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��23������11:28:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

import java.io.File;

/**
 * @class_name:FileClass_12020��9��23��
 * @comments:
 * @param:�ļ��ĳ��÷���
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��23������11:28:18
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
	 *@Date: 2020��9��23������11:28:18
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
	 *@Date: 2020��9��23������2:07:20
	 */
	public static void fileInfo(File f){
		System.out.println("�ļ�����"+f.getName());
		System.out.println("·����"+f.getPath());
		System.out.println("��·����"+f.getParent());
		System.out.println("����·����"+f.getAbsolutePath());
		if(f.exists()){
			System.out.println("�ļ�����");
			System.out.println("��Ŀ¼��"+f.isDirectory());
			System.out.println("�ɶ���"+f.canRead());
			System.out.println("��д��"+f.canWrite());
			System.out.println("�ļ��ĳ��ȣ�"+f.length());
			System.out.println("�ļ�����޸ĵ����ڣ�"+f.lastModified());
			if(f.delete()){
				System.out.println(f.exists()?"ɾ��ʧ��":"ɾ���ɹ�");
			}
		}
	}
}
