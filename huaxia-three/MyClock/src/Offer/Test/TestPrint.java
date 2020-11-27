package Offer.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Offer.util.Tool;

public class TestPrint {

	public TestPrint() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: printA
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019年11月21日上午11:19:35
	 */
	public void printA(){
		System.out.println("A");
	}
	/**
	 * @Title: printB
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019年11月21日上午11:19:33
	 */
	public void printB(){
		System.out.println("B");
	}
	/**
	 * @Title: use
	 *@Description: TODO
	 *@param method
	 *@author: LiuWei
	 *@Date: 2019年11月21日上午11:21:05
	 */
	public void use(Method method)
	{
		TestPrint  tool = new TestPrint();
		try {
			method.invoke(tool);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月21日上午11:21:44
	 */
	public static void main(String args[]){
		Contral test = new Contral();
		test.invoke(0);
	}
}
