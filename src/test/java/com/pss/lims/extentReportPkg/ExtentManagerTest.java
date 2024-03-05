/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pss.lims.extentReportPkg;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.pss.lims.ExtentTestNGPkg.ExtentTestNGReportBuilder;
import com.aventstack.extentreports.ExtentReports;
import static org.testng.Assert.*;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.testng.annotations.BeforeMethod;

/**
 *
 * @author likhitha
 */
public class ExtentManagerTest {
    
    public ExtentManagerTest() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

   private static ExtentReports extent;
   private static String extentReportsName = System.getProperty("extentReport");
//   LocalDateTime myDateObj = LocalDateTime.now();
//   DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm");
//   String formattedDate = myDateObj.format(myFormatObj);
    public static ExtentReports getInstance() {
    	if (extent == null) {
//    		createInstance("surefire-reports/extentReport.html");
    		 if (extentReportsName != null) {
    			 ExtentTestNGReportBuilder baseBuilder = new ExtentTestNGReportBuilder();
                 String dateTime1=baseBuilder.finalDateTime;
                 System.out.println("dateTime---------:"+dateTime1);
                 createInstance("surefire-reports/"+ extentReportsName+dateTime1+".html");
             } else {
            	 ExtentTestNGReportBuilder baseBuilder = new ExtentTestNGReportBuilder();
                 String dateTime2=baseBuilder.finalDateTime;
                 System.out.println("dateTime---------:"+dateTime2);
                 Date date = new Date();
                 String dateString = DateFormat.getDateInstance().format(date);
                 System.out.println("dateString: " + dateString);
                 createInstance("surefire-reports/extentReport" + dateTime2 + ".html");
             }
    	}
        return extent;
    }
    
    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
//        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
//        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        return extent;
    }
}
