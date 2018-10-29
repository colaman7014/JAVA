package com.sas.tradeFinance.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import com.sas.tradeFinance.bean.XBlInvErrMsgBean;
import com.sas.tradeFinance.bean.XBlInvValidateBean;
import com.sas.tradeFinance.bean.XInvImportBean;
import com.sas.util.AmlConfiguration;
import com.sas.util.ExcelUtils;

/**
 * 貿易融資Invoice Import讀取Excel程式
 * @author SAS
 *
 */
public class ImportXInvImport {
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		System.out.println("hello world");
		try {
			//String testFilePath = "";
			//String testFilePath = "C:/iA/billOfLanding/CommercialInvoice_import_template.xlsx";
			String testFilePath = "C:/meng/test-upload-file/TEST_INV_import_template.xlsx";
			FileInputStream file = new FileInputStream(new File(testFilePath));
			XBlInvValidateBean xBlInvValidateBean = importXInvImport(file);	
		
			for(XInvImportBean be : (List<XInvImportBean>)xBlInvValidateBean.getDataList()){
				System.out.println(be.getQueryPrice());
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/**
	 * 解析貿易融資Bill Import之Excel
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static XBlInvValidateBean importXInvImport(InputStream file) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		List<XInvImportBean> xInvImportList = new ArrayList<XInvImportBean>();
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
			XInvImportBean xInvImportBe = new XInvImportBean();
			// To Initial Error Msg Map 
			Map<String, List<String>> errMsgMap = new HashMap<String, List<String>>();
			// To Create Error Column Index List
			List<String> errColList = new ArrayList<String>();
			errMsgMap.put(String.valueOf(row.getRowNum()+1), errColList);
									
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if(cell.getRowIndex() > 3){
					isStartRecord = true;
					// To Get Cell String Value
					String word = ExcelUtils.getCellValueToString(cell);
					
					//1.SCR No	
					if(cell.getColumnIndex()==1){
						// To Get mapping Scr_No
						String scrNoMapping = AmlConfiguration.getString("com.sas.templateCheck.file.3.scrNo");
						// To Check Scr_No In mapping
						if(scrNoMapping.indexOf(word) < 0 || 
							StringUtils.isBlank(word) ||
							word.length() > 3){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
	            		xInvImportBe.setScrNo(word.toUpperCase());		
	            		continue;
					}
					//2.Type		
					if(cell.getColumnIndex()==2){					   
						if(word.length() == 1){
							String subMaping5410 = AmlConfiguration.getString("com.sas.templateCheck.file.3.5410type");
//							String subMaping6210 = AmlConfiguration.getString("com.sas.templateCheck.file.3.6210type");
							
			            	if(subMaping5410.indexOf(word) < 0){
		            		errColList.add(String.valueOf(cell.getColumnIndex()));
			            	}
						}else{
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
            			xInvImportBe.setType(word.toUpperCase());			
            			continue;
					}
					//3.L/C No		
					if(cell.getColumnIndex()==3){	
						if(word.length() > 12 || StringUtils.isBlank(word)){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
	            		xInvImportBe.setLCNo(word.toUpperCase());		
	            		continue;
					} 
					//4. IB No
					if(cell.getColumnIndex()==4){ 
						if(word.length() > 12){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xInvImportBe.setIbNo(word.toUpperCase());			 
						continue;
					} 
					//5. Invoice No
					if(cell.getColumnIndex()==5){	
						if(StringUtils.isBlank(word) || word.length() > 40){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
            			xInvImportBe.setInvoiceNo(word);	
            			continue;
					} 
					//6. Item Seq	
					if(cell.getColumnIndex()==6){	   
						boolean isErrNumFormat = 
								word.startsWith(SwiftMtConst.Err_SEQ_NUM_START);
						
						if(StringUtils.isBlank(word) || 
							!StringUtils.isNumeric(word) || 
							isErrNumFormat){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}else{
							xInvImportBe.setItemSeq(Integer.valueOf(word));	 
							continue;
						}
					} 
					//7. HK HS Code	
					if(cell.getColumnIndex()==7){
						if(word.length() > 6){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
        				xInvImportBe.setHkHsCode(word.toUpperCase());	 
        				continue;
					} 
					//8. Our Customer
					if(cell.getColumnIndex()==8){
						boolean isErrNumFormat = 
								word.startsWith(SwiftMtConst.Err_SEQ_NUM_START);
						if(!StringUtils.isNumeric(word) || word.length() > 10 
							|| StringUtils.isBlank(word) || isErrNumFormat){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
						xInvImportBe.setOurCustomer(word.toUpperCase());
						continue;
					} 
					//9. Applicant 
					if(cell.getColumnIndex()==9){
						if(word.length() > 60){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
	            		xInvImportBe.setApplicant(word.toUpperCase());
	            		continue;
					} 
					//10. Category of Goods
					if(cell.getColumnIndex()==10){
						if(word.length() > 60){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
	            		xInvImportBe.setCategoryOfGoods(word.toUpperCase());	 
	            		continue;
					} 
					//11. Description of Goods
					if(cell.getColumnIndex()==11){
						if(word.length() > 100){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
            			xInvImportBe.setDescriptionOfGoods(word.toUpperCase());
            			continue;
					} 
					//12. Currency
					if(cell.getColumnIndex()==12){
						if(word.length() > 3){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
	            		xInvImportBe.setCurrnecy(word.toUpperCase());
	            		continue;
					} 
					//13. Unit Price
					if(cell.getColumnIndex()==13){
						boolean isErrNumFormat = 
								word.startsWith(SwiftMtConst.Err_SEQ_NUM_START);
						
						boolean parseable = true;
						//To Check The Value Can Parseable
						try{
							Double.valueOf(word);
						}catch(Exception e){
							parseable = false;
						}
						
						if(isErrNumFormat || !parseable){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}else{
							BigDecimal specialword = BigDecimal.valueOf(Double.valueOf(word));
							xInvImportBe.setUnitPrice(specialword.setScale(5, BigDecimal.ROUND_DOWN));	 
						}
						continue;
					} 
					//14. Quantity
					if(cell.getColumnIndex()==14){
						boolean isErrNumFormat = 
								word.startsWith(SwiftMtConst.Err_SEQ_NUM_START);
						
						boolean parseable = true;
						//To Check The Value Can Parseable
						try{
							Double.valueOf(word);
						}catch(Exception e){
							parseable = false;
						}
						
						if(isErrNumFormat || !parseable){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}else{
							BigDecimal specialword = BigDecimal.valueOf(Double.valueOf(word));
							xInvImportBe.setQuantity(specialword.setScale(5, BigDecimal.ROUND_DOWN));  
						}
						continue;
					} 
					//15. Amount
					if(cell.getColumnIndex()==15){
						boolean isErrNumFormat = 
								word.startsWith(SwiftMtConst.Err_SEQ_NUM_START);
						
						boolean parseable = true;
						//To Check The Value Can Parseable
						try{
							Double.valueOf(word);
						}catch(Exception e){
							parseable = false;
						}
						
						if(isErrNumFormat || !parseable){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}else{
							BigDecimal specialword = BigDecimal.valueOf(Double.valueOf(word));
							xInvImportBe.setAmount(specialword.setScale(5, BigDecimal.ROUND_DOWN));  
						}
						continue;
					} 
					//16. Query Price
					if(cell.getColumnIndex()==16){
						boolean isErrNumFormat = 
								word.startsWith(SwiftMtConst.Err_SEQ_NUM_START);
						
						boolean parseable = true;
						//To Check The Value Can Parseable
						try{
							Double.valueOf(word);
						}catch(Exception e){
							parseable = false;
						}
						
						if(isErrNumFormat || !parseable){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}else{
							BigDecimal specialword = BigDecimal.valueOf(Double.valueOf(word));
							xInvImportBe.setQueryPrice(specialword.setScale(5, BigDecimal.ROUND_DOWN)); 
						}
						continue;
					} 
				}
			}
			
			xInvImportBe.setErrorRow(errMsgMap);
			
			if(isStartRecord){
				// To Count Effective Row 
				effCount++;
				// To Check Row Exist Error Column
				if(!CollectionUtils.isEmpty(errColList)){
					// To Set Document Status Error,If Exist Row Error
					if(!isDocFormatErr){
						isDocFormatErr = true; 
					}
					xInvImportBe.setCheckError(true);
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
				}
				
				xInvImportBe.setUniqueKey(String.format("%s%s", xInvImportBe.getInvoiceNo(), xInvImportBe.getItemSeq()));
				xInvImportBe.setSeq(seq++);
				xInvImportList.add(xInvImportBe);
			}
		}
		// To Close Excel WorkBook
		workbook.close();
				
		return new XBlInvValidateBean(isDocFormatErr, xInvImportList, 
				new XBlInvErrMsgBean(isDocFormatErr, effCount, errCount, successCount, errMsgList));
	}
}
