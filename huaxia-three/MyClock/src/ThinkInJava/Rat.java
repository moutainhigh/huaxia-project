package ThinkInJava;

public class Rat extends Pet{

	public Rat(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Rat [name=" + super.getName() + "]";
	}
}
