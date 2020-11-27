package ThinkInJava;

public class Hamster extends Pet {

	public Hamster(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Hamster [name=" + super.getName() + "]";
	}
	
}
