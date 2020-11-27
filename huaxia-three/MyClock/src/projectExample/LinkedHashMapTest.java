package projectExample;
import java.util.LinkedHashMap;
public class LinkedHashMapTest {
	public static void main(String args[]){
		LinkedHashMap scores = new LinkedHashMap();
		scores.put("语文",100);
		scores.put("数学",100);
		scores.put("英语",100);
		for(Object key : scores.keySet()){
			System.out.println(key+"------->"+scores.get(key));
		}
	}
}
