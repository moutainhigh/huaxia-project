package JavaMultiThreadProgramming.ThreadSafe.safe;
/**
 * 访问同一个对象中的同步方法是线程安全。synchronized是线程安全的
 * @author liuwei
 *
 */
public class HasSelfPrivateNum {
	private int num =0;
	synchronized public void addI(String username){
		try{
			if(username.equals("a")){
				num =100;
				System.out.println("a set over!");
				Thread.sleep(2000);
			}else{
				num = 200;
				System.out.println("b set over!");
			}
			System.out.println(username +" num="+num);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
