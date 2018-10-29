package com.sas.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.sas.constraint.SwiftMtConst;
import com.sas.db.aml.orm.fcfcore.XWatchlistCompressString;

public class StringUtils {

	public static void main(String args[]) {
		String fullStr = "abbla hblh eyahyoblahhey ";
//		fullStr = String.format("[%s]", convertLastWordTo(fullStr, new String[]{"blah", "hey", "yo"} , " "));
//		System.out.println(fullStr);
		System.out.println(fullStr.replace(" ", ""));
		//	String fullStr = "ＡＢＣｄｅｆ～！＠７８９中文犇堃";
		// String halfStr = "ABCdef~!@789中文犇堃";
		// System.out.println(convertToHalfWidth(fullStr));
		// System.out.println(convertToHalfWidth(fullStr).equals(halfStr));
		// System.out.println(convertToHalfWidth(halfStr));
		// System.out.println(convertToHalfWidth(halfStr).equals(halfStr));
		// System.out.println(convertToFullWidth(halfStr));
		// System.out.println(convertToFullWidth(halfStr).equals(fullStr));

		// String s=" 大 家 好 啊 ";
		// String[] c = "".split("");
		// System.out.println(String.format("[%s]", standardizationInput(s, c)));

	}
	
	public static String sortString(String str){
		StringBuffer sb = new StringBuffer();
		String[] words = str.split(" ");
		Arrays.sort(words);
		for(String word : words){
			sb.append(word).append(" ");
		}
		return sb.toString().trim();
	}
	
	public static String lianxuStr(String str) {
		String tmpString =  str.replaceAll("[\\s]{2,}", " ");
		tmpString =  str.replaceAll("[\\s]{1,}", " ");
		return tmpString;
	}
	
	/**
	 * 取得字串模糊化替代字串集 --> 個人: IND，法人: CORP
	 */
	private static String[] getCompressStringArrayBy(List<String> compressStringList) {
		String[] arrCompressString = null;
		String amlCompressString = null;
		for (String compressString : compressStringList) {
			if (amlCompressString == null) {
				amlCompressString = String.format("%s", compressString);
			} else {
				amlCompressString = String.format("%s,%s", amlCompressString, compressString);
			}
		}
		if ( amlCompressString!=null && amlCompressString.length() > 0 ) {
			arrCompressString = amlCompressString.split(",");
		}
		return arrCompressString;
	}

