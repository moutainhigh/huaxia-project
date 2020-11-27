/**
 * Title: Product.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月16日上午11:20:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

/**
 * @class_name:Product2020年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月16日上午11:20:55
 */
public final class Product {
	private final String no;
	private final String name;
	private final double price;
	
	
	/**
	 * Constructor
	 */
	public Product(String no,String name,double price) {
		// TODO Auto-generated constructor stub
		this.no = no;
		this.name = name;
		this.price = price;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月16日上午11:20:55
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Product p = new Product("1","1",1.0);
		System.out.println(p.toString());
		Product p2 = new Product("1","2",1.0);
		System.out.println(p2.toString());
	}
	public String getNo() {
		return no;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	@Override
	public String toString() {
		return "Product [no=" + no + ", name=" + name + ", price=" + price + "]";
	}

}
