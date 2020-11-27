package testProject;
/**
 * 测试连加的程序,计算365存钱法
 * @author liuwei
 *
 */
public class add {
	public static void main(String args[]){
		int sum=0;
		int day=167;
		for(int i=1;i<=day;i++)
		{
			sum+=i;
		}
		System.out.println("sum="+sum);
	}
}
