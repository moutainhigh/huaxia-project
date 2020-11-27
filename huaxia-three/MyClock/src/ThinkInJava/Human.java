package ThinkInJava;
/**
 * 测试异常的匹配
 * @author User
 *
 */
class Annoynace extends Exception{};
class Sneeze extends Annoynace{};
public class Human {
	public static void main(String args[]){
		//Catch the exact type
		try{
			throw new Sneeze();
		}catch(Sneeze s){
			System.out.println("Caught Sneeze");
		}catch(Annoynace a){
			System.out.println("Caught Annoyance1");
		}
		//Catch the base type:
		try{
			throw new Sneeze();
		}catch(Annoynace a){
			System.out.println("Caught Annoyance2");
		}
	}
}
