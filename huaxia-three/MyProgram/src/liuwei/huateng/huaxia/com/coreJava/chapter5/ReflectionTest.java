/**
 * Title: ReflectionTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月30日下午3:45:57
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * @class_name:ReflectionTest2019年12月30日
 * @comments: 输出类的方法和变量
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月30日下午3:45:57
 */
public class ReflectionTest {

	/**
	 * 
	 */
	public ReflectionTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月30日下午3:45:57
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//read class name from command line args or user input
		String name;
		if(args.length>0)name = args[0];
		else{
			Scanner in = new Scanner(System.in);
			System.out.println("Enter class name(e.g. java.util.Date):");
			name = in.next();
		}
		try{
			//print class name and superClass name(if！=Object）
			Class c1 = Class.forName(name);
			Class superc1 = c1.getSuperclass();
			String modifiers = Modifier.toString(c1.getModifiers());
			if(modifiers.length() >0)
				System.out.print(modifiers+" ");
			System.out.print("class "+name);
			if(superc1 != null && superc1 != Object.class)
				System.out.print(" extends "+superc1.getName());
			System.out.print("\n{\n");
			printConstructors(c1);
			System.out.println();
			printMethods(c1);
			System.out.println();
			printFields(c1);
			System.out.println("}");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} 
		System.exit(0);
	}
	/**
	 * @Title: printConstructors
	 *@Description: 打印构造方法
	 *@param c1
	 *@author: LiuWei
	 *@Date: 2019年12月30日下午4:34:03
	 */
	public static void printConstructors(Class c1){
		Constructor[] constructors = c1.getDeclaredConstructors();
		for(Constructor c :constructors){
			String name = c.getName();
			System.out.print("\t");
			String modifiers = Modifier.toString(c.getModifiers());
			if(modifiers.length() >0)
				System.out.print(modifiers+" ");
			System.out.print(name+"(");
			//print parameter types
			Class[] paramTypes = c.getParameterTypes();
			for(int j=0;j<paramTypes.length;j++)
			{
				if(j>0)System.out.print(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}
	/**
	 * @Title: printMethods
	 *@Description: Print all methods of a class
	 *@param c1
	 *@author: LiuWei
	 *@Date: 2019年12月30日下午4:36:35
	 */
	public static void printMethods(Class c1)
	{
		Method[] methods = c1.getDeclaredMethods();
		for(Method m :methods)
		{
			Class retType = m.getReturnType();
			String name = m.getName();
			System.out.print("\t");
			//print modifiers,return type and method name
			String modifiers = Modifier.toString(m.getModifiers());
			if(modifiers.length()>0)
				System.out.print(modifiers+" ");
			System.out.print(retType.getName()+" "+name+"(");
			//print parameter types;
			Class[] paramTypes = m.getParameterTypes();
			for(int j=0;j<paramTypes.length;j++)
			{
				if(j>0)System.out.print(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}
	/**
	 * @Title: printFields
	 *@Description: TODO
	 *@param c1
	 *@author: LiuWei
	 *@Date: 2019年12月30日下午4:50:15
	 */
	public static void printFields(Class c1)
	{
		Field[] fields = c1.getDeclaredFields();
		for(Field f:fields)
		{
			Class type = f.getType();
			String name = f.getName();
			System.out.print("\t");
			String modifiers = Modifier.toString(f.getModifiers());
			if(modifiers.length()>0)
				System.out.print(modifiers+" ");
			System.out.println(type.getName()+" "+name+";");
		}
	}
}
