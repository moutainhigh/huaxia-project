/**
 * Title: TV.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午2:30:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StatePattern;

/**
 * @class_name:TV2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午2:30:05
 */
public class TV {
	public final static Channel CCTV1 = new CCTV1();
	public final static Channel CCTV2 = new CCTV2();
	public final static Channel CCTV3 = new CCTV3();
	private Channel channel;

	
	/**
	 * 
	 */
	public TV() {
		// TODO Auto-generated constructor stub
	}


	public static Channel getCctv1() {
		return CCTV1;
	}


	public static Channel getCctv2() {
		return CCTV2;
	}


	public static Channel getCctv3() {
		return CCTV3;
	}


	public Channel getChannel() {
		return channel;
	}


	public void setChannel(Channel channel) {
		this.channel = channel;
	}


	@Override
	public String toString() {
		return "TV [channel=" + channel + "]";
	}
	/**
	 * @Title: disCCTV1
	 *@Description: TODO播放CCTV1频道
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午2:43:05
	 */
	public void disCCTV1(){
		this.setChannel(CCTV1);
		this.channel.display();
	}
	/**
	 * @Title: disCCTV2
	 *@Description: TODO 播放CCTV2频道
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午2:43:31
	 */
	public void disCCTV2(){
		this.setChannel(CCTV2);
		this.channel.display();
	}
	/**
	 * @Title: disCCTV3
	 *@Description: TODO 播放CCTV3频道
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午2:43:39
	 */
	public void disCCTV3(){
		this.setChannel(CCTV3);
		this.channel.display();
	}
}
