package com.pss.lims.StabilityManagement.Terminate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.pss.lims.Satbility.Login.LoginDetails;
import com.pss.lims.util.Helper;

public class TerminateSampleAcknowledgement extends LoginDetails {

	@Test
	public void terminateSampleAcknowledgement() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("FirstName"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[5]/a/map/area")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("myActTabInStabilityMngt")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("sampleAcknowledgementFormIdInSM")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("acknowledgeSampleAckAction")).click();
		Thread.sleep(4000);
		methodToterminateSampleAcknowledgement();

	}

	private void methodToterminateSampleAcknowledgement() throws Exception {

		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("StageName_30");
		isRecordSelected1 = selectRecordForSample(count1, isRecordSelected1, name1);
		if (isRecordSelected1) {
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
			Thread.sleep(3000);
			JavascriptExecutor jse12 = (JavascriptExecutor) driver;
			WebElement element12 = driver
					.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
			jse12.executeScript("arguments[0].click();", element12);
			Thread.sleep(5000);
			driver.findElement(By.id("qtyWithdrawInSampleAck")).sendKeys("2");
			Thread.sleep(2000);
			Select uom = new Select(driver.findElement(By.id("uomInSampleAck")));
			Thread.sleep(2000);
			uom.selectByIndex(10);
			Thread.sleep(2000);
			driver.findElement(By.id("usedForInSampleAck")).sendKeys(properties.getProperty("Vendor_Name_100"));
			Thread.sleep(2000);
			JavascriptExecutor jse311 = (JavascriptExecutor) driver;
			WebElement element311 = driver.findElement(By.id("selectApprFrmBtnInSampleAck"));
			jse311.executeScript("arguments[0].click();", element311);
			Thread.sleep(2000);
			JavascriptExecutor jse10 = (JavascriptExecutor) driver;
			WebElement element10 = driver.findElement(By.id("locTreeInCalPmBdm_2_a"));
			jse10.executeScript("arguments[0].click();", element10);
			Thread.sleep(2000);
			int count = 0;
			boolean isRecordSelected = false;
			String selectingUserSingleApproval = properties.getProperty("FirstName");
			isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval,
					isRecordSelected, count);
			Thread.sleep(3000);
			JavascriptExecutor jse4210 = (JavascriptExecutor) driver;
			WebElement element4210 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse4210.executeScript("arguments[0].click();", element4210);
			Thread.sleep(3000);
			driver.findElement(By.id("terminationCommntsInSampleAck"))
					.sendKeys(properties.getProperty("Vendor_Name_100"));
			Thread.sleep(2000);
			JavascriptExecutor jse42110 = (JavascriptExecutor) driver;
			WebElement element42110 = driver.findElement(By.id("terminateBtnInSampleAck"));
			jse42110.executeScript("arguments[0].click();", element42110);
			Thread.sleep(3000);
			JavascriptExecutor jse5110 = (JavascriptExecutor) driver;
			WebElement element5110 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse5110.executeScript("arguments[0].click();", element5110);
			Thread.sleep(3000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			Thread.sleep(2000);
//			driver.findElement(By.id("subBtnInValidateESign")).click();
			JavascriptExecutor jse7 = (JavascriptExecutor) driver;
			WebElement element7 = driver.findElement(By.id("subBtnInValidateESign"));
			jse7.executeScript("arguments[0].click();", element7);
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
//			driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			JavascriptExecutor jse8 = (JavascriptExecutor) driver;
			WebElement element8 = driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a"));
			Thread.sleep(2000);
			jse8.executeScript("arguments[0].click();", element8);
			Thread.sleep(4000);
		} else {
			System.out.println("Record is not selected");
		}
	}

	private boolean selectRecordForSample(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("sampleAckExistTableId"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"sampleAckExistTableId\"]/div/div[4]/div[2]/span"))
					.getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
//			System.out.println("hi:" + a);
			String[] parts = a.split(" of ");
//			System.out.println("parts:" + parts.toString());
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println(totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((name1 == null) || ("".equalsIgnoreCase(name1)))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"sampleAckExistTableId\"]/div/table/tbody/tr[1]/td[8]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"sampleAckExistTableId\"]/div/table/tbody/tr/td[8]"))
						.getText();// document
									// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"sampleAckExistTableId\"]/div/table/tbody/tr[ " + i + " ]/td[8]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By
									.xpath("//*[@id=\"sampleAckExistTableId\"]/div/table/tbody/tr[ " + i + " ]/td[8]"))
									.click();
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"sampleAckExistTableId\"]/div/table/tbody/tr/td[8]"))
							.getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"sampleAckExistTableId\"]/div/table/tbody/tr/td[8]"))
								.click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#sampleAckExistTableId > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("sampleAckExistTableId"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected1;
	}
}
