/**
 * Title: ComputerBuilder.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������10:48:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:ComputerBuilder2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������10:48:50
 */
public interface ComputerBuilder {
	 /**
	  * @Title: buildCpu
	  *@Description: TODO
	  *@author: LiuWei
	  *@Date: 2020��1��6������10:54:18
	  */
	public void buildCpu();//����cpu
	/**
	 * @Title: buildRam
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��1��6������10:54:24
	 */
	void buildRam();//�����ڴ�
	/**
	 * @Title: buildHardDisk
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��1��6������10:54:28
	 */
	void buildHardDisk();//����Ӳ��
	/**
	 * @Title: buildGraphicCard
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��1��6������10:54:34
	 */
	void buildGraphicCard();//�����Կ�
	/**
	 * @Title: buildMonitor
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��1��6������10:54:38
	 */
	void buildMonitor();//������ʾ��
	/**
	 * @Title: buildOs
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��1��6������10:54:42
	 */
	void buildOs();//�������ϵͳ
	/**
	 * @Title: getResult
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��6������10:54:46
	 */
	Computer getResult();//�õ�����õļ����
}
