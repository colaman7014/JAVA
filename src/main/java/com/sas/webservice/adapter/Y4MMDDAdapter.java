package com.sas.webservice.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang.StringUtils;

public class Y4MMDDAdapter extends XmlAdapter<String, Date>{

	private static String datePattern = "yyyyMMdd";
	private static SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
	@Override
	public String marshal(Date date) throws Exception {
		if(date != null) {
			return sdf.format(date);
		} else {
			return null;
		}
	}

	@Override
	public Date unmarshal(String dateStr) throws Exception {
		if(StringUtils.isNotBlank(dateStr)) {
			return sdf.parse(dateStr);
		} else {
			return null;
		}
	}

}
