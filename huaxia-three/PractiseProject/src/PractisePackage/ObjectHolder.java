package PractisePackage;
/**
 * @class_name:ObjectHolder2020��8��6��
 * @comments:��ϰ���͵�ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������12:44:11
 * @param <T>
 */
public class ObjectHolder<T>{

	public ObjectHolder() {
		// TODO Auto-generated constructor stub
	}
	private T obj;
	public T getObject(){
		return obj;
	}
	public void setObject(T obj){
		this.obj = obj;
	}
	public static void main(String args[]){
		ObjectHolder<String> holder = new ObjectHolder<String>();
		holder.setObject("hello");
		String str = holder.getObject();
		System.out.println(str);
	}
}
