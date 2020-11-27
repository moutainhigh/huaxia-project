/**
 * Title: ConcreteIterator.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������1:46:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.IneratorPattern;

/**
 * @class_name:ConcreteIterator2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������1:46:03
 */
public class ConcreteIterator implements Iterator{
	 private ConcreteAggregate agg;
	 private int index = 0;
	 private int size = 0;
	/**
	 * 
	 */
	public ConcreteIterator(ConcreteAggregate agg) {
		// TODO Auto-generated constructor stub
		this.agg = agg;
		size = agg.size();
		index = 0;
	}
	/**
	 * @Title: hasNext
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��7������1:54:56
	 */
	@Override
	public boolean hasNext(){
		return index < size;
	}
	//������һ��Ԫ��
	@Override
	public Object next() {
		// TODO Auto-generated method stub
		if(index < size){
			return agg.getElement(index ++);
		}else{
			return null;
		}
	}


}
