/**
 * 标准卡特殊【违例码】强制提交
 * 返回1，则表示可以正常提交。返回0则表示需要强制提交上级
 */
function tsBzk_violateCodeSubmit(violateCode,roleCode){
	if(roleCode=="L2"||roleCode=="L3"){
		//对应L2、L3角色强制提交的违例码
		if(violateCode=="A101"||violateCode=="A102"||violateCode=="A106"||
			violateCode=="A203"||violateCode=="A204"||violateCode=="A205"||
			violateCode=="A209"||violateCode=="A210"||violateCode=="A214"||
			violateCode=="A215"||violateCode=="A216"||violateCode=="A301"||
			violateCode=="A302"||violateCode=="A304"||violateCode=="A401"||
			violateCode=="A702"){
			return "0"
		}else//对应L2、L3角色强制提交的违例码  A207(下浮大于50%提交时)
		if(violateCode=="A207"){
			return "0"
		}else //对应L3角色强制提交的违例码
		if(roleCode=="L3"&&(violateCode=="A107"||violateCode=="A108"||violateCode=="A217")){
			return "0"
		}
	}
	return "1";
}
/**
 * 标准卡特殊【拒绝码】强制提交
 * 返回1，则表示可以正常提交。返回0则表示需要强制提交上级
 */
function tsBzk_refuseCodeSubmit(refuseCode,roleCode){
	if(roleCode=="L2"||roleCode=="L3"){
		//对应L2、L3角色强制提交的违例码
		if(refuseCode=="D213"||refuseCode=="D401"||refuseCode=="D704"){
			return "0"
		}
	}
	return "1";
}
/**
 * 【易达金】特殊【违例码】强制提交
 *  对于特殊情况下的违例码可不提交上级操作，其他的均提交上级
 * 返回1，则表示可以正常提交。返回0则表示需要强制提交上级
 */
function tsYdj_violateCodeSubmit(violateCode){
	//对应L1、L2、L3角色以下违例码均  不需要  强制提交的违例码
	if(violateCode=="YA107"||violateCode=="YA108"||violateCode=="YA109"||
		violateCode=="YA114"||violateCode=="YA701"||violateCode=="YA801"||
		violateCode=="YA901"){
		return "1"
	}else{
		return "0";
	}
}