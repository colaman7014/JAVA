package com.sas.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	
	public static final String y4Formate = "yyyy";
	public static final String defaultDateFormt = "yyyy-MM-dd";
	
	
	public static String getNowWestYr(Date date) {
		if(date != null ) {
			SimpleDateFormat sdf = new SimpleDateFormat(y4Formate);
			return sdf.format(date);
		}
		return null;
	}
	
	/**
	 * @author MengChe
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String getNowDate(Date date, String dateFormat){
		if(date != null ) {
			String format = StringUtils.EMPTY;
			if(StringUtils.EMPTY.equals(dateFormat)){
				format = defaultDateFormt;
			}else{
				format = dateFormat;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		return null;
	}
	
	/**
	 * @author MengChe
	 * @param timestamp
	 * @return
	 */
	public static Date transDate(Timestamp timestamp){
		Date date = new Date(); 
		date = timestamp;
		
		return date;
	}
	
	/**
	 * @author MengChe
	 * @param date
	 * @return
	 */
	public static Timestamp transTimestamp(Date date){
		return new Timestamp(date.getTime());
	}

}
