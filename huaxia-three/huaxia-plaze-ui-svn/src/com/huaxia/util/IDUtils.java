package com.huaxia.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDUtils {
    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾
 
        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾
 
 
        boolean matches = IDNumber.matches(regularExpression);
 
        //判断第18位校验值
        if (matches) {
 
            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        return false;
                    }
 
                } catch (Exception e) {
                    System.err.println(String.format("身份证号码校验异常:%s", e.getMessage()));
                    return false;
                }
            }
 
        }
        return matches;
    }

    /**
     * 正则：手机号（简单）, 1字头＋10位数字即可.
     * @param in
     * @return
     */
    public static boolean validateMobilePhone(String in) {
        Pattern pattern = Pattern.compile("^[1]\\d{10}$");
        return pattern.matcher(in).matches();
    }
    /**
     * @Title: isNumber
     *@Description: 判断字符串是否是字符串
     *@param input
     *@return
     *@author: LiuWei
     *@Date: 2019年11月15日上午11:09:51
     */
    public static boolean isNumber(String input){
    	  Pattern pattern = Pattern.compile("[0-9]{1,}");
          Matcher matcher = pattern.matcher((CharSequence)input);
          boolean result=matcher.matches();
          return result;
    }
    /**
     * @Title: main
     *@Description: TODO
     *@param args
     *@author: LiuWei
     *@Date: 2019年11月15日上午11:05:42
     */
    public static void main(String args[]){
    	String idNumber = "410221199505293452";
    	String idNumber2 = "410221199505293453";
    	System.out.println(isIDNumber(idNumber));
    	System.out.println(isIDNumber(idNumber2));
    	System.out.println(validateMobilePhone("17778014396"));
    	System.out.println(validateMobilePhone("18612995529"));
        System.out.println(validateMobilePhone("123"));
        String str1 = "13661366666";
        String str2 = "2341234a324123";
        System.out.println(isNumber(str1));
        System.out.println(isNumber(str2));
    }
}