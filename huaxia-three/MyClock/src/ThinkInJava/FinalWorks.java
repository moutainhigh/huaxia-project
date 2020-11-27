package ThinkInJava;
/**
 * 测试try catch finally
 * @author liuwei
 *
 */
public class FinalWorks {
	static int count = 0;
	public static void main(String args[]){
		while(true){
			try{
				if(count++ == 0){
					throw new ThreeException();
				}
				System.out.println("No Exception");
			}catch(ThreeException e){
				e.printStackTrace();
				System.out.println("ThreeException");
			}finally{
				System.out.println("In finally clause");
				if(count==2) break;//out of "while"
			}
		}
	}
}
