package PractisePackage;

public class TryCatchFinally {
	
	public TryCatchFinally() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int test = 0;
		try{
			System.out.println("Hello World!");
			if( test == 0 )
				throw new Error("test");
			System.out.println("Hello World!");
			System.out.println("Hello World!");
			System.out.println("Hello World!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
