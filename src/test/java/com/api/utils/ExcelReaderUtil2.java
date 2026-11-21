package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentilas;

public class ExcelReaderUtil2 {
	private ExcelReaderUtil2() {
		
	}

	public static Iterator<UserCredentilas> loadTestData()  {
		// APACHE POI OOXML LIB
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhoenixTestData.xlsx");
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet mysheet = myWorkBook.getSheet("LoginTestData");
	

		//Read the Excel File-----> Store in the ArrayList<UserCredentails>
		
		// I want to know the indexes for the username and password in our sheet!!
		
		XSSFRow headerRow=mysheet.getRow(0);
		int userNameIndex=-1;
		int passwordIndex=-1;
		for(Cell cell:headerRow) {
			if(cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
				userNameIndex=cell.getColumnIndex();
			}
			if(cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
				passwordIndex=cell.getColumnIndex();
			}
		}
		System.out.println(userNameIndex+" "+passwordIndex);
		
	int lastRowIndex=mysheet.getLastRowNum();
	XSSFRow rowData;
	UserCredentilas userCredentails;
	ArrayList<UserCredentilas> userList= new ArrayList<UserCredentilas>();
		for(int rowIndex=1;rowIndex<=lastRowIndex;rowIndex++) {
			rowData=mysheet.getRow(rowIndex);
			userCredentails=new UserCredentilas(rowData.getCell(userNameIndex).toString(),rowData.getCell(passwordIndex).toString());
		userList.add(userCredentails);
		}
	
		return userList.iterator();
		
		
	}

}
