package com.exceltojson.excelHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.exceltojson.entity.ExcelToJson;

public class ExcelHelper {
	
	public static String type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] Headers = {"Id", "EmployeeName", "Department"};
	static String SHEET = "ExcelToJson";
	
	public static boolean hasExcelFormat(MultipartFile file) {
		
		if(!type.equals(file.getContentType())) {
		return false;
		}
		return true;
	}
	
	public static List<ExcelToJson> excel(InputStream input){
		try {
//				Workbook workbook = new XSSFWorkbook(input);
//				Sheet sheet = workbook.getSheet(SHEET);
			
			Workbook workbook = WorkbookFactory.create(input);
			Sheet sheet = workbook.getSheetAt(0);
			
				Iterator<Row> rows = sheet.iterator();
				
				List<ExcelToJson> exceltojson = new ArrayList<ExcelToJson>();
				
				int rowNumber = 0;
				while(rows.hasNext()) {
					Row currentRow = rows.next();
					
					if(rowNumber==0) {
						rowNumber++;
						continue;
					}
					
					Iterator<Cell> cellsInRow = currentRow.iterator();
					
					ExcelToJson excelFile = new ExcelToJson();
					
					int cellId = 0;
					while(cellsInRow.hasNext()) {
						Cell currentCell = cellsInRow .next();
						
						switch(cellId) {
						case 0:
							excelFile.setId((long) currentCell.getNumericCellValue());
							break;
							
						case 1:
							excelFile.setEmployeeName(currentCell.getStringCellValue());
							break;
						
						case 2:
							excelFile.setDepartment(currentCell.getStringCellValue());
							break;
							
						default:
							break;
						}
						cellId++;
					}
					
					exceltojson.add(excelFile);
				}
				
				workbook.close();
				return exceltojson;
		
		}catch(IOException e) {
			throw new RuntimeException("Fail to parse the  excel file: "+ e.getMessage());
		}
	}

}
