/**
 * Title: DispUnicode.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��10������10:36:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:DispUnicode2020��8��10��
 * @comments:��ӡ���ַ����к��ֵ�unicode���룬���Ǻ��ֵķ����Զ�����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��10������10:36:53
 */
public class DispUnicode {

	/**
	 * Constructor
	 */
	public DispUnicode() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��10������10:36:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		queryCoding("��г���ݣ��ǻ۳��ţ���ʵ���¡�");
	}
	/**
	 * @Title: queryCoding
	 *@Description: TODO ��ӡ���ַ����к��ֵ�unicode���룬���Ǻ��ֵķ����Զ�����
	 *@param sArg
	 *@author: LiuWei
	 *@Date: 2020��8��10������10:37:22
	 */
	private static  void queryCoding(String sArg){
		System.out.println("�û����ݵ��ַ����ǣ�");
		System.out.println(sArg);
		System.out.println("����õ����ֵ�unicode�����ǣ�");
		for(int i = 0;i<sArg.length();i++){
			char ch = sArg.charAt(i);
			if(ch<19968 || ch>40869) 
				continue;
			System.out.print((int)ch+" ");
		}
	}
}
