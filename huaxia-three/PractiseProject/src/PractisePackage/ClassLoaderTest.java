package PractisePackage;
/**
 * @class_name:ClassLoaderTest2020��8��6��
 * @comments:���Լ�����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������1:07:10
 */
public class ClassLoaderTest {
	public void loadClass() throws Exception{
//		ClassLoader current = getClass().getClassLoader();
//		Class<?> cls = current.loadClass("PractisePackage.classLoader.IHelloServiceImpl");
//		if(cls != null){
//			return (IHelloService)cls.newInstance();
//		}
////		Object str = clazz.newInstance();
////		str Str = new str();
////		str = "";
//		System.out.println(str.getClass());
	}
	public ClassLoaderTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new ClassLoaderTest().loadClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
