package ThinkInJava;
//根据enum构建状态机,switch case实现状态的转换
enum Signal{GREEN,YELLOW,RED}
public class TrafficLight {
	Signal color = Signal.RED;
	public void change(){
		switch(color){
			case RED: color = Signal.GREEN;
						break;
			case GREEN:color = Signal.YELLOW;
						break;
			case YELLOW:color = Signal.RED;
						break;
		}
	}
	public String toString(){
		return "The traffic light is "+color;
	}
	public static void main(String args[]){
		TrafficLight t= new TrafficLight();
		for(int i=0;i<7;i++){
			System.out.println(t.toString());
			t.change();
		}
	}
}
