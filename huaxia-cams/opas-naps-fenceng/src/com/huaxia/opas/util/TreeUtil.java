package com.huaxia.opas.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeUtil {
	
	public static List<String> getTreeNodeIds(Map map,String keyName){
			
		List<String> list = getIds(map,keyName);
		
		StringBuilder sb = new StringBuilder();	
		
		for(int i =0; i<list.size(); i++){
			
			sb.append(list.get(i));
			
			sb.append(",");
			
		}
		
//		return sb.substring(0, sb.length()-1);
		return list;
				
	}
	
	private static List<String> getIds(Map dataMap,String keyName){
		
		String id = String.valueOf(dataMap.get(keyName));
		
		List<String> idList = new ArrayList<String>();
		
		idList.add(id);
		
		if(dataMap.containsKey("children")){
		
		List list = (ArrayList)dataMap.get("children");
		
		for(int i =0; i < list.size(); i++){
			
			Map map = (Map)list.get(i);
			
			idList.addAll((getIds(map,keyName)));
			
		}		
		
	}
					
		return idList;
		
	}

}
