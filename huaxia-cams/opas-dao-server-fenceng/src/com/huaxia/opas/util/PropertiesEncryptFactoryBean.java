package com.huaxia.opas.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Description:加密、解密 Copyright: Copyright (c) 2016 版权信息 Company: HUATENG 公司信息
 * @Author zhang.xinchun
 * @Version 1.0 版本信息 Created at 2016-5-9 上午9:16:04 创建日期 Modified by XXX at
 *          yyyy-mm-dd 修改信息
 */
public class PropertiesEncryptFactoryBean implements FactoryBean {

	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) throws InvalidKeyException, UnsupportedEncodingException,
			IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		this.properties = properties;
		String oriUsername = properties.getProperty("user");
		String oriPassword = properties.getProperty("password");
		if (oriUsername != null) {
			String newUser = deEncryptString(oriUsername);
			properties.put("user", newUser);
		}
		if (oriPassword != null) {
			String newPass = deEncryptString(oriPassword);
			properties.put("password", newPass);
		}
	}

	/**
	 * 
	 * @Description: 解密
	 * @author zhang.xinchun
	 * @version V1.0
	 * @param oriString
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public String deEncryptString(String oriString) throws InvalidKeyException, UnsupportedEncodingException,
			IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		EncrypAES aes = new EncrypAES();
		String result = aes.decryptor(oriString, "huateng2spdbcccc");
		return result;
	}

	/**
	 * 
	 * @Description: 加密
	 * @author zhang.xinchun
	 * @version V1.0
	 * @param oriString
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 */
	public String encryptString(String oriString) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		EncrypAES aes = new EncrypAES();
		byte[] result = aes.encrypt(oriString, "huateng2spdbcccc");
		return new String(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public Object getObject() throws Exception {
		return getProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class getObjectType() {
		return Properties.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

	public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		PropertiesEncryptFactoryBean bean = new PropertiesEncryptFactoryBean();

		// 加密
		String ori = "camsonline123";
		System.out.println("加密前" + ori);
		String passEncy1 = bean.encryptString(ori);
		System.out.println("加密后" + passEncy1);

		// 解密
		String passDency1 = bean.deEncryptString(passEncy1);
		System.out.println("解密后" + passDency1);

	}

}
