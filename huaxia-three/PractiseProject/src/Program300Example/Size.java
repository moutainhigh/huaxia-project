package Program300Example;
/**
 * 
 * @class_name:Size2020年9月18日
 * @comments:枚举类型。-衣服的型号
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日上午10:40:54
 */
public enum Size {
	SMALL(18.8),MEDIUM(26.8),LARGE(32.8),EXTRA_LARGE(40.8);
	private double value;
	private Size(){
		this(0);
	}
	private Size(double value){
		this.value = value;
	}
	double getValue(){
		return value;
	}
}	
