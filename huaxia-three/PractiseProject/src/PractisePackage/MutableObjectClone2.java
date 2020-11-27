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
		System.out.println("Counter2 clone����ִ����");
		try{
			return super.clone();
		}catch(CloneNotSupportedException e){
			throw new Error(e);
		}
	}
}
/**
 * @class_name:MutableObject22020��8��6��
 * @comments:��ȸ��ƣ�ÿ�����󶼽��и��ƣ�ÿ�����ö��󶼽��и���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������11:21:02
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
		System.out.println("clone����ִ����");
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
 * @class_name:MutableObjectClone2020��8��6��
 * @comments:clone���������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������11:12:13
 */
public class MutableObjectClone2 implements Cloneable{
	public void cloneObject(){
		MutableObject2 obj = new MutableObject2();
		obj.increase();
		MutableObject2 cloneObject = (MutableObject2) obj.clone();
		cloneObject.increase();
//		obj.increase();
		System.out.println(cloneObject.getValue());//ֵΪ2
		System.out.println(obj.getValue());//ֵΪ1
	}
	public MutableObjectClone2() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MutableObjectClone2().cloneObject();
	}

}
