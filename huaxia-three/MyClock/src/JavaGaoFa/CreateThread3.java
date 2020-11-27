package JavaGaoFa;
/***
 * 继承runnable接口
 * @author liuwei
 *
 */
public class CreateThread3 implements Runnable{
	public static void main(String args[]){
		Thread t1 = new Thread(new CreateThread3());
		t1.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Oh,I am Runnable");
	}
}
