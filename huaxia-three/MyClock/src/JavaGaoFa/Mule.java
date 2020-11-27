package JavaGaoFa;

public class Mule implements IHorse,IAnimal{

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("Mule eat");
	}
	public static void main(String[] args){
//		Mule m = new Mule();
//		m.run();
//		m.breath();
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		
	}
	
}
 interface IHorse {
	void eat();
//	default void run(){
//		System.out.println("hourse run");
//	}
}
 interface IAnimal{
//	default void breath(){
//		System.out.println("breath");
//	}
}
