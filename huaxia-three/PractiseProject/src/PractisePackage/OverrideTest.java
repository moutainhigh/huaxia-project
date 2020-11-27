package PractisePackage;

import java.util.ArrayList;
import java.util.List;
/**
 * @class_name:SuperClass2020年8月6日
 * @comments:测试子类和父类的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午12:54:55
 */
class SuperClass{
	public void method(List<?> param){
		System.out.println("调用父类中的方法");
	}
}
class SubClass extends SuperClass{
	public void method(List param){
		System.out.println("调用子类中的方法。");
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
