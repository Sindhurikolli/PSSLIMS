package com.pss.lims.IM.myactivties.InstrumentUsage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pss.lims.IM.Login.LoginDetails;


public class InstrumentUsage extends LoginDetails {

	@Test
	public void instrumentUsage() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ASSIGN_ROLE_USER_FIRST_NAME"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[11]/a/map/area")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("myActTabInInstMngt")).click();
		Thread.sleep(4000);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("instrumentUsageInIMS"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(5000);
		methodForInstrumentUsage();

	}

	private void methodForInstrumentUsage() throws Exception {

		driver.findElement(By.id("createInstUsageAction")).click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
		Thread.sleep(5000);
//		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//		WebElement element1 = driver
//				.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
//		jse1.executeScript("arguments[0].click();", element1);
//		Thread.sleep(3000);
		driver.findElement(By.id("selInstFromUserInInstUsage")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("srchBtnInInstSelForUsage")).click();
		Thread.sleep(3000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("StageDescription_50");
		isRecordSelected1 = selectRecordForInstrumentUsage(count1, isRecordSelected1,name1);
		if(isRecordSelected1) {
			Thread.sleep(3000);
			driver.findElement(By.id("selectBtnInInstSelWindowForUsage")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("batchNoInInstUsage")).sendKeys(properties.getProperty("Desc_300"));
			Thread.sleep(3000);
			
		}
	}

	private boolean selectRecordForInstrumentUsage(int count1, boolean isRecordSelected1, String name1) {
		// TODO Auto-generated method stub
		return isRecordSelected1;
	}
}
