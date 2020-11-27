package JavaConcurency;
/**
 * 	转账死锁的例子
 * @author liuwei
 */
public class TransferMoney {
	public void transferMoney(Account fromAccount,Account toAccount,int amount){
		synchronized(fromAccount){
			synchronized(toAccount){
				if((fromAccount.getBalance()-amount)<0){
					try {
						throw new InsufficientFundsException("账户余额不足");
					} catch (InsufficientFundsException e) {
						e.printStackTrace();
					}
				}
				else{
					fromAccount.debit(amount);
					toAccount.credit(amount);
					System.out.println("fromAccoutnBalance"+fromAccount.getBalance()+"toAccountBalance"+toAccount.getBalance());
				}
			}
		}
	}
	public static void main(String args[]){
		final TransferMoney transferMoney = new TransferMoney();
		final Account fromAccount = new Account();
		fromAccount.setBalance(10000);
		final Account toAccount = new Account();
		toAccount.setBalance(10000);
		Thread one = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					transferMoney.transferMoney(fromAccount, toAccount, 1);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		Thread two = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					transferMoney.transferMoney(toAccount, fromAccount, 1);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		one.start();
		two.start();
	}
}
