/**
 * Title: ObjectAdapter.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��29������2:48:20
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:ObjectAdapter2020��9��29��
 * @comments: ��������������������ʵ���Ƕ������չ,��������ʵ���Ƕ���һ����ķ���������չ����ǿ�䷽��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��29������2:48:20
 */
public class ObjectAdapter implements Targetable {
	private Source source;
	
	/**
	 * Constructor
	 */
	public ObjectAdapter() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor
	 */
	public ObjectAdapter(Source source) {
		// TODO Auto-generated constructor stub
		super();
		this.source = source;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��29������2:48:20
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Source source  = new Source();
		Targetable target = new ObjectAdapter(source);
		target.editTextFile();
		target.editWordFile();
	}
	/**
	 * @Title: editTextFile
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��29������2:50:28
	 */
	@Override
	public void editTextFile() {
		// TODO Auto-generated method stub
		this.source.editTextFile();
	}
	/**
	 * @Title: editWordFile
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��9��29������2:50:23
	 */
	@Override
	public void editWordFile() {
		// TODO Auto-generated method stub
		System.out.println("a word file editing");
	}

}
