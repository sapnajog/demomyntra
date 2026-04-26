package com.myntra.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
public class ExcelUtils {
	//This method accepts path of excel file and sheet name and return the data as list of data present in row in the Excel
	public static List getRowData(String filePath, String sheetName,int rowNum) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		List rowData = new ArrayList<>();
		int rows = sheet.getLastRowNum();
		Row row = sheet.getRow(rowNum);
		int cells = row.getLastCellNum();
		for(int cell=0;cell<cells;cell++) {
			String cellValue = row.getCell(cell).getStringCellValue();
			rowData.add(cellValue);
		
		}
		try {
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return rowData;
	  }
	
	// ✅ Get ALL rows data (each row = List<String>)
    public static List<List<String>> getAllRowsData(String filePath, String sheetName) {

        List<List<String>> allData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum();

            for (int i = 1; i <= rowCount; i++) { // skip header (row 0)

                Row row = sheet.getRow(i);
                if (row == null) continue;

                List<String> rowData = new ArrayList<>();

                int cellCount = row.getLastCellNum();

                for (int j = 0; j < cellCount; j++) {

                    Cell cell = row.getCell(j);

                    String value = getCellValue(cell);

                    // ❗ skip empty cells
                    if (value != null && !value.trim().isEmpty()) {
                        rowData.add(value.trim());
                    }
                }

                if (!rowData.isEmpty()) {
                    allData.add(rowData);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allData;
    }

    // ✅ Handle all cell types (VERY IMPORTANT FIX)
    private static String getCellValue(Cell cell) {

        if (cell == null) return "";

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                // 🔥 Fix for pincode like 411027.0
                return String.valueOf((long) cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getCellFormula();

            default:
                return "";
        }
    }	
	
	
	}
