/**
 * Title: Test.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日下午5:06:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:Test2020年9月22日
 * @comments:
 * @param:封装类型
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午5:06:29
 */
public class Test {

	/**
	 * Constructor
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午5:06:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student student = new Student();
		student.setName("张丽");
		student.setStore(85);
		student.setStore1(-98);
		System.out.println(student.getName()+" 总成绩是"+student.sumStore(student.getStore(), student.getStore1()));
	}

}
class Student{
	private String name;
	private int store;
	private int store1;
	/**
	 * @Title: sumStore
	 *@Description: TODO
	 *@param store
	 *@param store1
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午5:07:42
	 */
	int sumStore(int store,int store1){
		int sum = store+store1;
		return sum;
	}
	/**
	 * @Title: setName
	 *@Description: TODO
	 *@param name
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午5:08:39
	 */
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setStore(int store){
		if(store >0){
			this.store = store;
		}
	}
	public int getStore(){
		return store;
	}
	public void setStore1(int store1){
		if(store1 >0){
			this.store1 = store1;
		}
	}
	public int getStore1(){
		return store1;
	}
}