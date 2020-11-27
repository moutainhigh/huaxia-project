/**
 * Title: P11_4.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月13日上午11:27:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P11_42020年8月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月13日上午11:27:17
 */
public class P11_4 {
	public static final int ROWLEN = 10;//二维空间行数
	public static final int COLLEN = 10;//二维空间列数
	public static int DEAD = 0;//死细胞
	public static int ALIVE  = 1;//活细胞
	public static int[][] cell =new int[ROWLEN][COLLEN];//当前生命细胞状态
	public static int[][] celltemp= new  int[ROWLEN][COLLEN];//用于判断当前细胞的下一个状态
	/**
	 * @Title: initcell
	 *@Description: TODO 初始化细胞分布
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午12:35:17
	 */
	public static void initcell()
	{
		int row ,col;
		Scanner input = new Scanner(System.in);
		for( row = 1;row<ROWLEN; row++)
		{
			for(col = 0;col<COLLEN;col++){
				cell[row][col] = DEAD;
			}
		}
		System.out.printf("请先输入一个活细胞的坐标位置,输入（-1，-1）结束：\n");
		while(true){
			System.out.printf("请输入一个活细胞的坐标位置：");
			row = input.nextInt();
			col = input.nextInt();
			if(0<=row && row<ROWLEN && 0 <= col && col < COLLEN)
			{
				cell[row][col] = ALIVE;
			}
			else if(row == -1 && col == -1){
				break;
			}else{
				System.out.printf("输入坐标超过范围\n");
			}
		}
	}
	/**
	 * @Title: LinSum
	 *@Description: TODO 统计当前细胞周围的活细胞数目
	 *@param row
	 *@param col
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午12:35:58
	 */
	public static int LinSum(int row,int col){
		 int count =0,c,r;
		 for(r= row-1;r<=row+1;r++)
		 {
			 for(c = col -1;c<=col+1;c++){
				 if(r<0||r>=ROWLEN || c<0 || c>=COLLEN)
				 {
					 continue;
				 }
				 if(cell[r][c] == ALIVE){
					 count++;
				 }
			 }
		 }
		 if(cell[row][col] == ALIVE){
			 count --;
		 }
		return count;
	}
	/**
	 * @Title: OutCell 输出显示细胞状态
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午12:45:56
	 */
	public static void OutCell()
	{
		int row,col;
		System.out.printf("\n细胞状态\n");
		System.out.printf(" ┌");
		for(col = 0;col<COLLEN -1;col++){
			System.out.printf("┲");
		}
		System.out.printf("┓\n");
		for(row = 0;row<ROWLEN;row++)
		{
			System.out.printf(" | ");
			for(col = 0;col<COLLEN;col++)
			{
				switch(cell[row][col])
				{
				case 1:
					System.out.printf("●|");
					break;
				case 0:
					System.out.printf("○|");
					break;
				default: ;
				}
			}
			System.out.printf("\n");
			if(row <ROWLEN -1)
			{
				System.out.printf("┠");
				for(col = 0;col<COLLEN-1;col++)
				{
					System.out.print("╃");
				}
				System.out.printf("┧\n");
			}
		}
		System.out.printf("└");
		for(col = 0;col<COLLEN-1;col++)
		{
			System.out.printf("┴");
		}
		System.out.printf("┘\n");
	}
	/**
	 * @Title: cellfun
	 *@Description: TODO 生命游戏算法
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午2:21:31
	 */
	public static void cellfun()
	{
		int row,col,sum;
		int count  = 0;
		for(row = 0;row<ROWLEN;row++)
		{
			for(col = 0;col<COLLEN;col++)
			{
				switch(LinSum(row,col))
				{
				case 2:
					celltemp[row][col] = cell[row][col];
					break;
				case 3:
					celltemp[row][col] = ALIVE;
					break;
				default:
					celltemp[row][col] = DEAD;
				}
			}
		}
		for(row = 0;row<ROWLEN;row++)
		{
			for(col = 0;col<COLLEN;col++)
			{
				cell[row][col] = celltemp[row][col];
			}
		}
		for(row = 0;row<ROWLEN;row++)
		{
			for(col = 0;col<COLLEN;col++)
			{
				if(cell[row][col] == ALIVE)
				{
					count++;
				}
			}
		}
		sum = count;
		OutCell();
		System.out.printf("当前状态下，共有%d个活细胞。\n",sum);
	}
	/**
	 * Constructor
	 */
	public P11_4() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月13日上午11:27:17
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String go = null;
		Scanner input = new Scanner(System.in);
		System.out.printf("生命游戏！\n");
		initcell();
		OutCell();
		do{
			cellfun();
			System.out.print("\n继续生成下一次细胞状态（y/n)?");
			go = input.next();
		}while(go.equalsIgnoreCase("y"));
		System.out.println("游戏结束!");
	}

}
