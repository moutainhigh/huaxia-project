package UnderstandingTheJVM;
import static java.lang.invoke.MethodHandles.lookup;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
/**
 * 不知道什么意思，就是这样
 * @author liuwei
 *
 */
public class Test {
 class GrandFather{
	 void thinking(){
		 System.out.println("i am grandfather");
	 }
 }
 class Father extends GrandFather{
	 void thinking(){
		 System.out.println("i am father");
	 }
 }
 class Son extends Father{
	 void thinking(){
		try{
			MethodType mt = MethodType.methodType(void.class);
			MethodHandle mh = lookup().findSpecial(GrandFather.class,"thinking",mt,getClass());
			mh.invoke(this);
		}catch(Throwable e){
			e.printStackTrace();
		}
	 }
 }
 public static void main(String args[]){
	 (new Test().new Son()).thinking();
 }
}