	/**
	 * 全型轉半型
	 * 
	 * @param source
	 * @return
	 */
	public static String convertToHalfWidth(final String source) {
		if (null == source) {
			return null;
		}

		char[] charArray = source.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			int ic = (int) charArray[i];

			if (ic >= 65281 && ic <= 65374) {
				charArray[i] = (char) (ic - 65248);
			} else if (ic == 12288) {
				charArray[i] = (char) 32;
			}

		}
		return new String(charArray);
	}

	/**
	 * 半型轉全型
	 * 
	 * @param source
	 * @return
	 */
	public static String convertToFullWidth(final String source) {
		if (null == source) {
			return null;
		}

		char[] charArray = source.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			int ic = (int) charArray[i];

			if (ic >= 33 && ic <= 126) {
				charArray[i] = (char) (ic + 65248);
			} else if (ic == 32) {
				charArray[i] = (char) 12288;
			}

		}
		return new String(charArray);
	}
	
	public static String standardizationInputFromLast(String input, String type_desc, List<String> compressStringList) {
		if (input == null) {
			return null;
		}
		
		String tmpInput = convertToHalfWidth(input.trim());
		if (SwiftMtConst.ENTITY_TYPE_IND.indexOf(type_desc) > -1) {
			tmpInput = convertLastWordTo(tmpInput, getCompressStringArrayBy(compressStringList), " ");
		} else if (SwiftMtConst.ENTITY_TYPE_CORP.indexOf(type_desc) > -1) {
			tmpInput = convertLastWordTo(tmpInput, getCompressStringArrayBy(compressStringList), " ");
		} else if (SwiftMtConst.ENTITY_TYPE_INDANDCORP.indexOf(type_desc) > -1) {
			tmpInput = convertLastWordTo(tmpInput, getCompressStringArrayBy(compressStringList), " ");
			tmpInput = convertLastWordTo(tmpInput, getCompressStringArrayBy(compressStringList), " ");
		} else {
			tmpInput = convertLastWordTo(tmpInput, new String[0] , " ");
		}
		tmpInput = convertWordTo(tmpInput, SwiftMtConst.COVERTWORD, " ");
		tmpInput = lianxuStr(tmpInput);
		return tmpInput.toUpperCase();
	}

	public static String standardizationInput(String input, String[] convertWord) {
		if (input == null) {
			return null;
		}

		String tmpInput = convertToHalfWidth(input.trim());
		tmpInput = convertWordTo(tmpInput, convertWord, "");
		tmpInput = convertWordTo(tmpInput, SwiftMtConst.COVERTWORD, " ");
		tmpInput = org.apache.commons.lang3.StringUtils.stripEnd(tmpInput, " ");
		tmpInput = lianxuStr(tmpInput);
		return tmpInput.toUpperCase();
	}
	
	public static String convertLastWordTo(String name, String[] convertWord, String to) {
		if (name != null && name.length() > 0) {
			for (String conTmp : convertWord) {
				if(name.endsWith(conTmp)) {
					StringBuilder strb = new StringBuilder(name);
					int lastIndex = strb.lastIndexOf(conTmp);
					strb.replace(lastIndex, conTmp.length() + lastIndex, to);
					name = strb.toString();
				}
			}
		}
		return name;
	}

	public static String convertWordTo(String name, String[] convertWord, String to) {
		if(name != null && name.length() > 0){
			for (String conTmp : convertWord) {
				name = name.replace(conTmp, to);
			}
		}
		return name;
	}
	
	public static String handelEscape(String str){
		String result = str;
		if(str != null && str.length() > 0){
			result = str.replaceAll("'", "''");
		}
		return result;
	}
	
	public static boolean isEmpty(String str){
		return str == null || "".equals(str) || str.length() == 0;
	}
	
	public static boolean isEnglish(String name){
		if (name.getBytes().length != name.length()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 利用正則表達式匹配雙字節字符(包括漢字在內)：[^\x00-\xff] 為 "**"
	 * 來計算字串長度
	 * 
	 * @param validateStr
	 * @return length of validateStr
	 */
	public static int getDoubleByteCharsLength(String validateStr) {
		// String temp = validateStr.replaceAll("[\u4e00-\u9fa5]", "**");
		String temp = validateStr.replaceAll("[^\\x00-\\xff]", "**");
		return temp.length();
	}
	
	
	public static Map<String,String> standardrizeInput(String entityType, String nonEnglishName,String englishName,Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap) {
		//step1.全型轉半型
		String standardInputCnName = StringUtils.convertToHalfWidth(nonEnglishName);
		String standardInputEnName = StringUtils.convertToHalfWidth(englishName);
		standardInputEnName=standardInputEnName.toUpperCase();
		Map<String,String> nameMap = new HashMap<String,String>();
		if(standardInputEnName != "" || standardInputCnName != "") {
			nameMap = CompressStringByEntityType(entityType, standardInputEnName, standardInputCnName, xWatchlistCompressStringMap);
//			List<XWatchlistCompressString> CorpCompressStringList=xWatchlistCompressStringMap.get("CORP");
//			List<XWatchlistCompressString> IndCompressStringList=xWatchlistCompressStringMap.get("IND");	
//			List<XWatchlistCompressString> SignCompressStringList=xWatchlistCompressStringMap.get("SIG");
//			
//			//step2.移除特殊字元
//			if(SignCompressStringList != null && SignCompressStringList.size() > 0){
//				for(XWatchlistCompressString xWatchlistCompressString:SignCompressStringList){
//					String compressString=xWatchlistCompressString.getCompressString();
//					String replaceStr=xWatchlistCompressString.getReplaceStr();
//					if("blank".equalsIgnoreCase(replaceStr)){
//						if(standardInputCnName != null){
//							standardInputCnName = standardInputCnName.replace(compressString, " ");
//						}
//						if(standardInputEnName != null){
//							standardInputEnName = standardInputEnName.replace(compressString, " ");		
//						}
//					}else if("empty".equalsIgnoreCase(replaceStr)){
//						if(standardInputCnName != null){
//							standardInputCnName = standardInputCnName.replace(compressString, "");
//						}
//						if(standardInputEnName != null){
//							standardInputEnName = standardInputEnName.replace(compressString, "");		
//						}
//					}
//				}
//			}
//
//			
//			//step4.移除法人字串(從X_WATCHLIST_COMPRESS_STRING table)
//			if(SwiftMtConst.ENTITY_TYPE_CORP.equals(entityType)           //entityType=03 法人
//					|| SwiftMtConst.ENTITY_TYPE_INDANDCORP.equals(entityType) //entityType=09(個人/法人)
//					|| SwiftMtConst.ENTITY_TYPE_ALL.equals(entityType)){  //entityType=01 所有
//				
//				//前後加空白(只有英文)
//				standardInputEnName =" "+standardInputEnName+" ";
//
////				logger.debug(entityType + "  " + String.valueOf(CorpCompressStringList != null) + "  " + CorpCompressStringList.size() );
//				if(CorpCompressStringList != null && CorpCompressStringList.size() > 0){
//					for(XWatchlistCompressString xWatchlistCompressString:CorpCompressStringList){
//						String compressString=xWatchlistCompressString.getCompressString().toUpperCase();
//						String replaceStr=xWatchlistCompressString.getReplaceStr();
//						//取代單引號
//						compressString=compressString.replace("\'","");
//						if("Y".equalsIgnoreCase(xWatchlistCompressString.getIsEnglish())){
//							if(standardInputEnName != null){
//								standardInputEnName=standardInputEnName.replace(compressString," ");		
//							}
//							if("blank".equalsIgnoreCase(replaceStr)){
//								if(standardInputEnName != null){
//									standardInputEnName=standardInputEnName.replace(compressString," ");		
//								}
//							}else if("empty".equalsIgnoreCase(replaceStr)){
//								if(standardInputEnName != null){
//									standardInputEnName = standardInputEnName.replace(compressString, "");								
//								}
//							}
//						}else{
//							if(standardInputCnName != null){
//								standardInputCnName=standardInputCnName.replace(compressString,"");
//							}
//							if("blank".equalsIgnoreCase(replaceStr)){
//								if(standardInputEnName != null){
//									standardInputEnName = standardInputEnName.replace(compressString, " ");		
//								}
//							}else if("empty".equalsIgnoreCase(replaceStr)){
//								if(standardInputEnName != null){
//									standardInputEnName = standardInputEnName.replace(compressString, "");								
//								}
//							}
//						}
//					}
//				}
//			}
//			if(SwiftMtConst.ENTITY_TYPE_IND.equals(entityType)                //entityType=02 個人
//					|| SwiftMtConst.ENTITY_TYPE_INDANDCORP.equals(entityType) //entityType=09(個人/法人)
//					|| SwiftMtConst.ENTITY_TYPE_ALL.equals(entityType)){      //entityType=01 所有				
//				//前後加空白(只有英文)
//				standardInputEnName =" "+standardInputEnName+" ";
//				
//				//step4.移除個人字串(從X_WATCHLIST_COMPRESS_STRING table)
//				if(IndCompressStringList != null && IndCompressStringList.size() > 0){
//					for(XWatchlistCompressString xWatchlistCompressString:IndCompressStringList){
//						String compressString=xWatchlistCompressString.getCompressString().toUpperCase();
//						String replaceStr=xWatchlistCompressString.getReplaceStr();
//						//取代單引號
//						compressString=compressString.replaceAll("\'","");
//						if("Y".equalsIgnoreCase(xWatchlistCompressString.getIsEnglish())){
//							if("blank".equalsIgnoreCase(replaceStr)){
//								if(standardInputEnName != null){
//									standardInputEnName = standardInputEnName.replace(compressString, " ");		
//								}
//							}else if("empty".equalsIgnoreCase(replaceStr)){
//								if(standardInputEnName != null){
//									standardInputEnName = standardInputEnName.replace(compressString, "");								
//								}
//							}
//						}else{
//							if("blank".equalsIgnoreCase(replaceStr)){
//								if(standardInputCnName != null){
//									standardInputCnName=standardInputCnName.replace(compressString," ");	
//								}
//							}else if("empty".equalsIgnoreCase(replaceStr)){
//								if(standardInputCnName != null){
//									standardInputCnName=standardInputCnName.replace(compressString,"");								
//								}									
//							}
//						}
//					}
//				}
//			}
//
//			//step.5多個空白(含tab)replace成一個
//			if(standardInputCnName != null){
//				standardInputCnName=StringUtils.lianxuStr(standardInputCnName);
//			}
//			if(standardInputEnName != null){
//				standardInputEnName=StringUtils.lianxuStr(standardInputEnName);
//			}	
//			//step.6移除前後空白
//			if(standardInputEnName != null){
//				standardInputEnName = org.apache.commons.lang.StringUtils.stripEnd(standardInputEnName, " ");
//				standardInputEnName = org.apache.commons.lang.StringUtils.stripStart(standardInputEnName, " ");
//			}
//			if(standardInputCnName != null){
//				standardInputCnName = org.apache.commons.lang.StringUtils.stripEnd(standardInputCnName, " ");
//				standardInputCnName = org.apache.commons.lang.StringUtils.stripStart(standardInputCnName, " ");
//			}
		}
		
//		nameMap.put("en",standardInputEnName);
//		nameMap.put("cn",standardInputCnName);		
		return nameMap;
	}	
	
	public static Map<String,String> CompressStringByEntityType(String entityType, String standardInputEnName, String standardInputCnName,  Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap) {
		Map<String,String> nameNewMap=new HashMap<String,String>();
		nameNewMap.put("cn", standardInputCnName);
		nameNewMap.put("en", standardInputEnName);
		nameNewMap.put("cnFuzzy", standardInputCnName);  //Fuzzy使用
		nameNewMap.put("enFuzzy", standardInputEnName);  //Fuzzy使用
		
		List<XWatchlistCompressString> CorpCompressStringList=xWatchlistCompressStringMap.get("CORP");
		List<XWatchlistCompressString> IndCompressStringList=xWatchlistCompressStringMap.get("IND");	
		List<XWatchlistCompressString> SignCompressStringList=xWatchlistCompressStringMap.get("SIG");
		
		nameNewMap = DoSignCompressString(nameNewMap, SignCompressStringList);
		if(SwiftMtConst.ENTITY_TYPE_ALL.equals(entityType)
				|| SwiftMtConst.ENTITY_TYPE_INDANDCORP.equals(entityType)
				|| SwiftMtConst.ENTITY_TYPE_FREEFPRMAT.equals(entityType)) {
			nameNewMap = DoCorpCompressString(nameNewMap, CorpCompressStringList);
			nameNewMap = DoIndCompressString(nameNewMap, IndCompressStringList);
		} else if (SwiftMtConst.ENTITY_TYPE_IND.equals(entityType)
				) {
			nameNewMap = DoIndCompressString(nameNewMap, IndCompressStringList);
		} else if (SwiftMtConst.ENTITY_TYPE_CORP.equals(entityType)
				|| SwiftMtConst.ENTITY_TYPE_BANK.equals(entityType)
				|| SwiftMtConst.ENTITY_TYPE_COUNTRY.equals(entityType)) {
			nameNewMap = DoCorpCompressString(nameNewMap, CorpCompressStringList);
		}
		
		standardInputCnName = nameNewMap.get("cn");
		standardInputEnName = nameNewMap.get("en");
		//step.5多個空白(含tab)replace成一個
		if(standardInputCnName != null){
			standardInputCnName=StringUtils.lianxuStr(standardInputCnName);
		}
		if(standardInputEnName != null){
			standardInputEnName=StringUtils.lianxuStr(standardInputEnName);
		}	
		//step.6移除前後空白
		if(standardInputEnName != null){
			standardInputEnName = org.apache.commons.lang3.StringUtils.stripEnd(standardInputEnName, " ");
			standardInputEnName = org.apache.commons.lang3.StringUtils.stripStart(standardInputEnName, " ");
		}
		if(standardInputCnName != null){
			standardInputCnName = org.apache.commons.lang3.StringUtils.stripEnd(standardInputCnName, " ");
			standardInputCnName = org.apache.commons.lang3.StringUtils.stripStart(standardInputCnName, " ");
		}
		nameNewMap.put("cn", standardInputCnName);
		nameNewMap.put("en", standardInputEnName);
		
		
		
		//Fuzzy使用
		standardInputCnName = nameNewMap.get("cnFuzzy");
		standardInputEnName = nameNewMap.get("enFuzzy");
		//step.5多個空白(含tab)replace成一個
		if(standardInputCnName != null){
			standardInputCnName=StringUtils.lianxuStr(standardInputCnName);
		}
		if(standardInputEnName != null){
			standardInputEnName=StringUtils.lianxuStr(standardInputEnName);
		}	
		//step.6移除前後空白
		if(standardInputEnName != null){
			standardInputEnName = org.apache.commons.lang3.StringUtils.stripEnd(standardInputEnName, " ");
			standardInputEnName = org.apache.commons.lang3.StringUtils.stripStart(standardInputEnName, " ");
		}
		if(standardInputCnName != null){
			standardInputCnName = org.apache.commons.lang3.StringUtils.stripEnd(standardInputCnName, " ");
			standardInputCnName = org.apache.commons.lang3.StringUtils.stripStart(standardInputCnName, " ");
		}
		nameNewMap.put("cnFuzzy", standardInputCnName);
		nameNewMap.put("enFuzzy", standardInputEnName);
		
		
		return nameNewMap;		
	}
	
	public static String  DoCorpCompressString(String inputName,
			List<XWatchlistCompressString> CorpCompressStringList) {
		//前後加空白(只有英文)
		inputName =" "+inputName+" ";
		if(CorpCompressStringList != null && CorpCompressStringList.size() > 0){
			for(XWatchlistCompressString xWatchlistCompressString:CorpCompressStringList){
				String compressString=xWatchlistCompressString.getCompressString().toUpperCase();
				String replaceStr=xWatchlistCompressString.getReplaceStr();
				//取代單引號
				compressString=compressString.replace("\'","");
				if("Y".equalsIgnoreCase(xWatchlistCompressString.getIsEnglish())){
					if(inputName != null){
						inputName=inputName.replace(compressString," ");		
					}
					if("blank".equalsIgnoreCase(replaceStr)){
						if(inputName != null){
							inputName=inputName.replace(compressString," ");		
						}
					}else if("empty".equalsIgnoreCase(replaceStr)){
						if(inputName != null){
							inputName = inputName.replace(compressString, "");								
						}
					}
				}else{
					if(inputName != null){
						inputName=inputName.replace(compressString,"");
					}
					if("blank".equalsIgnoreCase(replaceStr)){
						if(inputName != null){
							inputName = inputName.replace(compressString, " ");		
						}
					}else if("empty".equalsIgnoreCase(replaceStr)){
						if(inputName != null){
							inputName = inputName.replace(compressString, "");								
						}
					}
				}
			}
		}
		return inputName;
	}

	public static String DoSignCompressString(String inputName,
			List<XWatchlistCompressString> SignCompressStringList) {
		
		//step2.移除特殊字元
		if(SignCompressStringList != null && SignCompressStringList.size() > 0){
			for(XWatchlistCompressString xWatchlistCompressString:SignCompressStringList){
				String compressString=xWatchlistCompressString.getCompressString();
				String replaceStr=xWatchlistCompressString.getReplaceStr();
				if("blank".equalsIgnoreCase(replaceStr)){
					if(inputName != null){
						inputName = inputName.replace(compressString, " ");
					}
				}else if("empty".equalsIgnoreCase(replaceStr)){
					if(inputName != null){
						inputName = inputName.replace(compressString, "");
					}
				}
			}
		}
		return inputName;
	}
	
	private static Map<String, String> DoIndCompressString(Map<String, String> nameMap,
			List<XWatchlistCompressString> IndCompressStringList) {
		String standardInputCnName = nameMap.get("cn");
		String standardInputEnName = nameMap.get("en");
		
		//前後加空白(只有英文)
		standardInputEnName =" "+standardInputEnName+" ";
		//step4.移除個人字串(從X_WATCHLIST_COMPRESS_STRING table)
		if(IndCompressStringList != null && IndCompressStringList.size() > 0){
			for(XWatchlistCompressString xWatchlistCompressString:IndCompressStringList){
				String compressString=xWatchlistCompressString.getCompressString().toUpperCase();
				String replaceStr=xWatchlistCompressString.getReplaceStr();
				//取代單引號
				compressString=compressString.replaceAll("\'","");
				if("Y".equalsIgnoreCase(xWatchlistCompressString.getIsEnglish())){
					if("blank".equalsIgnoreCase(replaceStr)){
						if(standardInputEnName != null){
							standardInputEnName = standardInputEnName.replace(compressString, " ");		
						}
					}else if("empty".equalsIgnoreCase(replaceStr)){
						if(standardInputEnName != null){
							standardInputEnName = standardInputEnName.replace(compressString, "");								
						}
					}
				}else{
					if("blank".equalsIgnoreCase(replaceStr)){
						if(standardInputCnName != null){
							standardInputCnName=standardInputCnName.replace(compressString," ");	
						}
					}else if("empty".equalsIgnoreCase(replaceStr)){
						if(standardInputCnName != null){
							standardInputCnName=standardInputCnName.replace(compressString,"");								
						}									
					}
				}
			}
		}
		nameMap.put("cn", standardInputCnName);
		nameMap.put("en", standardInputEnName);
		
		
		standardInputCnName = nameMap.get("cnFuzzy");
	//	standardInputEnName = nameMap.get("enFuzzy");
		
		//前後加空白(只有英文)
		standardInputEnName =" "+standardInputEnName+" ";
		//step4.移除個人字串(從X_WATCHLIST_COMPRESS_STRING table)
		if(IndCompressStringList != null && IndCompressStringList.size() > 0){
			for(XWatchlistCompressString xWatchlistCompressString:IndCompressStringList){
				String compressString=xWatchlistCompressString.getCompressString().toUpperCase();
				String replaceStr=xWatchlistCompressString.getReplaceStr();
				//取代單引號
				compressString=compressString.replaceAll("\'","");
				if("Y".equalsIgnoreCase(xWatchlistCompressString.getIsEnglish())){
					if("blank".equalsIgnoreCase(replaceStr)){
						if(standardInputEnName != null){
							standardInputEnName = standardInputEnName.replace(compressString, " ");		
						}
					}else if("empty".equalsIgnoreCase(replaceStr)){
						if(standardInputEnName != null){
							standardInputEnName = standardInputEnName.replace(compressString, "");								
						}
					}
				}else{
					if("blank".equalsIgnoreCase(replaceStr)){
						if(standardInputCnName != null){
							standardInputCnName=standardInputCnName.replace(compressString," ");	
						}
					}else if("empty".equalsIgnoreCase(replaceStr)){
						if(standardInputCnName != null){
							standardInputCnName=standardInputCnName.replace(compressString,"");								
						}									
					}
				}
			}
		}
		nameMap.put("cnFuzzy", standardInputCnName);
//		nameMap.put("enFuzzy", standardInputEnName);  不做符號的個法人CompressString
		
		return nameMap;
	}

	private static Map<String, String> DoCorpCompressString(Map<String, String> nameMap,
			List<XWatchlistCompressString> CorpCompressStringList) {
		String standardInputCnName = nameMap.get("cn");
		String standardInputEnName = nameMap.get("en");
		//前後加空白(只有英文)
		standardInputEnName =" "+standardInputEnName+" ";
		if(CorpCompressStringList != null && CorpCompressStringList.size() > 0){
			for(XWatchlistCompressString xWatchlistCompressString:CorpCompressStringList){
				String compressString=xWatchlistCompressString.getCompressString().toUpperCase();
				String replaceStr=xWatchlistCompressString.getReplaceStr();
				//取代單引號
				compressString=compressString.replace("\'","");
				if("Y".equalsIgnoreCase(xWatchlistCompressString.getIsEnglish())){
					if(standardInputEnName != null){
						standardInputEnName=standardInputEnName.replace(compressString," ");		
					}
					if("blank".equalsIgnoreCase(replaceStr)){
						if(standardInputEnName != null){
							standardInputEnName=standardInputEnName.replace(compressString," ");		
						}
					}else if("empty".equalsIgnoreCase(replaceStr)){
						if(standardInputEnName != null){
							standardInputEnName = standardInputEnName.replace(compressString, "");								
						}
					}
				}else{
					if(standardInputCnName != null){
						standardInputCnName=standardInputCnName.replace(compressString,"");
					}
					if("blank".equalsIgnoreCase(replaceStr)){
						if(standardInputCnName != null){
							standardInputCnName = standardInputCnName.replace(compressString, " ");		
						}
					}else if("empty".equalsIgnoreCase(replaceStr)){
						if(standardInputCnName != null){
							standardInputCnName = standardInputCnName.replace(compressString, "");								
						}
					}
				}
			}
		}
		nameMap.put("cn", standardInputCnName);
		nameMap.put("en", standardInputEnName);
		
		
		standardInputCnName = nameMap.get("cnFuzzy");
	//	standardInputEnName = nameMap.get("enFuzzy"); //不做英文的個法人CompressString
		//前後加空白(只有英文)
		standardInputEnName =" "+standardInputEnName+" ";
		if(CorpCompressStringList != null && CorpCompressStringList.size() > 0){
			for(XWatchlistCompressString xWatchlistCompressString:CorpCompressStringList){
				String compressString=xWatchlistCompressString.getCompressString().toUpperCase();
				String replaceStr=xWatchlistCompressString.getReplaceStr();
				//取代單引號
				compressString=compressString.replace("\'","");
				if("Y".equalsIgnoreCase(xWatchlistCompressString.getIsEnglish())){
					if(standardInputEnName != null){
						standardInputEnName=standardInputEnName.replace(compressString," ");		
					}
					if("blank".equalsIgnoreCase(replaceStr)){
						if(standardInputEnName != null){
							standardInputEnName=standardInputEnName.replace(compressString," ");		
						}
					}else if("empty".equalsIgnoreCase(replaceStr)){
						if(standardInputEnName != null){
							standardInputEnName = standardInputEnName.replace(compressString, "");								
						}
					}
				}else{
					if(standardInputCnName != null){
						standardInputCnName=standardInputCnName.replace(compressString,"");
					}
					if("blank".equalsIgnoreCase(replaceStr)){
						if(standardInputCnName != null){
							standardInputCnName = standardInputCnName.replace(compressString, " ");		
						}
					}else if("empty".equalsIgnoreCase(replaceStr)){
						if(standardInputCnName != null){
							standardInputCnName = standardInputCnName.replace(compressString, "");								
						}
					}
				}
			}
		}
		nameMap.put("cnFuzzy", standardInputCnName);
	//	nameMap.put("enFuzzy", standardInputEnName);  //不做英文的個法人CompressString
		
		
		return nameMap;
	}

	private static Map<String, String> DoSignCompressString(Map<String, String> nameMap,
			List<XWatchlistCompressString> SignCompressStringList) {
		String standardInputCnName = nameMap.get("cn");
		String standardInputEnName = nameMap.get("en");
		
		//step2.移除特殊字元
		if(SignCompressStringList != null && SignCompressStringList.size() > 0){
			for(XWatchlistCompressString xWatchlistCompressString:SignCompressStringList){
				String compressString=xWatchlistCompressString.getCompressString();
				String replaceStr=xWatchlistCompressString.getReplaceStr();
				if("blank".equalsIgnoreCase(replaceStr)){
					if(standardInputCnName != null){
						standardInputCnName = standardInputCnName.replace(compressString, " ");
					}
					if(standardInputEnName != null){
						standardInputEnName = standardInputEnName.replace(compressString, " ");		
					}
				}else if("empty".equalsIgnoreCase(replaceStr)){
					if(standardInputCnName != null){
						standardInputCnName = standardInputCnName.replace(compressString, "");
					}
					if(standardInputEnName != null){
						standardInputEnName = standardInputEnName.replace(compressString, "");		
					}
				}
			}
		}
		nameMap.put("cn", standardInputCnName);
		nameMap.put("en", standardInputEnName);
		return nameMap;
	}

	public static String standardrizeSwiftInput(String name,Map<String, List<XWatchlistCompressString>> xWatchlistCompressStringMap) {
//		List<XWatchlistCompressString> CorpCompressStringList=xWatchlistCompressStringMap.get("CORP");
//		List<XWatchlistCompressString> IndCompressStringList=xWatchlistCompressStringMap.get("IND");
		List<XWatchlistCompressString> SignCompressStringList=xWatchlistCompressStringMap.get("SIG");
		
		//step1.全型轉半型
				String standardInputName = StringUtils.convertToHalfWidth(name);
				//step2.移除特殊字元
				if(SignCompressStringList != null && SignCompressStringList.size() > 0){
					for(XWatchlistCompressString xWatchlistCompressString:SignCompressStringList){
						String compressString=xWatchlistCompressString.getCompressString();
						String replaceStr=xWatchlistCompressString.getReplaceStr();
						if("blank".equalsIgnoreCase(replaceStr)){
							standardInputName = standardInputName.replace(compressString, " ");
						}else if("empty".equalsIgnoreCase(replaceStr)){
							standardInputName = standardInputName.replace(compressString, "");
						}
					}
				}
				//step3.前後加空白(只有英文)
				standardInputName =" "+standardInputName+" ";
				

				//step.4多個空白(含tab)replace成一個
				standardInputName=StringUtils.lianxuStr(standardInputName);
				//step.5移除前後空白
				standardInputName = org.apache.commons.lang3.StringUtils.stripEnd(standardInputName, " ");
				standardInputName = org.apache.commons.lang3.StringUtils.stripStart(standardInputName, " ");
				return standardInputName;
	}
	
	public static String getLocale(Locale locale){
		String strLocale = "";
		if (locale != null){
			strLocale = locale.getLanguage();
			if (org.apache.commons.lang3.StringUtils.isNotBlank(locale.getCountry()))
				strLocale = strLocale + "_" + locale.getCountry();
		}
		
		if(strLocale == null || "".equalsIgnoreCase(strLocale)) {
			strLocale = "en_US";
		}
		return strLocale;
	}
}

