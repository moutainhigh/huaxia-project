/**
 * Title: ticker.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月7日下午2:45:34
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:ticker2020年8月7日
 * @comments:假设有一种彩票，每个数字是7个数字组成1-29，7个数字不能相同，写出所有的号码组合
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月7日下午2:45:34
 */
public class ticker {

	/**
	 * Constructor
	 */
	public ticker() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月7日下午2:45:34
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i[] = new int[7] ;
		int j;
		long count =0;
		for(i[0]= 1;i[0]<29;i[0]++){
			for(i[1]= 1;i[1]<29;i[1]++){
				if(i[0] ==i[1]) continue;
				for(i[2]= 1;i[2]<29;i[2]++){
					if(i[0] ==i[2] ||i[1]==i[2]) continue;
					for(i[3]= 1;i[3]<29;i[3]++){
						if(i[0] ==i[3] || i[1] == i[3] ||i[2]== i[3]) 
							continue;
						for(i[4]= 1;i[4]<29;i[4]++){
							if(i[0] ==i[4] || i[1] == i[4] ||i[2]== i[4] || i[3] == i[4]) 
								continue;
							for(i[5]= 1;i[5]<29;i[5]++){
								if(i[0] ==i[5] || i[1] == i[5] ||i[2]== i[5] || i[3] == i[5]||i[4] == i[5]) 
									continue;
								for(i[6]= 1;i[6]<29;i[6]++){
									if(i[0] ==i[6] || i[1] == i[6] ||i[2]== i[6] || i[3] == i[6]||i[4] == i[6] ||i[5] == i[6]) 
										continue;
//									for(j=0;j<7;j++)
//										System.out.printf("%3d",i[j]);
//									System.out.println();
									count++;
									if(count<0) break;
								}
							}
						}
					}
				}
			}
		}
		System.out.println(count);
	}

}
