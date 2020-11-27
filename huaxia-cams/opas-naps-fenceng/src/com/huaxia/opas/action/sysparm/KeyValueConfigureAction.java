package com.huaxia.opas.action.sysparm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.KeyValueConfigure;
import com.huaxia.opas.service.system.KeyValueConfigureService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 键值对配置表
 * @author 岳晓辉
 */
public class KeyValueConfigureAction implements Action {
	private static Logger logger = LoggerFactory.getLogger(KeyValueConfigureAction.class);

	@Resource(name = "keyValueConfigureService")
	private KeyValueConfigureService keyValueConfigureService;
	
	/**
	 * 查询 键值对配制信息 列表
	 * @param context
	 * @return
	 */
	public void queryKeyValueConfigureList(Context context) throws CoreException{
		Gson gson = new Gson();
		KeyValueConfigure keyValueConfigure = gson.fromJson(gson.toJson(context.getDataMap()), KeyValueConfigure.class);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
		
		if (curPage > 1){
			curNum = (curPage -1) * pageNum;
		}
		
		int count = keyValueConfigureService.queryKeyValueConfigureCount(keyValueConfigure);
		
		List<KeyValueConfigure> list = new ArrayList<KeyValueConfigure>();
		
		if(count > 0){
			list = keyValueConfigureService.queryKeyValueConfigureList(keyValueConfigure, curNum, pageNum);
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		
		context.setDataMap(dataMap);
	}
	
	/**
	 * 添加 键值对配制信息
	 * @author 岳晓辉
	 * @throws Exception
	 */
	public void addKeyValueConfigure(Context context) throws CoreException {
		
		//1，获取界面上下文传过来的实体
		Gson json = new Gson();
		KeyValueConfigure keyValueConfigure = json.fromJson(json.toJson(context.getDataMap()), KeyValueConfigure.class);
		
		//2. 键名称 的非空判断
		if(StringUtils.isEmpty(keyValueConfigure.getKeyName())){
			context.setData("isSuccess", false);
			context.setData("message", "键名称  不能为空");
			return;
		}
		
		//3. 值内容 的非空判断
		if(StringUtils.isEmpty(keyValueConfigure.getValueContent())){
			context.setData("isSuccess", false);
			context.setData("message", "值内容  不能为空");
			return;
		}
		
		//4，验证 键名称 是否已存在
		KeyValueConfigure keyValueConfigure_key = new KeyValueConfigure();
		keyValueConfigure_key.setKeyName(keyValueConfigure.getKeyName());
		int count_key = keyValueConfigureService.queryKeyValueConfigureEqualCount(keyValueConfigure_key);
		if(count_key >= 1){
			context.setData("isSuccess", false);
			context.setData("message", "键名称 "+ keyValueConfigure.getKeyName() + " 已存在");
			return;
		}
		
		//5，把数据 插入到表中
		keyValueConfigure.setAutoId(SequenceUtil.getUUID());
		String crtUser = (String) context.getDataMap().get("userCode_add");
		keyValueConfigure.setCrtUser(crtUser);
		try {
			int count_insert = keyValueConfigureService.insertKeyValueConfigure(keyValueConfigure);
			if(count_insert == 1){
				context.setData("isSuccess", true);
				context.setData("message", "添加成功");
			}else{
				context.setData("isSuccess", false);
				context.setData("message", "添加失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			
			context.setData("isSuccess", false);
			if(e.getMessage().contains("值太大")){
				context.setData("message", "要保存的目的字段长度过大，保存失败（一个汉字等于两个字符的长度）");
			}else{
				context.setData("message", "未知异常导致保存失败");
			}
			
		}
	}
	
	/**
	 * 更新 键值对配制信息
	 * @author 岳晓辉
	 * @param context
	 * @throws CoreException
	 */
	public void updateKeyValueConfigure(Context context) throws CoreException {
		
		//1，获取界面上下文传过来的实体
		Gson json = new Gson();
		KeyValueConfigure keyValueConfigure = json.fromJson(json.toJson(context.getDataMap()), KeyValueConfigure.class);
		
		//2. 键名称 的非空判断
		if(StringUtils.isEmpty(keyValueConfigure.getKeyName())){
			context.setData("isSuccess", false);
			context.setData("message", "键名称  不能为空");
			return;
		}
		
		//3. 值内容 的非空判断
		if(StringUtils.isEmpty(keyValueConfigure.getValueContent())){
			context.setData("isSuccess", false);
			context.setData("message", "值内容  不能为空");
			return;
		}
		
		//4，验证 键名称（不允许 键名称 同名的记录主键有两个）
		KeyValueConfigure keyValueConfigure_key = new KeyValueConfigure();
		keyValueConfigure_key.setKeyName(keyValueConfigure.getKeyName());
		String autoId_fromDB_key = keyValueConfigureService.queryAutoIdEqualByKeyOrValue(keyValueConfigure_key);
		if(!StringUtils.isEmpty(autoId_fromDB_key)){	//如果来自页面的新 键名称 对应在数据库中有 对应的主键
			if(!autoId_fromDB_key.equals(keyValueConfigure.getAutoId())){	//如果来自页面的新的 键名称 对应在数据库中的主键与 页面上的 主键不相等，则不允许更新，不然会导致 键名称 重复
				context.setData("isSuccess", false);
				context.setData("message", "要修改的 键名称 "+ keyValueConfigure.getKeyName() +" 与列表中已有的  键名称 重复，不允许修改");
				return;
			}
		}else{	//如果与数据库里原有的 键名称 不冲突的情况
			
		}
		
		//5，把新数据更新到 数据库中
		String lstUpdUser = (String) context.getDataMap().get("userCode_edit");
		keyValueConfigure.setLstUpdUser(lstUpdUser);
		try {
			keyValueConfigureService.updateKeyValueConfigure(keyValueConfigure);
			context.setData("isSuccess", true);
			context.setData("message", "更新成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			
			context.setData("isSuccess", false);
			if(e.getMessage().contains("值太大")){
				context.setData("message", "要修改的目的字段长度过大，修改失败（一个汉字等于两个字符的长度）");
			}else{
				context.setData("message", "未知异常导致保存失败");
			}
		}
	}
	
	/**
	 * 根据  键名称 得到对的 值内容
	 * @author 岳晓辉
	 * @param context
	 * @throws CoreException
	 */
	public void getValueContentByKeyName(Context context) throws CoreException {
		String keyName = (String) context.getDataMap().get("keyName");
		String valueContent = keyValueConfigureService.getValueContentByKeyName(keyName);
		
		if(!StringUtils.isEmpty(valueContent)){
			context.setData("isSuccess", true);
			context.setData("message", valueContent);
		}else{
			context.setData("isSuccess", false);
			context.setData("message", "没有 "+keyName+" 对应的值");
		}
		
	}
	
}
