/**
 * Title: ListCollection.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午5:08:30
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @class_name:ListCollection2020年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午5:08:30
 */
public class ListCollection implements Collection {
	public List list = new ArrayList();
	/**
	 * Constructor
	 */
	public ListCollection() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.Collection#iterator()
	 */
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new ConcreteIterator(this);
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.Collection#get(int)
	 */
	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		return list.get(i);
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(Object object) {
		// TODO Auto-generated method stub
		list.add(object);
		return true;
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.Collection#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

}
