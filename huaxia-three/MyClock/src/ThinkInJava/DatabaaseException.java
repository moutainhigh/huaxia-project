package ThinkInJava;
/**
 * 测试Spring.format
 * @author User
 *
 */
public class DatabaaseException extends Exception {
	public DatabaaseException(int transactionID,int queryID,String message){
		super(String.format("(t%d,q%d) %s", transactionID,queryID,message));
	}
	public static void main(String args[]){
		try{
			throw new DatabaaseException(3,7,"Write failed");
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
