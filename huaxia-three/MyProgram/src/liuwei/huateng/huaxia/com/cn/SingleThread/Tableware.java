/**
 * Title: Tableware.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��13������10:31:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.SingleThread;

/**
 * @class_name:Tableware2019��12��13��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��13������10:31:29
 */
public class Tableware {
	//�;�����
	private final String toolName;
	public Tableware(String toolName){
		this.toolName = toolName;
	}
	
	@Override
	public String toString() {
		return "Tableware [toolName=" + toolName + "]";
	}

}
