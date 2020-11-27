/**
 * Title: TextDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��22������3:33:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:TextDemo2020��9��22��
 * @comments:
 *  run result ���ֽ���Thread1  ��ʼ����2000����
���ֽ���Thread2  ��ʼ����100����
���ֽ���Thread3  ��ʼ����300000����
���ֽ���Thread2  ��ʼ����100�������
���ֽ���Thread1  ��ʼ����2000�������
 * @param: ���̵߳�����
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������3:33:31
 */
public class TextDemo {

	/**
	 * Constructor
	 */
	public TextDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��22������3:33:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadSubName t1 = new ThreadSubName("Thread1",2000);
		ThreadSubName t2 = new ThreadSubName("Thread2",100);
		ThreadSubName t3 = new ThreadSubName("Thread3",300000);
		t1.start();
		t2.start();
		t3.start();
	}

}
/**
 * 
 * @class_name:ThreadSubName2020��9��22��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������3:36:06
 */
class ThreadSubName extends Thread{
	private String threadName;
	private int Ms;
	public ThreadSubName(String name,int ms){
		this.threadName =name;
		this.Ms = ms;
	}
	/**
	 * ������дrun����
	 */
	@Override
	public void run(){
		System.out.println("���ֽ���"+threadName+"  ��ʼ����"+Ms+"����");
		try {
			sleep(Ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The Thread is Wrong")	;
		}
		System.out.println("���ֽ���"+threadName+"  ��ʼ����"+Ms+"�������");
	}
}