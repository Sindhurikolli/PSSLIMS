package com.pss.lims.Listners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.pss.lims.ExtentTestNGPkg.Utility;
import com.pss.lims.SolutionManagement.LoginDetails.LoginDetails;


//public class TestListener implements ITestListener 
public class TestListenerSolutionManagement extends LoginDetails  implements ITestListener  
{
//	public static WebDriver driver;
//	WebDriver driver=null;
//	private WebDriver driver;
	
//	driver = new ChromeDriver();
    	@Override
    	public void onTestStart(ITestResult result) {
    		// TODO Auto-generated method stub
    		System.out.println("Test Name - " +result.getName());
    	}

    	@Override
    	public void onTestSuccess(ITestResult result) {
    		// TODO Auto-generated method stub
    		
    		System.out.println("Successfully executed Test - " +result.getName());
			 
    	}

    	@Override
//    	public void onTestFailure(ITestResult result) {
//    		// TODO Auto-generated method stub
//    		//screenshot code 
//    		//response if API is failed
//    		//public static WebDriver driver;
//    		System.out.println("Failed Test Case - " +result.getName());
//    		System.out.println("Failed Test Method - " +result.getMethod());
//    		   if (null != result.getThrowable()) {
//    		       String msg = result.getThrowable().getMessage();
//    		       System.out.println(msg);
//    		     }
//    		 if(ITestResult.FAILURE==result.getStatus()){
//			try{
//				TakesScreenshot screenshot=(TakesScreenshot)driver;
//				File src=screenshot.getScreenshotAs(OutputType.FILE);
//				FileUtils.copyFile(src, new File(".\\" +"/FailedSS/" +result.getName()+".png"));
//				System.out.println("Successfully captured a screenshot");
//			}catch (Exception e){
//				System.out.println("Exception while taking screenshot "+e.getMessage());
//				e.printStackTrace();
//			} 
//    		}
//    	}
    	
    	public void onTestFailure(ITestResult result) {
    		// TODO Auto-generated method stub
    		//screenshot code 
    		//response if API is failed
//    		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//    		driver = new ChromeDriver();
    		System.out.println("Failed Test Case - " +result.getName());
    		System.out.println("Failed Test Method - " +result.getMethod());
//    		ITestContext context = result.getTestContext();
//    	       WebDriver driver = (WebDriver)context.getAttribute("driver");
    		  result.getThrowable().printStackTrace();
    		Utility.captureScreenshot(driver, result.getName());
    	}
    		 
//    		  if (null != result.getThrowable()) {
//    		      String msg = result.getThrowable().getMessage();
//    		        System.out.println(msg);
//    		        
//    		      result.getThrowable().printStackTrace();
//    		      
//    		    }
    		 
    		   		
//    		  if(ITestResult.FAILURE==result.getStatus())
//    			{
//    				Utility.captureScreenshot(driver, result.getName());
//    				
//    			}	  
//      			if(ITestResult.FAILURE==result.getStatus()){
//    					try{
//    						TakesScreenshot screenshot=(TakesScreenshot)driver;
//    						File file = new File(".\\" +"/FailedSS/" +result.getName()+".png");			
//    								
//    					    File src  =	screenshot.getScreenshotAs(OutputType.FILE);
//    						FileUtils.copyFile(src, file);
//    						System.out.println("Successfully captured a screenshot");
//    					}catch (Exception e){
//    						System.out.println("Exception while taking screenshot "+e.getMessage());
//    						e.printStackTrace();
//    					} 
//    		    		}
//    		  captureScreenshot(driver, screenshotname);
    		 
//    	}
    		  
      			
//    		if(ITestResult.FAILURE==result.getStatus()){
//			try{
//				TakesScreenshot screenshot=(TakesScreenshot)driver;
//				File file = new File(".\\" +"/FailedSS/" +result.getName()+".png");			
//						
//			    File src  =	screenshot.getScreenshotAs(OutputType.FILE);
//				FileUtils.copyFile(src, file);
//				System.out.println("Successfully captured a screenshot");
//			}catch (Exception e){
//				System.out.println("Exception while taking screenshot "+e.getMessage());
//				e.printStackTrace();
//			} 
//    		}
    	//}
    	
//    	 public static void captureScreenshot(WebDriver driver, String screenshotname) 
//			{
//				try
//				{
//					TakesScreenshot screenshot =(TakesScreenshot)driver;
//					
//					File source = screenshot.getScreenshotAs(OutputType.FILE);
//					
//					FileUtils.copyFile(source, new File("./Screenshots/"+screenshotname+".png"));
//					
//					System.out.println("Screenshot Taken");
//					
//				}catch(Exception excep)
//				{
//					System.out.println("Throwing exception while taking screenshot" +excep.getMessage());
//				}
//		 
//			}

    	@Override
    	public void onTestSkipped(ITestResult result) {
    		// TODO Auto-generated method stub
    		System.out.println("Skipped Test - " +result.getName());
    	}

    	@Override
    	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    		// TODO Auto-generated method stub
    		System.out.println("onTestFailedButWithinSuccessPercentage - " +result.getName());
    	}

    	@Override
    	public void onStart(ITestContext context) {
    		// TODO Auto-generated method stub
    		System.out.println("onStart - " +context.getName());
    		
    	}

    	@Override
    	public void onFinish(ITestContext context) {
    		// TODO Auto-generated method stub
    		System.out.println("onFinish - " +context.getName());	
    	}

    }

	










	





	
	

