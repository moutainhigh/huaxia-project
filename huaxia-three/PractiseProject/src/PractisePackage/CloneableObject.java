package PractisePackage;
/**
 * @class_name:CloneableObject2020��8��6��
 * @comments:��дclone����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������10:43:37
 */
public class CloneableObject implements Cloneable {
	public Object clone(){
		System.out.println("clone������ִ��");
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
