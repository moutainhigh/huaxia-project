package JavaConcurency;
/**
 * 自定义异常
 * @author liwuei
 */
public class InsufficientFundsException extends Exception{
	public InsufficientFundsException(String s){
		super(s);
	}
}
