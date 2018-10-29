package com.sas.swift.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sas.constraint.SwiftMtConst;

public class SwiftMtBaseBean {
	public List<BicCodeBean> bicCodeBeanList;
	public List<String> orCond = new ArrayList<String>();
	public List<String> inclusiveCond = new ArrayList<String>();
	public Map<String, String> orConditionMap = new HashMap<String, String>();
	/*
	 * "If 'F' and starting with '3/', next 2 characters are Country code If 'A'
	 * , the 8 or 11 characters code are named as the BIC code and inside it the
	 * 5th and 6th characters is the country code"
	 */

	public List<BicCodeBean> parseBicCode(String tag, String rawStr, List<BicCodeBean> bicCodeBeanList) {
		BicCodeBean bicCodeBean = new BicCodeBean();
		
		String prefix = "3/";
		if ("A".equalsIgnoreCase(tag)) {
//			String bicCdoe = rawStr;
			String countryCode = rawStr.substring(4, 6);

			bicCodeBean.setBicCode(rawStr);
			bicCodeBean.setCountryCode(countryCode);
			if(!contains(bicCodeBeanList, bicCodeBean)){
				bicCodeBeanList.add(bicCodeBean);
//				System.out.println(bicCodeBean.toString());
			}
		} else if ("F".equalsIgnoreCase(tag) && rawStr != null && rawStr.indexOf(prefix) > -1) {
			String countryCode = rawStr.substring(rawStr.indexOf(prefix) + 2,
					rawStr.indexOf(prefix) + 4);
			bicCodeBean.setCountryCode(countryCode);
			if(!contains(bicCodeBeanList, bicCodeBean)){
				bicCodeBeanList.add(bicCodeBean);
//				System.out.println(bicCodeBean.toString());
			}
		}
			
		return this.bicCodeBeanList;
	}
	
	public List<BicCodeBean> parseBicCode(String tag, String rawStr, List<BicCodeBean> bicCodeBeanList, boolean chkPOBOX) {
		BicCodeBean bicCodeBean = new BicCodeBean();
		if(chkPOBOX){
			bicCodeBean.setContainsPOBOX(containsPOBOX(rawStr));
		}
		
		String prefix = "3/";
		if ("A".equalsIgnoreCase(tag)) {
//			String bicCdoe = rawStr;
			String countryCode = rawStr.substring(4, 6);

			bicCodeBean.setBicCode(rawStr);
			bicCodeBean.setCountryCode(countryCode);
			if(!contains(bicCodeBeanList, bicCodeBean)){
				bicCodeBeanList.add(bicCodeBean);
//				System.out.println(bicCodeBean.toString());
			}
		} else if ("F".equalsIgnoreCase(tag) && rawStr.indexOf(prefix) > -1) {
			String countryCode = rawStr.substring(rawStr.indexOf(prefix) + 2,
					rawStr.indexOf(prefix) + 4);
			bicCodeBean.setCountryCode(countryCode);
			if(!contains(bicCodeBeanList, bicCodeBean)){
				bicCodeBeanList.add(bicCodeBean);
//				System.out.println(bicCodeBean.toString());
			}
		}
		
		return this.bicCodeBeanList;
	}
	
	public List<BicCodeBean> parseBicCode(String tag, String rawStr) {
		BicCodeBean bicCodeBean = new BicCodeBean();
		
		String prefix = "3/";
		if ("A".equalsIgnoreCase(tag)) {
//			String bicCdoe = rawStr;
			String countryCode = rawStr.substring(4, 6);

			bicCodeBean.setBicCode(rawStr);
			bicCodeBean.setCountryCode(countryCode);
			
			if(!contains(this.bicCodeBeanList, bicCodeBean)){
				bicCodeBeanList.add(bicCodeBean);
//				System.out.println(bicCodeBean.toString());
			}
		} else if ("F".equalsIgnoreCase(tag) && rawStr.indexOf(prefix) > -1) {
			String countryCode = rawStr.substring(rawStr.indexOf(prefix) + 2,
					rawStr.indexOf(prefix) + 4);
			bicCodeBean.setCountryCode(countryCode);
			if(!contains(this.bicCodeBeanList, bicCodeBean)){
				bicCodeBeanList.add(bicCodeBean);
//				System.out.println(bicCodeBean.toString());
			}
		}
			
		return this.bicCodeBeanList;
	}
	
	private boolean contains(List<BicCodeBean> beanList, BicCodeBean bcBean){
		boolean result = false;
		for(BicCodeBean bean : beanList){
			if(bean.getCountryCode().equalsIgnoreCase(bcBean.getCountryCode())){
				if(bean.getBicCode() != null){
					if(bean.getBicCode().equalsIgnoreCase(bcBean.getBicCode())){
						result = true;
					}
				}else{
					if(bcBean.getBicCode() == null){
						result = true;
					}
				}
			}
		}
		return result;
	}
	
	public void screenCondition(String tag, String rawStr, List<BicCodeBean> bicCodeBeanList, Map<String, String> orConditionMap){
		bicCodeBeanList.addAll(parseBicCode(tag, rawStr, bicCodeBeanList));
		
		if (!"A".equalsIgnoreCase(tag) && rawStr != null && rawStr.length() > 0) {
			String[] comps = splitComps(rawStr);
			for(String comp : comps){
//				System.out.println(String.format("98 : [%s] , %s", comp, comp.length()));
				if(!orConditionMap.containsKey(comp) && comp.length() > 0){
					orConditionMap.put(comp, comp);
				}
			}
		}
	}
	
	public void screenCondition(String tag, String rawStr, List<BicCodeBean> bicCodeBeanList, Map<String, String> orConditionMap, boolean chkPOBOX){
		bicCodeBeanList.addAll(parseBicCode(tag, rawStr, bicCodeBeanList, chkPOBOX));
		screenCondition(tag, rawStr, bicCodeBeanList, orConditionMap);
	}
	
	private String[] splitComps(String rawStr){
		String[] result = null;
		Pattern p = Pattern.compile("\\r|\n");
        Matcher m = p.matcher(rawStr);
        String s = m.replaceAll("@@");
        result = s.split("@@");
		return result;
	}
	
	/*
	 * "P O Box", "PO Box", "P.O. Box", "P. O. Box", "GPO Box", "Box", "Postbus"
	 * 
	 */
	public boolean containsPOBOX(String rawStr){
		boolean result = false;
		for(String str : SwiftMtConst.POBOX){
			if(rawStr.toUpperCase().contains(str.toUpperCase())){
				return true;
			}
		}
		return result;
	}

	public List<BicCodeBean> getBicCodeBeanList() {
		return bicCodeBeanList;
	}

	public void setBicCodeBeanList(List<BicCodeBean> bicCodeBeanList) {
		this.bicCodeBeanList = bicCodeBeanList;
	}

	public List<String> getOrCond() {
		return orCond;
	}

	public void setOrCond(List<String> orCond) {
		this.orCond = orCond;
	}

	public List<String> getInclusiveCond() {
		return inclusiveCond;
	}

	public void setInclusiveCond(List<String> inclusiveCond) {
		this.inclusiveCond = inclusiveCond;
	}

	public Map<String, String> getOrConditionMap() {
		return orConditionMap;
	}

	public void setOrConditionMap(Map<String, String> orConditionMap) {
		this.orConditionMap = orConditionMap;
	}

	@Override
	public String toString() {
		return "SwiftMtBaseBean [bicCodeBeanList=" + bicCodeBeanList
				+ ", orCond=" + orCond + ", inclusiveCond=" + inclusiveCond
				+ ", orConditionMap=" + orConditionMap + "]";
	}
}