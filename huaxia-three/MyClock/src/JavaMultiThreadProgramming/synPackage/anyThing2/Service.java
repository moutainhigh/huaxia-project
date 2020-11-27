package JavaMultiThreadProgramming.synPackage.anyThing2;

public class Service {
	private String usernameParam;
	private String passwordParam;
	
	public void setUsernamePassword(String username,String password){
		String anySting = new String();
		try{
			synchronized(anySting){
				System.out.println("线程名字为："+Thread.currentThread().getName()+" 在"+System.currentTimeMillis()+" 进入同步块");
				usernameParam = username;
				Thread.sleep(2000);
				passwordParam = password;
				System.out.println("线程名字为："+Thread.currentThread().getName()+" 在"+System.currentTimeMillis()+" 离开同步块");
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
