/**
 * Title: ConcreteIterator.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午5:09:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:ConcreteIterator2020年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午5:09:16
 */
public class ConcreteIterator implements Iterator {
	private Collection collection;
	private int pos = -1;
	/**
	 * Constructor
	 */
	public ConcreteIterator() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor
	 */
	public ConcreteIterator(Collection collection) {
		// TODO Auto-generated constructor stub
		this.collection = collection;
	}
	/* (non-Javadoc)
	 * @see Offer.designModel.Iterator#previous()
	 */
	@Override
	public Object previous() {
		// TODO Auto-generated method stub
		if(pos > 0){
			pos--;
		}
		return collection.get(pos);
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.Iterator#next()
	 */
	@Override
	public Object next() {
		// TODO Auto-generated method stub
		if(pos < collection.size() -1){
			pos++;
		}
		return collection.get(pos);
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(pos < collection.size() -1){
			return true;
		}else{
			return false;
		}
	}

}
