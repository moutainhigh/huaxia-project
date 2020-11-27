package ThinkInJava;
/**
 * 测试用的人  类
 * @author liuwei
 *
 */
public class Person {
	private String name ;
	public Person(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
}
