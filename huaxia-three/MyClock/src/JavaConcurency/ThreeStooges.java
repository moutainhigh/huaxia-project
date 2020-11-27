package JavaConcurency;
import java.util.HashSet;
import java.util.Set;
/**
 * 在可变对象基础上构建不可变类
 * @author liuwei
 */
public class ThreeStooges {
	private final Set<String> stooges = new HashSet<String>();
	public ThreeStooges(){
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("curly");
	}
	public boolean isStooge(String name){
		return stooges.contains(name);
	}
	public static void main(String args[]){
		ThreeStooges stooges = new ThreeStooges();
		System.out.println(stooges.isStooge("Moe"));
		System.out.println(stooges.isStooge("Moe1"));
	}
}
