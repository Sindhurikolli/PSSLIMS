/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.lims.ExtentTestNGPkg;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pss.lims.extentReportPkg.ExtentManager;
import com.pss.lims.extentReportPkg.ExtentManagerTest;
import com.aventstack.extentreports.ExtentTest;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author likhitha
 */
public class ExtentTestNGReportBuilder {

    public ExtentTestNGReportBuilder() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd,MM,yyyy,HH,mm,ss");
    String dateTime = sdf.format(cal.getTime());
    public String finalDateTime = dateTime; 
    //String localTime = dtf. 
    private String extentReportsName = System.getProperty("extentReport");
    @BeforeSuite
    public void beforeSuite() {
    	
    	System.out.println("Running Before Suite Method in BaseExtentTestNG");
        System.out.println("extentReportsName:" + extentReportsName);
        System.out.println("dateString: " + finalDateTime);
        if (extentReportsName != null) {
            extent = ExtentManager.createInstance(extentReportsName+finalDateTime+".html");
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(extentReportsName+finalDateTime+".html");
            extent.attachReporter(htmlReporter);
        } else {
            System.out.println("finalDateTime=========:"+finalDateTime);
            extent = ExtentManager.createInstance("extentReport" + finalDateTime + ".html");
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport" + finalDateTime + ".html");
            extent.attachReporter(htmlReporter);
        }
    	
//    	
//        extent = ExtentManagerTest.createInstance("ExtentReport.html");
//        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("ExtentReport.html");
//        extent.attachReporter(htmlReporter);
    }

    @BeforeClass
    public synchronized void beforeClass() throws InterruptedException {
        ExtentTest parent = extent.createTest(getClass().getName());
        parentTest.set(parent);
        System.out.println("Before Login");


    }

    @BeforeMethod
    public synchronized void beforeMethod(Method method) {
        ExtentTest child = parentTest.get().createNode(method.getName());
        test.set(child);
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.get().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.get().skip(result.getThrowable());
        } else {
            test.get().pass("Test passed");
        }


    }

    @AfterSuite
    public void afterSuite() throws IOException {
        extent.flush();
//        File htmlFile = new File("ExtentReport.html");
//        Desktop.getDesktop().browse(htmlFile.toURI());
        
        if (extentReportsName != null) {
            System.out.println("extentReport Without Html: " + extentReportsName+finalDateTime);
            File htmlFile = new File(extentReportsName+finalDateTime+".html");
            Desktop.getDesktop().browse(htmlFile.toURI());
        } else {
            System.out.println("extentReport In BaseExtent TestNg:"+finalDateTime);
            File htmlFile = new File("extentReport" + finalDateTime + ".html");
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
    }
}
