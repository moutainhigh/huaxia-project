package PractisePackage;
/**
 * @class_name:RunFinalize2020��8��6��
 * @comments:����finalize���������󱻻���֮�����ִ�еķ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������10:31:33
 */
public class RunFinalize {
	protected void finalize() throws Throwable{
		System.out.println("����finalize����");
		super.finalize();
	}
	public RunFinalize() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunFinalize runFinalize = new RunFinalize();
		runFinalize = null;
		for(int i=0;i<10;i++){
			System.gc();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
