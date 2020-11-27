/**
 * Title: EnumIndexTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月16日上午10:28:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.enumExample;

/**
 * @class_name:EnumIndexTest2019年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月16日上午10:28:58
 */
public class EnumIndexTest {
	/**
	 * 
	 * @class_name:Constants22019年10月16日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2019年10月16日上午10:36:02
	 */
	enum Constants2{
		Constants_A("我是枚举成员A"),
		Constants_B("我是枚举成员B"),
		Constants_C("我是枚举成员C"),
		Constants_D(3);
		private String description;
		private int i = 4;
		private Constants2(){
		}
		private Constants2(String description){
			this.description = description;
		}
		private Constants2(int i){
			this.i = this.i+i;
		}
		public String getDescription() {
			return description;
		}
		public int getI() {
			return i;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public void setI(int i) {
			this.i = i;
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月16日上午10:28:58
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<Constants2.values().length;i++){
			System.out.println(Constants2.values()[i]+"调用getDescription()方法为："+Constants2.values()[i].getDescription());
		}
		System.out.println(Constants2.valueOf("Constants_D")+"调用getI()方法为："+Constants2.valueOf("Constants_D").getI());
	}
}
