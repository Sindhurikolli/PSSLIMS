package com.pss.lims.IM.AuditTrials;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pss.lims.IM.Login.LoginDetails;


public class InstrumentTestTemplate extends LoginDetails {

	@Test
	public void instrumentTestTemplate() throws Exception {

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
		driver.findElement(By.id("instTestTemplateInImsAuditForm")).click();
		Thread.sleep(5000);
		methodForinstrumentTestTemplate();

	}

	private void methodForinstrumentTestTemplate() throws Exception {

		driver.findElement(By.id("selInstInImsInstTestTemplateAuditTrailsForm")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("srchBtnInInstSelWindow")).click();
		Thread.sleep(3000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name = properties.getProperty("Instrument_Id");
		isRecordSelected1 = selectRecordForInstrument(count1, isRecordSelected1, name);
		if (isRecordSelected1) {
			Thread.sleep(3000);
			driver.findElement(By.id("selectBtnInInstSelWindow")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("searchBtnInImsInstTestTemplateAuditTrailsForm")).click();
			Thread.sleep(3000);
			int count = 0;
			boolean isRecordSelected = false;
			String record = properties.getProperty("Instrument_Id");
			isRecordSelected = selectRecordForTestTemplate(count, isRecordSelected, record);
			if (isRecordSelected) {
				Thread.sleep(3000);
				driver.findElement(By.id("closeBtnInHistoryViewInTestTemplateWindow")).click();
				Thread.sleep(3000);
				isRecordSelected = selectRecordForTestTemplate(count, isRecordSelected, record);
				Thread.sleep(2000);
//				driver.findElement(By.id("historyViewInTestTemplateAuditGrid")).click();
//				Thread.sleep(3000);
				JavascriptExecutor jse410 = (JavascriptExecutor) driver;
				WebElement element410 = driver.findElement(By.id("historyViewInTestTemplateAuditGrid"));
				jse410.executeScript("arguments[0].click();", element410);
				Thread.sleep(3000);
//				driver.findElement(By.id("closeBtnInHistoryViewInTestTemplateWindow")).click();
				JavascriptExecutor jse4110 = (JavascriptExecutor) driver;
				WebElement element4110 = driver.findElement(By.id("closeBtnInHistoryViewInTestTemplateWindow"));
				jse4110.executeScript("arguments[0].click();", element4110);
				Thread.sleep(5000);
			} else {
				System.out.println("Record is not Selected");
			}
		} else {
			System.out.println("Instrument is not Selected");
		}
	}

	private boolean selectRecordForTestTemplate(int count, boolean isRecordSelected, String record) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("imsInstTestTemplateJtableInAuditTrails"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"imsInstTestTemplateJtableInAuditTrails\"]/div/div[4]/div[2]/span"))
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
						.findElement(By.xpath(
								"//*[@id=\"imsInstTestTemplateJtableInAuditTrails\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((record == null) || ("".equalsIgnoreCase(record))) {
				record = driver
						.findElement(By
								.xpath("//*[@id=\"imsInstTestTemplateJtableInAuditTrails\"]/div/table/tbody/tr/td[3]"))
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
								By.xpath("//*[@id=\"imsInstTestTemplateJtableInAuditTrails\"]/div/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (record.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"imsInstTestTemplateJtableInAuditTrails\"]/div/table/tbody/tr[ "
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
									"//*[@id=\"imsInstTestTemplateJtableInAuditTrails\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (record.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"imsInstTestTemplateJtableInAuditTrails\"]/div/table/tbody/tr/td[6]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#imsInstTestTemplateJtableInAuditTrails > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("imsInstTestTemplateJtableInAuditTrails"));// Document Tree
					// approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	private boolean selectRecordForInstrument(int count1, boolean isRecordSelected1, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("tableInInstSelectionWindow"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/div[4]/div[2]/span"))
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
						.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr/td[4]"))
						.getText();// document
									// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By
								.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();// documentTypeName
						if (name.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
									.click();
//							driver.findElement(
//									By.id("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[5]/td[4]"))
//									.click();

							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (name.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr/td[4]"))
								.click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#tableInInstSelectionWindow > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("tableInInstSelectionWindow"));// Document Tree
					// approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected1;
	}
}
