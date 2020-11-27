/**
 * Title: AllData.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月7日上午11:06:56
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:AllData2020年8月7日
 * @comments:枚举法解决填数问题
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月7日上午11:06:56
 */
public class AllData {

	/**
	 * Constructor
	 */
	public AllData() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月7日上午11:06:56
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   int i1,i2,i3,i4,i5;
		   long multi,result;
		   for(i1 = 1;i1<=9;i1++){
			   for(i2 = 0;i2<= 9;i2++){
				   for(i3 = 0;i3<= 9;i3++){
					   for(i4 = 0;i4<= 9;i4++){
						   for(i5 = 0;i5<= 9;i5++){
							   multi = i1*10000+i2*1000+i3*100+i4*10+i5;
							   result = i5*100000+i5*10000+i5*1000+i5*100+i5*10+i5*1;
							   if(multi*i1 == result){
								   System.out.printf("\n%5d%2d%2d%2d%2d\n",i1,i2,i3,i4,i5);
								   System.out.printf("X%12d\n",i1);
								   System.out.println("____________\n");
								   System.out.printf("%3d%2d%2d%2d%2d%2d\n",i5,i5,i5,i5,i5,i5);
							   }
						   }
					   }
				   }
			   }
		   }
	}

}
