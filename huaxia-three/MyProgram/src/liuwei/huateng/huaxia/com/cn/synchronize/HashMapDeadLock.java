/**
 * Title: HashMapDeadLock.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��10������4:45:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

import java.util.HashMap;

/**
 * @class_name:HashMapDeadLock2019��12��10��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��10������4:45:51
 */
public class HashMapDeadLock {
	private final HashMap<String,String> map = new HashMap<>();
	public void add(String key,String value){
		this.map.put(key, value);
	}
	/**
	 * 
	 */
	public HashMapDeadLock() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��10������4:45:51
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final HashMapDeadLock hmdl = new HashMapDeadLock();
		for(int x=0;x<2;x++){
			new Thread(new Runnable(){
				@Override
				public void run(){
					for(int i=0;i<Integer.MAX_VALUE;i++){
						hmdl.add(String.valueOf(i), String.valueOf(i));
					}
				}
			}).start();
		}
	}

}
