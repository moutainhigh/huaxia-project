package JavaGaoFa;
/**
 * 使用stop方法终止线程导致的线程数据不一致
 * @author liuwei
 *
 */
public class StopThreadUnsafe {
	public static User u = new User();
	public static class User{
		private int id;
		private String name;
		public User(){
			id =0;
			name = "0";
		}
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public void setId(int id) {
			this.id = id;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		}
	}
	public static class ChangeObjectThread extends Thread{
		@Override
		public void run(){
			while(true){
				synchronized(u){
					int v = (int)(System.currentTimeMillis()/1000);
					u.setId(v);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					u.setName(String.valueOf(v));
				}
				Thread.yield();
			}
		}
	}
	public static void main(String args[]){
		new ReadObjectThread().start();
		while(true){
			ChangeObjectThread2 t= new ChangeObjectThread2();
				t.start();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.stopMe();
		}
	}
	public static class ChangeObjectThread2 extends Thread{
		volatile boolean stopme = false;
		public void stopMe(){
			stopme = true;
		}
		@Override
		public void run(){
			while(true){
				if(stopme){
					System.out.println("exit by stop me");
					break;
				}
				synchronized(u){
					int v = (int)(System.currentTimeMillis()/1000);
					u.setId(v);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					u.setName(String.valueOf(v));
				}
				Thread.yield();
			}
		}
	}
	public static class ReadObjectThread extends Thread{
		@Override
		public void run(){
			while(true){
				synchronized(u){
					if(u.getId() != Integer.parseInt(u.getName()))
						System.out.println(u.toString());
				}
				Thread.yield();
			}
		}
	}
}
