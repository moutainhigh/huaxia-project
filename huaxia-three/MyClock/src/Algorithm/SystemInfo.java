package Algorithm;
/**
 * 获取程序运行环境的信息
 * @author Liuwei
 * 運行結果运行结果
用户的账户名称:User
当前用户工作目录:D:\eclipse_workPlace\MyClock
当前用户的home路径:C:\Users\User
类所在的路径:D:\eclipse_workPlace\MyClock\build\classes;C:\apache-tomcat\apache-tomcat-7.0.92\lib\annotations-api.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\catalina-ant.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\catalina-ha.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\catalina-tribes.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\catalina.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\ecj-4.4.2.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\el-api.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\jasper-el.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\jasper.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\jsp-api.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\servlet-api.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\tomcat-api.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\tomcat-coyote.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\tomcat-dbcp.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\tomcat-i18n-es.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\tomcat-i18n-fr.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\tomcat-i18n-ja.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\tomcat-i18n-ru.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\tomcat-jdbc.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\tomcat-util.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\tomcat7-websocket.jar;C:\apache-tomcat\apache-tomcat-7.0.92\lib\websocket-api.jar
操作系统名称:Windows 8
操作系统版本:6.2
操作系统的架构:amd64
Java的安裝路徑:C:\Program Files\Java\jre7
 */
public class SystemInfo {
	public static void main(String args[]){
		System.out.println("用户的账户名称:"+System.getProperty("user.name"));
		System.out.println("当前用户工作目录:"+System.getProperty("user.dir"));
		System.out.println("当前用户的home路径:"+System.getProperty("user.home"));
		System.out.println("类所在的路径:"+System.getProperty("java.class.path"));
		System.out.println("操作系统名称:"+System.getProperty("os.name"));
		System.out.println("操作系统版本:"+System.getProperty("os.version"));
		System.out.println("操作系统的架构:"+System.getProperty("os.arch"));
		System.out.println("Java的安裝路徑:"+System.getProperty("java.home"));
	}
}
