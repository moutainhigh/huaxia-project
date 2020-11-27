package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

public class GrapeGardener implements FruitGardener {

	public GrapeGardener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fruit factory() {
		// TODO Auto-generated method stub
		return new Grape();
	}

}
