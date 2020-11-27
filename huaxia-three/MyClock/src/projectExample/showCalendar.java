package projectExample;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import projectExample.BytesWriteFile;

public class showCalendar {
	
	static final String head[] = {"周次","星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
	static int  weekCount = 0;;
	static String str ="";
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
//		System.out.println("请输入年份：");
//		int year = in.nextInt();
//		System.out.println("请输入月份：");
//		int month  = in.nextInt() -  1;//月份从0开始
		in.close();
		showYear(2019);
		 String fileName = "C:/Users/User/Desktop/JavaFile/myFile.txt";
		 System.out.println("以字节为单位写文件");
		 BytesWriteFile.writeFileByBytes(fileName,str);
//		System.out.println(str);
//		int year = 2019;
//		int month = 0;
		
	}
	public static void showYear(int year){
		weekCount = 1;
		for(int i = 0 ;i < 12 ;i++){
			str +="\r\n"+ (i+1)+"月份"+"\r\n";
			System.out.println((i+1)+"月份");
			 showMonth(year,i);
			 System.out.println();
		}
		  
	}
	
	public static void showMonth(int year,int month){
		int i ;
		GregorianCalendar cal =new GregorianCalendar(year,month,1);
		// 获取这个月的天数
		int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//		System.out.println(totalDays);
		//获取星期几
		int startDay = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(startDay == 0) startDay = 7; //国外的第一天是星期日
//		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
//		System.out.println(startDay);
//		System.out.println(startDay);
		//输出头部星期几
		for(i = 0 ;i< head.length;i++){
			str+=head[i]+"	";
			System.out.print(head[i]+"	");
		}
		str+="\r\n";
		System.out.println();
		//输出一天前的空格
		str+=new String(weekCount+" 	");
		System.out.printf("%2d	",weekCount);
		for(i =  1;i<startDay;i++){
			str+="	";
			System.out.print("	");
		}
		//依次输出每一天
	
		for(int  day =1 ;day<=totalDays;day++){
			
			str+=new String(day+" 	");
			System.out.printf("%2d	",day);
			
			if(i==7){
				weekCount++;
				if((day+1)<=totalDays){//处理一下每月月末最后一天正好是周末的情况
					str+="\r\n";
					System.out.println();
					str+=new String(weekCount+" 	");
					System.out.printf("%2d	",weekCount);
				}
				
				i=0;	
			}
			i++;
		}
	}

}
