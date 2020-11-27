package JavaMultiThreadProgramming.mingribook.javaStruct;
/**
 * switch的使用
 * @author liuwei
 *
 */
public class GetSwitch {
	public static void main(String args[]){
		int week = 2;
		switch(week){
		case 1:
			System.out.println("Monday");
			break;
		case 2:
			System.out.println("Tuesday");
			break;
		case 3:
			System.out.println("Wednesday");
			break;
		case 4:
			System.out.println("fourday");
			break;
		default:
			System.out.println("Sorry ，I dont not know");
		}
	}
}
