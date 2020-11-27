package ThinkInJava;
interface A{
	void f();
}
class B implements A{
	public void f(){
		System.out.println("f方法");
	};
	public void g(){
		System.out.println("g方法");
	};
}
public class InterfaceViolation {
	public InterfaceViolation() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String args[]){
		A a= new B();
		a.f();
		System.out.println(a.getClass().getName());
		if( a instanceof B){
			B b= (B)a;
			b.g();
		}
	}
}
