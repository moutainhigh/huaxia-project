/**
 * Title: Factory.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��28������4:12:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Factory2020��9��28��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��28������4:12:49
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
	 *@Date: 2020��9��28������4:14:36
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
	 *@Date: 2020��9��28������4:16:30
	 */
	public static void main(String args[]){
		Factory factory =new Factory();
		Phone huawei = factory.createPhone("HuaWei");
		Phone iphone = factory.createPhone("Apple");
		System.out.println(huawei.brand());
		System.out.println(iphone.brand());
	}
}
