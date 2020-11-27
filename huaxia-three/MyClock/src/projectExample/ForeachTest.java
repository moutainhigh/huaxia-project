package projectExample;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ForeachTest {
	public static void main(String args[]){
		Collection books = new HashSet();
		books.add("轻量级Java EE企业应用实战");
		books.add("疯狂Java讲义");
		books.add("疯狂Android讲义");
		books.add("轻量级Java EE企业应用实战2");
		
		for(Object obj: books){
			String book = (String)obj;
			System.out.println(book);
			if(book.equals("疯狂Java讲义")){
				books.remove(book);
				book  = "测试字符串";
			}
		}
		System.out.println(books.toString());
	}
}
