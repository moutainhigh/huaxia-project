package ThinkInJava;
/**
 * 测试数组的使用，数组的引用关系一清二楚
 * @author User
 *
 */
public class ArrayOfPrimitives {
	public static void main(String args[]){
		int[] a1= {1,2,3,4,5};
		int[] a2;
		a2 = a1;
		for(int i=0;i<a2.length;i++){
			a2[i] = a2[i]+1;
		}
		for(int i=0; i<a1.length;i++){
			System.out.println("a1["+i+"]="+a1[i]);
			System.out.println("a2["+i+"]="+a2[i]);
		}
	}
}
