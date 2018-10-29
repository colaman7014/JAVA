package com.sas.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;

import org.springframework.util.ReflectionUtils;


public class AmlRoutRuleComparator implements Comparator<Object> {
	
	private Map<String, BigDecimal> routeRuleOrder;
	
	public AmlRoutRuleComparator(Map<String, BigDecimal> routeRuleOrder) {
		this.routeRuleOrder = routeRuleOrder;
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		BigDecimal o1Score , o2Score;
		Field o1WatchListCd = ReflectionUtils.findField(o1.getClass(), "watchListTypeCd");
		Field o2WatchListCd = ReflectionUtils.findField(o2.getClass(), "watchListTypeCd");
		ReflectionUtils.makeAccessible(o1WatchListCd);
		ReflectionUtils.makeAccessible(o2WatchListCd);
		
		String o1Type = (String) ReflectionUtils.getField(o1WatchListCd, o1);
		String o2Type = (String) ReflectionUtils.getField(o2WatchListCd, o2);
		if(o1Type != null && o1Type.indexOf(",") > 0) {
		     o1Score = routeRuleOrder.get(o1Type.split(",")[0]);
	    } else {
	    	o1Score = routeRuleOrder.get(o1Type);
	    }
	    if(o2Type != null && o2Type.indexOf(",") > 0) {
	    	o2Score = routeRuleOrder.get(o2Type.split(",")[0]);
	    } else {
	    	o2Score = routeRuleOrder.get(o2Type);
	    }
		return o1Score.compareTo(o2Score);
	}
	
	public void setRouteRuleOrder(Map<String, BigDecimal> routeRuleOrder) {
		this.routeRuleOrder = routeRuleOrder;
	}
}