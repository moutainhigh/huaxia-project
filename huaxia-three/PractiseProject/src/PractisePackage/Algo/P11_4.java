/**
 * Title: P11_4.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��13������11:27:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:P11_42020��8��13��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��13������11:27:17
 */
public class P11_4 {
	public static final int ROWLEN = 10;//��ά�ռ�����
	public static final int COLLEN = 10;//��ά�ռ�����
	public static int DEAD = 0;//��ϸ��
	public static int ALIVE  = 1;//��ϸ��
	public static int[][] cell =new int[ROWLEN][COLLEN];//��ǰ����ϸ��״̬
	public static int[][] celltemp= new  int[ROWLEN][COLLEN];//�����жϵ�ǰϸ������һ��״̬
	/**
	 * @Title: initcell
	 *@Description: TODO ��ʼ��ϸ���ֲ�
	 *@author: LiuWei
	 *@Date: 2020��8��13������12:35:17
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
		System.out.printf("��������һ����ϸ��������λ��,���루-1��-1��������\n");
		while(true){
			System.out.printf("������һ����ϸ��������λ�ã�");
			row = input.nextInt();
			col = input.nextInt();
			if(0<=row && row<ROWLEN && 0 <= col && col < COLLEN)
			{
				cell[row][col] = ALIVE;
			}
			else if(row == -1 && col == -1){
				break;
			}else{
				System.out.printf("�������곬����Χ\n");
			}
		}
	}
	/**
	 * @Title: LinSum
	 *@Description: TODO ͳ�Ƶ�ǰϸ����Χ�Ļ�ϸ����Ŀ
	 *@param row
	 *@param col
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��13������12:35:58
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
	 * @Title: OutCell �����ʾϸ��״̬
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��8��13������12:45:56
	 */
	public static void OutCell()
	{
		int row,col;
		System.out.printf("\nϸ��״̬\n");
		System.out.printf(" ��");
		for(col = 0;col<COLLEN -1;col++){
			System.out.printf("��");
		}
		System.out.printf("��\n");
		for(row = 0;row<ROWLEN;row++)
		{
			System.out.printf(" | ");
			for(col = 0;col<COLLEN;col++)
			{
				switch(cell[row][col])
				{
				case 1:
					System.out.printf("��|");
					break;
				case 0:
					System.out.printf("��|");
					break;
				default: ;
				}
			}
			System.out.printf("\n");
			if(row <ROWLEN -1)
			{
				System.out.printf("��");
				for(col = 0;col<COLLEN-1;col++)
				{
					System.out.print("��");
				}
				System.out.printf("��\n");
			}
		}
		System.out.printf("��");
		for(col = 0;col<COLLEN-1;col++)
		{
			System.out.printf("��");
		}
		System.out.printf("��\n");
	}
	/**
	 * @Title: cellfun
	 *@Description: TODO ������Ϸ�㷨
	 *@author: LiuWei
	 *@Date: 2020��8��13������2:21:31
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
		System.out.printf("��ǰ״̬�£�����%d����ϸ����\n",sum);
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
	 *@Date: 2020��8��13������11:27:17
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String go = null;
		Scanner input = new Scanner(System.in);
		System.out.printf("������Ϸ��\n");
		initcell();
		OutCell();
		do{
			cellfun();
			System.out.print("\n����������һ��ϸ��״̬��y/n)?");
			go = input.next();
		}while(go.equalsIgnoreCase("y"));
		System.out.println("��Ϸ����!");
	}

}
