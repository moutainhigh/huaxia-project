package Algorithm;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * 高精度浮点数BigDecimal的使用
 * @author liuwei
 * result
 * 高精度浮点数number13.141500000000000181188397618825547397136688232421875
高精度浮点数number21.2344999999999999307220832633902318775653839111328125
高精度浮点数加法4.3760000000000001119104808822157792747020721435546875
高精度浮点数减法1.9070000000000002504663143554353155195713043212890625
高精度浮点数乘法3.8781817500000000060405014323805391527821713030086874448250692203055134399392045452259480953216552734375
高精度浮点数除法2.544754961522884048184463311690970190997595968556798
 */
public class MathBigDecimal {
	public static void main(String args[]){
		BigDecimal number1 = new BigDecimal(3.1415);
		BigDecimal number2 = new BigDecimal(1.2345);
		BigDecimal addition = number1.add(number2);
		BigDecimal substraction = number1.subtract(number2);
		BigDecimal multiplication = number1.multiply(number2);
		//四舍五入的方式获得高精度的除法运算
		BigDecimal division = number1.divide(number2,RoundingMode.HALF_UP);
		System.out.println("高精度浮点数number1"+number1);
		System.out.println("高精度浮点数number2"+number2);
		System.out.println("高精度浮点数加法"+addition);
		System.out.println("高精度浮点数减法"+substraction);
		System.out.println("高精度浮点数乘法"+multiplication);
		System.out.println("高精度浮点数除法"+division);
	}
}
