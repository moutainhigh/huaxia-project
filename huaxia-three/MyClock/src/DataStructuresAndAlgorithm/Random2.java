package DataStructuresAndAlgorithm;
/**
 * 测试随机数生成器
 * @author liuwei
 *
 */
public class Random2 {
	private static final int A = 48271;
	private static final int M = 2147483647;
	private static final int Q = M/A;
	private static final int R = M %A;
	private static long state;
	public Random2(){
		state = System.currentTimeMillis() %Integer.MAX_VALUE;
//		state = 179424105;
	}
	public long randomInt(){
		long tempState = A*(state%Q)-R*(state /Q);
		if(tempState >=0){
			state = tempState;
		}else
			state = tempState+M;
		return state;
	}
	public static void main(String args[]){
		Random2 r = new Random2();
		for(int i=0;i<10;i++){
			System.out.print(r.randomInt()+" ");
		}
		System.out.println();
	}
}
