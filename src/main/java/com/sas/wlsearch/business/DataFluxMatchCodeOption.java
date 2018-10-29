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

import com.dataflux.wsdl.archserver.option.DMService;
import com.dataflux.wsdl.archserver.option.DataManagementServicePortType;
import com.dataflux.wsdl.archserver.option.RowIn;
import com.dataflux.wsdl.archserver.option.RowOut;
import com.dataflux.wsdl.archserver.option.TableIn;
import com.dataflux.wsdl.archserver.option.TableOut;
import com.sas.constraint.SwiftMtConst;
import com.sas.util.AmlConfiguration;
import com.sas.wlsearch.bean.MatchCodeResultBean;

/**
 * 輸入名稱模糊化主程式
 * @author SAS
 *
 */
@Component
public class DataFluxMatchCodeOption {
	private static final Logger logger = LoggerFactory.getLogger(DataFluxMatchCodeOption.class);

	/**
	 * 將輸入的中文/英文姓名，透過SAS DataFlux工具，將名稱模糊化 (使用Web Service方式呼叫)
	 * @param non_english_name
	 * @param english_name
	 * @return Map<String, String>
	 */
	public Map<String, MatchCodeResultBean> datafluxMatchCode(String sensitivity, String non_english_name, String english_name) {
		Map<String, MatchCodeResultBean> matchCodeMap = new HashMap<String, MatchCodeResultBean>();
		try {
			
			String dataFluxUserName = AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_USERNAME);//"sasdemo";
			String dataFluxPassWord = new String(Base64Utils.decodeFromString(AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_PASSWORD)));//"Orion123";

			TableIn tableInList = new TableIn();
			RowIn rowInBean = new RowIn();
			rowInBean.setSensitivity(sensitivity);
			rowInBean.setChName(non_english_name);
			rowInBean.setEnName(english_name);
			tableInList.getRow().add(rowInBean);

