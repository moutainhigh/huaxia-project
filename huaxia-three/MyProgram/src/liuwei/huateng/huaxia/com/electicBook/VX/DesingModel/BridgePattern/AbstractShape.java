package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BridgePattern;

public abstract class AbstractShape {
	Color color;
	public AbstractShape(Color color) {
		// TODO Auto-generated constructor stub
		this.color = color;
	}
	public abstract void draw();

}
