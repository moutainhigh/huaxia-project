package projectExample;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public static void main(String args[]){
		List books = new ArrayList();
		books.add(new String("轻量级Java EE企业应用实战"));
		books.add(new String("疯狂Java讲义"));
		books.add(new String("疯狂Android讲义"));
		System.out.println(books);
		for(int i = 0 ;i < books.size();i++){
			System.out.println(books.get(i));
		}
		books.remove(2);
		System.out.println(books);
		System.out.println(books.indexOf(new String("疯狂Java讲义")));
		books.set(1, new String("疯狂Java讲义2"));
		System.out.println(books);
		System.out.println(books.subList(0, 2));
	}
}
