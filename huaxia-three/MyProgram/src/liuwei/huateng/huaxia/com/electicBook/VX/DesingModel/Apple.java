package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

public class Apple implements Fruit {
	private int treeAge;
	
	public Apple() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void grow() {
		// TODO Auto-generated method stub
		System.out.println("苹果正在生长....");
	}

	@Override
	public void harvest() {
		// TODO Auto-generated method stub
		System.out.println("收获苹果");
	}

	@Override
	public void plant() {
		// TODO Auto-generated method stub
		System.out.println("种植苹果");
	}

	public int getTreeAge() {
		return treeAge;
	}

	public void setTreeAge(int treeAge) {
		this.treeAge = treeAge;
	}
	
}
