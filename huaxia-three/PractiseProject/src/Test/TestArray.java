package Test;

import java.io.UnsupportedEncodingException;

public class TestArray {

	public TestArray() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String args[]){
		byte[] body = new byte[1435623150];
//		byte[] body = "Hello World".getBytes();
		/*try {
			new String(new byte[143562315*3], "ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
//		for(int i=0;i<body.length-1;i++){
//			body[i] = '1';
//		}
//		body[body.length-1]= '0';
		StringBuilder sb = new StringBuilder();
//		int b = 143562315;//拆分数组的大小，100兆左右
//		int f =body.length/b;//拆分成f个数组
//		for(int i=0;i<f;i++){
//			byte[] bbb = new byte[b];
////			if(i == f-1){
////				 bbb = new byte[body.length-i*b];
////			}
//			for(int j=0;j<bbb.length;j++){
//				if(j+i*b<body.length){
//					bbb[j]=body[j+i*b];
//					
//				}
//			}
//			try {
//				sb.append(new String(bbb, "ISO-8859-1"));
//				System.out.println(sb.length());
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		for(int i=0;i<body.length;i++){
//			sb.append(new String(body[i], "ISO-8859-1"));
		}
		System.out.println(sb.toString());
	}
}
