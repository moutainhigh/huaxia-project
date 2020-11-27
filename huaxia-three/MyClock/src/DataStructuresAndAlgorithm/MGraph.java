package DataStructuresAndAlgorithm;

import java.util.Scanner;

/**
 * 邻接矩阵存储的无向图
 * @author liuwei
 *
 */
public class MGraph {
	int MaxSize = 10;
	Integer[]  vertex = new Integer[MaxSize];//存放顶点的一维数组
	Integer[][] arc= new Integer[MaxSize][MaxSize];//存放边的二维数组
	int vertexNum,arcNum;//图的顶点数和边数目
	int[] visited;
	Scanner input = new Scanner(System.in);
	/**
	 * @Title: InitGraph
	 *@Description: 初始化邻接矩阵
	 *@param G
	 *@param a
	 *@param n
	 *@param e
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午7:04:05
	 */
	void InitGraph(MGraph G,int[] a,int n,int e){
		G.vertexNum = n;
		G.arcNum = e;
		for(int i=0;i<G.vertexNum;i++)
			G.vertex[i] = a[i];
		for(int i=0;i<G.vertexNum;i++)
			for(int j=0;j<G.vertexNum;j++)
				G.arc[i][j] = 0;
		for(int k=0;k<G.arcNum;k++)
		{
			int i=input.nextInt();
			int j=input.nextInt();
			G.arc[i][j] = 1;
			G.arc[j][i] = 1;
		}
	}
	/**
	 * @Title: DFSTraverse
	 *@Description: 深度优先遍历图
	 *@param G
	 *@param v
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午7:08:02
	 */
	void DFSTraverse(MGraph G,int v){
//		for(int i=0;i<visited.length;i++)
//			visited[i] = 0;
		System.out.println(G.vertex[v]);
		visited[v] = 1;
		for(int j=0;j<G.vertexNum;j++)
			if(G.arc[v][j]== 1  && (visited[j] == 0))
				DFSTraverse(G,j);
	}
	/**
	 * @Title: BFSTraverse
	 *@Description: 广度优先遍历
	 *@param G
	 *@param v
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午7:35:02
	 */
	void BFSTraverse(MGraph G,int v){
		
	}
	public static void main(String args[]){
		int[] a= {0,1,2,3,4};
		MGraph G = new MGraph();
		G.InitGraph(G,a,5,4);
		G.visited = new int[G.vertexNum];
		G.DFSTraverse(G,0);
		for(int i=0;i<G.vertexNum;i++){
			for(int j=0;j<G.vertexNum;j++)
				System.out.print(G.arc[i][j]+" ");
			System.out.println();
		}
	}
}
