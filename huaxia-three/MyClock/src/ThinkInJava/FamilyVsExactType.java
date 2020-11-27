package ThinkInJava;
class Base{};
class Derived extends Base{};
/**
 * instanceof he class的等价性
 * @author User
 *
 */
public class FamilyVsExactType {
	public FamilyVsExactType() {
		// TODO Auto-generated constructor stub
	}
	static void test(Object x){
		System.out.println("Testing  x of type 	"+ x.getClass());
		System.out.println("x instanceof Base "+(x instanceof Base));
		System.out.println("x instanceof Derived "+(x instanceof Derived));
		System.out.println("Base.isInstace(x)"+(Base.class.isInstance(x)));
		System.out.println("Derived.isInstance(x)"+(Derived.class.isInstance(x)))	;
		System.out.println("x.getClass() ==Base.class"+(x.getClass() == Base.class));
		System.out.println("x.getClass() == Derived.class"+(x.getClass() == Derived.class));
		System.out.println("x.getClass().equals(Base.class)"+(x.getClass().equals(Base.class)));
		System.out.println("x.getClass().equals(Derived.class)"+(x.getClass().equals(Derived.class)));
	}
	public static void main(String args[]){
		test(new Base());
		test(new Derived());
	}
}
