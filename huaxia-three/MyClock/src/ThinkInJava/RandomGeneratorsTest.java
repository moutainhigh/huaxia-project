package ThinkInJava;
/**
 * 测试随机数生成的类
 * @author Liuwei
 *
 */
public class RandomGeneratorsTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeneratorsTest.test(RandomGenerator.class);
		RandomGenerator.String ss = new RandomGenerator.String(100);
		for(int i=0;i<10;i++)
		System.out.println(ss.next());
	}
}
