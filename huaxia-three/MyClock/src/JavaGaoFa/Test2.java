package JavaGaoFa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal对象是线程安全的
 * @author User
 *
 */
public class Test2  {
	private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();
	public static class ParseDate implements Runnable{
		int i=0;
		public ParseDate(int i){
			this.i =i;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
				try {
					if(t1.get()==null){
						t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
					}
					Date t = t1.get().parse("2015-03-29 19:29:"+i%60);
					System.out.println(i+":"+t);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public static void main(String args[]){
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			es.execute(new ParseDate(i));
		}
	}
}
