package com.sas.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

/**
 * SecretUtils {3DES加密解密的工具類 }
 * 
 * @author Eric Su
 * 
 */
public class TripleDesSecretUtils {
	public static final String DEFAULT_PASSWORD_CRYPT_KEY = TripleDesSecretUtils.class.getName();
	private static final Logger logger = LoggerFactory.getLogger(TripleDesSecretUtils.class);

	// 定義加密算法，有DES、DESede(即3DES)、Blowfish
	private static final String Algorithm = "DESede";

	/**
	 * 加密方法
	 * 
	 * @param src
	 *            源數據的字節數組
	 * @return
	 */
	public static byte[] encryptMode(byte[] src, String inKey) {
		byte[] result = null;
		try {
			String key = (inKey == null) ? DEFAULT_PASSWORD_CRYPT_KEY : inKey;
			SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm); // 生成密鑰
			Cipher c1 = Cipher.getInstance(Algorithm); // 實例化負責加密/解密的Cipher工具類
			c1.init(Cipher.ENCRYPT_MODE, deskey); // 初始化為加密模式
			result = c1.doFinal(src);
		} catch (Exception e) {
			logger.error(String.format("encryptMode fail, exception : %s", e.getMessage()), e);
		}

		return result;
	}

	/**
	 * 加密方法
	 * 
	 * @param src
	 *            源數據的字節數組
	 * @return
	 */
	public static String encryptToBase64(String src, String inKey) {
		byte[] secretArr = encryptMode(src.getBytes(), inKey);
		return new String(Base64.encodeBase64(secretArr)); // 再透過 base64 編成可讀的字串
	}

	/**
	 * 加密方法
	 * 
	 * @param src
	 *            源數據的字節數組
	 * @return
	 */
	public static String encryptToHex(String src, String inKey) {
		byte[] secretArr = encryptMode(src.getBytes(), inKey);
		return DatatypeConverter.printHexBinary(secretArr); // 再編成 Hex String 可讀字串
	}

	/**
	 * 解密函數
	 * 
	 * @param src
	 *            密文的字節數組
	 * @return
	 */
	public static byte[] decryptMode(byte[] src, String inKey) {
		byte[] result = null;
		try {
			String key = (inKey == null) ? DEFAULT_PASSWORD_CRYPT_KEY : inKey;
			SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey); // 初始化為解密模式
			result = c1.doFinal(src);
		} catch (Exception e) {
			logger.error(String.format("decryptMode fail, exception : %s", e.getMessage()), e);
		}
		return result;
	}

	/**
	 * 解密函數
	 * 
	 * @param src
	 *            密文的字節數組
	 * @return
	 */
	public static String decryptFromHex(String src, String inKey) {
		byte[] secretArr = DatatypeConverter.parseHexBinary(src);
		byte[] origArr = TripleDesSecretUtils.decryptMode(secretArr, inKey);
		return new String(origArr);
	}

	/**
	 * 解密函數
	 * 
	 * @param src
	 *            密文的字節數組
	 * @return
	 */
	public static String decryptFromBase64(String src, String inKey) {
		byte[] secretArr = Base64.decodeBase64(src);
		byte[] origArr = TripleDesSecretUtils.decryptMode(secretArr, inKey);
		return new String(origArr);
	}

	/*
	 * 根據字符串生成密鑰字節數組
	 * 
	 * @param keyStr 密鑰字符串
	 * 
	 * @return
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
		byte[] key = new byte[24]; // 聲明一個24位的字節數組，默認裡面都是0
		byte[] temp = keyStr.getBytes("UTF-8"); // 將字符串轉成字節數組

		/*
		 * 執行數組拷貝 System.arraycopy(源數組，從源數組哪裡開始拷貝，目標數組，拷貝多少位)
		 */
		if (key.length > temp.length) {
			// 如果temp不夠24位，則拷貝temp數組整個長度的內容到key數組中
			System.arraycopy(temp, 0, key, 0, temp.length);
		} else {
			// 如果temp大於24位，則拷貝temp數組24個長度的內容到key數組中
			System.arraycopy(temp, 0, key, 0, key.length);
		}
		return key;
	}

	public static void main(String[] args) {
		String  msg = "Bot123456";
		String cryptKey = "NameCheck@SAS";
		System.out.println("【加密前】：" + msg);//Bot@sasaml
		

		byte[] b = msg.getBytes();
		String ftpPwd = new String(Base64Utils.encodeToString(b));
		System.out.println(ftpPwd);
//		// 加密
//		String secretStr = TripleDesSecretUtils.encryptToBase64(msg, cryptKey);
//		System.out.println("【加密後轉 Base64】：" + secretStr);
//
//		// 解密
//		String origStr = TripleDesSecretUtils.decryptFromBase64(secretStr, cryptKey);
//		System.out.println("【解密從 Base64  】：" + new String(origStr)); // vm8djPqGyDmtjjQKNwwCtg==
//
//		// 加密
//		secretStr = TripleDesSecretUtils.encryptToHex(msg, cryptKey);
//		System.out.println("【加密後轉 Hex】：" + secretStr);
//
//		// 解密
//		origStr = TripleDesSecretUtils.decryptFromHex(secretStr, cryptKey);
//		System.out.println("【解密從 Hex  】：" + new String(origStr)); // BE6F1D8CFA86C839AD8E340A370C02B6
	}
}
