package com.pss.lims.sm.RegistrationForwardFlow;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SupplierReg {
	public WebDriver driver;
  @Test
  
  public void supplierRegistration() throws Exception {
	  
	  System.setProperty("webdriver.chrome.driver", "D:\\SeleniumProjects\\LIMS\\chromedriver.exe");
	  
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("http://192.168.1.8:9090/LIMS/Logout.do");
	  Thread.sleep(2000);
	  driver.findElement(By.name("loginUserName")).sendKeys("testuser1");
	  Thread.sleep(2000);
	  driver.findElement(By.name("loginPassword")).sendKeys("demo1");
	  Thread.sleep(2000);
	  Select limsModule = new Select(driver.findElement(By.id("limsModule")));
	  limsModule.selectByIndex(1);
	  Thread.sleep(2000);
	  driver.findElement(By.className("button")).click();
	  Thread.sleep(3000);
	  JavascriptExecutor jse = (JavascriptExecutor) driver;
	  WebElement element = driver.findElement(By.cssSelector("a[href='supplierRegnPage.do']"));
	  //WebElement element = driver.findElement(By.id("limsCreateSuplier"));
	  jse.executeScript("arguments[0].click()", element);
	  
	  methodToCreateSupplier();
  }

  private void methodToCreateSupplier() throws InterruptedException {
	  
	  Thread.sleep(2000);
	  System.out.println(driver.getTitle());
	  Thread.sleep(2000);
	  JavascriptExecutor jse = (JavascriptExecutor) driver;
	  WebElement element = driver.findElement(By.linkText("Next"));
	  jse.executeScript("arguments[0].click()", element);
	  Thread.sleep(2000);
	  driver.findElement(By.id("supplierNameInSupplierReg")).sendKeys("Gland Pharma2");
	  Thread.sleep(2000);
	  driver.findElement(By.id("supplierCodeInSupplierReg")).sendKeys("GPL2");
	  Thread.sleep(2000);
	  driver.findElement(By.id("selAppFromUserInSupplierReg")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("locTreeInCalPmBdm_2_span")).click();
	  Thread.sleep(2000);
	  int count = 0;
	  boolean isRecordSelected = false;
	  String lastName = "6";
	  isRecordSelected = selectSupplierRecord(count,lastName,isRecordSelected);
	  if(isRecordSelected) {
		  
		  Thread.sleep(2000);
		  driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
		  Thread.sleep(2000);
		  JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		  WebElement element1 = driver.findElement(By.linkText("Finish"));
		  jse1.executeScript("arguments[0].click()", element1);
		  
		  Thread.sleep(3000);
		  driver.findElement(By.id("eSignPwdInWnd")).sendKeys("demo1");
		  Thread.sleep(2000);
		  driver.findElement(By.id("subBtnInValidateESign")).click();
		  Thread.sleep(5000);
		  driver.findElement(By.cssSelector("div.modal-buttons[modal-btn btn-green btn-square='OK']")).click();
	  }
	  
	  
	  
  }
  private boolean selectSupplierRecord(int count, String lastName, boolean isRecordSelected) throws InterruptedException {
	  WebElement table = driver.findElement(By.id("usersTableContainer"));
	  WebElement tableBody = table.findElement(By.tagName("tbody"));
	  int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	  int totalNoOfRecords = 0;
	  int noOfRecordsChecked = 0;
	  if(perPageNoOfRecordsPresent>0) {
	  String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span")).getText();
	  String parts [] = a.split("of");
	  try {
		  totalNoOfRecords  = Integer.parseInt(parts[1].trim());
		  System.out.println(totalNoOfRecords);
	  }catch(Exception e){
		  System.out.println(e.getMessage());
	  }
	  }
	  if(perPageNoOfRecordsPresent>0 && count ==0) {
		  if((totalNoOfRecords>1) && (lastName == null) || ("".equalsIgnoreCase(lastName))) {
			  lastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();
		  } else if((lastName == null) || ("".equalsIgnoreCase(lastName))) {
			  lastName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();
			  
		  }
		  ++ count;
	  }
	  
	  if(perPageNoOfRecordsPresent>0) {
		  while(noOfRecordsChecked<totalNoOfRecords) {
		  if(totalNoOfRecords>1) {
			  for (int i =1 ; i<= perPageNoOfRecordsPresent ; i++) {
				  String lastNameSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr["+i+"]/td[2]")).getText();
				  if(lastName.equalsIgnoreCase(lastNameSequence)) {
					  driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr["+i+"]/td[2]")).click();
					  isRecordSelected = true;
					  break;
				  }
			  }
			  if(isRecordSelected) {
				  break;
			  }
		  }else {
			  
			  String lastNameSequence = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).getText();
			  if(lastName.equalsIgnoreCase(lastNameSequence)) {
				  driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).click();
				  isRecordSelected = true;
				  break;  
				  
			  }
		  }
		  
		  noOfRecordsChecked += perPageNoOfRecordsPresent;
		  if((!isRecordSelected) && (noOfRecordsChecked<totalNoOfRecords)){
			  driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();
			  Thread.sleep(3000);
			  table = driver.findElement(By.id("usersTableContainer"));
			  tableBody = table.findElement(By.tagName("tbody"));
			  perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
			  
		  }
		  }
	  }
	  return isRecordSelected;
  }
}
