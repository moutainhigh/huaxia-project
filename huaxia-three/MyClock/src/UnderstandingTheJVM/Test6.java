package UnderstandingTheJVM;
public class Test6 {
	public static void main(String args[]){
		System.out.println(test7.i);
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		new test7();
		System.out.println(test7.i); 
	}
	static class test7{
		
		static	int i;
		static {
				i =1234;
				System.out.println(i);
			}
}
}
