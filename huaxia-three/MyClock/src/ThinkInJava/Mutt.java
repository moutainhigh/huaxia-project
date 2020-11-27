package ThinkInJava;

public class Mutt extends Pet{

	public Mutt(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Mutt [name=" + super.getName() + "]";
	}

}
