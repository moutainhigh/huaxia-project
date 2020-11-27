package ThinkInJava;
/**
 * 不要catch中做资源的关闭，而是在finally中做资源的关闭
 * @author User
 *
 */
public class WIthFinally {
	private static Switch sw = new Switch();
	public static void main(String args[]){
		try {
			sw.on();
			OnOfSwitch.f();
		} catch(OnOffException1 e){
			System.out.println("OnOffException1");
		}catch(OnOffException2 e){
			System.out.println("OnOffException2");	
		}finally{
			sw.off();
		}
	}
}
