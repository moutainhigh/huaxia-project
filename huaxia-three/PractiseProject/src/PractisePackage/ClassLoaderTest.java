package PractisePackage;
/**
 * @class_name:ClassLoaderTest2020年8月6日
 * @comments:测试加载类
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午1:07:10
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
