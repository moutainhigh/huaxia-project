/**
 * Title: BootStrapClassLoader.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��12������2:16:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ClassLoader;

/**
 * @class_name:BootStrapClassLoader2019��12��12��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��12������2:16:51
 */
public class BootStrapClassLoader {

	/**
	 * 
	 */
	public BootStrapClassLoader() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��12������2:16:51
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bootstrap:"+String.class.getClassLoader());
		System.out.println(System.getProperty("sun.boot.class.path"));
	}

}
