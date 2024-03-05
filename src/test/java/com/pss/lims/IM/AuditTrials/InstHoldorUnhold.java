package com.pss.lims.IM.AuditTrials;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pss.lims.IM.Login.LoginDetails;


public class InstHoldorUnhold extends LoginDetails {

	@Test
	public void instrumentHoldOrUnhold() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ASSIGN_ROLE_USER_FIRST_NAME"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[11]/a/map/area")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("auditTabInInstMngt")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("instHoldOrUnholdInImsAuditForm")).click();
		Thread.sleep(5000);
		methodForinstrumentHoldOrUnhold();

	}

	private void methodForinstrumentHoldOrUnhold() throws Exception {

		driver.findElement(By.id("searchBtnInIMSHoldOrUnholdAuditForm")).click();
		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String record = properties.getProperty("Instrument_Id");
		isRecordSelected = selectRecordForInstrumentCatgeory(count, isRecordSelected, record);
		if (isRecordSelected) {
			Thread.sleep(4000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
//		driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			JavascriptExecutor jse8 = (JavascriptExecutor) driver;
			WebElement element8 = driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a"));
			Thread.sleep(2000);
			jse8.executeScript("arguments[0].click();", element8);
			Thread.sleep(4000);
			int count1 = 0;
			boolean isRecordSelect = false;
			String name = properties.getProperty("Instrument_Id");
			isRecordSelect = selectRecordForInstrumentCatgeory1(count1, isRecordSelect, name);
			Thread.sleep(4000);
			WebDriverWait wait1 = new WebDriverWait(driver, 90);
			wait1.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
//		driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			JavascriptExecutor jse18 = (JavascriptExecutor) driver;
			WebElement element18 = driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a"));
			Thread.sleep(2000);
			jse18.executeScript("arguments[0].click();", element18);
			Thread.sleep(4000);

		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordForInstrumentCatgeory1(int count1, boolean isRecordSelect, String name)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("imsInstHoldUnHoldJtableInAuditTrails"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/div[4]/div[2]/span"))
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
//			System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
//				System.out.println("hi this is ravi");
				name = driver
						.findElement(By
								.xpath("//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver
						.findElement(
								By.xpath("//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr/td[3]"))
						.getText();// document
									// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (name.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr[ "
											+ i + " ]/td[9]/button"))
									.click();
//							driver.findElement(By.id("historyViewInTestTemplateAuditGrid")).click();

							isRecordSelect = true;
							break;
						}
					}
					if (isRecordSelect) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath(
									"//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr/td[9]/button"))
								.click();
						isRecordSelect = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelect) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#imsInstHoldUnHoldJtableInAuditTrails > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("imsInstHoldUnHoldJtableInAuditTrails"));// Document Tree
					// approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelect;
	}

	private boolean selectRecordForInstrumentCatgeory(int count, boolean isRecordSelected, String record)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("imsInstHoldUnHoldJtableInAuditTrails"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/div[4]/div[2]/span"))
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
			if ((totalNoOfRecords > 1) && ((record == null) || ("".equalsIgnoreCase(record)))) {
//				System.out.println("hi this is ravi");
				record = driver
						.findElement(By
								.xpath("//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((record == null) || ("".equalsIgnoreCase(record))) {
				record = driver
						.findElement(
								By.xpath("//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr/td[3]"))
						.getText();// document
									// type
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (record.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr[ "
											+ i + " ]/td[6]/button"))
									.click();
//							driver.findElement(By.id("historyViewInTestTemplateAuditGrid")).click();

							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath(
									"//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (record.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"imsInstHoldUnHoldJtableInAuditTrails\"]/div/table/tbody/tr/td[6]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#imsInstHoldUnHoldJtableInAuditTrails > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("imsInstHoldUnHoldJtableInAuditTrails"));// Document Tree
					// approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}
