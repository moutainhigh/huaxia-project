package projectExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiamondTest {
	public static void main(String args[]){
		List<String> books = new ArrayList<>();
		books.add(new String("轻量级Java EE企业应用实战"));
		books.add(new String("疯狂Java讲义"));
		books.add(new String("疯狂Android讲义"));
		for(String book:books){
			System.out.println(book);
		}
		
		Map<String,List<String>> schollsInfo = new HashMap<>();
		/**
		 * map用put,foreach循环，使用key进行循环
		 */
		List<String> schools = new ArrayList();
		schools.add("斜月三星洞");
		schools.add("西天取经路");
		schollsInfo.put("孙悟空",schools);
		for(String key : schollsInfo.keySet()){
			List<String> list = schollsInfo.get(key);
			System.out.println(key+"--->"+list);
		}
	}
}
