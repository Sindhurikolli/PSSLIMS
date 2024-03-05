package com.pss.lims.sm.InternalForwardFlow;



import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;




public class MultiJobWorksheetPrint extends SMLoginDetails{

	@Test
	
   
    public void methodToapproveMyJobMulti( ) throws Throwable {
        Thread.sleep(1000);
//        PropertiesConfiguration properties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\SampleManagement.properties");
//        Thread.sleep(5000);
//        properties.load();
//        SMLoginDetails smlogin = new SMLoginDetails();
//        smlogin.setUp();
        String NoofTests= properties.getProperty("NoofTests").toString();
        int rows = Integer.parseInt(NoofTests.trim());
        System.out.println(rows);
        for(int i=1; i<=rows; i++ ) {
        MyJob mydetails = new MyJob();
        mydetails.approveMyJob(); 
        
        }
      //  SMLoginDetails.tearDown();
   }

   
	
	
    }   
	  

