package PractisePackage;
/**
 * @class_name:ToBeCloned2020��8��6��
 * @comments:����cloneable�ӿ�ʵ�ָ��ƶ���ķ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������10:55:54
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
		System.out.println("clone������ִ��");
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
