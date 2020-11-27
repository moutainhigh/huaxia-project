package ThinkInJava;
/**
 * 测试异常的丢失错误
 * 运行结果A trivial exception
 * VeryImportantException 异常丢失
 * @author User
 *
 */
class VeryImportantException extends Exception{
	public String toString(){
		return "A very important exception!";
	}
}
class HoHumException extends Exception{
	public String toString(){
		return "A trivial exception";
	}
}
public class LostMessage {
	void f() throws VeryImportantException{
		throw new VeryImportantException();
	}
	void dispose() throws HoHumException{
		throw new HoHumException();
	}
	public static void main(String args[]){
		try{
			LostMessage lm = new LostMessage();
			try{
				lm.f();
			}finally{
				lm.dispose();
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
