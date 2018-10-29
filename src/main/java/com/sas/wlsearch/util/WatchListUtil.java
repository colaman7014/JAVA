package com.sas.wlsearch.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.Base64Utils;

import com.sas.constraint.SwiftMtConst;
import com.sas.util.AmlConfiguration;
import com.sas.util.StringUtils;
import com.sas.wlsearch.bean.ScoreWeightConfig;
import com.sas.wlsearch.bean.WatchListBean;

public class WatchListUtil {
	
	public static String getFirstNCharactersInBCCCode(String bicCode) {
		if(bicCode != null) {
			int bccStrNumber = Integer.parseInt(AmlConfiguration.getString("com.sas.swiftCheck.bccCodeNNumbers")) != 0 
					? Integer.parseInt(AmlConfiguration.getString("com.sas.swiftCheck.bccCodeNNumbers")) 
					: bicCode.length();
			bicCode = bicCode.substring(0, Math.min(bicCode.length(), bccStrNumber));
			if(bicCode.length() >= bccStrNumber)
				bicCode = String.format("%s%%", bicCode);
		} else {
			bicCode = "";
		}
		return bicCode;
	}
	
	public static List<String> handelInclusiveList(String inputCnName, String inputEnName){
		List<String> inclusiveList = new ArrayList<String>();
		if(!StringUtils.isEmpty(inputCnName)){
			List<List<String>> cnList = WatchListUtil.permutation(inputCnName, false);
			if(cnList != null && cnList.size() > 0){
				handelInclusiveString(inclusiveList, cnList);
			}
		}
		if(!StringUtils.isEmpty(inputEnName)){
			List<List<String>> enList = WatchListUtil.permutation(inputEnName, true);
			if(enList != null && enList.size() > 0){
				handelInclusiveString(inclusiveList, enList);
			}
		}
		return inclusiveList;
	}
	
	
	/**
	 * Inclusive 字串轉換處理
	 * @param inclusiveList
	 * @param nameList
	 */
	private static void handelInclusiveString(List<String> inclusiveList, List<List<String>> nameList){
		for(List<String> strList : nameList){
			StringBuffer sb = new StringBuffer();
			for(String str : strList){
				sb.append(String.format("AND \"%s\" ", str));
			}
			inclusiveList.add(sb.substring(4));
		}
	}
	
	public static void main(String args[]){
		WatchListUtil wl = new WatchListUtil();
//		System.out.println(ss.indexOf(" RFBCUBA BET072"));
//		System.out.println("["+lrTrim("   RFBCUBA BET072 ")+"]");
//		Map<String, String> selectMap = new HashMap<String, String>();
//		String[] testStr = {"ORDERRES","BE","MEILAAN","1","9000","GENT"};
//		int maxIndex = testStr.length >= 6 ? 5 : testStr.length; 
//		for(int i=1; i<=maxIndex; i++){
//			arrangementSelectMap(testStr, i, selectMap);
//		}
//		System.out.println("total select : " + selectMap.size());
		
//		shiftSelectMap(testStr, 5, selectMap);
//		String ss = "abc's";
//		ss = ss.replaceAll("'", "''");
//		String result = String.format("ss : %s", ss);
//		System.out.println(result);
		String str = "ONE LOT OF SPARE PARTS FOR FP-1 ELECTROCHLORINATION SYSTEM PRICE TERM:FOB JAPANESE PORT";
		String s[] = WatchListUtil.convertWordToSpace(str, SwiftMtConst.COVERTWORD).split(" ");
		
		long maxIndex = 10;
		long limitIndex = 3;
//		System.out.println((float)limitIndex/maxIndex);
//		List<String> shiftList = wl.getShiftInMaxList(s,maxIndex);
//		for(String shift : shiftList){
//			String[] shiftInMax = shift.split(" "); 
//			if(shiftInMax.length == maxIndex){
//				getArrangementInLimitList(shiftInMax, limitIndex);
//			}
//		}
		
//		List<String> resultList = wl.getShiftInMaxArrangementInLimitList(s, maxIndex, limitIndex);
//		for(String results : resultList){
//			System.out.println(results);
//		}
//		System.out.println("resultList size : " + resultList.size());
		
//		String ss = "A B C D";
		List<List<String>>  testListList = permutation("蔡英文" , false);
		for(List<String> tmpTest : testListList){
			System.out.println(tmpTest.toString());
		}
		
//		String source = "sasdemo";
//		byte src[] = source.getBytes();
//		String encode = Base64Utils.encodeToString(src);
//		String decode = new String(Base64Utils.decodeFromString(encode));
//		System.out.println(String.format("source : %s, encode : [%s], decode : [%s]", source, encode, decode));
	}
	
