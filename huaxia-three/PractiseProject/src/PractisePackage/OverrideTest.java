package PractisePackage;

import java.util.ArrayList;
import java.util.List;
/**
 * @class_name:SuperClass2020��8��6��
 * @comments:��������͸����ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������12:54:55
 */
class SuperClass{
	public void method(List<?> param){
		System.out.println("���ø����еķ���");
	}
}
class SubClass extends SuperClass{
	public void method(List param){
		System.out.println("���������еķ�����");
	}
}
public class OverrideTest {
	
	public OverrideTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubClass subclass = new SubClass();
		subclass.method(new ArrayList<String>());
	}

}
