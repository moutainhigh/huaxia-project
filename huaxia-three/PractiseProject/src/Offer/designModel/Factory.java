/**
 * Title: Factory.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月28日下午4:12:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Factory2020年9月28日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月28日下午4:12:49
 */
public class Factory {

	/**
	 * Constructor
	 */
	public Factory() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: createPhone
	 *@Description: TODO 
	 *@param phoneName
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月28日下午4:14:36
	 */
	public Phone createPhone(String phoneName){
		if("HuaWei".equals(phoneName)){
			return new HuaWei();
		}else if("Apple".equals(phoneName)){
			return new Iphone();
		}else{
			return null;
		}
	}
	/**
	 * @Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月28日下午4:16:30
	 */
	public static void main(String args[]){
		Factory factory =new Factory();
		Phone huawei = factory.createPhone("HuaWei");
		Phone iphone = factory.createPhone("Apple");
		System.out.println(huawei.brand());
		System.out.println(iphone.brand());
	}
}
