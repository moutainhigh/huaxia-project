/**
 * Title: AmcountOfConversion.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��13������3:51:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.text.NumberFormat;
import java.util.HashMap;

/**
 * @class_name:AmcountOfConversion2020��8��13��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��13������3:51:53
 */
public class AmcountOfConversion {
	private static final String EMPTY = "";
	private static final String ZERO = "��";
	private static final String ONE = "Ҽ";
	private static final String TWO = "��";
	private static final String THREE = "��";
	private static final String FOUR = "��";
	private static final String FIVE = "��";
	private static final String SIX = "½";
	private static final String SERVEN = "��";
	private static final String EIGHT = "��";
	private static final String NINE = "��";
	private static final String TEN = "ʰ";
	private static final String HUNDRED = "��";
	private static final String THOUSAND = "Ǫ";
	private static final String TEN_THOUSAND = "��";
	private static final String HUNDRED_MILLION ="��";
	private static final String YUAN = "Ԫ";
	private static final String JIAO = "��";
	private static final String FEN = "��";
	private static final String DOT = ".";
	private static AmcountOfConversion formatter = null;
	//������ʼ��һ��HashMap����
	private HashMap NumberMap = new HashMap();
	private HashMap HighnumberofMoney = new HashMap();
	private NumberFormat numberFormat = NumberFormat.getInstance();
	/**
	 * Constructor
	 */
	public AmcountOfConversion() {
		// TODO Auto-generated constructor stub
		numberFormat.setMaximumFractionDigits(4);//�������ݵ�С���������λ����4λ
		numberFormat.setMinimumFractionDigits(2);//�������ݵ�С��������Сλ����2λ
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
	 *@Description: TODO ��ȡʵ��������
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��13������5:14:10
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
	 *@param moneyStr ת�����
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��13������5:22:08
	 */
	public String format(String moneyStr)
	{
		String result = "���ܽ��н��ת����!";
		if(isConversion(moneyStr)){
			result = convertIntegerTochinese(moneyStr);
			result = convertPointTochinese(result);
		}
		return result;
	}
	/**
	 * @Title: format
	 *@Description: TODO ��̬���Ե�ʵ��
	 *@param moneyDouble
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��13������5:28:58
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
	 *@Date: 2020��8��13������5:30:10
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
	 *@Date: 2020��8��13������5:30:52
	 */
	public String format(long moneyLong)
	{
		return format(numberFormat.format(moneyLong));
	}
	/**
	 * @Title: format
	 *@Description: TODO ��̬��ʵ��
	 *@param moneyNum
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��13������5:31:44
	 */
	public String format(Number moneyNum)
	{
		return format(numberFormat.format(moneyNum));
	}
	/**
	 * @Title: convertIntegerTochinese
	 *@Description: TODO ����������ת��������
	 *@param moneyStr 
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��13������5:23:26
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
	 *@Description: TODO ��С�����������ֽ��д���
	 *@param moneyStr
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��13������5:24:22
	 */
	private String convertPointTochinese(String moneyStr){
		return null;
	}
	/**
	 * @Title: isConversion
	 *@Description: TODO �ж��Ƿ���Խ���ת��
	 *@param moneyStr
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��13������5:20:02
	 */
	private boolean isConversion(String moneyStr){
		int fractionDigits = moneyStr.length() - moneyStr.indexOf(DOT) -1;
		boolean flag = true;
		if(fractionDigits > 2)
		{
			System.out.println("���"+moneyStr+"��С��λ��������λ��");
			flag = false;
		}
		return flag;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��13������3:51:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
