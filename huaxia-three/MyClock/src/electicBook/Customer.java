package electicBook;
/**
 * 简单工厂模式的使用
 * @author liuwei
 */
 abstract class Car {
	public abstract String getInfo();
}
class BMW extends Car{
	@Override
	public String getInfo() {	
		return "BMW";
	}
}
class Benz extends Car{
	@Override
	public String getInfo() {	
		return "Benz";
	}
}
class CarFactory{
	public static Car getCar(String  name){
		if(name.equalsIgnoreCase("BMW")){
			return new BMW();
		} else if(name.equalsIgnoreCase("Benz")){
			return new Benz();
		}else{
			return null;
		}
	}
}
class Customer{
	public static void main(String args[]){
		System.out.println("顾客要购买BMW");
		Car bmw  = CarFactory.getCar("BMW");
		System.out.println("提取汽车："+bmw.getInfo());
		System.out.println("顾客要购买Benz");
		Car benz  = CarFactory.getCar("Benz");
		System.out.println("提取汽车："+benz.getInfo());
	}
}