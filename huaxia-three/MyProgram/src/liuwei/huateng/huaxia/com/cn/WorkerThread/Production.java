/**
 * Title: Production.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��16������4:13:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.WorkerThread;

/**
 * @class_name:Production2019��12��16��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��16������4:13:13
 */
public class Production extends InstructionBook {
	//��Ʒ���
	private  int prodID;
	/**
	 * 
	 */
	public Production(int prodID) {
		// TODO Auto-generated constructor stub
		this.prodID = prodID;
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.cn.WorkerThread.InstructionBook#firstProcess()
	 */
	@Override
	protected void firstProcess() {
		// TODO Auto-generated method stub
		System.out.println("execute the "+prodID+" first process");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.cn.WorkerThread.InstructionBook#secondProcess()
	 */
	@Override
	protected void secondProcess() {
		// TODO Auto-generated method stub
		System.out.println("execute the "+prodID+" second process");
	}
}
