package com.sas.util;

import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.Cell;

public final class ExcelUtils {

	/**
	 * To Get Cell Value To String
	 * 
	 * @param execCell
	 * @return String
	 * @author Jay
	 */
	public static String getCellValueToString(Cell execCell){
		// Initial Cell Value
		String cellValue = org.apache.commons.lang.StringUtils.EMPTY;
		DecimalFormat df = new DecimalFormat("#");
		
		// Check Cell Is Null Or Not
		if (execCell == null) {
			return cellValue;
		}
		
		// By Cell Type To Get Value
		switch (execCell.getCellType()){
			// String Type
			case Cell.CELL_TYPE_STRING:
				cellValue = execCell.getRichStringCellValue().getString().trim();
				break;
			// Numeric Type
			case Cell.CELL_TYPE_NUMERIC:
				cellValue = df.format(execCell.getNumericCellValue()).toString();
				break;
			// Boolean Type
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(execCell.getBooleanCellValue()).trim();
				break;
			// Formula Type
			case Cell.CELL_TYPE_FORMULA:
				cellValue = execCell.getCellFormula();
				break;
			default:
		}
		return cellValue;
	}
}
