package ThinkInJava;
/**
 * 格式化输出字符串。字符串格式化String.format
 * @author liuwei
 *
 */
public class DatabaseException extends Exception{
public DatabaseException(int transactionID,int queryID,String message){
	super(String.format("(t%d, q%d)      %s", transactionID,queryID,message));
}
public static void main(String args[]){
	try{
		throw new DatabaseException(3,7,"Write filed");
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
