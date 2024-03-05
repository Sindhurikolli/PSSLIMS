package com.pss.lims.sm.InternalForwardFlow.MultiTest;

import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;
import com.pss.lims.sm.InternalForwardFlow.SampleRegistrationApprove;

public class LoadSampleRegApproval extends SMLoginDetails{
	@Test
	
    public void methodToApproveMultiSamplReg( ) throws Throwable {
        Thread.sleep(1000);

        String NoOfRuns = properties.getProperty("NoOfRuns");
        int rows = Integer.parseInt(NoOfRuns.trim());
        System.out.println(rows);
        for(int i=1; i<=rows; i++ ) {
        	SampleRegistrationApprove sampleRegApp = new SampleRegistrationApprove();
        	sampleRegApp.approveSampleRegistration(); 
        
        }
   }

}
