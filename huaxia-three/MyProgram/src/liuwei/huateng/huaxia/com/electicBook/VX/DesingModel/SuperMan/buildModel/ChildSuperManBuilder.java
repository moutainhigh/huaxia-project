/**
 * Title: AdultSuperManBuilder.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������5:12:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel;

/**
 * @class_name:AdultSuperManBuilder2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������5:12:03
 */
public class ChildSuperManBuilder extends Builder {

	/**
	 * 
	 */
	public ChildSuperManBuilder() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel.Builder#setBody()
	 */
	@Override
	public void setBody() {
		// TODO Auto-generated method stub
		superMan.setBody("����������");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel.Builder#setSpecialTalnent()
	 */
	@Override
	public void setSpecialTalnent() {
		// TODO Auto-generated method stub
		superMan.setSpecialTalent("�����ƶ�");

	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel.Builder#setSpecialSymbol()
	 */
	@Override
	public void setSpecialSymbol() {
		// TODO Auto-generated method stub
		superMan.setSpecialSymbol("��ǰ��СS���");
	}
}