			String wdslFilePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUXOPTION_WSDLFILEPATH);//"C:/Users/Databox/workspace/doc/SWIFT/amlCheck/MatchCode_1.wsdl";
			DMService dmService = new DMService(new File(wdslFilePath).toURI().toURL());
			DataManagementServicePortType dmspt = dmService.getDMService();
			
			Map<String, Object> request = ((BindingProvider) dmspt).getRequestContext();
			request.put("com.sun.xml.internal.ws.connect.timeout", SwiftMtConst.COM_SUN_XML_INTERNAL_WS_CONNECT_TIMEOUT_MS);
			request.put("com.sun.xml.internal.ws.request.timeout", SwiftMtConst.COM_SUN_XML_INTERNAL_WS_REQUEST_TIMEOUT_MS);
			request.put("javax.xml.ws.client.receiveTimeout", SwiftMtConst.JAVAX_XML_WS_CLIENT_RECEIVETIMEOUT_MS);
			request.put("javax.xml.ws.client.connectionTimeout", SwiftMtConst.JAVAX_XML_WS_CLIENT_CONNECTIONTIMEOUT_MS);
			request.put(BindingProvider.USERNAME_PROPERTY, dataFluxUserName);
			request.put(BindingProvider.PASSWORD_PROPERTY, dataFluxPassWord);
			TableOut tableOutList = dmspt.datasvcMatchCodeOptionDdfIn(tableInList);
			
			for(int i=0;i<tableOutList.getRow().size();i++){
				MatchCodeResultBean cnMatchCodeResult = new MatchCodeResultBean();
				MatchCodeResultBean enMatchCodeResult = new MatchCodeResultBean();
				matchCodeMap.put(rowInBean.getChName(), cnMatchCodeResult);
				matchCodeMap.put(rowInBean.getEnName(), enMatchCodeResult);
				
				String cnMatchCodeInd = tableOutList.getRow().get(i).getChNameMatchcode().replace("$", "").length()==0 ? "" : tableOutList.getRow().get(i).getChNameMatchcode();
				String cnMatchCodeOrg = tableOutList.getRow().get(i).getChNameMatchcodeOrg().replace("$", "").length()==0 ? "" : tableOutList.getRow().get(i).getChNameMatchcodeOrg();
				String enMatchCodeInd = tableOutList.getRow().get(i).getEnNameMatchcode().replace("$", "").length()==0 ? "" : tableOutList.getRow().get(i).getEnNameMatchcode();
				String enMatchCodeOrg = tableOutList.getRow().get(i).getEnNameMatchcodeOrg().replace("$", "").length()==0 ? "" : tableOutList.getRow().get(i).getEnNameMatchcodeOrg();
								
				cnMatchCodeResult.setMatchcodeInd(cnMatchCodeInd);
				cnMatchCodeResult.setMatchcodeOrg(cnMatchCodeOrg);
				enMatchCodeResult.setMatchcodeInd(enMatchCodeInd);
				enMatchCodeResult.setMatchcodeOrg(enMatchCodeOrg);
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
	public List<Map<String, MatchCodeResultBean>> datafluxMatchCodeList(String sensitivity, List<RowIn> rowInList) {
		List<Map<String,MatchCodeResultBean>> matchCodeMapList = new ArrayList<Map<String,MatchCodeResultBean>>();
		
		Map<String,MatchCodeResultBean> matchCodeMap = new HashMap<String,MatchCodeResultBean>();
		try {
			String dataFluxUserName = AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_USERNAME);//"sasdemo";
			String dataFluxPassWord = new String(Base64Utils.decodeFromString(AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_PASSWORD)));//"Orion123";
			TableIn tableInList = new TableIn();
			
			for(RowIn rowIn : rowInList){
				RowIn rowInBean = new RowIn();
				rowInBean.setSensitivity(sensitivity);
				rowInBean.setChName(rowIn.getChName());
				rowInBean.setEnName(rowIn.getEnName());
				tableInList.getRow().add(rowInBean);
			}
			
			String wdslFilePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUXOPTION_WSDLFILEPATH);//"C:/Users/Databox/workspace/doc/SWIFT/amlCheck/MatchCode_1.wsdl";
			DMService dmService = new DMService(new File(wdslFilePath).toURI().toURL());
			DataManagementServicePortType dmspt = dmService.getDMService();
			
			Map<String, Object> request = ((BindingProvider) dmspt).getRequestContext();
			request.put("com.sun.xml.internal.ws.connect.timeout", SwiftMtConst.COM_SUN_XML_INTERNAL_WS_CONNECT_TIMEOUT_MS);
			request.put("com.sun.xml.internal.ws.request.timeout", SwiftMtConst.COM_SUN_XML_INTERNAL_WS_REQUEST_TIMEOUT_MS);
			request.put("javax.xml.ws.client.receiveTimeout", SwiftMtConst.JAVAX_XML_WS_CLIENT_RECEIVETIMEOUT_MS);
			request.put("javax.xml.ws.client.connectionTimeout", SwiftMtConst.JAVAX_XML_WS_CLIENT_CONNECTIONTIMEOUT_MS);
			request.put(BindingProvider.USERNAME_PROPERTY, dataFluxUserName);
			request.put(BindingProvider.PASSWORD_PROPERTY, dataFluxPassWord);
			logger.debug("tableInList row size : "+tableInList.getRow().size());
			TableOut tableOutList = dmspt.datasvcMatchCodeOptionDdfIn(tableInList);
			
			for(int i = 0; i < tableInList.getRow().size(); i ++){
				RowIn rowIn = tableInList.getRow().get(i);
				RowOut rowOut = tableOutList.getRow().get(i);
				
				String cnMatchCodeInd = rowOut.getChNameMatchcode().replace("$", "").length()==0 ? "" : rowOut.getChNameMatchcode();
				String cnMatchCodeOrg = rowOut.getChNameMatchcodeOrg().replace("$", "").length()==0 ? "" : rowOut.getChNameMatchcodeOrg();
				String enMatchCodeInd = rowOut.getEnNameMatchcode().replace("$", "").length()==0 ? "" : rowOut.getEnNameMatchcode();
				String enMatchCodeOrg = rowOut.getEnNameMatchcodeOrg().replace("$", "").length()==0 ? "" : rowOut.getEnNameMatchcodeOrg();
				
				if(rowIn.getChName() != null && rowIn.getChName().length() >0){
					MatchCodeResultBean cnMatchCodeResult = new MatchCodeResultBean();
					cnMatchCodeResult.setMatchcodeInd(cnMatchCodeInd);
					cnMatchCodeResult.setMatchcodeOrg(cnMatchCodeOrg);
					matchCodeMap.put(rowIn.getChName(), cnMatchCodeResult);
				}
				if(rowIn.getEnName() != null && rowIn.getEnName().length() >0){
					MatchCodeResultBean enMatchCodeResult = new MatchCodeResultBean();
					enMatchCodeResult.setMatchcodeInd(enMatchCodeInd);
					enMatchCodeResult.setMatchcodeOrg(enMatchCodeOrg);
					matchCodeMap.put(rowIn.getEnName(), enMatchCodeResult);

				}
				matchCodeMapList.add(matchCodeMap);
			}
		} catch (Exception ex) {
			logger.error(String.format("datafluxMatchCodeList fail, exception : ", ex.getMessage()), ex);
		}
		
		return matchCodeMapList;
	}
	
	/**
	 * 將輸入的List<RowIn>中文/英文姓名，透過SAS DataFlux工具，將名稱模糊化 (使用Web Service方式呼叫)
	 * @param rowInList
	 * @return List<Map<String, String>>
	 */
	public List<Map<String, MatchCodeResultBean>> datafluxMatchCodeList(List<RowIn> rowInList) {
		List<Map<String,MatchCodeResultBean>> matchCodeMapList = new ArrayList<Map<String,MatchCodeResultBean>>();
		
		Map<String,MatchCodeResultBean> matchCodeMap = new HashMap<String,MatchCodeResultBean>();
		try {
			String dataFluxUserName = AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_USERNAME);//"sasdemo";
			String dataFluxPassWord = new String(Base64Utils.decodeFromString(AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUX_PASSWORD)));//"Orion123";
			TableIn tableInList = new TableIn();
			
			for(RowIn rowIn : rowInList){
				RowIn rowInBean = new RowIn();
				rowInBean.setSensitivity(rowIn.getSensitivity());
				rowInBean.setChName(rowIn.getChName());
				rowInBean.setEnName(rowIn.getEnName());
				tableInList.getRow().add(rowInBean);
			}
			
			String wdslFilePath = AmlConfiguration.getString(SwiftMtConst.COM_SAS_DATAFLUXOPTION_WSDLFILEPATH);//"C:/Users/Databox/workspace/doc/SWIFT/amlCheck/MatchCode_1.wsdl";
			DMService dmService = new DMService(new File(wdslFilePath).toURI().toURL());
			DataManagementServicePortType dmspt = dmService.getDMService();
			
			Map<String, Object> request = ((BindingProvider) dmspt).getRequestContext();
			request.put("com.sun.xml.internal.ws.connect.timeout", SwiftMtConst.COM_SUN_XML_INTERNAL_WS_CONNECT_TIMEOUT_MS);
			request.put("com.sun.xml.internal.ws.request.timeout", SwiftMtConst.COM_SUN_XML_INTERNAL_WS_REQUEST_TIMEOUT_MS);
			request.put("javax.xml.ws.client.receiveTimeout", SwiftMtConst.JAVAX_XML_WS_CLIENT_RECEIVETIMEOUT_MS);
			request.put("javax.xml.ws.client.connectionTimeout", SwiftMtConst.JAVAX_XML_WS_CLIENT_CONNECTIONTIMEOUT_MS);
			request.put(BindingProvider.USERNAME_PROPERTY, dataFluxUserName);
			request.put(BindingProvider.PASSWORD_PROPERTY, dataFluxPassWord);
			logger.debug("tableInList row size : "+tableInList.getRow().size());
			TableOut tableOutList = dmspt.datasvcMatchCodeOptionDdfIn(tableInList);
			
			for(int i = 0; i < tableInList.getRow().size(); i ++){
				RowIn rowIn = tableInList.getRow().get(i);
				RowOut rowOut = tableOutList.getRow().get(i);
				
				String cnMatchCodeInd = rowOut.getChNameMatchcode().replace("$", "").length()==0 ? "" : rowOut.getChNameMatchcode();
				String cnMatchCodeOrg = rowOut.getChNameMatchcodeOrg().replace("$", "").length()==0 ? "" : rowOut.getChNameMatchcodeOrg();
				String enMatchCodeInd = rowOut.getEnNameMatchcode().replace("$", "").length()==0 ? "" : rowOut.getEnNameMatchcode();
				String enMatchCodeOrg = rowOut.getEnNameMatchcodeOrg().replace("$", "").length()==0 ? "" : rowOut.getEnNameMatchcodeOrg();
				
				if(rowIn.getChName() != null && rowIn.getChName().length() >0){
					MatchCodeResultBean cnMatchCodeResult = new MatchCodeResultBean();
					cnMatchCodeResult.setMatchcodeInd(cnMatchCodeInd);
					cnMatchCodeResult.setMatchcodeOrg(cnMatchCodeOrg);
					matchCodeMap.put(rowIn.getChName(), cnMatchCodeResult);
				}
				if(rowIn.getEnName() != null && rowIn.getEnName().length() >0){
					MatchCodeResultBean enMatchCodeResult = new MatchCodeResultBean();
					enMatchCodeResult.setMatchcodeInd(enMatchCodeInd);
					enMatchCodeResult.setMatchcodeOrg(enMatchCodeOrg);
					matchCodeMap.put(rowIn.getEnName(), enMatchCodeResult);

				}
				matchCodeMapList.add(matchCodeMap);
			}
		} catch (Exception ex) {
			logger.error(String.format("datafluxMatchCodeList fail, exception : ", ex.getMessage()), ex);
		}
		
		return matchCodeMapList;
	}
}
