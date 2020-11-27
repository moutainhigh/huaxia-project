/**
 * Title: Production.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月16日下午4:13:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.WorkerThread;

/**
 * @class_name:Production2019年12月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月16日下午4:13:13
 */
public class Production extends InstructionBook {
	//产品编号
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
