package com.huaxia.plaze.common;

import com.huaxia.opas.util.AppConfig;

public class InitPropertyParams {

	public static final String YZ_UNIONPAY_IP = AppConfig.getInstance().getValue("unionpay.yz_ip");
	public static final String SJS_UNIONPAY_IP = AppConfig.getInstance().getValue("unionpay.sjs_ip");;
	public static final int YZ_UNIONPAY_PORT = Integer.parseInt(AppConfig.getInstance().getValue("unionpay.yz_port"));
	public static final int SJS_UNIONPAY_PORT = Integer.parseInt(AppConfig.getInstance().getValue("unionpay.sjs_port"));
	public static final int UNIONPAY_TIMEOUT = Integer.parseInt(AppConfig.getInstance().getValue("unionpay.timeout"));

}
