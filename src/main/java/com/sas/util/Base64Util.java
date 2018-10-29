package com.sas.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.net.util.Base64;

public class Base64Util {

	public static String  decodeFromString(String encodeString) {
		if(encodeString != null) {
			byte[] decoder = Base64.decodeBase64(encodeString);
			String decodeString = new String(decoder, StandardCharsets.UTF_8);
			return decodeString;
		}
		return "";
	}
	
	public static String  encodeFromString(String decodeString) {
		if(decodeString != null) {
			byte[] encodedBytes = Base64.encodeBase64(decodeString.getBytes());
			String encodeString = new String(encodedBytes, StandardCharsets.UTF_8);
			return encodeString;
		}
		return "";
	}
	
	public static String fileToBase64(File file) throws IOException {
        byte[] fileByteArr = Base64.encodeBase64(org.apache.commons.io.FileUtils.readFileToByteArray(file));
        String encodedString = new String(fileByteArr);
        return encodedString;
    }
	
	public static InputStream decodeFileByBase64(String fileString) {
		InputStream is = new ByteArrayInputStream(Base64.decodeBase64(fileString));
		return is;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password = "Bot123456";
		//加密
		System.out.println(new String(encodeFromString(password)));
		
		String encodedText = "Qm90MTIzNDU2";
		//解碼
		System.out.println(new String(decodeFromString(encodedText)));
	}

}
