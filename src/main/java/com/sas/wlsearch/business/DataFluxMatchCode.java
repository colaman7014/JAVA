package com.sas.wlsearch.business;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.dataflux.wsdl.archserver.DMService;
import com.dataflux.wsdl.archserver.DataManagementServicePortType;
import com.dataflux.xsd.archserver.RowIn;
import com.dataflux.xsd.archserver.RowOut;
import com.dataflux.xsd.archserver.TableIn;
import com.dataflux.xsd.archserver.TableOut;
import com.sas.constraint.SwiftMtConst;
import com.sas.util.AmlConfiguration;

/**
 * 輸入名稱模糊化主程式
 * @author SAS
 *
 */
@Component
public class DataFluxMatchCode {
	private static final Logger logger = LoggerFactory.getLogger(DataFluxMatchCode.class);

	/**
	 * 將輸入的中文/英文姓名，透過SAS DataFlux工具，將名稱模糊化 (使用Web Service方式呼叫)
	 * @param non_english_name
	 * @param english_name
	 * @return Map<String, String>
	 */
	public Map<String, String> datafluxMatchCode(String non_english_name, String english_name, String inputCnNamePinYin) {
		Map<String,String> matchCodeMap = new HashMap<String,String>();
		matchCodeMap.putAll(datafluxMatchCode(non_english_name, english_name));
		if(inputCnNamePinYin != null && !"".equals(inputCnNamePinYin)){
			matchCodeMap.putAll(datafluxMatchCode("", inputCnNamePinYin));
		}
		return matchCodeMap;
	}
	/**
	 * 將輸入的中文/英文姓名，透過SAS DataFlux工具，將名稱模糊化 (使用Web Service方式呼叫)
	 * @param non_english_name
	 * @param english_name
	 * @return Map<String, String>
	 */
	public Map<String, String> datafluxMatchCode(String non_english_name, String english_name) {
		Map<String,String> matchCodeMap = new HashMap<String,String>();
		try {
			
			String dataFluxUserName = AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_USERNAME);//"sasdemo";
			String dataFluxPassWord = new String(Base64Utils.decodeFromString(AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_PASSWORD)));//"Orion123";

			TableIn tableInList = new TableIn();

			RowIn rowInBean = new RowIn();
			rowInBean.setChName(non_english_name);
			rowInBean.setEnName(english_name);
			tableInList.getRow().add(rowInBean);

			String wdslFilePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_WSDLFILEPATH);//"C:/Users/Databox/workspace/doc/SWIFT/amlCheck/MatchCode_1.wsdl";
			DMService dmService = new DMService(new File(wdslFilePath).toURI().toURL());
			DataManagementServicePortType dmspt = dmService.getDMService();
			Map<String, Object> request = ((BindingProvider) dmspt).getRequestContext();
			request.put(BindingProvider.USERNAME_PROPERTY, dataFluxUserName);
			request.put(BindingProvider.PASSWORD_PROPERTY, dataFluxPassWord);
			TableOut tableOutList = dmspt.datasvcMatchCodeDdfIn(tableInList);
			
			for(int i=0;i<tableOutList.getRow().size();i++){
				if(tableOutList.getRow().get(i).getChNameMatchcode95().replace("$", "").length()==0){
					matchCodeMap.put(rowInBean.getChName(),  "");
				}else{
					matchCodeMap.put(rowInBean.getChName(),  tableOutList.getRow().get(i).getChNameMatchcode95());
				}
				if(tableOutList.getRow().get(i).getEnNameMatchcode95().replace("$", "").length()==0){
					matchCodeMap.put(rowInBean.getEnName(), "");
				}else{
					matchCodeMap.put(rowInBean.getEnName(), tableOutList.getRow().get(i).getEnNameMatchcode95());
				}
			}			
		} catch (Exception ex) {
			logger.error(String.format("datafluxMatchCode fail, exception : ", ex.getMessage()), ex);
		}
		return matchCodeMap;
	}
	
	/**
	 * 將輸入的List<RowIn>中文/英文姓名，透過SAS DataFlux工具，將名稱模糊化 (使用Web Service方式呼叫)
	 * @param rowInList
	 * @return List<Map<String, String>>
	 */
	public List<Map<String, String>> datafluxMatchCodeList(List<RowIn> rowInList) {
		List<Map<String,String>> matchCodeMapList = new ArrayList<Map<String,String>>();
		
		Map<String,String> matchCodeMap = new HashMap<String,String>();
//		for (RowIn map : rowInList) {
//			System.out.println(map.getEnName());
//		}
		try {
			String dataFluxUserName = AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_USERNAME);//"sasdemo";
			String dataFluxPassWord = new String(Base64Utils.decodeFromString(AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_PASSWORD)));//"Orion123";
			TableIn tableInList = new TableIn();
			
			for(RowIn rowIn : rowInList){
				RowIn rowInBean = new RowIn();
				rowInBean.setChName(rowIn.getChName());
				rowInBean.setEnName(rowIn.getEnName());
				tableInList.getRow().add(rowInBean);
			}
			
			String wdslFilePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_WSDLFILEPATH);//"C:/Users/Databox/workspace/doc/SWIFT/amlCheck/MatchCode_1.wsdl";
			DMService dmService = new DMService(new File(wdslFilePath).toURI().toURL());
			DataManagementServicePortType dmspt = dmService.getDMService();
			
			Map<String, Object> request = ((BindingProvider) dmspt).getRequestContext();
			request.put("com.sun.xml.internal.ws.connect.timeout", 10000);
			request.put("com.sun.xml.internal.ws.request.timeout", 30000);
			request.put(BindingProvider.USERNAME_PROPERTY, dataFluxUserName);
			request.put(BindingProvider.PASSWORD_PROPERTY, dataFluxPassWord);
			logger.debug("tableInList row size : "+tableInList.getRow().size());
			TableOut tableOutList = dmspt.datasvcMatchCodeDdfIn(tableInList);
			
			for(int i = 0; i < tableInList.getRow().size(); i ++){
				RowIn rowIn = tableInList.getRow().get(i);
				RowOut rowOut = tableOutList.getRow().get(i);
				
				if(rowIn.getChName() != null && rowIn.getChName().length() >0){
//					logger.debug("rowOut.getChNameMatchcode95() : [" + rowOut.getChNameMatchcode95()+ "]");
					if(rowOut.getChNameMatchcode95().replace("$", "").length()==0){
						matchCodeMap.put(rowIn.getChName(),  "");
					}else{
						matchCodeMap.put(rowIn.getChName(),  rowOut.getChNameMatchcode95());
					}
				}
				if(rowIn.getEnName() != null && rowIn.getEnName().length() >0){
//					logger.debug("rowOut.getEnNameMatchcode95() : [" + rowOut.getEnNameMatchcode95()+ "]");
					if(rowOut.getEnNameMatchcode95().replace("$", "").length()==0){
						matchCodeMap.put(rowIn.getEnName(),  "");
					}else{
						matchCodeMap.put(rowIn.getEnName(),  rowOut.getEnNameMatchcode95());
					}
				}
				matchCodeMapList.add(matchCodeMap);
			}
//			for (Map<String,String> map : matchCodeMapList) {
//				System.out.println(map.keySet());
//			}
		} catch (Exception ex) {
//			System.out.println("datafluxMatchCodeList fail, exception");
			logger.error(String.format("datafluxMatchCodeList fail, exception : ", ex.getMessage()), ex);
		}
		
		return matchCodeMapList;
	}
}
