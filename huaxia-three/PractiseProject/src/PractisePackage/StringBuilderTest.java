package PractisePackage;
/**
 * @class_name:StringBuilderTest2020��8��6��
 * @comments:String Builder��ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������1:18:33
 */
public class StringBuilderTest {
	
	public StringBuilderTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("����˵���");
		sb.insert(0, "��ע");
		sb.insert(sb.length(), "�������һ��̽����̱���");
		sb.insert(7, ",");  
		System.out.println(sb.toString());
	}

}
