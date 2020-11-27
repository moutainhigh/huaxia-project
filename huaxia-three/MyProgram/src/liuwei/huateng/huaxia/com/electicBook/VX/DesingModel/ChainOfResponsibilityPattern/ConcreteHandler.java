/**
 * Title: ConcreteHandler.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������9:56:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern;

/**
 * @class_name:ConcreteHandler2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������9:56:26
 */
public class ConcreteHandler extends Handler {

	/**
	 * 
	 */
	public ConcreteHandler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern.Handler#handleRequest()
	 */
	@Override
	public void handleRequest() {
		// TODO Auto-generated method stub
		if(getSuccessor() != null){
			System.out.println("���󴫵ݸ���"+getSuccessor())	;
			getSuccessor().handleRequest();
		}else{
			System.out.println("������");
		}
	}

}
