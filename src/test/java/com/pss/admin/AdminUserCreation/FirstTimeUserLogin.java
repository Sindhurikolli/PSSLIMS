package com.pss.admin.AdminUserCreation;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.lims.ExtentTestNGPkg.Utility;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;





public class FirstTimeUserLogin extends AdminLoginDetails {
 
	@Test
    public void Login() throws InterruptedException, DocumentException, IOException, Throwable {
//		try {

			document = new Document(PageSize.A4, 36, 36, 20, 20);
			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "FirstTimeUserLogin"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("First Time Login", "PSS-QMS-001", "Pass");
			writer.setPageEvent(event);
			document.open();
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("FirstTimeUserID"));
			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("OlDPASSWORD"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Thread.sleep(1000);
//			driver.findElement(By.linkText("LOGIN")).click();
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[2]/button[1]")).click();
			Thread.sleep(3000);
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);
			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			methodToChangePassword();
	        document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			////desktop.open(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//
//    }
	}
    private void methodToChangePassword() throws Exception {
        Thread.sleep(5000);
        if(driver.findElement(By.id("oldPassword")).isDisplayed())
        {
         driver.findElement(By.id("oldPassword")).sendKeys(properties.getProperty("OlDPASSWORD")); 
         Thread.sleep(1000);
         driver.findElement(By.id("newPassword")).sendKeys(properties.getProperty("newPassword")); 
         Thread.sleep(1000);
         driver.findElement(By.id("confirmPassword")).sendKeys(properties.getProperty("newPassword")); 
         Thread.sleep(1000);
         driver.findElement(By.id("oldESignPassword")).sendKeys(properties.getProperty("OlDPASSWORD")); 
         Thread.sleep(1000);
         driver.findElement(By.id("newESignPassword")).sendKeys(properties.getProperty("newPassword")); 
         Thread.sleep(1000);
         driver.findElement(By.id("confirmESignPassword")).sendKeys(properties.getProperty("newPassword")); 
         Thread.sleep(1000);
         document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Entered all details",sno,false);
         sno++;
         driver.findElement(By.xpath("//*[@id=\"firstTimeUserLoginForm\"]/div[7]/div/button[1]")).click();
         Thread.sleep(3000);
        }
        else if(driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).isDisplayed())
        {
        	Thread.sleep(2000);
        	System.out.println(driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).getText());
        	driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
        }
        else
        {
        	System.out.println("unknown error");
        	
        }
     
    }

@AfterMethod
public void testIT(ITestResult result)
{
	if(ITestResult.FAILURE==result.getStatus())
	{
		Utility.captureScreenshot(driver, result.getName());
	}
	
}
} 
	










	





	
	

