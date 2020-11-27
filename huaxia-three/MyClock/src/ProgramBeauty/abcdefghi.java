package ProgramBeauty;

/**
 * 神奇的九位数
 * 前n位都能被n整除
 * @author liuwei
 *
 */
public class abcdefghi {
 
	public static void main(String args[]) {
		int count = 0;
		for (int i1 = 1; i1 <= 9; i1++) {
			for (int i2 = 1; i2 <= 9; i2++) {
				for (int i3 = 1; i3 <= 9; i3++) {
					for (int i4 = 1; i4 <= 9; i4++) {
						for (int i5 = 1; i5 <= 9; i5++) {
							for (int i6 = 1; i6 <= 9; i6++) {
								for (int i7 = 1; i7 <= 9; i7++) {
									for (int i8 = 1; i8 <= 9; i8++) {
										for (int i9 = 1; i9 <= 9; i9++) {

											if(calcul(i1,i2,i3,i4,i5,i6,i7,i8,i9)!=-1){
												System.out.println(calcul(i1,i2,i3,i4,i5,i6,i7,i8,i9));
												count++;
											};
											
											
										}
									}
								}
							}
						}
					}
				}

			}
		}
		System.out.println("共有"+count+"个");
	}
	public static int  calcul(int i1,int i2,int i3,int i4,int i5,int i6,int i7,int i8,int i9){
		int a2 = i1*10+i2;
		int a3= i1*100+i2*10+i3;
		int a4 = i1*1000+i2*100+i3*10+i4;
		int a5 = i1*10000+i2*1000+i3*100+i4*10+i5;
		int a6 = i1*100000+i2*10000+i3*1000+i4*100+i5*10+i6;
		int a7 = i1*1000000+i2*100000+i3*10000+i4*1000+i5*100+i6*10+i7;
		int a8 = i1*10000000+i2*1000000+i3*100000+i4*10000+i5*1000+i6*100+i7*10+i8;
		int a9 = i1*100000000+i2*10000000+i3*1000000+i4*100000+i5*10000+i6*1000+i7*100+i8*10+i9;
		if(a2%2==0){
			if(a3%3 ==0){
				if(a4 %4 ==0){
					if(a5 %5 ==0){
						if(a6 %6 ==0){
							if(a7 %7 ==0){
								if(a8 %8 ==0){
									if(a9 %9 ==0){
										return a9;
									}
								}
							}
						}
					}
				}
			}
		}
		return -1;
	}
}
