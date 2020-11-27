package ThinkInJava;
class OnOffException1 extends Exception{}
class OnOffException2 extends Exception{}
public class OnOfSwitch {
	private static Switch sw = new Switch();
	public static void f() throws OnOffException1 ,OnOffException2{} ;
	public static void main(String args[]){
		try{
			sw.on();
			f();
			sw.off();
		}catch(OnOffException1 e){
			System.out.println("OnOffException1");
			sw.off();
		}catch(OnOffException2 e){
			System.out.println("OnOffException2");
			sw.off();
		}finally{
			
		}
	}
}
