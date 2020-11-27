package ThinkInJava;
/**
 * 元组的使用，一个对象返回多个对象
 * @author liuwei
 *
 * @param <A>
 * @param <B>
 */
public class TwoTuple<A,B>{
	public final A first;
	public final B second;
	public TwoTuple(A a,B b){
		first = a;
		second = b;
	}
	@Override
	public String toString() {
		return "TwoTuple [first=" + first + ", second=" + second + "]";
	}
}
