package ThinkInJava;

public class Pug extends Pet {

	public Pug(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Pug [name=" + super.getName() + "]";
	}
	
}
