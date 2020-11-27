/**
 * Title: AnyEnum.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月16日上午10:44:35
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.enumExample;

interface d{
	public String getDescription();
	public int getI();
}
/**
 * @class_name:AnyEnum2019年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月16日上午10:44:35
 */
public enum AnyEnum implements d{
	Constants_A{
		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return ("我是枚举成员A");
		}

		@Override
		public int getI() {
			// TODO Auto-generated method stub
			return i;
		}
	},Constants_B{
		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return ("我是枚举成员B");
		}

		@Override
		public int getI() {
			// TODO Auto-generated method stub
			return i;
		}
	},Constants_C{
		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return ("我是枚举成员C");
		}

		@Override
		public int getI() {
			// TODO Auto-generated method stub
			return i;
		}
	},
	Constants_D{
		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return ("我是枚举成员D");
		}

		@Override
		public int getI() {
			// TODO Auto-generated method stub
			return i;
		}
	};
	private static int i=5;
	/**
	 * @Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月16日上午10:51:24
	 */
	public static void main(String args[]){
		for(int i=0;i<AnyEnum.values().length;i++){
			System.out.println(AnyEnum.values()[i]+"调用getDescription()方法为："+AnyEnum.values()[i].getDescription());
			System.out.println(AnyEnum.values()[i]+"调用getI()方法为："+AnyEnum.values()[i].getI());
		}
	}
}
