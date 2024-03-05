package com.pss.lims.sm.approvals.modify;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;


public class ModifySampleRegistration extends SMLoginDetails {

	@Test
	public void modifySampleRegistration() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("FirstName"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[4]/a/map/area")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("sampleMngrTabInSampleMngt")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("sampleRegPageInSample")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("modifySampleAction")).click();
		Thread.sleep(3000);
		methodTomodifySampleRegistration();

	}

	private void methodTomodifySampleRegistration() throws Exception {

		driver.findElement(By.id("searchBtnInLimsSampleRegModify")).click();
		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String storageCondition = properties.getProperty("SampleId");
		isRecordSelected = selectRecordForStorageLocation(count, isRecordSelected, storageCondition);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("numOfContInLimsSampleReg")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("numOfContInLimsSampleReg")).sendKeys("3");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a")).click();
		Thread.sleep(3000);
		JavascriptExecutor jse6 = (JavascriptExecutor) driver;
		WebElement element6 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
		jse6.executeScript("arguments[0].click();", element6);
		Thread.sleep(2000);
		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
		Thread.sleep(3000);
//		driver.findElement(By.id("subBtnInValidateESign")).click();
		JavascriptExecutor jse7 = (JavascriptExecutor) driver;
		WebElement element7 = driver.findElement(By.id("subBtnInValidateESign"));
		jse7.executeScript("arguments[0].click();", element7);
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 70);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
		Thread.sleep(3000);
//		driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
		JavascriptExecutor jse8 = (JavascriptExecutor) driver;
		WebElement element8 = driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a"));
		jse8.executeScript("arguments[0].click();", element8);
		Thread.sleep(4000);
	}

	private boolean selectRecordForStorageLocation(int count, boolean isRecordSelected, String storageCondition)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("limsSampleModifyGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"limsSampleModifyGrid\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
//			System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((storageCondition == null) || ("".equalsIgnoreCase(storageCondition)))) {
//				System.out.println("hi this is ravi");
				storageCondition = driver
						.findElement(By.xpath("//*[@id=\"limsSampleModifyGrid\"]/div/table/tbody/tr[1]/td[29]"))
						.getText();// documentType
			} else if ((storageCondition == null) || ("".equalsIgnoreCase(storageCondition))) {
				storageCondition = driver
						.findElement(By.xpath("//*[@id=\"limsSampleModifyGrid\"]/div/table/tbody/tr/td[29]")).getText();// document
				// type
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"limsSampleModifyGrid\"]/div/table/tbody/tr[ " + i + " ]/td[29]"))
								.getText();// documentTypeName
						if (storageCondition.contains(DevNumberSequence)) {
							driver.findElement(By
									.xpath("//*[@id=\"limsSampleModifyGrid\"]/div/table/tbody/tr[ " + i + " ]/td[29]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"limsSampleModifyGrid\"]/div/table/tbody/tr/td[29]"))
							.getText();
					if (storageCondition.contains(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"limsSampleModifyGrid\"]/div/table/tbody/tr/td[29]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#limsSampleModifyGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("limsSampleModifyGrid"));// Document Tree approve
																				// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}
