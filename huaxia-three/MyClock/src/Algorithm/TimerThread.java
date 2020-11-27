//package Algorithm;
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;
///**
// * 任务定时器
// * @author liuwei
//结果
//开始执行我的第1个任务，执行时间为2019-5-21 18:04:14
//开始执行我的第2个任务，执行时间为2019-5-21 18:04:14
//开始执行我的第3个任务，执行时间为2019-5-21 18:04:15
//开始执行我的第2个任务，执行时间为2019-5-21 18:04:15
//开始执行我的第2个任务，执行时间为2019-5-21 18:04:16
//任务定时器已经被取消
// */
//public class TimerThread {
//	public static void main(String args[]){
//		Timer timer = new Timer();
//		TimerTask tt1 = new MyTask(1);
//		timer.schedule(tt1, 200);
//		TimerTask tt2 =new MyTask(2);
//		timer.schedule(tt2, 500,1000);
//		TimerTask tt3 = new MyTask(3);
//		Date date = new Date(System.currentTimeMillis()+1000);
//		timer.schedule(tt3, date);
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		timer.cancel();
//		System.out.println("任务定时器已经被取消");
//	}
//}
//class MyTask extends TimerTask{
//	private int taskId = 0;
//	public MyTask(int id){
//		this.taskId = id;
//	}
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		System.out.println("开始执行我的第"+this.taskId+"个任务，执行时间为"+new Date().toLocaleString());
//	}
//}