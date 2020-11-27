/**
 * Title: ConcreteHandler.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日上午9:56:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern;

/**
 * @class_name:ConcreteHandler2020年1月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日上午9:56:26
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
			System.out.println("请求传递给："+getSuccessor())	;
			getSuccessor().handleRequest();
		}else{
			System.out.println("请求处理");
		}
	}

}
