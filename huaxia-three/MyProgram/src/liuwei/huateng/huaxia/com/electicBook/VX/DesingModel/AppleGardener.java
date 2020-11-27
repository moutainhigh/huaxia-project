package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

public class AppleGardener implements FruitGardener {

	public AppleGardener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fruit factory() {
		// TODO Auto-generated method stub
		return new Apple();
	}
		
}
