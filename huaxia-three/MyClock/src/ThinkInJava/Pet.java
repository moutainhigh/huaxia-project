package ThinkInJava;
/**
 * 宠物类
 * @author liuwei
 */
public class Pet {
	private String name;
	public Pet(String name){
		this.name = name;
	}
	@Override
	public String toString() {
		return "Pet [name=" + name + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
