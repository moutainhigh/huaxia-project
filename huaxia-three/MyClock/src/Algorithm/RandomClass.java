package Algorithm;

import java.util.Random;

/**
 * 使用随机数的测试类，random类的各种类型
 * @author liuwei
 * 运行结果
1. 使用Random类的构造方法生成谁随机数的示例如下：
生成double型的随机数列如下：
0.058085836873242536 0.392733502901373 0.9623733380177192 0.7216340959059433 0.29138794004379287 
生成float型的随机数列如下：
0.40333593 0.10100454 0.622739 0.64454925 0.7080605 
生成long型的随机数列如下：
-5921240582146199751 2059558477627532102 3604649552211992559 7493089150013436008 4760558751151236132 
生成int型的随机数列如下：
353872934 1503234972 -828928465 -217610039 -2003511006 
生成正态分布类型的double型的随机数列如下：
0.003627446075614414 -0.13673537866402008 1.4094045231244818 -1.1910174255603687 -1.0310126239227813 

2.在指定范围内产生随机数列：
在[0,8)的范围内产生的随机数列如下
6 0 0 0 2 
在[5,50)的范围内产生的随机数列如下
7 30 31 24 27 
在[0,100)的范围内产生的float型的随机数如下：
77.528824 89.96907 62.564148 30.05159 41.198635 
 */
public class RandomClass {
	public static void randomType(){
		System.out.println("1. 使用Random类的构造方法生成谁随机数的示例如下：");
		Random rdm = new Random();
		int a =0 , b=0,c= 0,d=0,e=0;
		while(true){
			if(a<5){
				if(a==0){
					System.out.println("生成double型的随机数列如下：");
				}
				System.out.print(rdm.nextDouble()+ " ");
				a++;
			}else if(a==5 && b<5){
				if(a==5 && b==0){
					System.out.println("\n生成float型的随机数列如下：");
				}
				System.out.print(rdm.nextFloat()+ " ");
				b++;
			}else if(b==5 && c<5){
				if(b==5 && c==0){
					System.out.println("\n生成long型的随机数列如下：");
				}
				System.out.print(rdm.nextLong()+ " ");//均匀分布产生的随机数
				 
				c++;
			}else if(c==5 && d<5){
				if(c==5 && d==0){
					System.out.println("\n生成int型的随机数列如下：");
				}
				System.out.print(rdm.nextInt()+ " ");//均匀分布产生的随机数
				d++;
			}else if(d==5 && e<5){
				if(d==5 && e==0){
					System.out.println("\n生成正态分布类型的double型的随机数列如下：");
				}
				System.out.print(rdm.nextGaussian()+ " ");//均匀分布产生的随机数
				e++;
			}else if( e==5){
				break;
			}
		}
	}
	public static void nextInt(){
		System.out.println("\n\n2.在指定范围内产生随机数列：");
		System.out.println("在[0,8)的范围内产生的随机数列如下");
		Random rdm = new Random();
		for(int i=0;i<5;i++){
			//Random的nextInt(n)方法产生[0,n]范围内的随机数
			System.out.print(rdm.nextInt(7)+" ");
		}
		System.out.println();
		System.out.println("在[5,50)的范围内产生的随机数列如下");
		for(int i=0;i<5;i++){
			//Random的nextInt(n)方法产生[0,n]范围内的随机数
			System.out.print((5+rdm.nextInt(45))+" ");
		}
		System.out.println();
		System.out.println("在[0,100)的范围内产生的float型的随机数如下：");
		for(int i=0;i<5;i++){
			//Random的nextInt(n)方法产生[0,n]范围内的随机数
			System.out.print((rdm.nextFloat()*100)+" ");
		}
		System.out.println();
	}
	public static void main(String args[]){
		randomType();
		nextInt();
	}
}
