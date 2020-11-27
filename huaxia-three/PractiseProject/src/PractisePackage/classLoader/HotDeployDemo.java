/**
 * Title: TestClient.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月6日下午4:32:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.classLoader;

/**
 * @class_name:TestClient2020年8月6日
 * @comments:根据classLoader获取类的实例，动态加载
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午4:32:47
 */
public class HotDeployDemo {
	private static volatile IHelloService helloService;
	/**
	 * Constructor
	 */
	public HotDeployDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午4:32:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		IHelloService helloService = new HotDeployDemo().createHelloService();
//		helloService.sayHello();
		client2();
	}
	/**
	 * @Title: getHelloService
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午4:48:01
	 */
	public  IHelloService getHelloService(){
		if(helloService != null){
			return helloService;
		}
		synchronized(HotDeployDemo.class){
			if(helloService == null){
				helloService = createHelloService();
			}
			return helloService;
		}
	}
	/**
	 * @Title: createHelloService
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午4:47:55
	 */
	public  IHelloService createHelloService(){
		try{
			ClassLoader current = getClass().getClassLoader();
			Class<?> cls = current.loadClass("PractisePackage.classLoader.IHelloServiceImpl");
			if(cls != null){
				return (IHelloService)cls.newInstance();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @Title: client
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午4:54:43
	 */
	public static void client2(){
		Thread t = new Thread(){
			@Override
			public void run(){
				try{
					while(true){
						Thread.sleep(100);
						IHelloService helloService = new HotDeployDemo().createHelloService();
						helloService.sayHello();
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
}
