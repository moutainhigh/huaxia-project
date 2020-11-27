package projectExample;
/**
 * 线程的同步
 * @author liuwei
 */
public class SyschronizedThead {
	public static void main(String args[]){
		for(int i=0;i<100;i++){
//			System.out.println(Thread.currentThread().getName()+" "+i);
			if(i == 20){
				myThread st = new myThread();
				new Thread(st,"新线程1").start();
				new Thread(st,"新线程2").start();
				new Thread(st,"新线程3").start();
			}
		}
	}
}
class myThread implements Runnable{
	 String i= "0";
	int j=0;
	@Override
	public void run(){
		while(j<100000){
			//锁住的是变量，外部不能更改变量，但是代码块可以执行
			//this锁住的是 代码块不能被重复执行。
			synchronized(this){
				int j2 = Integer.parseInt(i);
				j2++;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i=String.valueOf(j2);
				System.out.println(Thread.currentThread().getName()+" "+i);
			};
//				System.out.println(Thread.currentThread().getName()+" j= "+j);
			j++;
		}
		
	}
}