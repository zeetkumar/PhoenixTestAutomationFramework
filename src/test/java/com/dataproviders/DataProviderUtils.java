package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider",parallel=true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv",UserBean.class);
	}
	
	// Data Provider needs to return something !!
	//
	
	@DataProvider(name= "CreateJobDataProvider", parallel= true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		Iterator<CreateJobBean> createjobIterator=CSVReaderUtil.loadCSV("testData/CreateJobData.csv",
				CreateJobBean.class);
		
		
		List<CreateJobPayload> payloadList= new ArrayList<CreateJobPayload>();
		CreateJobBean tempBean;
		CreateJobPayload  tempPayload;
		while(createjobIterator.hasNext()) {
			tempBean= createjobIterator.next();
			tempPayload=CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}
		return payloadList.iterator();
		 
	 }
	@DataProvider(name= "CreateJobAPIFakerDataProvider", parallel= true)
	public static Iterator<CreateJobPayload> createJobFakeDataProvider() {
	Iterator<CreateJobPayload> payloadIterator=	FakerDataGenerator.generateFakeCreateJobData(100);
		return payloadIterator; 
	 }
	
	
	
}
