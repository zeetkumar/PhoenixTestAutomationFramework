package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	/*
	 * Constructor is private
	 * 
	 * 
	 * static- static Method!! Job: Help me Read the CSV file and Map it a Bean
	 * 
	 * 
	 */
	private CSVReaderUtil() {
		// No one can create Object of CSVReaderUtil Outside the Class
		// Singleton Class Constructor are Private
	}

	public static void loadCSV(String PathOfCSVFile) {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(PathOfCSVFile);
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr); // CsvReader Constructor // Requires a Reader!!!

		// Write the code to Map the CSV to POJO

		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();

		List<UserBean> userList = csvToBean.parse();
		System.out.println(userList.get(1).getUsername());

	}

}
