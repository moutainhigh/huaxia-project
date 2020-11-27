package projectExample;
import java.math.BigDecimal;
/**
 * 大精度的浮点数运算
 * new BigDecimal("0.05")主要使用创造一个0.05
 * BigDecima.valueOf(0.01)创造一个0.01
 * new BigDecimal(0.01)创造一个0.01 的近视数。浮点数，
 * 所有创建字符串主要使用字符串创建和valueOf方法
 * @author Liuwei
 */
public class BigDecimalTest {
	public static void main(String args[]){
		BigDecimal f1= new BigDecimal("0.05");
		BigDecimal f2=BigDecimal.valueOf(0.01);
		BigDecimal f3= new BigDecimal(0.05);
		System.out.println("使用String作为BigDecimal的构造器参数");
		System.out.println("0.05 + 0.01 = "+f1.add(f2));
		System.out.println("0.05 - 0.01 = "+f1.subtract(f2));
		System.out.println("0.05 * 0.01 = "+f1.multiply(f2));
		System.out.println("0.05 / 0.01 = "+f1.divide(f2));
		System.out.println("使用double作为BigDecimal的构造器参数");
		System.out.println("0.05 + 0.01 = "+f3.add(f2));
		System.out.println("0.05 - 0.01 = "+f3.subtract(f2));
		System.out.println("0.05 * 0.01 = "+f3.multiply(f2));
		System.out.println("0.05 / 0.01 = "+f3.divide(f2));
	}
}
