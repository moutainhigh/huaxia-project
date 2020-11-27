/**
 * 获取小数位精确位数
 * 
 * @param num
 *            数据
 * @returns 小数位精确位数
 */
function fnGetNum(num) {
	var result = 0;
	try {
		result = num.toString().split('.')[1].length;
	} catch (e) {
		result = 0;
	}
	return result;
}

/**
 * 加法运算（避免数据相加小数点后产生多位数和计算精度损失）
 * 
 * @param num1
 *            加数1
 * @param num2
 *            加数2
 * @returns 加数1 + 加数2
 */
function fnAdd(num1, num2) {
	var baseNum, baseNum1, baseNum2;
	baseNum1 = fnGetNum(num1);
	baseNum2 = fnGetNum(num2);
	baseNum = Math.pow(10, Math.max(baseNum1, baseNum2));
	return (num1 * baseNum + num2 * baseNum) / baseNum;
}

/**
 * 减法运算（避免数据相减小数点后产生多位数和计算精度损失）
 * 
 * @param num1
 *            被减数1
 * @param num2
 *            减数2
 * @returns 被减数1 - 减数2
 */
function fnSubstract(num1, num2) {
	var baseNum, baseNum1, baseNum2;

	// 精度
	var precision;

	baseNum1 = fnGetNum(num1);
	baseNum2 = fnGetNum(num2);

	baseNum = Math.pow(10, Math.max(baseNum1, baseNum2));
	precision = (baseNum1 >= baseNum2) ? baseNum1 : baseNum2;
	return ((num1 * baseNum - num2 * baseNum) / baseNum).toFixed(precision);
}

/**
 * 乘法运算（避免数据相减小数点后产生多位数和计算精度损失）
 * 
 * @param num1
 *            被乘数1
 * @param num2
 *            乘数2
 * @returns 被乘数1 * 乘数2
 */
function fnMultiply(num1, num2) {
	var baseNum = 0;
	baseNum += fnGetNum(num1);
	baseNum += fnGetNum(num2);
	return Number(num1.toString().replace('.', ''))
			* Number(num2.toString().replace('.', '')) / Math.pow(10, baseNum);
}

/**
 * 除法运算（避免数据相减小数点后产生多位数和计算精度损失）
 * 
 * @param num1
 *            被除数1
 * @param num2
 *            除数2
 * @returns 被除数1 / 除数2
 */
function fnDivide(num1, num2) {
	var baseNum1 = baseNum2 = 0;
	var baseNum3, baseNum4;
	var result = 0;
	baseNum1 = fnGetNum(num1);
	baseNum2 = fnGetNum(num2);
	with (Math) {
		baseNum3 = Number(num1.toString().replace('.', ''));
		baseNum4 = Number(num2.toString().replace('.', ''));
		result = (baseNum3 / baseNum4) * Math.pow(10, baseNum2 - baseNum1);
		return isNaN(result) ? 0 : result;
	}
}

/**
 * 求和
 * 
 * @param num1
 *            加数1
 * @param num2
 *            加数2
 */
function fnSum(num1, num2) {
	return fnAdd(num1, num2);
}