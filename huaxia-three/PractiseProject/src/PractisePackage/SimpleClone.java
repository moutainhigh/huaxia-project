package PractisePackage;
/**
 * @class_name:ToBeCloned2020年8月6日
 * @comments:测试cloneable接口实现复制对象的方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日上午10:55:54
 */
class ToBeCloned implements Cloneable{
	private int value = 0;
	public void setValue(int value){
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
	public Object clone(){
		System.out.println("clone方法被执行");
		try{
			return super.clone();
		}catch(CloneNotSupportedException e){
			throw new Error(e);
		}
	}
}
public class SimpleClone {
	
	public SimpleClone() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ToBeCloned obj = new ToBeCloned();
		obj.setValue(1);
		ToBeCloned cloneObj = (ToBeCloned) obj.clone();
		System.out.println(cloneObj.getValue());
	}

}
