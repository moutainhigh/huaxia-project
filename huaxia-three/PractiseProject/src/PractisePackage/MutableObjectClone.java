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
		System.out.println("clone����ִ����");
		try{
			return super.clone();
		}catch(CloneNotSupportedException e){
			throw new Error(e);
		}
	}
}
/**
 * @class_name:MutableObjectClone2020��8��6��
 * @comments:clone������ǳ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������11:12:13
 */
public class MutableObjectClone implements Cloneable{
	public void cloneObject(){
		MutableObject obj = new MutableObject();
		obj.increase();
		MutableObject cloneObject = (MutableObject) obj.clone();
		cloneObject.increase();
		obj.increase();
		System.out.println(cloneObject.getValue());//ֵΪ3
	}
	public MutableObjectClone() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MutableObjectClone().cloneObject();
	}

}
