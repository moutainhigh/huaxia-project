/**
 * Title: Mail.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午2:33:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.PrototypePattern;

/**
 * @class_name:Mail2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午2:33:58
 */
public class Mail implements Cloneable {
	//收件人
	private String receiver;
	//邮件标题
	private String subject;
	//称谓
	private String appellation;
	//邮件内容
	private String contxt;
	//邮件的尾部，一般都是“***版权所有"等信息
	private String tail;
	/**
	 * 
	 */
	public Mail(String subject,String contxt) {
		// TODO Auto-generated constructor stub
		this.subject = subject;
		this.contxt = contxt;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Mail mail = null;
		try{
			mail = (Mail) super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return mail;
	}
	public String getReceiver() {
		return receiver;
	}
	public String getSubject() {
		return subject;
	}
	public String getAppellation() {
		return appellation;
	}
	public String getContxt() {
		return contxt;
	}
	public String getTail() {
		return tail;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}
	public void setContxt(String contxt) {
		this.contxt = contxt;
	}
	public void setTail(String tail) {
		this.tail = tail;
	}
	@Override
	public String toString() {
		return "Mail [receiver=" + receiver + ", subject=" + subject + ", appellation=" + appellation + ", contxt="
				+ contxt + ", tail=" + tail + "]";
	}
	
}
