package com.huaxia.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/*生成批次号*/
public class BatchNoGenerator {

	/* 生成18位批次号 */
	public synchronized static String generateNextNumber() {
		// 定义日期对象
		Date date = new Date();
		// 定义日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 获取10000以内随机整数
		int num = new Random().nextInt(10000);
		// 定义数字格式，不足4位补0
		DecimalFormat df = new DecimalFormat("0000");
		// 返回日期字符串+随机四位数生成流水号
		return sdf.format(date) + df.format(num);
	}

}