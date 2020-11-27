package ThinkInJava;
//在return中使用finally
//try catch finally是一個語句，即使有return，finally也會執行完
public class MultipleReturns {
public static void f(int i){
	System.out.println("Initialization that requires cleanup");
	try{
		System.out.println("Point 1");
		if(i == 1) return ;
			System.out.println("Point 2");
		if(i == 2)return ;
			System.out.println("Point 3");
		if(i == 3)return ;
			System.out.println("end");
		return ;
	}finally{
		System.out.println("performing cleanup");
		System.out.println("HelloWorld");
		System.out.println("HelloWorld");
		System.out.println("HelloWorld");
		System.out.println("HelloWorld");
		System.out.println("HelloWorld");
		System.out.println("HelloWorld");
	}
}
public static void main(String args[]){
	for(int i= 1;i<=4;i++) 
		f(i);
}
}
