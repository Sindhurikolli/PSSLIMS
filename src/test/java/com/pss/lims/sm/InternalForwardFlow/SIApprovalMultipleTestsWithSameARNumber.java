package com.pss.lims.sm.InternalForwardFlow;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;





public class SIApprovalMultipleTestsWithSameARNumber extends SMLoginDetails{

	@Test

    public void methodToapproveMyJobMultiSI( ) throws Throwable {
        Thread.sleep(1000);

        Thread.sleep(5000);
//        SMLoginDetails smlogin = new SMLoginDetails();
//        smlogin.setUp();
        String NoofTests= properties.getProperty("NoofTests").toString();
        int rows = Integer.parseInt(NoofTests.trim());
        System.out.println(rows);
        for(int i=1; i<=rows; i++ ) {
        SIApproval siapprovals = new SIApproval();
        siapprovals.approveSI();        
        }
      //  SMLoginDetails.tearDown();
   }

   
	
	
    }   
	  

