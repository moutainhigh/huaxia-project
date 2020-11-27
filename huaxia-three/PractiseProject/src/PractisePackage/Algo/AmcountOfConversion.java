/**
 * Title: AmcountOfConversion.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月13日下午3:51:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.text.NumberFormat;
import java.util.HashMap;

/**
 * @class_name:AmcountOfConversion2020年8月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月13日下午3:51:53
 */
public class AmcountOfConversion {
	private static final String EMPTY = "";
	private static final String ZERO = "零";
	private static final String ONE = "壹";
	private static final String TWO = "贰";
	private static final String THREE = "叁";
	private static final String FOUR = "肆";
	private static final String FIVE = "伍";
	private static final String SIX = "陆";
	private static final String SERVEN = "柒";
	private static final String EIGHT = "捌";
	private static final String NINE = "玖";
	private static final String TEN = "拾";
	private static final String HUNDRED = "佰";
	private static final String THOUSAND = "仟";
	private static final String TEN_THOUSAND = "万";
	private static final String HUNDRED_MILLION ="亿";
	private static final String YUAN = "元";
	private static final String JIAO = "角";
	private static final String FEN = "分";
	private static final String DOT = ".";
	private static AmcountOfConversion formatter = null;
	//创建初始化一个HashMap对象
	private HashMap NumberMap = new HashMap();
	private HashMap HighnumberofMoney = new HashMap();
	private NumberFormat numberFormat = NumberFormat.getInstance();
	/**
	 * Constructor
	 */
	public AmcountOfConversion() {
		// TODO Auto-generated constructor stub
		numberFormat.setMaximumFractionDigits(4);//设置数据的小数部分最大位数是4位
		numberFormat.setMinimumFractionDigits(2);//设置数据的小数部分最小位数是2位
		numberFormat.setGroupingUsed(false);
		NumberMap.put(0, ZERO);
		NumberMap.put(1,ONE);
		NumberMap.put(2, TWO);
		NumberMap.put(3, THREE);
		NumberMap.put(4,FOUR);
		NumberMap.put(5, FIVE);
		NumberMap.put(6, SIX);
		NumberMap.put(7, SERVEN);
		NumberMap.put(8, EIGHT);
		NumberMap.put(9, NINE);
		NumberMap.put(DOT, DOT);
		HighnumberofMoney.put(1, TEN);
		HighnumberofMoney.put(2, HUNDRED);
		HighnumberofMoney.put(3,THOUSAND);
		HighnumberofMoney.put(4, TEN_THOUSAND);
		HighnumberofMoney.put(5, TEN);
		HighnumberofMoney.put(6, HUNDRED);
		HighnumberofMoney.put(7, THOUSAND);
		HighnumberofMoney.put(8, HUNDRED_MILLION);
	}
	/**
	 * @Title: getInstance
	 *@Description: TODO 获取实例化变量
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午5:14:10
	 */
	public static AmcountOfConversion getInstance()
	{
		if(formatter ==  null)
			formatter = new AmcountOfConversion();
		return formatter;
	}
	/**
	 * @Title: format
	 *@Description: TODO
	 *@param moneyStr 转化结果
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午5:22:08
	 */
	public String format(String moneyStr)
	{
		String result = "不能进行金额转换！!";
		if(isConversion(moneyStr)){
			result = convertIntegerTochinese(moneyStr);
			result = convertPointTochinese(result);
		}
		return result;
	}
	/**
	 * @Title: format
	 *@Description: TODO 多态属性的实现
	 *@param moneyDouble
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午5:28:58
	 */
	public String format(double moneyDouble){
		return format(numberFormat.format(moneyDouble));
	}
	/**
	 * @Title: format
	 *@Description: TODO
	 *@param moneyInt
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午5:30:10
	 */
	 
	public String format(int moneyInt)
	{
		return format(numberFormat.format(moneyInt));
	}
	/**
	 * @Title: format
	 *@Description: TODO
	 *@param moneyLong
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午5:30:52
	 */
	public String format(long moneyLong)
	{
		return format(numberFormat.format(moneyLong));
	}
	/**
	 * @Title: format
	 *@Description: TODO 多态的实现
	 *@param moneyNum
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午5:31:44
	 */
	public String format(Number moneyNum)
	{
		return format(numberFormat.format(moneyNum));
	}
	/**
	 * @Title: convertIntegerTochinese
	 *@Description: TODO 阿拉伯数字转化成中文
	 *@param moneyStr 
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午5:23:26
	 */
	private String convertIntegerTochinese(String moneyStr){
		String result;
		StringBuffer C2CStrBuffer = new StringBuffer();
		for(int i =0;i<moneyStr.length();i++)
		{
			C2CStrBuffer.append(NumberMap.get(moneyStr.substring(i, i+1)));
		}
		int indexOfDot = C2CStrBuffer.indexOf(DOT);
		int moneyPatternCursor = 1;
		for(int i= indexOfDot -1;i>0;i--)
		{
			
		}
		return null;
	}
	/**
	 * @Title: convertPointTochinese
	 *@Description: TODO 对小数点后面的数字进行处理
	 *@param moneyStr
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午5:24:22
	 */
	private String convertPointTochinese(String moneyStr){
		return null;
	}
	/**
	 * @Title: isConversion
	 *@Description: TODO 判断是否可以进行转化
	 *@param moneyStr
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午5:20:02
	 */
	private boolean isConversion(String moneyStr){
		int fractionDigits = moneyStr.length() - moneyStr.indexOf(DOT) -1;
		boolean flag = true;
		if(fractionDigits > 2)
		{
			System.out.println("金额"+moneyStr+"的小数位数多余两位。");
			flag = false;
		}
		return flag;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午3:51:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
