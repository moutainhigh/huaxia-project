package JavaGaoFa;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 测试一些AtomicReference对象的使用
 * @author liuwei
 *
 */
public class test4 {
	static AtomicReference<Integer> money = new AtomicReference<Integer>();
	public static void main(String args[]){
		money.set(19);
		//充值线程
		for(int i=0;i<3;i++){
			new Thread(){
				public void run(){
					while(true){
						while(true){
							Integer m =money.get();
							if(m<20){
								if(money.compareAndSet(m, m+20)){
									System.out.println("余额小于20元，充值成功，余额："+money.get()+"元");
									break;
								}else{
									System.out.println("余额大于20元，无须充值");
									break;
								}
							}
						}
					}
				}
			}.start();
		};
		//消费线程
		new Thread(){

			public void run(){
//			    for(int i=0;i<100;i++)
				while(true)
					while(true){
						Integer m =money.get();
						if(m>10){
							System.out.println("大于10元");
							if(money.compareAndSet(m, m-20)){
								System.out.println("成功消费"+money.get()+"元");
								break;
							}else{
								System.out.println("没有足够的金额");
								break;
							}
						}
					}
				}
		}.start();
}
}
