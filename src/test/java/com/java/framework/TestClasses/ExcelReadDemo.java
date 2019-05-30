package com.java.framework.TestClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.java.framework.Utils.EnvironmentPropertiesReader;
import com.java.framework.Utils.Excel;

public class ExcelReadDemo {

	static Object ob;
	static Excel objExcel;

	public static void main(String[] args) {

		objExcel = new Excel();
		ob = new Object();
		Properties prop;
		prop = EnvironmentPropertiesReader.getInstance().PropertiesFile();
		String folderName = prop.getProperty("FolderName");
		String fileName = prop.getProperty("FileName");
		String sheetName = prop.getProperty("SheetName");
		int row = 0;
		int col = 5;
		String value = "Status";
		String newRow = "yes";
		String color = "Green";
		String excelSheetPath="./TestDataFile/Suggestion.xlsx";
		String sheetName1="Bugs_4_29_2019";
		try {

			/*
			ReadData1(folderName, fileName, sheetName);
			ReadData2(folderName, fileName, sheetName);
			writeData(row, col, value, newRow, color,sheetName, folderName, fileName); 						
			ReadAllExcelData(excelSheetPath, sheetName);
			*/
			
			ReadAllExcelSheetDataByAlternativeWay(excelSheetPath, sheetName1);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}

	}
	
	public static Workbook GetWorkbookObj(String workbookpath) throws IOException {
		Workbook wobj=null;
		FileInputStream file=new FileInputStream(workbookpath);
		ZipSecureFile.setMinInflateRatio(0);
		if(workbookpath.endsWith("xlsx")) {
			wobj=new XSSFWorkbook(file);
		}else {
			wobj=new HSSFWorkbook(file);
		}
		return wobj;
	}
	
	
	public static void ReadAllExcelData(String path,String sheetname) throws Exception {
		 Workbook wobj=GetWorkbookObj(path);
		 Sheet sheetobj=wobj.getSheet(sheetname);
		 int rownum=sheetobj.getLastRowNum();
		 for(int i=1;i<=rownum;i++) {
			 Row rowobj=sheetobj.getRow(i);
			 int cellnum=rowobj.getLastCellNum();
			 for(int j=0;j<=cellnum-1;j++) {
				 Cell cellobj=rowobj.getCell(j);
				 cellobj.setCellType(CellType.STRING);
				 String cellvalue=cellobj.getStringCellValue();	
				 System.out.print(cellvalue + " || ");
			 }
			 System.out.println("\n");
		 }
	}
	
	public static void ReadAllExcelSheetDataByAlternativeWay(String excelPath, String sheeName)
	{
		Excel excelObj=new Excel();
		try
		{
			excelObj.GetAllExcelSheetData(excelPath, sheeName);
		}
		catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void ReadData1(String folderName, String fileName, String sheetName) {
		try {

			int sheetIndex = 3;
			objExcel.getExcelFile(folderName, fileName);
			int countOfRow = objExcel.getRowCount(folderName, fileName, sheetIndex);
			System.out.println("TotalCountOfRow are as follow : " + countOfRow);
			objExcel.getSheet(sheetName);
			ob = objExcel.readExcelUserInput(folderName, fileName, sheetIndex, 2, 2);
			String str = ob.toString();
			System.out.println(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ReadData2(String folderName, String fileName, String sheetName) {
		try {

			int sheetIndex = 3;
			objExcel.getExcelFile(folderName, fileName);
			int countOfRow = objExcel.getRowCount(folderName, fileName, sheetIndex);
			System.out.println("TotalCountOfRow are as follow : " + countOfRow);
			objExcel.getSheet(sheetName);
			ob = objExcel.readExcelUserInput(folderName, fileName, sheetIndex, 1, 0);
			String str = ob.toString();
			System.out.println(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeData(int row, int col, String value, String newRow, String color, String sheetName, String folderName,
			String fileName) {
		try {
			objExcel.getExcelFile(folderName, fileName);
			objExcel.writeExcel(row, col, value, newRow, color, sheetName, folderName, fileName);
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getCause());
			System.out.println(e.getMessage());

		}
	} 
}