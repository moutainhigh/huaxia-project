package ThinkInJava;
/**
 * 测试类的成员变量的初始值
 * @author User
 *
 */
public class InitialValues {
	boolean t;
	char c;
	byte b;
	short s;
	int i;
	long l;
	float f;
	double d;
	InitialValues reference;
	void printInitialValues(){
		System.out.println("InitialValues [t=" + t + ", c=" + c + ", b=" + b + ", s=" + s + ", i=" + i + ", l=" + l + ", f=" + f
				+ ", d=" + d + ", reference=" + reference + "]");
	}
	public static void main(String args[]){
		InitialValues iv = new InitialValues();
		iv.printInitialValues();
	}
	@Override
	public String toString() {
		return "InitialValues [t=" + t + ", c=" + c + ", b=" + b + ", s=" + s + ", i=" + i + ", l=" + l + ", f=" + f
				+ ", d=" + d + ", reference=" + reference + "]";
	}
}
