/**
 * Title: DefaultInitialization.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��21������10:56:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:DefaultInitialization2020��9��21��
 * @comments:�������͵ĳ�ʼֵ
 * @param: boolean���͵�get����������isBlean();
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��21������10:56:21
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
	 *@Date: 2020��9��21������10:56:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DefaultInitialization init = new DefaultInitialization();
		System.out.println("byte���͵ĳ�ʼֵ��"+init.b);
		System.out.println("���������͵ĳ�ʼֵ��"+init.s);
		System.out.println("�������͵ĳ�ʼֵ��"+init.i);	
		System.out.println("���������͵ĳ�ʼֵ��"+init.l);
		System.out.println("�����ȸ����͵ĳ�ʼֵ��"+init.f);
		System.out.println("˫���ȸ����͵ĳ�ʼֵ��"+init.d);	
		System.out.println("���������͵ĳ�ʼֵ��"+init.bl);
		System.out.println("�ַ������͵ĳ�ʼֵ��"+init.c);
		System.out.println("�������͵ĳ�ʼֵ��"+init.string);
		System.out.println("--------------------getset--------------------------");
		System.out.println("byte���͵ĳ�ʼֵ��"+init.getB());
		System.out.println("���������͵ĳ�ʼֵ��"+init.getS());
		System.out.println("�������͵ĳ�ʼֵ��"+init.getI());	
		System.out.println("���������͵ĳ�ʼֵ��"+init.getL());
		System.out.println("�����ȸ����͵ĳ�ʼֵ��"+init.getF());
		System.out.println("˫���ȸ����͵ĳ�ʼֵ��"+init.getD());	
		System.out.println("���������͵ĳ�ʼֵ��"+init.isBl());
		System.out.println("�ַ������͵ĳ�ʼֵ��"+init.getC());
		System.out.println("�������͵ĳ�ʼֵ��"+init.getString());
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
