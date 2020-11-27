package DataStructuresAndAlgorithm;
/**
 * 编写生成随机数的类
 * 看是正确，其实错误的，容易造成溢出
 * @author liuwei
 *
 */
public class Random {
		private static final int A = 48271;
		private static final int M = 2147483647;
		private static long state;
		public Random(){
			state = System.currentTimeMillis() %Integer.MAX_VALUE;
		}
		public long randomIntWrong(){
			return state = (A*state) %M;
		}
		
		public double randomn0_1(){
			return (double) randomIntWrong()/M;
		}
		public static void main(String args[]){
			Random r = new Random();
			for(int i=0;i<10;i++){
				System.out.print(r.randomIntWrong()+" ");
			}
			System.out.println();
			for(int j=0;j<10;j++){
				System.out.print(r.randomn0_1()+" ");
			}
			System.out.println();
		}
}
