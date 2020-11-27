package DataStructuresAndAlgorithm;
/**
 * 为了拼够300行而写的程序
 * @author liuwei
 */
public class HelloWorld {
	public static void main(String args[]){
		for(int u=0;u<10000;u++){
//			System.out.println("Hello World!");
//			System.out.println("         *         ");
//			System.out.println("       *****       ");
//			System.out.println("   ****************  ");
//			System.out.println("********************");
//			System.out.println("   ****************  ");
//			System.out.println("       *****        ");
//			System.out.println("         *          ");
//			System.out.println("Hello World!");
			int starLine = u%40;
			for(int i=1;i<=starLine;i++){
				for(int j=1;j<=starLine-i;j++){
					System.out.print(" ");
				}
				for(int k=1;k<=2*i-1;k++){
					System.out.print("*");
				}
				System.out.println();
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

