package ThinkInJava;
class Candy{
	static {
		System.out.println("LOading Candy");
	}
}
class Gum{
	static {
		System.out.println("Loading Gum");
	}
}
class Cookie{
	static{
		System.out.println("Loading Cookie");
	}
}
public class SweetShop {
	public SweetShop() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String args[]){
		System.out.println("inside main");
		new Candy();
		System.out.println("After Creating Candy");
		try{
			Class.forName("Gum");
		}catch(ClassNotFoundException e){
			System.out.println("Could not find Gum");
		}
		System.out.println("After Creating Gum");
		new Cookie();
		System.out.println("After Creating Cookie");
	}
}
