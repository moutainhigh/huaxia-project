/**
 * Title: Mail.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������2:33:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.PrototypePattern;

/**
 * @class_name:Mail2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������2:33:58
 */
public class Mail implements Cloneable {
	//�ռ���
	private String receiver;
	//�ʼ�����
	private String subject;
	//��ν
	private String appellation;
	//�ʼ�����
	private String contxt;
	//�ʼ���β����һ�㶼�ǡ�***��Ȩ����"����Ϣ
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
