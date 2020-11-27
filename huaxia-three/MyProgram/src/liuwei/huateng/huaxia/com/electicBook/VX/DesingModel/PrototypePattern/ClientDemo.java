/**
 * Title: ClientDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午2:39:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.PrototypePattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @class_name:ClientDemo2020年1月6日
 * @comments:原型模式，clone对象，避免构造函数创建对象。实现Cloneable接口方法即可
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午2:39:26
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
	 *@Date: 2020年1月6日下午2:39:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=0;
		Mail mail = new Mail("某商场五一抽奖活动","五一抽奖活动通知。。。。");
		mail.setTail("解释权归某商场所有");
		List<Mail> mailList = new ArrayList<Mail>();
		mailList.add(mail);
		while(i<MAX_COUNT){
			try {
				Mail cloneMail =(Mail) mail.clone();
				mailList.add(cloneMail);
				cloneMail.setAppellation(getRandString(5)+" 先生（女士)");
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
	 *@Date: 2020年1月6日下午2:41:14
	 */
	public static void sendMail(Mail mail){
		System.out.println("标题:"+mail.getSubject()+"\t收件人："+mail.getReceiver()+"\t....发送成功！");
	}
	/**
	 * @Title: getRandString
	 *@Description: TODO
	 *@param maxLength
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午2:41:44
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
