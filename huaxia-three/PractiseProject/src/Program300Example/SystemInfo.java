/**
 * Title: SystemInfo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��21������2:49:34
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:SystemInfo2020��9��21��
 * @comments:��ȡ�������л�����Ϣ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��21������2:49:34
 */
public class SystemInfo {

	/**
	 * Constructor
	 */
	public SystemInfo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��21������2:49:34
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("�û����˻����ƣ�"+System.getProperty("user.name"));
		System.out.println("��ǰ�û�����Ŀ¼��"+System.getProperty("user.dir"));
		System.out.println("�û���home·����"+System.getProperty("user.home"));
		System.out.println("�����ڵ�·����"+System.getProperty("user.class.path"));
		System.out.println("����ϵͳ�����ƣ�"+System.getProperty("os.name"));
		System.out.println("����ϵͳ�汾��"+System.getProperty("os.version"));
		System.out.println("����ϵͳ�ܹ���"+System.getProperty("os.arch"));
		System.out.println("�����ʵ�ֵİ汾��"+System.getProperty("java.vm.version"));
		System.out.println("�����ʵ�ֵ������̣�"+System.getProperty("java.vm.vendor"));
		System.out.println("Ĭ�ϵ���ʱ�ļ�·����"+System.getProperty("java.io.tmpdir"));
		System.out.println("���л����淶�����ƣ�"+System.getProperty("java.specification.name"));
		System.out.println("Java���ʽ���İ汾��"+System.getProperty("java.class.version"));
		System.out.println("java���л��İ汾��"+System.getProperty("java.version"));
		System.out.println("java�İ�װ·����"+System.getProperty("java.home"));
	}
}
