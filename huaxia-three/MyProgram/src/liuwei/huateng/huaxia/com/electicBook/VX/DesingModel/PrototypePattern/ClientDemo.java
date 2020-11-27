/**
 * Title: ClientDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������2:39:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.PrototypePattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @class_name:ClientDemo2020��1��6��
 * @comments:ԭ��ģʽ��clone���󣬱��⹹�캯����������ʵ��Cloneable�ӿڷ�������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������2:39:26
 */
public class ClientDemo {
	private static int MAX_COUNT = 6;
	/**
	 * 
	 */
	public ClientDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��6������2:39:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=0;
		Mail mail = new Mail("ĳ�̳���һ�齱�","��һ�齱�֪ͨ��������");
		mail.setTail("����Ȩ��ĳ�̳�����");
		List<Mail> mailList = new ArrayList<Mail>();
		mailList.add(mail);
		while(i<MAX_COUNT){
			try {
				Mail cloneMail =(Mail) mail.clone();
				mailList.add(cloneMail);
				cloneMail.setAppellation(getRandString(5)+" ������Ůʿ)");
				cloneMail.setReceiver(getRandString(5)+"@"+getRandString(8)+".com");
				sendMail(cloneMail);
				i++;
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j=0;j<mailList.size();j++){
			System.out.println(mailList.get(j));
		}
	}
	/**
	 * @Title: sendMail
	 *@Description: TODO
	 *@param mail
	 *@author: LiuWei
	 *@Date: 2020��1��6������2:41:14
	 */
	public static void sendMail(Mail mail){
		System.out.println("����:"+mail.getSubject()+"\t�ռ��ˣ�"+mail.getReceiver()+"\t....���ͳɹ���");
	}
	/**
	 * @Title: getRandString
	 *@Description: TODO
	 *@param maxLength
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��6������2:41:44
	 */
	public static String getRandString(int maxLength){
		String source = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
		StringBuffer sb= new StringBuffer();
		Random rand = new Random();
		for(int i=0;i<maxLength;i++){
			sb.append(source.charAt(rand.nextInt(source.length())));
		}
		return sb.toString();
	}
}
