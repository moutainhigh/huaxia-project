package projectExample;
import java.io.IOException;
/**
 *启动一些进程来运行操作系统指令
 *使用cmd命令开启记事本
 * @author Liuwei
 */
public class ExecTest {
	public static void main(String args[]){
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("notepad.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
