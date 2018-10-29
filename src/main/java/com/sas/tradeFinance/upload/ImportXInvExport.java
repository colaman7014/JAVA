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
import com.sas.tradeFinance.bean.XInvExportBean;
import com.sas.util.AmlConfiguration;
import com.sas.util.ExcelUtils;


/**
 * 貿易融資Invoice Export讀取Excel程式
 * @author SAS
 *
 */
public class ImportXInvExport {
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		System.out.println("hello world");
		try {
			String testFilePath = "";
			//String testFilePath = "C:/iA/billOfLanding/CommercialInvoice_export_template.xlsx";
			//String testFilePath = "C:/meng/test-upload-file/TEST_INV_export_template.xlsx";
			FileInputStream file = new FileInputStream(new File(testFilePath));
			XBlInvValidateBean xBlInvValidateBean = importXinvExport(file);	
			
			for(XInvExportBean be : (List<XInvExportBean>)xBlInvValidateBean.getDataList()){
				System.out.println(be.getAmount());
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/**
	 * 解析貿易融資Invoice Export之Excel
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static XBlInvValidateBean importXinvExport(InputStream file) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();

		List<XInvExportBean> xInvExportList = new ArrayList<XInvExportBean>();
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
			XInvExportBean xInvExportBe = new XInvExportBean();
			// To Initial Error Msg Map 
			Map<String, List<String>> errMsgMap = new HashMap<String, List<String>>();
			// To Create Error Column Index List
			List<String> errColList = new ArrayList<String>();
			errMsgMap.put(String.valueOf(row.getRowNum()+1), errColList);
			
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if(cell.getRowIndex()>3){
					isStartRecord = true;
					// To Get Cell String Value
					String word = ExcelUtils.getCellValueToString(cell);
					
					//1.SCR No	
					if(cell.getColumnIndex()==1){	 
						// To Get mapping Scr_No
						String scrNoMapping = AmlConfiguration.getString("com.sas.templateCheck.file.4.scrNo");
						// To Check Scr_No In mapping
						if(scrNoMapping.indexOf(word) < 0 || 
							StringUtils.isBlank(word) ||
							word.length() > 3){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
		            	xInvExportBe.setScrNo(word.toUpperCase());	
		            	continue;
					}
					//2.Currency	
					if(cell.getColumnIndex()==2){				   
		          		if(StringUtils.isBlank(word) || word.length() > 3){
							errColList.add(String.valueOf(cell.getColumnIndex()));
	            		}
	            		xInvExportBe.setCurrnecy(word.toUpperCase());
	            		continue;
					}
					//3.Our Ref No	
					if(cell.getColumnIndex()==3){				   
						if(StringUtils.isBlank(word) || word.length() > 12){
							errColList.add(String.valueOf(cell.getColumnIndex()));
	            		}
	            		xInvExportBe.setOurRefNo(word.toUpperCase());		
	            		continue;
					} 
					//4. Seq No	
					if(cell.getColumnIndex()==4){
					
						if(StringUtils.isBlank(word) ||
							!StringUtils.isNumeric(word)
							|| word.length() > 2){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
	            		xInvExportBe.setSeqNo(word.toUpperCase());	
	            		continue;
					} 
					//5. Invoice No	
					if(cell.getColumnIndex()==5){  
						if(StringUtils.isBlank(word) || word.length() > 40){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
		            	xInvExportBe.setInvoiceNo(word.toUpperCase());	
		            	continue;
					} 
					//6. Item Seq	
					if(cell.getColumnIndex()==6){ 
						boolean isErrNumFormat = 
								word.startsWith(SwiftMtConst.Err_SEQ_NUM_START);
						
						if(StringUtils.isBlank(word) || 
							!StringUtils.isNumeric(word) || isErrNumFormat
							|| word.length() > 2){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}else{
							xInvExportBe.setItemSeq(Integer.valueOf(word));
						}
						continue;
					} 
					//7. HK HS Code	
					if(cell.getColumnIndex()==7){
						if(word.length() > 6){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
	            		xInvExportBe.setHkHsCode(word.toUpperCase());	 
	            		continue;
					} 
					//8. Applicant
					if(cell.getColumnIndex()==8){
						if(word.length() > 60){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
	            		xInvExportBe.setApplicant(word.toUpperCase());	 
	            		continue;
					} 
					//9. Category of Goods	 
					if(cell.getColumnIndex()==9){  
						if(word.length() > 60){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
	            		xInvExportBe.setCategoryOfGoods(word.toUpperCase());
	            		continue;
					} 
					//10. Description of Goods
					if(cell.getColumnIndex()==10){
						if(word.length() > 100){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
	            		xInvExportBe.setDescriptionOfGoods(word.toUpperCase());	 
	            		continue;
					} 
					//11. Currency
					if(cell.getColumnIndex()==11){
						if(word.length() > 3){
							errColList.add(String.valueOf(cell.getColumnIndex()));
						}
	            		xInvExportBe.setInvCurrency(word);
	            		continue;
					} 
					//12. Unit Price
					if(cell.getColumnIndex()==12){	 //12. Unit Price
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
							xInvExportBe.setUnitPrice(specialword.setScale(5, BigDecimal.ROUND_DOWN));
						}
						continue;
					} 
					//13. Quantity
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
							xInvExportBe.setQuantity(specialword.setScale(5, BigDecimal.ROUND_DOWN));	
						}
						continue;
					} 
					//14. Amount
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
							xInvExportBe.setAmount(specialword.setScale(5, BigDecimal.ROUND_DOWN)); 
						}
						continue;
					} 
				}
			}
			
			xInvExportBe.setErrorRow(errMsgMap);
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
					xInvExportBe.setCheckError(true);
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
				
				xInvExportBe.setUniqueKey(String.format("%s%s", xInvExportBe.getInvoiceNo(), xInvExportBe.getItemSeq()));
				xInvExportBe.setSeq(seq++);
				xInvExportList.add(xInvExportBe);
			}
		}
		// To Close Excel WorkBook
		workbook.close();
				
		return new XBlInvValidateBean(isDocFormatErr, xInvExportList, 
				new XBlInvErrMsgBean(isDocFormatErr, effCount, errCount, successCount, errMsgList));
	}
}
