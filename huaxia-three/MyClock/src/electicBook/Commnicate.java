package electicBook;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
public class Commnicate extends JFrame {
	private static final long serialVersionUID = 1L;
	Thread t1;
	Thread t2;
	private int count = 0;
	final JProgressBar progressBar = new JProgressBar();
	public static  void main(String args[]){
		init(new Commnicate(),150,100);
	}
	public Commnicate(){
		super();
		progressBar.setStringPainted(true);
		this.getContentPane().add(progressBar,BorderLayout.NORTH);
		deValue();
		addValue();
		t1.start();
		t2.start();
	}
	public void addValue(){
		t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
//					if(count>100){
//						 System.out.println("线程条已经满了，递增线程等待");
//						 break;
//					}
					if(count == 0){
						progressBar.setValue(count+=100);
						System.out.println("add进度条当前值为："+count);
						synchronized(t2){
							System.out.println("进度条当前已经有值，可以进行递减操作");
							t2.notify();
						}
					}
					try{
						Thread.sleep(10);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}		
		});
	}
	public void deValue(){
		t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
				// TODO Auto-generated method stub
			   if(count ==0){
				   synchronized(this){
					   try{
						   this.wait();
					   }catch(Exception e){
						   e.printStackTrace();
					   }
				   }
			   }else{
				   count--;
				   progressBar.setValue(count);
				   System.out.println("de进度条的当前值为："+count);
			   }
			   try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			}
		});
	}
	public static void init(JFrame frame,int width,int height){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width,height);
		frame.setVisible(true);
	}
}
