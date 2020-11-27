package PractisePackage;
class Counter2 implements Cloneable{
	private int value;
	public void increase(){
		value++;
	}
	public int getValue(){
		return value;
	}
	public Object clone(){
		System.out.println("Counter2 clone方法执行了");
		try{
			return super.clone();
		}catch(CloneNotSupportedException e){
			throw new Error(e);
		}
	}
}
/**
 * @class_name:MutableObject22020年8月6日
 * @comments:深度复制，每个对象都进行复制，每个引用对象都进行复制
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日上午11:21:02
 */
class MutableObject2 implements Cloneable{
	private Counter2 counter = new Counter2();
	public void increase(){
		counter.increase();
	}
	public int getValue(){
		return counter.getValue();
	}
	public Object clone(){
		System.out.println("clone方法执行了");
		MutableObject2 obj;
		try{
			obj = (MutableObject2) super.clone();
			obj.counter = (Counter2) counter.clone();
			return obj;
		}catch(CloneNotSupportedException e){
			throw new Error(e);
		}
	}
}
/**
 * @class_name:MutableObjectClone2020年8月6日
 * @comments:clone方法的深复制
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日上午11:12:13
 */
public class MutableObjectClone2 implements Cloneable{
	public void cloneObject(){
		MutableObject2 obj = new MutableObject2();
		obj.increase();
		MutableObject2 cloneObject = (MutableObject2) obj.clone();
		cloneObject.increase();
//		obj.increase();
		System.out.println(cloneObject.getValue());//值为2
		System.out.println(obj.getValue());//值为1
	}
	public MutableObjectClone2() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MutableObjectClone2().cloneObject();
	}

}
