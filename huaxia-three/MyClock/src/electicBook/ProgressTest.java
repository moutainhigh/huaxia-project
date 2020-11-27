package electicBook;
import javax.swing.ProgressMonitor;
/**
 * 测试进度条的使用
 * @author Liuwei
 */
public class ProgressTest {
final static ProgressMonitor progressMonitor = new ProgressMonitor(null,"正在计算各项经费","",0,100);
	public static void main(String args[]){
		new Thread(new  Runnable(){
			public void run(){
				for(int i=0;i<=100 && !progressMonitor.isCanceled() ;i++){
					progressMonitor.setProgress(i);
					progressMonitor.setNote(String.format("已经计算%d%%...", i));
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				progressMonitor.close();
			}
		}).start();
	}
}
