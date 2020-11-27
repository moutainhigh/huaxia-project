package com.huaxia.opas.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉语拼音工具类
 * 
 * @author zhiguo.li
 *
 */
public class PinYinUtil {

	/** 小写 */
	public static final boolean LOWERCASE = false;

	/** 大写 */
	public static final boolean UPPERCASE = true;

	/**
	 * 获取汉字串大写首字母
	 * 
	 * @param chinese
	 *            汉字串
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getFirstUpperChar(String chinese) throws BadHanyuPinyinOutputFormatCombination {
		return getFirstChar(chinese, UPPERCASE);
	}

	/**
	 * 获取汉字串小写首字母
	 * 
	 * @param chinese
	 *            汉字串
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getFirstLowerChar(String chinese) throws BadHanyuPinyinOutputFormatCombination {
		return getFirstChar(chinese, LOWERCASE);
	}

	/**
	 * 获取汉字串首字母
	 * 
	 * @param chinese
	 *            汉字串
	 * @param upperCase
	 *            首字母大写
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getFirstChar(String chinese, boolean upperCase) throws BadHanyuPinyinOutputFormatCombination {
		StringBuffer buffer = new StringBuffer();
		char[] charArray = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		if (upperCase) {
			defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		} else {
			defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		}
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] > 128) {
				String[] array = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
				if (array != null) {
					buffer.append(array[0].charAt(0));
				} else {
					buffer.append(charArray[i]);
				}
			}
		}
		return buffer.toString().replaceAll("\\W", "").trim().substring(0, 1);
	}

	/**
	 * 将字符串中的中文转化为拼音，其他字符不变。
	 * 
	 * @param chinese
	 *            汉字串
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getPinYin(String chinese) throws BadHanyuPinyinOutputFormatCombination {
		StringBuffer buffer = new StringBuffer();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

		char[] charArray = chinese.trim().toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (java.lang.Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
				buffer.append(PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat)[0]);
			} else {
				buffer.append(java.lang.Character.toString(charArray[i]));
			}
		}
		return buffer.toString();
	}

	/**
	 * 获取汉字串拼音首字母，英文字符不变。
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音首字母
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getFirstSpell(String chinese) throws BadHanyuPinyinOutputFormatCombination {
		StringBuffer buffer = new StringBuffer();
		char[] charArray = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] > 128) {
				buffer.append(PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat)[0].charAt(0));
			} else {
				buffer.append(charArray[i]);
			}
		}
		return buffer.toString().replaceAll("\\W", "").trim();
	}

	/**
	 * 获取汉字串拼音，英文字符不变。
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getFullSpell(String chinese) throws BadHanyuPinyinOutputFormatCombination {
		StringBuffer buffer = new StringBuffer();
		char[] charArray = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] > 128) {
				buffer.append(PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat)[0]);
			} else {
				buffer.append(charArray[i]);
			}
		}
		return buffer.toString();
	}

	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		String convert = PinYinUtil.getPinYin("中国人民银行");
		System.out.println(convert);
	}

}
