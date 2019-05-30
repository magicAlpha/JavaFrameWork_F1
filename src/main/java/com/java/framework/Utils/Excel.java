package com.java.framework.Utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

public class Excel {
	public File file;
	public FileInputStream fileStream;
	public XSSFWorkbook workbook;
	public FileOutputStream fileOutputStream;
	public XSSFSheet worksheet;
	public XSSFRow excelRow;
	public XSSFCell excelCell;

	public void getExcelFile(String folderName, String fileName) throws IOException, InterruptedException {
		Thread.sleep(500);
		try {

			file = new File("./" + folderName + "/" + fileName + ".xlsx");
			fileStream = new FileInputStream(file);
			ZipSecureFile.setMinInflateRatio(0);
			workbook = new XSSFWorkbook(fileStream);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(fileName + " file not found in " + folderName + " folder");
		} catch (Exception e) {			
			System.out.println(fileName + " file not found in " + folderName + " folder");
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}

	}

	public void createExcelFile(String folderName, String fileName, String sheetName)
			throws IOException, InterruptedException {
		Thread.sleep(500);
		fileOutputStream = new FileOutputStream(new File("./" + folderName + "/" + fileName + ".xlsx"));
		workbook = new XSSFWorkbook();
		createSheet(sheetName);
		closeExcelFile(folderName, fileName);
	}

	public void closeExcelFile(String folderName, String fileName) throws IOException {
		FileOutputStream outfile = new FileOutputStream(new File("./" + folderName + "/" + fileName + ".xlsx"));
		workbook.write(outfile);
		outfile.flush();
		outfile.close();
		workbook = null;
		worksheet = null;
		file = null;
		fileStream = null;
		excelRow = null;
		excelCell = null;
	}

	public void createSheet(String sheetName) {
		worksheet = workbook.createSheet(sheetName);
	}

	public void getSheet(String sheetName) {
		try {
			worksheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(sheetName + " sheet not found in file");
		}
	}

	public void writeExcel(int row, int col, String value, String newRow, String color, String sheetName, String folder, String fileName) {
		
		worksheet=workbook.getSheet(sheetName);
		File file = new File("./" + folder + "/" + fileName + ".xlsx");		
		if (newRow.equalsIgnoreCase("yes")) {
			excelRow = worksheet.createRow(row);
		} else {
			excelRow = worksheet.getRow(row);
		}
		excelCell = excelRow.createCell(col);
		excelCell.setCellValue(value);
		if (color.equalsIgnoreCase("Green")) {
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			excelCell.setCellStyle(style);
		} else if (color.equalsIgnoreCase("red")) {
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			excelCell.setCellStyle(style);
		}
		try {
			fileOutputStream=new FileOutputStream(file);
			try {
				workbook.write(fileOutputStream);
				fileOutputStream.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		
	}

	public Object readExcelUserInput(String folder, String fileName, int sheet, int rowNum, int cellNum) {
		Object data = "";
		XSSFWorkbook wb = null;
		try {
			String fileLocation = "./" + folder + "/" + fileName + ".xlsx";
			File file = new File(fileLocation);
			FileInputStream fileInputStream = new FileInputStream(file);
			wb = new XSSFWorkbook(fileInputStream);
			data = wb.getSheetAt(sheet).getRow(rowNum).getCell(cellNum).getStringCellValue();
		} catch (NullPointerException e) {
		} catch (IllegalStateException e) {
			data = wb.getSheetAt(sheet).getRow(rowNum).getCell(cellNum).getNumericCellValue();
			double num = (Double) data;
			int n = (int) num;
			data = n;
		} catch (Exception e) {
			data = wb.getSheetAt(sheet).getRow(rowNum).getCell(cellNum).getNumericCellValue();
			double num = (Double) data;
			int n = (int) num;
			data = n;
		}
		return data;
	}

	public int getRowCount(String folder, String fileName, int sheet) throws IOException {
		int numOFRows = 0;
		try {
			File file = new File("./" + folder + "/" + fileName + ".xlsx");
			FileInputStream fileInputStream = new FileInputStream(file);
			Workbook wb = new XSSFWorkbook(fileInputStream);
			numOFRows = wb.getSheetAt(sheet).getLastRowNum();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (numOFRows);
	}
	
	
	public Workbook GetWorkBook(String excelSheetPath)
	{
		File file=null;
		FileInputStream fis=null;		
		Workbook workBook=null;
		try
		{
			file=new File(excelSheetPath);
			fis=new FileInputStream(file);
			ZipSecureFile.setMinInflateRatio(0);
			if(excelSheetPath.endsWith("xlsx"))
			{
				workBook=new XSSFWorkbook(fis);
			}
			else
			{
				workBook=new HSSFWorkbook(fis);
			}
		}
		catch (Exception e) {
			e.printStackTrace();

		}
		return workBook;
	}
	public void GetAllExcelSheetData(String excelSheetPath, String SheetName)
	{
		Workbook workBook=null;
		Sheet sheet;
		Row row;
		Cell cell;
		try
		{
			workBook=GetWorkBook(excelSheetPath);
			sheet=workBook.getSheet(SheetName);
			int totalNumberOfRows=sheet.getLastRowNum();
			for(int i=1;i<totalNumberOfRows;i++)
			{
				row=sheet.getRow(i);
				int totalCellNumber=row.getLastCellNum();
				
				for(int j=0;j<totalCellNumber-1;j++)
				{
					cell=row.getCell(j);					
					cell.setCellType(CellType.STRING);					
					String cellValue=cell.getStringCellValue();
					 System.out.print(cellValue + " || ");
				}
				System.out.println();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
