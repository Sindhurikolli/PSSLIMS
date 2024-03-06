package com.pss.lims.sm.InternalForwardFlow;

import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;
import com.pss.lims.util.Utilities;

public class JobPrintStatusUpdate extends SMLoginDetails{
		
	 		int limsSampleLoginId = 0;
	 		int printedBy = 0;
	 		PreparedStatement pstmt = null;
	 		
	 		DateFormat dateFormatYMD = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");	
	 		Date now = new Date();
	 		String vDateYMD = dateFormatYMD.format(now);
//	 		java.sql.Date date = new java.sql.Date(now.getTime());
	 		java.sql.Timestamp timestamp = new java.sql.Timestamp(now.getTime());
	 		
		    @Test
		    public void getARNUmberFromDataBase() throws InterruptedException, ConfigurationException {
		    	
		    	Thread.sleep(1000);
				driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Reviewer"));
				Thread.sleep(1000);
				driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
				Thread.sleep(1000);
				Select module = new Select(driver.findElement(By.id("limsModule")));
				Thread.sleep(1000);
				module.selectByVisibleText(properties.getProperty("Lims_Module_Name1"));
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='loginform']/div[4]/button[1]")).click();
				sno++;
				WebDriverWait wait = new WebDriverWait(driver, 240);
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='myJobsListPageInSample.do'")));
				JavascriptExecutor jse1 = (JavascriptExecutor) driver;
				WebElement element1 = driver.findElement(By.cssSelector("a[href='myJobsListPageInSample.do']"));
				jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
				Thread.sleep(1000);
				jse1.executeScript("arguments[0].click();", element1);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#myJobListTable > div > div.jtable-busy-message[style='display: none;']")));
				Thread.sleep(4000);
				String AR_NumberFor_Job_Print = driver.findElement(By.xpath("//*[@id='jobAllotmentTable']/div/table/tbody/tr/td[13]")).getText();
				System.out.println(AR_NumberFor_Job_Print);
				
				PropertiesConfiguration prop = new PropertiesConfiguration("src/test/java/LIMSUIProperties/SampleManagement.properties");
				prop.setProperty("Ar_Number_For_Print_Staus_Update", AR_NumberFor_Job_Print);	
				prop.save();
				Thread.sleep(2000);
		    	
		        try {
		            String query1 = "select ID from LIMS_SAMPLE_LOGIN where AR_NO = '"+properties.getProperty("Ar_Number_For_Print_Staus_Update")+"'";
		            statement = connection.createStatement();
		            rs = statement.executeQuery(query1);
		           
		            while(rs.next()){
		            	limsSampleLoginId = rs.getInt("ID");
	                System.out.println("limsSampleLoginId:- "+limsSampleLoginId);
		            }

		            String query2 = "select IN_CHARGE  from LIMS_SAMPLE_TESTS where SAMPLE_ID = '"+limsSampleLoginId+"'";
		            statement = connection.createStatement();
		            rs = statement.executeQuery(query2);

		            while(rs.next()){
		               printedBy= rs.getInt("IN_CHARGE");
//
		               System.out.println("printedBy:- "+printedBy);
	                pstmt = connection.prepareStatement("update LIMS_SAMPLE_TESTS set JOB_STATUS = ?, PRINTED_BY = ?, PRINTED_ON = ? where SAMPLE_ID = ?");
		            //Set name value which you wants to update.
		            pstmt.setInt(1, 2);
		            pstmt.setInt(2, printedBy);
		            pstmt.setTimestamp(3,timestamp);
		            //Set id of record which you wants to update.
		            pstmt.setInt(4, limsSampleLoginId);
		            //To execute update query.   
		            pstmt.executeUpdate();
		            }
		            
		            
		        } catch (SQLException ex) {
		           ex.printStackTrace();
		        }
		        
		        Thread.sleep(3000);
				sno++;
				driver.findElement(By.className("username")).click();
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		        
		    }

		}
