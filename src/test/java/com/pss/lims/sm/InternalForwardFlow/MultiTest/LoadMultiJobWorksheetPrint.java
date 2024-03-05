package com.pss.lims.sm.InternalForwardFlow.MultiTest;

import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;
import com.pss.lims.sm.InternalForwardFlow.MultiJobWorksheetPrint;

public class LoadMultiJobWorksheetPrint extends SMLoginDetails{
	@Test
	
    public void methodToPrintMultiJobs( ) throws Throwable {
        Thread.sleep(1000);

        String NoOfRuns = properties.getProperty("NoOfRuns");
        int rows = Integer.parseInt(NoOfRuns.trim());
        System.out.println(rows);
        for(int i=1; i<=rows; i++ ) {
        	MultiJobWorksheetPrint jobWorksheetPrint = new MultiJobWorksheetPrint();
        	jobWorksheetPrint.methodToapproveMyJobMulti();
        }
   }

}
