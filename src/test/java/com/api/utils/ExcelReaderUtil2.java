package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtil2 {
	private ExcelReaderUtil2() {
		
	}

	public static <T> Iterator<T> loadTestData(String xlsxFile,String sheetName, Class<T>clazz)  {
		// APACHE POI OOXML LIB
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(xlsxFile);
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet mysheet = myWorkBook.getSheet(sheetName);//
	

		List<T>list=Poiji.fromExcel(mysheet, clazz);
		return list.iterator();
		
		
		
	}

}
