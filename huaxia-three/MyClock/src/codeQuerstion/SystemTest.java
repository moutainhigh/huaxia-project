package codeQuerstion;
import java.util.Map;
/**
 * 系统相关的类System类
 * @author liuwei
 *
 */
public class SystemTest {
	public static void main(String args[]){
		//获取系统所有的环境变量
		Map<String,String> env= System.getenv();
		for(String name :env.keySet()){
			System.out.println(name+"---->"+env.get(name));
		}
		//获取制定环境变量的值
		System.out.println(System.getenv("JAVA_HOME"));
	}
}
