package com.sas.tradeFinance.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import com.sas.constraint.SwiftMtConst;
import com.sas.tradeFinance.bean.XBlExportBean;
import com.sas.tradeFinance.bean.XBlInvErrMsgBean;
import com.sas.tradeFinance.bean.XBlInvValidateBean;
import com.sas.util.AmlConfiguration;
import com.sas.util.ExcelUtils;

/**
 * 貿易融資 Bill Export讀取Excel程式
 * @author SAS
 *
 */
public class ImportXBlExport {
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		System.out.println("hello world");
		try {
			String testFilePath = "";
			//String testFilePath = "C:/iA/billOfLanding/BillOfLanding_export_template.xlsx";
			//String testFilePath = "C:/meng/test-upload-file/TEST_BL_export_template.xlsx";
			FileInputStream file = new FileInputStream(new File(testFilePath));		
			XBlInvValidateBean xBlInvValidateBean = importXBLExport(file);	
			for(XBlExportBean be : (List<XBlExportBean>)xBlInvValidateBean.getDataList()){
				System.out.println(be.getCountryOfTranshipment());
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
    
	
	/**
	 * 解析貿易融資Bill Export之Excel
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static XBlInvValidateBean importXBLExport(InputStream file) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		// To Initial xBlExport List 
		List<XBlExportBean> xBlExportList = new ArrayList<XBlExportBean>();
		boolean isStartRecord = false;
		int seq = 1;
		// Document Error mark
		boolean isDocFormatErr = false;
		// Effective Row Count
		int effCount = 0;
		// Error Row Count
		int errCount = 0;
		// Succes Row Count
		int successCount = 0;
		// Row Error Msg List
		List<String> errMsgList = new ArrayList<String>();
		
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			// To Check Primary Key Column Empty
			if(StringUtils.isBlank(ExcelUtils.getCellValueToString(row.getCell(1))) &&
			   StringUtils.isBlank(ExcelUtils.getCellValueToString(row.getCell(2))) &&
			   StringUtils.isBlank(ExcelUtils.getCellValueToString(row.getCell(3))) &&
			   StringUtils.isBlank(ExcelUtils.getCellValueToString(row.getCell(4)))){
				   break;
		   }
			
			Iterator<Cell> cellIterator = row.cellIterator();
			XBlExportBean xBlExportBe = new XBlExportBean();
			// To Initial Error Msg Map 
			Map<String, List<String>> errMsgMap = new HashMap<String, List<String>>();
			// To Create Error Column Index List
			List<String> errColList = new ArrayList<String>();
			errMsgMap.put(String.valueOf(row.getRowNum()+1), errColList);
						
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if(cell.getRowIndex() < 4){
					break;
				}
					isStartRecord = true;					
					// To Get Cell String Value
					String word = ExcelUtils.getCellValueToString(cell);
					
					//1.SCR No	
					if(cell.getColumnIndex() == 1){				 	            		
		        		// To Get mapping Scr_No
						String scrNoMapping = AmlConfiguration.getString("com.sas.templateCheck.file.2.scrNo");
						// To Check Scr_No In mapping
						if(scrNoMapping.indexOf(word) < 0 || 
							StringUtils.isBlank(word) ||
							word.length() > 3){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
		        		xBlExportBe.setScrNo(word.toUpperCase());		
		        		continue;
					}
					//2.Currency	
					if(cell.getColumnIndex() == 2){				   
	            		if(StringUtils.isBlank(word) || word.length() > 3){
							errColList.add(String.valueOf(cell.getColumnIndex()));
	            		}
	            		xBlExportBe.setCurrency(word.toUpperCase());		
	            		continue;
					}
					//3.Our Ref No	
					if(cell.getColumnIndex() == 3){				   
	            		if(StringUtils.isBlank(word) || word.length() > 12){
							errColList.add(String.valueOf(cell.getColumnIndex()));
	            		}
	            		xBlExportBe.setOurRefNo(word.toUpperCase());
	            		continue;
					} 
					//4. Seq No
					if(cell.getColumnIndex() == 4){		
					

						if(StringUtils.isBlank(word) || 
							!StringUtils.isNumeric(word) 
							|| word.length() > 2){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setSeqNo(word.toUpperCase());	
						continue;
					} 
					//5. BL No
					if(cell.getColumnIndex() == 5){			   
						if(StringUtils.isBlank(word) || word.length() > 40){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setBlNo(word.toUpperCase());	
						continue;
					} 
					//6. L/C No
					if(cell.getColumnIndex() == 6){		    
						if((SwiftMtConst.BL_EXPORT_SCR_NO_7220.equals(xBlExportBe.getScrNo()) &&
								StringUtils.isBlank(word)) ||
						   (SwiftMtConst.BL_EXPORT_SCR_NO_7310.equals(xBlExportBe.getScrNo()) &&
								   !StringUtils.isBlank(word)) ||
						    word.length() > 12){
							 errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setlCNo(word.toUpperCase());
						continue;
					} 
					//7. Shipper/Exporter
					if(cell.getColumnIndex() == 7){	   
						if(word.length() > 60){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setShipper(word.toUpperCase());	 
					} 
					//8. Consignee
					if(cell.getColumnIndex() == 8){
						if(word.length() > 60){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setConsignee(word.toUpperCase());	 
					} 
					//9. Notify Party
					if(cell.getColumnIndex() == 9){	   
						if(word.length() > 60){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setNotifyParty(word.toUpperCase());
						continue;
					} 
					//10. Second Notify Party
					if(cell.getColumnIndex() == 10){
						if(word.length() > 60){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setSecondNotifyParty(word.toUpperCase());	 
						continue;
					} 
					//11. Shipping Company
					if(cell.getColumnIndex() == 11){
						if(word.length() > 60){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setShippingCompany(word.toUpperCase());
						continue;
					} 
					//12. Forworder
					if(cell.getColumnIndex() == 12){
						if(word.length() > 60){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setForworder(word.toUpperCase());
						continue;
					} 
					//13. Shipping Agent
					if(cell.getColumnIndex() == 13){
						if(word.length() > 60){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setShippingAgent(word.toUpperCase());
						continue;
					} 
					//14. Country of Origin
					if(cell.getColumnIndex() == 14){
						if(word.length() > 2){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setCountryOfOrigin(word.toUpperCase());
						continue;
					} 
					//15. Place of Receipt 
					if(cell.getColumnIndex() == 15){
						if(word.length() > 20){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
					 	xBlExportBe.setPlaceOfReceipt(word.toUpperCase());
					 	continue;
					} 
					//16. Country of Receipt
					if(cell.getColumnIndex() == 16){
						if(word.length() > 2){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setCountryOfReceipt(word.toUpperCase());
					} 
					//17. Place of Delivery
					if(cell.getColumnIndex() == 17){
						if(word.length() > 20){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setPlaceOfDelievery(word.toUpperCase());
						continue;
					} 
					//18. Country of Delivery
					if(cell.getColumnIndex() == 18){
						if(word.length() > 2){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setCountryOfDelivery(word.toUpperCase());
						continue;
					} 
					//19.Vessel
					if(cell.getColumnIndex() == 19){  
						if(word.length() > 60){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setVessel(word);
						continue;
					}
					//20. Pre Carriage Vessel
					if(cell.getColumnIndex() == 20){
						if(word.length() > 60){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setPreCarriageVessel(word.toUpperCase());
						continue;
					} 
					//21. Port of Loading
					if(cell.getColumnIndex() == 21){
						if(word.length() > 40){
    						errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setPortOfDischarge(word.toUpperCase());
						continue;
					} 	
					//22. Country of Loading
					if(cell.getColumnIndex() == 22){
						if(word.length() > 2){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setCountryOfLanding(word.toUpperCase());
						continue;
					}		
					//23. Port of Discharge
					if(cell.getColumnIndex() == 23){
						if(word.length() > 40){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setPortOfDischarge(word.toUpperCase());
						continue;
					} 
					//24. Country of Discharge
					if(cell.getColumnIndex() == 24){	 
						if(word.length() > 2){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setCountryOfDischarge(word.toUpperCase());
						continue;
					}
					//25. Transhipment Port
					if(cell.getColumnIndex() == 25){
						if(word.length() > 40){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setTranshipmentPort(word.toUpperCase());
						continue;
					} 	
					//26. Country of Transhipment
					if(cell.getColumnIndex() == 26){
						if(word.length() > 2){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xBlExportBe.setCountryOfTranshipment(word.toUpperCase());
						continue;
					} 		
			}
			
			xBlExportBe.setErrorRow(errMsgMap);
			if(isStartRecord){
				// To Count Effective Row 
				effCount++;
				// To Check Row Exist Error Column
				if(!CollectionUtils.isEmpty(errColList)){
					// To Set Document Status Error,If Exist Row Error
					if(!isDocFormatErr){
						isDocFormatErr = true; 
					}
					// To Mark Row Err
					xBlExportBe.setCheckError(true);
					// To Count Error Row
					errCount++;
					// To Generate Error Msg String To List
					StringBuilder msg = new StringBuilder();
					for(String col: errColList){
						msg.append(",").append(col);
					}
					// To Add Message To Error Message List 
					errMsgList.add(
							String.format(SwiftMtConst.UPLOAD_EXCEL_ROW_VALIDATE_ERR_MSG,
									row.getRowNum()+1, msg.toString().substring(1)));

				}else{
					// To Count Success Row 
					successCount++;
				}

				xBlExportBe.setUniqueKey(xBlExportBe.getBlNo());
				xBlExportBe.setSeq(seq++);
				xBlExportList.add(xBlExportBe);
			}
		}
		
		// To Close Excel WorkBook
		workbook.close();
		
		return new XBlInvValidateBean(isDocFormatErr, xBlExportList, 
				new XBlInvErrMsgBean(isDocFormatErr, effCount, errCount, successCount, errMsgList));
	}
}
