package PractisePackage;
/**
 * @class_name:CloneableObject2020年8月6日
 * @comments:重写clone方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日上午10:43:37
 */
public class CloneableObject implements Cloneable {
	public Object clone(){
		System.out.println("clone方法被执行");
		try{
			return super.clone();
		}catch(CloneNotSupportedException e){
			throw new Error(e);
		}
	}
	public CloneableObject() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CloneableObject obj = new CloneableObject();
		obj.clone();
	}
}
