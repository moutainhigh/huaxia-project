package codeQuerstion;
//把一個矩陣行列互换
/**
 * 
 * @author Liuwei
 *
 */
public class HWjuzhen {

	public static void main(String args[]){
		String inarr="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16";
		int n = 4;
		System.out.println("inarr"+inarr);
		HWjuzhen hwj = new HWjuzhen();
		String result = hwj.matrixTranspose(inarr, n);
		System.out.println(result);
	}
	public String matrixTranspose(String inarr,int n){
		String[] ss = inarr.split(",");
		String[][] css= new String[n][n];
		int k = 0;
		for(int i = 0;i < n;i++){
			for(int j = 0 ;j < n;j++){
				css[i][j] = ss[k];
				k++;
				System.out.printf(css[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		//行列互换
		StringBuffer sb =new  StringBuffer();
		for(int i = 0;i < n;i++){
			for(int j = 0 ;j < n;j++){
				sb.append(css[j][i]+",");
				System.out.printf(css[j][i]+"\t");
			}
			System.out.println();
		}
		return sb.substring(0,sb.length()-1);
	}
}
