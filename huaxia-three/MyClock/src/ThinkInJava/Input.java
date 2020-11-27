package ThinkInJava;
import java.util.Random;
/**
 *  枚举就像一个超类一样，可以实例化，也可以继承和实现。
 * @author User
 *
 */
public enum Input {
	NICKEL(5),DIME(10),QUARTER(25),DOLLAR(100),
	TOOTHPASTE(200),CHIPS(75),SODA(100),SOAP(50),
	ABORT_TRANSACTION{
		public int amount(){
//			throw new RuntimeException("ABORT.amount()");
			return -1;
		}
	},
	STOP{
		public int amount(){
//			throw new RuntimeException("SHUT_DOWN.amount");
			return -1;
		}
	};
	int value;
	Input(int value){
		this.value = value;
	}
	Input(){}
	int amount(){
		return value;
	};
	static Random rand = new Random(47);
	public static Input randomSelection(){
		System.out.println(values().length);
		return values()[rand.nextInt(values().length)];
	}
	public static void main(String argsp[]){
		for(int i=0;i<100;i++){
			System.out.println(randomSelection().amount());
			System.out.println(randomSelection());
		}
		if(( randomSelection()).equals(Input.STOP)){
			System.out.println(randomSelection());
		}
	}
}
