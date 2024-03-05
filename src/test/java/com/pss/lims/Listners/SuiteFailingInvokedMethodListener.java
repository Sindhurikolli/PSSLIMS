package com.pss.lims.Listners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class SuiteFailingInvokedMethodListener implements IInvokedMethodListener {
    private static volatile boolean failing;

    public SuiteFailingInvokedMethodListener() {
        failing = false;
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (failing) {
            throw new RuntimeException("Test skipped due to a detected failure in the overall suite.");
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (! testResult.isSuccess()) {
            failing = true;
        }

        //Update the test Result with "Skipped".  
        //Alternatively, you could use omit this code.
        //The RuntimeException thrown above will mark the test with "Failed" by default.
        if ((failing) && 
                (testResult.getThrowable().getMessage().contains("Test skipped"))) {
            testResult.setStatus(ITestResult.SKIP);
        }
    }

}


