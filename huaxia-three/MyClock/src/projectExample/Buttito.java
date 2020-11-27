package projectExample;
/**
 * 测试运行enum的Exceple
 * @author Liuwei
 */
public class Buttito {
	Spiciness degree;
	public Buttito(Spiciness degree){
		this.degree = degree;
	}
	public String toString(){
		return "Burrito is "+degree;
	}
	public static void main(String args[]){
		System.out.println(new Buttito(Spiciness.NOT));
		System.out.println(new Buttito(Spiciness.MEDIUM));
		System.out.println(new Buttito(Spiciness.HOT));
	}
}
