package ThinkInJava;
/**
 * 测试final类，不可被继承。final类中所有方法默认都是final。
 * 但是变量不是final
 * @author User
 *
 */
class SmallBrain{};
final class Dinosaur{
	public int i=7;
	public int j = 1;
	public SmallBrain x = new SmallBrain();
	public void f(){
		System.out.println("Dinosaur [i=" + i + ", j=" + j + ", x=" + x + "]");
	}
	@Override
	public String toString() {
		return "Dinosaur [i=" + i + ", j=" + j + ", x=" + x + "]";
	}
}
public class Jurassic {
	public static void main(String args[]){
		Dinosaur n1 = new Dinosaur();
		n1.f();
		n1.i= 40;
		n1.j++;
		n1.f();
	}
	
}
