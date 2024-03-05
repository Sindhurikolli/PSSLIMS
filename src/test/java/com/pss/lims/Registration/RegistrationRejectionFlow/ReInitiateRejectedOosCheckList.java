package com.pss.lims.Registration.RegistrationRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.lims.login.RegistrationLoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;

public class ReInitiateRejectedOosCheckList extends RegistrationLoginDetails{
  @Test
  public void modifyRejectedOosCheckListName () throws Exception {
	  
	  document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "Modify Rejected OOSList" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Modify Rejected OOS List", "PSS-LIMS-050", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("MasterCreator"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("limsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("Lims_Module_Name1"));
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id='loginform']/div[7]/input")).click();
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno + "." + "Enter the username, password, Select Module and click on login button"
				+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		
		document.add(new Paragraph("                                     "));
		Thread.sleep(6000);
		sno++;
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("limsOosList"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OOS List", sno, false);
		Thread.sleep(4000);
		methodToModifiyRejectedOosCheckListName();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	  
	  //methodToModifiyRejectedOosCheckListName();
	  
  }
  private void methodToModifiyRejectedOosCheckListName() throws Exception {
	  
	  int count = 0;
	  boolean isRecordSelected = false;
	  String oosCheckListName = properties.getProperty("OosCheckList_Name");
	  isRecordSelected = selectRejectedOosCheckListNameRecord(count, isRecordSelected, oosCheckListName);
	  if(isRecordSelected) {
		  sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record and Click on View", sno,
					false);		  
		  Thread.sleep(5000);
		  driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
		  Thread.sleep(3000);
		  sno++;
		  JavascriptExecutor jse = (JavascriptExecutor) driver;
		  WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		  jse.executeScript("arguments[0].click();", element);
		  document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		  Thread.sleep(3000);
		  sno++;
		  driver.findElement(By.id("oOSListDescriptionInLims")).clear();
		  document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Description", sno, false);
		  Thread.sleep(5000);
		  sno++;
		  driver.findElement(By.id("oOSListDescriptionInLims")).sendKeys(properties.getProperty("Modify_OosCheckList_Description"));
		  document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description", sno, false);
		  Thread.sleep(3000);
		  sno++;
		  JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		  WebElement element1 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
		  jse1.executeScript("arguments[0].click();", element1);
		  document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		  Thread.sleep(4000);
		  
		  sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse3 = (JavascriptExecutor) driver;
			WebElement element3 = driver.findElement(By.id("subBtnInValidateESign"));
			jse3.executeScript("arguments[0].click()", element3);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse4 = (JavascriptExecutor) driver;
			WebElement element4 = driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK", sno, false);
			jse4.executeScript("arguments[0].click()", element4);
			
		 Thread.sleep(3000);
		 
		sno++;
		JavascriptExecutor jse208 = (JavascriptExecutor) driver;
		WebElement element208 =driver.findElement(By.className("username"));
		jse208.executeScript("arguments[0].click();", element208);;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on UserName",sno,false);
		 Thread.sleep(3000);
		 sno++;
		 JavascriptExecutor jse209 = (JavascriptExecutor) driver;
		 WebElement element209 =driver.findElement(By.cssSelector("a[href='Logout.do']"));
		jse209.executeScript("arguments[0].click();", element209);
		 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout",sno,true);
		 Thread.sleep(3000);

		} else {
			System.out.println("Record is not Selected");
		}
	}
  
  private boolean selectRejectedOosCheckListNameRecord(int count, boolean isRecordSelected, String oosCheckListName) throws Exception {
	  WebElement table = driver.findElement(By.id("rejectedOOSListTable"));
	  WebElement tableBody = table.findElement(By.tagName("tbody"));
	  int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	  int totalNoOfRecords = 0;
	  int noOfRecordsChecked = 0;
	  if(perPageNoOfRecordsPresent > 0) {
	  String a = driver.findElement(By.xpath("//*[@id=\"rejectedOOSListTable\"]/div/div[4]/div[2]/span")).getText();
	  String parts[] = a.split("of");
	  try {
		  totalNoOfRecords = Integer.parseInt(parts[1].trim());
		  System.out.println(totalNoOfRecords);
	  }catch (Exception e){
		  
		  System.out.println(e.getMessage());
	  }
	  }
	  
	  if(perPageNoOfRecordsPresent>0 && (count==0)) {
		  if((totalNoOfRecords>1) && ((oosCheckListName == null) || ("".equalsIgnoreCase(oosCheckListName)))) {
			  oosCheckListName = driver.findElement(By.xpath("//*[@id=\"rejectedOOSListTable\"]/div/table/tbody/tr/td[3]")).getText();
		  }else if ((oosCheckListName == null)|| ("".equalsIgnoreCase(oosCheckListName))) {
			  
			  oosCheckListName = driver.findElement(By.xpath("//*[@id=\"rejectedOOSListTable\"]/div/table/tbody/tr/td[3]")).getText();
		  }
		  ++ count;
	  }
	  if(perPageNoOfRecordsPresent>0) {
		  while(noOfRecordsChecked<totalNoOfRecords) {
		  if(totalNoOfRecords>1) {
			  for(int i =1; i<=perPageNoOfRecordsPresent; i++) {
				 String oosCheckListNameSequence = driver.findElement(By.xpath("//*[@id=\"rejectedOOSListTable\"]/div/table/tbody/tr[" + i + "]/td[3]")).getText();
			  if(oosCheckListName.equalsIgnoreCase(oosCheckListNameSequence)) {
				  driver.findElement(By.xpath("//*[@id=\"rejectedOOSListTable\"]/div/table/tbody/tr[" + i + "]/td[8]/button")).click();
				  isRecordSelected = true;
				  break;
			  }
			  }
			  if(isRecordSelected) {
				  break;
			  } 
		  }else {
			  
			 String oosCheckListNameSequence = driver.findElement(By.xpath("//*[@id=\"rejectedOOSListTable\"]/div/table/tbody/tr/td[3]")).getText();
			 if(oosCheckListName.equalsIgnoreCase(oosCheckListNameSequence)) {
				 driver.findElement(By.xpath("//*[@id=\"rejectedOOSListTable\"]/div/table/tbody/tr/td[8]/button")).click();
				 isRecordSelected = true;
				 break;
			 }
		  }
		  
		  noOfRecordsChecked = noOfRecordsChecked+perPageNoOfRecordsPresent;
		  if((!isRecordSelected) && (noOfRecordsChecked>totalNoOfRecords)) {
			  
			  driver.findElement(By.cssSelector("#rejectedOOSListTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled")).click();
			  Thread.sleep(4000);
			  table = driver.findElement(By.id("rejectedOOSListTable"));
			  tableBody = table.findElement(By.tagName("tbody"));
			  perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		  }
	  }
	  }
	  return isRecordSelected;
  }
}