	public static List<List<String>> permutation(String name, boolean isEnglish) {
		
		String[] nameSpilit;
		if (name.getBytes().length != name.length()) {
			int len  = name.length();
			nameSpilit = new String[len];

			for(int i = 0; i < len ; i ++ ){
				nameSpilit[i] = name.substring(i,i+1);
			}
		} else {
			nameSpilit = name.split(" ");
		}
		int length = nameSpilit.length;
		int count = 1;
		if(isEnglish){
			if (length == 2 || length == 3 || length == 4) {
				count = 1;
			} else if ( length == 5) {
				count = 2;
			} else if (length == 6) {
				count = 3;
			} else if (length == 7 || length == 8) {
				count = 4;
			} else if (length == 9 || length == 10 || length == 11){
				count = 5;
			} else if (length == 12){
				count = 6;
			}else{
				return null;
			}
		}else{
			if (length > 2) {
				count = length - 2;
			}
		}
		
		int start = 0;
		int end = 1;

		List<List<String>> nameListList = new ArrayList<List<String>>(); 
		while ((start + count) <= length) {
			if ((end + count) <= length) {
				for (int i = end; i + count <= length; i++) {	
					List<String> nameList = new ArrayList<String>();
					nameList.add(String.valueOf(nameSpilit[start]));
					for (int j = i; j < count + i; j++) {
						nameList.add(String.valueOf(nameSpilit[j]));
					}
					nameListList.add(nameList);
				}
			}
			end = end + 1;
			start = start + 1;
		}
		
		if(!isEnglish){ //中文加上全排列
			if(name.length() < 5) { //五個中文字以下才做全排列
				List<String> fullPermList = printStr(name);
				for(String be : fullPermList){
					List<String> tmpList = new ArrayList<String>();
					tmpList.add(be);
					nameListList.add(tmpList);
				}
			}
		}else{
			List<String> nameList = new ArrayList<String>();
			String [] nameTmp = name.split(" ");
			if(nameTmp.length == 2){
				nameList.add(nameTmp[0]+" "+nameTmp[1]);
				nameList.add(nameTmp[1]+" "+nameTmp[0]);
			}
			for(String be : nameTmp){
				if(be.length() > 1 && !be.equals("SAID")){
					nameList.add(be);
				}
			}
			if(nameList.size() >= 2){
				nameListList.add(nameList);
			}
		}

		return nameListList;

	}
	
	
	public static List<List<String>> permutation(String name) {
		
		String[] nameSpilit;
		if (name.getBytes().length != name.length()) {
			int len  = name.length();
			nameSpilit = new String[len];

			for(int i = 0; i < len ; i ++ ){
				nameSpilit[i] = name.substring(i,i+1);
			}
		} else {
			nameSpilit = name.split(" ");
		}
		int length = nameSpilit.length;
		int count = 1;

		if (length == 3 || length == 4) {
			count = 1;
		} else if (length == 5) {
			count = 2;
		} else if (length == 6) {
			count = 3;
		} else if (length == 7 || length == 8) {
			count = 4;
		} else if (length == 9 || length == 10){
			count = 5;
		} else if (length > 10){
			return null;
		}
		int start = 0;
		int end = 1;

		List<List<String>> nameListList = new ArrayList<List<String>>(); 
		while ((start + count) <= length) {
			if ((end + count) <= length) {
				for (int i = end; i <= (end + count) && i + count <= length; i++) {
					List<String> nameList = new ArrayList<String>();
					nameList.add(String.valueOf(nameSpilit[start]));
					for (int j = i; j < count + i; j++) {
						nameList.add(String.valueOf(nameSpilit[j]));
					}
					nameListList.add(nameList);
				}
			}
			end = end + 1;
			start = start + 1;
		}
		return nameListList;

	}
	
	
	  public static List<String> printStr(String str) {
	    	List<String> fullPermList = new ArrayList<String>();
	        int len = str.length();    
	        for (int i=0; i<len; i++) {   
	            String s = String.valueOf(str.charAt(i));    
	            StringBuffer buffer = new StringBuffer(str);    
	            buffer.deleteCharAt(i);    
	            fullPermList.add(s + buffer);
	            printStr(s, buffer.toString(), fullPermList);    
	        } 
	        return fullPermList;
	    }    
	        
