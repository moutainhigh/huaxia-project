/**
 * Title: MonkeyKing.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日上午9:18:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import javax.swing.JOptionPane;

/**
 * @class_name:MonkeyKing2020年9月22日
 * @comments:循环出局编号
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日上午9:18:17
 */
public class MonkeyKing {

	/**
	 * Constructor
	 */
	public MonkeyKing() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月22日上午9:18:17
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String s;
		 int n,k,m,n1;
		 s = JOptionPane.showInputDialog("请输入猴子总数：");
		 n = Integer.parseInt(s);
		 n1 = n+1;
		 s = JOptionPane.showInputDialog("请输入起始报数猴子编号：");
		 k = Integer.parseInt(s);
		 s = JOptionPane.showInputDialog("请输入厨具数字");
		 m = Integer.parseInt(s);
		 int a[] = new int[n+1];
		 a[0] = 0;
		 System.out.println("出具的猴子编号：");
		 for(int i=1;i<a.length;i++)
			 a[i] = 1;
			for(int i=1;i<=m;i++){
				if(n == 1)
					break;
				else if(i ==m ){
					n--;
					i =0;
					a[k] = 0;
					System.out.print(k+" ");
				}
				do{
					k++;
					k = k%n1;
				}while(a[k] != 1);
			}
			System.out.println("\n猴王编号为："+k);
		 }
	}
