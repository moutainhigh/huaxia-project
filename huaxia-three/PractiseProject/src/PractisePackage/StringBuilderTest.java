package PractisePackage;
/**
 * @class_name:StringBuilderTest2020年8月6日
 * @comments:String Builder的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午1:18:33
 */
public class StringBuilderTest {
	
	public StringBuilderTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("老马说编程");
		sb.insert(0, "关注");
		sb.insert(sb.length(), "老马和你一起探索编程本质");
		sb.insert(7, ",");  
		System.out.println(sb.toString());
	}

}
