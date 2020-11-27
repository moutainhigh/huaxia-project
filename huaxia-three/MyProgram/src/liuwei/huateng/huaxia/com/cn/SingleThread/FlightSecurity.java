/**
 * Title: FlightSecurity.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月13日上午9:07:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.SingleThread;

/**
 * @class_name:FlightSecurity2019年12月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月13日上午9:07:27
 */
public class FlightSecurity {
	private int count =0;
	//登机牌
	private String boardingPass =  "null";
	//身份证
	private String idCard = "null";
	public void pass(String boardingPass,String idCard)
	{
		this.boardingPass = boardingPass;
		this.idCard = idCard;
		this.count++;
		check();
	}
	private void check(){
		if(boardingPass.charAt(0)!= idCard.charAt(0)){
			throw new RuntimeException("====Exception========="+toString());
		}else{
			System.out.println("符合要求");
		}
	}
	
	@Override
	public String toString() {
		return "FlightSecurity [count=" + count + ", boardingPass=" + boardingPass + ", idCard=" + idCard + "]";
	}
	/**
	 * 
	 */
	public FlightSecurity() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月13日上午9:07:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
