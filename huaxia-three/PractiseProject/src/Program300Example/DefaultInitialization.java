/**
 * Title: DefaultInitialization.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月21日上午10:56:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:DefaultInitialization2020年9月21日
 * @comments:数据类型的初始值
 * @param: boolean类型的get方法命名是isBlean();
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月21日上午10:56:21
 */
public class DefaultInitialization {
	private byte b;
	private short s;
	private int i ;
	private long l;
	private float f;
	private double d;
	private boolean bl;
	private char c;
	private String string;
	/**
	 * Constructor
	 */
	public DefaultInitialization() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月21日上午10:56:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DefaultInitialization init = new DefaultInitialization();
		System.out.println("byte类型的初始值："+init.b);
		System.out.println("短整型类型的初始值："+init.s);
		System.out.println("整型类型的初始值："+init.i);	
		System.out.println("长整型类型的初始值："+init.l);
		System.out.println("单精度浮点型的初始值："+init.f);
		System.out.println("双精度浮点型的初始值："+init.d);	
		System.out.println("布尔型类型的初始值："+init.bl);
		System.out.println("字符型类型的初始值："+init.c);
		System.out.println("引用类型的初始值："+init.string);
		System.out.println("--------------------getset--------------------------");
		System.out.println("byte类型的初始值："+init.getB());
		System.out.println("短整型类型的初始值："+init.getS());
		System.out.println("整型类型的初始值："+init.getI());	
		System.out.println("长整型类型的初始值："+init.getL());
		System.out.println("单精度浮点型的初始值："+init.getF());
		System.out.println("双精度浮点型的初始值："+init.getD());	
		System.out.println("布尔型类型的初始值："+init.isBl());
		System.out.println("字符型类型的初始值："+init.getC());
		System.out.println("引用类型的初始值："+init.getString());
	}

	public byte getB() {
		return b;
	}

	public short getS() {
		return s;
	}

	public int getI() {
		return i;
	}

	public long getL() {
		return l;
	}

	public float getF() {
		return f;
	}

	public double getD() {
		return d;
	}

	public boolean isBl() {
		return bl;
	}

	public char getC() {
		return c;
	}

	public String getString() {
		return string;
	}

	public void setB(byte b) {
		this.b = b;
	}

	public void setS(short s) {
		this.s = s;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setL(long l) {
		this.l = l;
	}

	public void setF(float f) {
		this.f = f;
	}

	public void setD(double d) {
		this.d = d;
	}

	public void setBl(boolean bl) {
		this.bl = bl;
	}

	public void setC(char c) {
		this.c = c;
	}

	public void setString(String string) {
		this.string = string;
	}
	
}
