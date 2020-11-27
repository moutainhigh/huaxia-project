package ThinkInJava;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * map扩展到多维，即使从map嵌套map或者list，set，map。
 * 实现扩展到多维。
 * 测试map存储对象的使用
 * @author liuwei
 */
public class MapOfList {
	public static Map<Person,List<? extends Pet>> petPeople = new HashMap<Person,List<? extends Pet>>();
	static{
		petPeople.put(new Person("Dawn"), Arrays.asList(new Cymric("Molly"),new Mutt("spot")));
		petPeople.put(new Person("Kate"), Arrays.asList(new Cat("Shackleton"),new Cat("Elsie May"),new Dog("Margrett")));
		petPeople.put(new Person("Marilyn"), Arrays.asList(new Pug("Louie aka Louis Snorkelstrin Dupree"),new Cat("Stanford aka Stinky el Negro"),new Cat("Pinkola")));
		petPeople.put(new Person("Luke"), Arrays.asList(new Rat("Fuzzy"),new Rat("Fizzy")));
		petPeople.put(new Person("Isaac"), Arrays.asList(new Rat("Freckly")));
	}
	public static void main(String args[]){
		System.out.println("People:"+petPeople.keySet());
		System.out.println("Pets:"+petPeople.values());
		for(Person person :petPeople.keySet()){
			System.out.print(person+" has:");
			for(Pet pet : petPeople.get(person))
				System.out.print("  "+pet);
			System.out.println();
		}
	}
}
