package ThinkInJava;
import java.util.Arrays;
import java.util.Random;
//每一个向量都有任意的长度
public class RaggedArray {
public static void main(String args[]){
	Random rand = new Random(47);
	//3-D array with varied-length vectors:
	int[][][] a = new int[(rand.nextInt(7)+1)][][];
	for(int i =0;i<a.length;i++){
		a[i] = new int[(rand.nextInt(5)+1)][];
		for(int j=0;j<a[i].length;j++){
			a[i][j] = new int[(rand.nextInt(5)+1)];
		}
	}
	System.out.println(Arrays.deepToString(a));
	for(int i =0;i<a.length;i++){
		for(int j=0;j<a[i].length;j++){
			for(int k=0;k<a[i][j].length;k++){
				System.out.print(a[i][j][k]);
			}
			System.out.println();
		}
	}
}
}