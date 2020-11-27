/**
 * Title: Work2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日下午5:19:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @class_name:Work22020年9月23日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日下午5:19:09
 */
public class Work2 implements Serializable{
	private String name;
	//transient修饰的变量不会序列化
	private transient int salary;
	//静态变量属于类的信息不会被序列化
	static int age = 100;
	/**
	 * Constructor
	 */
	public Work2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日下午5:19:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("worker.txt");
			try {
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				Work2 testObject = new Work2();
				testObject.setName("alex");
				oos.writeObject(testObject);
				oos.flush();
				oos.close();
				FileInputStream fis = new FileInputStream("worker.txt");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Work2 deTest;
				try {
					deTest = (Work2) ois.readObject();
					System.out.println(deTest.getName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public int getSalary() {
		return salary;
	}

	public static int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public static void setAge(int age) {
		Work2.age = age;
	}
}