	    public static void printStr(String start, String str, List<String> fullPermList) {    
	        int len = str.length();    
	        for (int i=0; i<len; i++) {    
	            String s = String.valueOf(str.charAt(i));    
	            StringBuffer buffer = new StringBuffer(str);    
	            buffer.deleteCharAt(i);    
	            if (i != 0){    
	            	fullPermList.add(start + s + buffer);
	            }
	            printStr(start + s, buffer.toString(), fullPermList);    
	        }    
	    }    

	
	
	public void watchListWeight(Map<Float,WatchListBean> wlMap, ScoreWeightConfig swConfig, String name, String identification,
			String country, String yearOfBirth) {
		
		Iterator<Entry<Float, WatchListBean>> iter = wlMap.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    WatchListBean be = (WatchListBean) entry.getValue();
		    		    
		   
		    //Exact match score
		    if(be.isExact_match()){
		    	be.setExact_match_score(swConfig.getExact_match_score_default());
		    }
		    
		    
		    //Inclusive match score
		    if(be.isInclusive_match()){  //Fuzzy + { (Exact Fuzzy) * (number of hit words / total words) }
		    	float inclusiveMatchScore = (float) (swConfig.getFuzzy_match_score_default() + ((swConfig.getExact_match_score_default()-swConfig.getFuzzy_match_score_default()) * 0.6667 )) ;		    	
		    
		    	be.setInclusive_match_score(inclusiveMatchScore);	
		    }
		    
		    //Fuzzy match score
		    if(be.isFuzzy_match()){  //(Fuzzy Matching Score) + sum(Weighting Sore)

			    float weightScore = 0;
			    
				if (!"".equals(identification) && identification.equals(be.getIdentification_id())) {
					weightScore = weightScore + swConfig.getId_match_default();
				}

				if (!"".equals(country) && country.equals(be.getCountry_code())) {
					weightScore = weightScore + swConfig.getCountry_match_default();
				}

				if (!"".equals(yearOfBirth) && !"".equals(be.getYear_of_birth())){
					
					int yearMinus = Math.abs(Integer.valueOf(yearOfBirth) - Integer.valueOf(be.getYear_of_birth()));
					if(yearMinus <=2){
						weightScore = weightScore + swConfig.getYear_match_default();
					}

				}
				
		    	be.setFuzzy_match_score(swConfig.getFuzzy_match_score_default() + weightScore);  
		    	
		    }
		    float finalScore = 0;
		    finalScore = Math.max(be.getExact_match_score(), be.getFuzzy_match_score());
		    finalScore = Math.max(finalScore,be.getInclusive_match_score());
		    finalScore = Math.min(finalScore, 100);
		    be.setFinal_score(finalScore);
		    
		}
		
	}
	
	public static String convertWordToSpace(String name, String[] convertWord) {
		if(name != null && name.length() > 0){
			for (String conTmp : convertWord) {
				name = name.replace(conTmp, " ");
			}
		}
		return lrTrim(name);
	}
	
	public static String convertWordTo(String name, String[] convertWord, String to) {
		if(name != null && name.length() > 0){
			for (String conTmp : convertWord) {
				name = name.replace(conTmp, to);
			}
		}
		return lrTrim(name);
	}
	
	private static String lrTrim(String rawStr){
		if(rawStr != null && rawStr.length() > 0){
			Pattern p = Pattern.compile("\\s*$|^\\s*");
	        Matcher m = p.matcher(rawStr);
	        return m.replaceAll("");
		}else{
			return rawStr;
		}
	}
	
	/** 
	 * n! = n * (n-1) * ... * 2 * 1 
	 * @param n 
	 * @return 
	 */  
	private static long factorial(int n) {  
	    return (n > 1) ? n * factorial(n - 1) : 1;  
	}  
	  
	/** 
	 * A(n, m) = n!/(n-m)! 
	 * @param n 
	 * @param m 
	 * @return 
	 */  
	public static long arrangement(int n, int m) {  
	    return (n >= m) ? factorial(n) / factorial(n - m) : 0;  
	}  
	  
	/** 
	 * C(n, m) = n!/((n-m)! * m!) 
	 * @param n 
	 * @param m 
	 * @return 
	 */  
	public static long combination(int n, int m) {  
	    return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;  
	}
	
	public static void arrangementSelect(String[] dataList, int n) { 
	    arrangementSelect(dataList, new String[n], 0);
	}
	
	public static List<String> getArrangementSelectList(String[] rawStr){
		List<String> resultList= new ArrayList<String>();
		Map<String, String> selectMap = new HashMap<String, String>();
		int limitIndex = 4;
		int maxIndex = rawStr.length >= limitIndex ? limitIndex : rawStr.length; 
		for(int i=1; i<=maxIndex; i++){
			arrangementSelectMap(rawStr, i, selectMap);
		}
		
		if(rawStr.length > limitIndex){
			for(int i=limitIndex+1; i<=rawStr.length; i++){
				shiftSelectMap(rawStr, i, selectMap);
			}
		}
		
		for(String key : selectMap.keySet()){
			resultList.add(key);
		};
		return resultList;
	}
	
	public static List<String> getArrangementSelectList(String[] rawStr, int limitIndex){
		List<String> resultList= new ArrayList<String>();
		Map<String, String> selectMap = new HashMap<String, String>();
		int maxIndex = rawStr.length >= limitIndex ? limitIndex : rawStr.length; 
		for(int i=1; i<=maxIndex; i++){
			arrangementSelectMap(rawStr, i, selectMap);
		}
		
		if(rawStr.length > limitIndex){
			for(int i=limitIndex+1; i<=rawStr.length; i++){
				shiftSelectMap(rawStr, i, selectMap);
			}
		}
		
		for(String key : selectMap.keySet()){
			resultList.add(key);
		};
		return resultList;
	}
	
	public static List<String> getShiftInMaxList(String[] rawStr, int maxIndex){
		List<String> resultList= new ArrayList<String>();
		Map<String, String> selectMap = new HashMap<String, String>();
		
		int endIndex = rawStr.length > maxIndex ? maxIndex : rawStr.length;
		for(int i=1; i<=endIndex; i++){
			shiftSelectMap(rawStr, i, selectMap);
		}
		for(String key : selectMap.keySet()){
			resultList.add(key);
		};
		return resultList;
	}
	
	public static List<String> getArrangementInLimitList(String[] rawStr, int limitIndex){
		List<String> resultList= new ArrayList<String>();
		Map<String, String> selectMap = new HashMap<String, String>();
		int endIndex = rawStr.length > limitIndex ? limitIndex : rawStr.length;
		for(int i=1; i<=endIndex; i++){
			arrangementSelectMap(rawStr, i, selectMap);
		}
		for(String key : selectMap.keySet()){
			resultList.add(key);
		};
		return resultList;
	}
	
	public static List<String> getShiftInMaxArrangementInLimitList(String[] rawStr, int maxIndex, int limitIndex){
		List<String> resultList= new ArrayList<String>();
		List<String> shiftList = getShiftInMaxList(rawStr,maxIndex);
		//limit must less & equal max
		limitIndex = limitIndex > maxIndex ? maxIndex : limitIndex; 
		resultList.addAll(shiftList);
		for(String shift : shiftList){
			String[] shiftInMax = shift.split(" "); 
			if(shiftInMax.length == maxIndex){
				List<String> arrangementList = getArrangementInLimitList(shiftInMax, limitIndex);
				resultList.addAll(arrangementList);
			}
		}
		return resultList;
	}
	
	public static List<String> getArrangementSelectList(String[] rawStr, int limitIndex, int maxIndex){
		List<String> resultList= new ArrayList<String>();
		Map<String, String> selectMap = new HashMap<String, String>();
		
		int maxIndex1 = rawStr.length >= limitIndex ? limitIndex : rawStr.length; 
		for(int i=1; i<=maxIndex1; i++){
			arrangementSelectMap(rawStr, i, selectMap);
		}
		if(rawStr.length > limitIndex){
			int endIndex = rawStr.length > maxIndex ? maxIndex : rawStr.length;
			for(int i=1; i<=endIndex; i++){
				shiftSelectMap(rawStr, i, selectMap);
			}
		}
		for(String key : selectMap.keySet()){
			resultList.add(key);
		};
		return resultList;
	}
	
	private static void arrangementSelectMap(String[] dataList, int n, Map<String, String> selectMap) {
		arrangementSelectMap(dataList, new String[n], 0, selectMap);
	}
	  

	private static void arrangementSelect(String[] dataList, String[] resultList, int resultIndex) {  
	    int resultLen = resultList.length;  
	    if (resultIndex >= resultLen) {
	        return;  
	    }  
	    for (int i = 0; i < dataList.length; i++) {  
	        boolean exists = false;  
	        for (int j = 0; j < resultIndex; j++) {  
	            if (dataList[i].equals(resultList[j])) {  
	                exists = true;  
	                break;  
	            }  
	        }  
	        if (!exists) { 
	            resultList[resultIndex] = dataList[i];  
	            arrangementSelect(dataList, resultList, resultIndex + 1);  
	        }  
	    }  
	}
	
	private static void arrangementSelectMap(String[] dataList, String[] resultList, int resultIndex, Map<String, String> selectMap) {  
		if(selectMap ==  null){
			selectMap = new HashMap<String, String>();
		}
		int resultLen = resultList.length;
	    if (resultIndex >= resultLen) {
	    	String result = convertWordTo(Arrays.toString(resultList), SwiftMtConst.COVERTWORD, "");
	    	if(result != null && result.length() > 0){
	    		selectMap.put(result, result);
	    	}
	    	return;
	    }
	    for (int i = 0; i < dataList.length; i++) {  
	        boolean exists = false;  
	        for (int j = 0; j < resultIndex; j++) {  
	            if (dataList[i].equals(resultList[j])) {  
	                exists = true;  
	                break;  
	            }  
	        }  
	        if (!exists) {   
	            resultList[resultIndex] = dataList[i];
	            arrangementSelectMap(dataList, resultList, resultIndex + 1, selectMap);  
	        }  
	    }
	}
	
	public static void shiftSelect(String[] dataList, int n){
		int dataLength = dataList.length;
		for(int j=0; j<dataLength-n+1; j++){
			shiftSelect(dataList, j, new String[n]);
		}
	}
	
	public static void shiftSelectMap(String[] dataList, int n, Map<String, String> selectMap){
		int dataLength = dataList.length;
		for(int j=0; j<dataLength-n+1; j++){
			shiftSelectMap(dataList, j, new String[n], selectMap);
		}
	}
	
	private static void shiftSelect(String[] dataList, int dataIndex, String[] resultList){
		int dataLength = dataList.length;
		int resultLength = resultList.length;
		int selectLength = dataIndex+resultLength < dataLength ? dataIndex+resultLength : dataLength;
		int resultIndex = 0;
		for(int i=dataIndex; i<selectLength; i++){
			resultList[resultIndex] = dataList[i];
			resultIndex++;
		}
	}
	
	private static void shiftSelectMap(String[] dataList, int dataIndex, String[] resultList, Map<String, String> selectMap){
		int dataLength = dataList.length;
		int resultLength = resultList.length;
		int selectLength = dataIndex+resultLength < dataLength ? dataIndex+resultLength : dataLength;
		int resultIndex = 0;
		for(int i=dataIndex; i<selectLength; i++){
			resultList[resultIndex] = dataList[i];
			resultIndex++;
		}
		
		String result = convertWordTo(Arrays.toString(resultList), SwiftMtConst.COVERTWORD, "");
    	if(result != null && result.length() > 0){
    		selectMap.put(result, result);
    	}
	}
	
 
	public static void combinationSelect(String[] dataList, int n) {
	    combinationSelect(dataList, 0, new String[n], 0);  
	}  
	  
	private static void combinationSelect(String[] dataList, int dataIndex, String[] resultList, int resultIndex) {  
	    int resultLen = resultList.length;  
	    int resultCount = resultIndex + 1;  
	    if (resultCount > resultLen) {
	        return;  
	    }  
	    for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {  
	        resultList[resultIndex] = dataList[i];  
	        combinationSelect(dataList, i + 1, resultList, resultIndex + 1);  
	    }  
	}  
}
