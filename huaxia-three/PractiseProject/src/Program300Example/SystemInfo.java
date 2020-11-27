/**
 * Title: SystemInfo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月21日下午2:49:34
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:SystemInfo2020年9月21日
 * @comments:获取程序运行环境信息
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月21日下午2:49:34
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
	 *@Date: 2020年9月21日下午2:49:34
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("用户的账户名称："+System.getProperty("user.name"));
		System.out.println("当前用户工作目录："+System.getProperty("user.dir"));
		System.out.println("用户的home路径："+System.getProperty("user.home"));
		System.out.println("类所在的路径："+System.getProperty("user.class.path"));
		System.out.println("操作系统的名称："+System.getProperty("os.name"));
		System.out.println("操作系统版本："+System.getProperty("os.version"));
		System.out.println("操作系统架构："+System.getProperty("os.arch"));
		System.out.println("虚拟机实现的版本："+System.getProperty("java.vm.version"));
		System.out.println("虚拟机实现的生产商："+System.getProperty("java.vm.vendor"));
		System.out.println("默认的临时文件路径："+System.getProperty("java.io.tmpdir"));
		System.out.println("运行环境规范的名称："+System.getProperty("java.specification.name"));
		System.out.println("Java类格式化的版本："+System.getProperty("java.class.version"));
		System.out.println("java运行化的版本："+System.getProperty("java.version"));
		System.out.println("java的安装路径："+System.getProperty("java.home"));
	}
}
