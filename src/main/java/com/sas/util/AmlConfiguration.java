package com.sas.util;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sas.constraint.SwiftMtConst;

public class AmlConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(AmlConfiguration.class);
	public static String getString(String name){
		ResourceBundle res = ResourceBundle.getBundle("global");
		String str = "";
		try{
			str = res.getString(name);
		}catch(Exception e){
			logger.error(String.format("AmlConfiguration getString fail by key : [%s] , exception : %s}", name, e.getMessage()), e);
		}
		
		return str;
	}
	
	public static String getString(String propertiesName, String name){
		ResourceBundle res = ResourceBundle.getBundle(propertiesName);
		String str = "";
		try{
			str = res.getString(name);
		}catch(Exception e){
			logger.error(String.format("AmlConfiguration getString fail by key : [%s] , exception : %s}", name, e.getMessage()), e);
		}
		
		return str;
	}
	
	public static String getAmlCatalog(){
		return getString(SwiftMtConst.COM_SAS_JPACFG_PROPERTIES, "spring.jpa.properties.hibernate.aml_catalog");
	}
	
	public static String getFcfcoreSchema(){
		return getString(SwiftMtConst.COM_SAS_JPACFG_PROPERTIES, "spring.jpa.properties.hibernate.fcfcore_schema");
	}
	
	public static String getEcmSchema(){
		return getString(SwiftMtConst.COM_SAS_JPACFG_PROPERTIES, "spring.jpa.properties.hibernate.ecm_schema");
	}
	
	public static String getFcfkcSchema(){
		return getString(SwiftMtConst.COM_SAS_JPACFG_PROPERTIES, "spring.jpa.properties.hibernate.fcfkc_schema");
	}
	
	public static String getNcScCatalog(){
		return getString(SwiftMtConst.COM_SAS_JPACFG_PROPERTIES, "spring.jpa.properties.hibernate.default_catalog");
	}
}
