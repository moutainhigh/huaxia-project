/**
 * Title: TestClient.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��6������4:32:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.classLoader;

/**
 * @class_name:TestClient2020��8��6��
 * @comments:����classLoader��ȡ���ʵ������̬����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������4:32:47
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
	 *@Date: 2020��8��6������4:32:47
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
	 *@Date: 2020��8��6������4:48:01
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
	 *@Date: 2020��8��6������4:47:55
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
	 *@Date: 2020��8��6������4:54:43
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
