package UnderstandingTheJVM;
/**
 *  练习trycatchfinally语句
 * @author liuwei
 *
 */
public class Test4 {
	public int inc(){
		int x ;
		try{
			x=1;
			int  a = x / 0;
			return x;
		}catch(Exception e){
			e.printStackTrace();
			x = 2;
			return x;
		}finally{
			x =3;
		}
	}
	public static void main(String args[]){
		System.out.println(new Test4().inc());
	}
}
