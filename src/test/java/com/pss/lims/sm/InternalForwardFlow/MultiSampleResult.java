package com.pss.lims.sm.InternalForwardFlow;



import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;




public class MultiSampleResult extends SMLoginDetails{

	@Test
	
   
    public void methodToapproveMyJobMulti( ) throws Throwable {
        Thread.sleep(1000);
//        PropertiesConfiguration properties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\SampleManagement.properties");
//        Thread.sleep(5000);
//        properties.load();
//        SMLoginDetails smlogin = new SMLoginDetails();
//        smlogin.setUp();
//        String NoofTests= properties.getProperty("NoofTests").toString();
//        int NoofTests= 30;
        int rows = 7;
        System.out.println(rows);
        for(int i=1; i<=rows; i++ ) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.createSampleResult();
        
        }
      //  SMLoginDetails.tearDown();
   }

   
	
	
    }   
	  

