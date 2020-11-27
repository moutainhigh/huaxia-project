/**
 * Title: CCTV1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午2:31:10
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StatePattern;

/**
 * @class_name:CCTV12020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午2:31:10
 */
public class CCTV1 implements Channel {
	
	/**
	 * 
	 */
	public CCTV1() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StatePattern.Channel#display()
	 */
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("CCTV 1 新闻联播");
	}

}
