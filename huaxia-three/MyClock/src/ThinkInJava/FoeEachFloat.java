package ThinkInJava;

import java.util.Random;
/**
 * foreach的循环
 * @author User
 *
 */
public class FoeEachFloat {
	public static void main(String args[]){
		Random rand = new Random();
		float f[] = new float[10];
		for(int i=0;i<10;i++){
			f[i] = rand.nextFloat();
		}
		for(float x:f){
			System.out.println(x);
		}
	}
}
