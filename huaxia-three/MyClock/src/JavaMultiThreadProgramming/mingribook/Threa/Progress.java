/**
 * Title: Progress.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月18日下午1:46:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.Threa;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 * @class_name:Progress2019年10月18日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月18日下午1:46:15
 */
public class Progress extends Thread {
	private final int[] progressValue= {6,18,27,39,51,66,81,100};
	private JProgressBar progressBar;
	private JButton button;
	/**
	 * 
	 * @param progressBar
	 * @param button
	 */
	public Progress(JProgressBar progressBar,JButton button){
		this.progressBar = progressBar;
		this.button = button;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月18日下午1:46:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("进度条");
		JProgressBar progressBar = new JProgressBar();
		JButton button = new JButton("确定");
		frame.add(progressBar,BorderLayout.CENTER);
		frame.add(button,BorderLayout.SOUTH);
		frame.setSize(200,300);
		frame.setVisible(true);
		progressBar.setStringPainted(true);
		progressBar.setIndeterminate(true);
		progressBar.setString("升级进行中。。。。");
		new Progress(progressBar,button).start();
	}
	@Override
	public void run(){
		for(int i=0;i<progressValue.length;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			progressBar.setValue(progressValue[i]);
		}
		progressBar.setIndeterminate(false);
		progressBar.setString("升级完成！");
		progressBar.setEnabled(true);
	}
}
