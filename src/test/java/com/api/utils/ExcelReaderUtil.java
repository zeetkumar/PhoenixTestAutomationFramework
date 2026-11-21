package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {

	public static void main(String[] args) throws IOException {
		//APACHE POI OOXML LIB
	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/PhoenixTestData.xlsx");	
		XSSFWorkbook myWorkBook = new XSSFWorkbook(is);
		
   XSSFSheet mysheet=myWorkBook.getSheet("LoginTestData");
	XSSFRow myRow;
	XSSFCell mycell;
	
   
   
   int lastRowindex=mysheet.getLastRowNum();
   System.out.println(lastRowindex);
   XSSFRow rowHeader=mysheet.getRow(0);
   int lastindexofCol=rowHeader.getLastCellNum()-1;
   System.out.println(lastindexofCol);
   
   for(int rowIndex=0;rowIndex<=lastRowindex; rowIndex++) {
	   for(int colIndex=0;colIndex<=lastindexofCol;colIndex++) {
		   myRow=mysheet.getRow(rowIndex);
		   mycell=myRow.getCell(colIndex);
		   System.out.print(mycell+" ");
	   }
	   System.out.println(" ");
   }

	}

}
