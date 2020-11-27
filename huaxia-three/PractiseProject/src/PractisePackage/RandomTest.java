package PractisePackage;

import java.util.Random;

public class RandomTest {

	public RandomTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<3;i++)
		{
			System.out.println(Math.random());
		}
//		for(int i=0;true;)
//		{
//			while(Math.random()!=0.6544540320776754)
//				System.out.println(Math.random());
//			System.out.println("Math.random() == 0");
//		}
		Random rnd  = new Random ();
		System.out.println(rnd.nextInt());
		System.out.println(rnd.nextInt(100));
		Random rmd = new Random(20160824);
		for(int i=0;i<5;i++){
			System.out.printf(rmd.nextInt(100)+" ");
		}
	}

}
