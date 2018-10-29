package com.sas.aml.retry.event.queue.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XRetryPropertyUtils {
	private static final Logger logger = LoggerFactory.getLogger(XRetryPropertyUtils.class);
	private static final String file_name = "retryQueue.properties";
	
	/**
	 * load properties
	 * @param propertyFileName
	 * @return
	 */
	public static  Properties loadProperties(String propertyFileName) {
		Properties prop = new Properties();
		InputStreamReader  in = null;
		try {
			URL url = null;
			ClassLoader loder = Thread.currentThread().getContextClassLoader();
			url = loder.getResource(propertyFileName); 
			in = new InputStreamReader(new FileInputStream(url.getPath()), "UTF-8");
			prop.load(in);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return prop;
	}

	public static String getString(String name){
		ResourceBundle res = ResourceBundle.getBundle("retryQueue");
		String str = "";
		try{
			str = res.getString(name);
		}catch(Exception e){
			logger.error(String.format("AmlConfiguration getString fail by key : [%s] , exception : %s}", name, e.getMessage()), e);
		}
		return str;
	}
	
	public static  String getPropString(String key) {
		Properties prop = loadProperties(file_name);
		if (prop!=null) {
			return prop.getProperty(key);
		}
		return null;
	}
	
	public static  void main(String[] args) {
		System.out.println(getString("retry.schedule.mail.enable"));
	}


}
