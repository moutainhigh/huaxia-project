package PractisePackage;
class Animal{
	int legs = 0;
	Animal(int legs){
		this.legs = legs;
	}
}
class Dog extends Animal{
	String name = "<default>";
	Dog(){
		super(4);
	}
}
public class NewObject {
	public NewObject() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog dog = new Dog();
		System.out.println(dog.legs);//Êä³öÖµ4
	}
}
