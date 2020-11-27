/**
 * Title: ConcreteAggregate.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������1:49:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.IneratorPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @class_name:ConcreteAggregate2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������1:49:09
 */
public class ConcreteAggregate implements Aggregate {
	private List vector = new ArrayList();
	/**
	 * 
	 */
	public ConcreteAggregate() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.IneratorPattern.Aggregate#add(java.lang.Object)
	 */
	@Override
	public void add(Object object) {
		// TODO Auto-generated method stub
		this.vector.add(object);
	}
	/**
	 * @Title: getElement
	 *@Description: TODO ��ȡԪ��
	 *@param index
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��7������1:51:05
	 */
	public Object getElement(int index){
		if(index < vector.size()){
			return vector.get(index);
		}else{
			return null;
		}
	}
	/**
	 * @Title: size
	 *@Description: TODO ��ȡ���ϵĴ�С
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��7������1:52:34
	 */
	public int size(){
		return vector.size();
	}
	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.IneratorPattern.Aggregate#createIterator()
	 */
	@Override
	public Iterator createIterator() {
		// TODO Auto-generated method stub
		return new ConcreteIterator(this);
	}

}
