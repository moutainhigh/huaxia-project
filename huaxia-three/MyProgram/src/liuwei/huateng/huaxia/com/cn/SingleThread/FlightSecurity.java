/**
 * Title: FlightSecurity.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��13������9:07:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.SingleThread;

/**
 * @class_name:FlightSecurity2019��12��13��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��13������9:07:27
 */
public class FlightSecurity {
	private int count =0;
	//�ǻ���
	private String boardingPass =  "null";
	//���֤
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
			System.out.println("����Ҫ��");
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
	 *@Date: 2019��12��13������9:07:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
