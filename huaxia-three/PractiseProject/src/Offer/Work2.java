/**
 * Title: Work2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��23������5:19:09
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
 * @class_name:Work22020��9��23��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��23������5:19:09
 */
public class Work2 implements Serializable{
	private String name;
	//transient���εı����������л�
	private transient int salary;
	//��̬�������������Ϣ���ᱻ���л�
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
	 *@Date: 2020��9��23������5:19:09
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
