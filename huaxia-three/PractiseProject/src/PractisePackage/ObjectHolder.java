package PractisePackage;
/**
 * @class_name:ObjectHolder2020年8月6日
 * @comments:练习泛型的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午12:44:11
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
