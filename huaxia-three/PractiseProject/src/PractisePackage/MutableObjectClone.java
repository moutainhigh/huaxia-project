package PractisePackage;
class Counter
{
	private int value;
	public void increase(){
		value++;
	}
	public int getValue(){
		return value;
	}
}
class MutableObject implements Cloneable{
	private Counter counter = new Counter();
	public void increase(){
		counter.increase();
	}
	public int getValue(){
		return counter.getValue();
	}
	public Object clone(){
		System.out.println("clone方法执行了");
		try{
			return super.clone();
		}catch(CloneNotSupportedException e){
			throw new Error(e);
		}
	}
}
/**
 * @class_name:MutableObjectClone2020年8月6日
 * @comments:clone方法的浅复制
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日上午11:12:13
 */
public class MutableObjectClone implements Cloneable{
	public void cloneObject(){
		MutableObject obj = new MutableObject();
		obj.increase();
		MutableObject cloneObject = (MutableObject) obj.clone();
		cloneObject.increase();
		obj.increase();
		System.out.println(cloneObject.getValue());//值为3
	}
	public MutableObjectClone() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MutableObjectClone().cloneObject();
	}

}
