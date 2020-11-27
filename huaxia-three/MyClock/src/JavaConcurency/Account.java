package JavaConcurency;
/**
 * 测试死锁的转账的例子
 * @author liuwei
 */
public class Account {
	private int balance;
	public void debit(int amount){
		this.balance = this.balance - amount;
	}
	public void credit(int amount){
		this.balance = this.balance + amount;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
