/**
 * Title: CompositeSpecification.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��9������9:42:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern;

/**
 * @class_name:CompositeSpecification2020��1��9��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��9������9:42:50
 */
public abstract class CompositeSpecification implements IUserSpecification {

	/**
	 * 
	 */
	public CompositeSpecification() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern.IUserSpecification#isSatisfiedBy(liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern.User)
	 */
	@Override
	public boolean isSatisfiedBy(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern.IUserSpecification#and(liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern.IUserSpecification)
	 */
	@Override
	public IUserSpecification and(IUserSpecification spec) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern.IUserSpecification#or(liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern.IUserSpecification)
	 */
	
	@Override
	public IUserSpecification or(IUserSpecification spec) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern.IUserSpecification#not()
	 */
	@Override
	public IUserSpecification not() {
		// TODO Auto-generated method stub
		return null;
	}

}
