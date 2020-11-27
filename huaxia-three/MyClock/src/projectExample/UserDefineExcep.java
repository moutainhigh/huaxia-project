package projectExample;

public class UserDefineExcep {

	public static void main(String args[]){
		try{
			
			throw new userException("This is my Define Exception");
			
		}catch(userException e){
			System.out.println("异常信息为"+e.toString());
		}
	}
}
class userException extends Exception{
	public userException(String message){
		super(message);
	}
}