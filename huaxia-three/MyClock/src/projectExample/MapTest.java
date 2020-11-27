package projectExample;
import java.util.HashMap;
import java.util.Map;
/**
 * Map的使用
 * @author Liuwe 运行结果
10
{轻量级Java EE企业应用实战	=99, 疯狂ios讲义=99, 疯狂Ajax讲义=79, 疯狂Java讲义=109}
是否包含值为 疯狂IOS讲义 key:true
是否包含值为 99 value:true
轻量级Java EE企业应用实战	-->99
疯狂ios讲义-->99
疯狂Ajax讲义-->79
疯狂Java讲义-->109
{轻量级Java EE企业应用实战	=99, 疯狂ios讲义=99, 疯狂Java讲义=109}
 */
public class MapTest {
	public static void main(String args[]){
		Map map = new HashMap();
		map.put("疯狂Java讲义", 109);
		map.put("疯狂ios讲义", 10);
		map.put("疯狂Ajax讲义", 79);
		map.put("轻量级Java EE企业应用实战	", 99);
		System.out.println(map.put("疯狂ios讲义", 99));
		System.out.println(map);
		System.out.println("是否包含值为 疯狂IOS讲义 key:"+map.containsKey("疯狂ios讲义"));
		System.out.println("是否包含值为 99 value:"+map.containsValue(99));
		for(Object key : map.keySet()){
			System.out.println(key+"-->"+map.get(key));
		}
		map.remove("疯狂Ajax讲义");
		System.out.println(map);
	}
}
