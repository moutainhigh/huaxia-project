package ThinkInJava;

public class Dog extends Pet{
	public Dog(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Dog [name=" + super.getName() + "]";
	}
	
}
