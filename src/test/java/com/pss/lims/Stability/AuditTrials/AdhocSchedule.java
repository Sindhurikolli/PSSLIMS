package com.pss.lims.Stability.AuditTrials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.pss.lims.Satbility.Login.LoginDetails;

public class AdhocSchedule extends LoginDetails {

	@Test
	public void adhocScheduleAudit() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("FirstName"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[5]/a/map/area")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("auditTabInStabilityMngt")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("adhocScheduleAudit")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("selectProtInASAuditForm")).click();
		Thread.sleep(3000);
		methodForAdhocSchedule();

	}

	private void methodForAdhocSchedule() throws Exception {

		driver.findElement(By.id("searchBtnInSMStabSel")).click();
		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("Protocol_Number");
		isRecordSelected = selectRecord(count, isRecordSelected, name);
		if (isRecordSelected) {
			Thread.sleep(3000);
			driver.findElement(By.id("continueBtnInStabilityReqSelectionForm")).click();
			Thread.sleep(3000);
			int count1 = 0;
			boolean isRecordSelected1 = false;
			String name1 = properties.getProperty("Protocol_Number");
			isRecordSelected = selectRecordForStorage(count1, isRecordSelected1, name1);
			if (isRecordSelected) {
				Thread.sleep(3000);
				driver.findElement(By.id("selectBtnInProtPrepSelectionForm")).click();
				Thread.sleep(5000);

			} else {
				System.out.println("storage is not Selected");
			}
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordForStorage(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("protPrepSelGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"protPrepSelGrid\"]/div/div[4]/div[2]/span")).getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
//					System.out.println("hi:" + a);
			String[] parts = a.split(" of ");
//					System.out.println("parts:" + parts.toString());
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println(totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
//					System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((name1 == null) || ("".equalsIgnoreCase(name1)))) {
//						System.out.println("hi this is ravi");
				name1 = driver.findElement(By.xpath("//*[@id=\"protPrepSelGrid\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"protPrepSelGrid\"]/div/table/tbody/tr/td[2]")).getText();// document
																														// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver
								.findElement(
										By.xpath("//*[@id=\"protPrepSelGrid\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"protPrepSelGrid\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
									.click();
//							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
//							WebElement element8 = driver.findElement(By.xpath(
//									"//*[@id=\"protocolTableInAdhocSchDetails\"]/div/table/tbody/tr[" + i + "]/td[3]"));
//							jse8.executeScript("arguments[0].click();", element8);
//									Thread.sleep(4000);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"protPrepSelGrid\"]/div/table/tbody/tr/td[2]")).getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"protPrepSelGrid\"]/div/table/tbody/tr/td[2]")).click();
//						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
//						WebElement element8 = driver.findElement(
//								By.xpath("//*[@id=\"protocolTableInAdhocSchDetails\"]/div/table/tbody/tr/td[3]"));
//						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#protPrepSelGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("protPrepSelGrid"));// Document Tree approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected1;
	}

	private boolean selectRecord(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("proSpecInStabSelGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"proSpecInStabSelGrid\"]/div/div[3]/div[2]/span"))
					.getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
//					System.out.println("hi:" + a);
			String[] parts = a.split(" of ");
//					System.out.println("parts:" + parts.toString());
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println(totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
//					System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
//						System.out.println("hi this is ravi");
				name = driver.findElement(By.xpath("//*[@id=\"proSpecInStabSelGrid\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"proSpecInStabSelGrid\"]/div/table/tbody/tr/td[1]"))
						.getText();// document
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
										"//*[@id=\"proSpecInStabSelGrid\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
								.getText();// documentTypeName
						if (name.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"proSpecInStabSelGrid\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
									.click();
//							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
//							WebElement element8 = driver.findElement(By.xpath(
//									"//*[@id=\"protocolTableInAdhocSchDetails\"]/div/table/tbody/tr[" + i + "]/td[3]"));
//							jse8.executeScript("arguments[0].click();", element8);
//									Thread.sleep(4000);
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"proSpecInStabSelGrid\"]/div/table/tbody/tr/td[1]"))
							.getText();
					if (name.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"proSpecInStabSelGrid\"]/div/table/tbody/tr/td[1]"))
								.click();
//						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
//						WebElement element8 = driver.findElement(
//								By.xpath("//*[@id=\"protocolTableInAdhocSchDetails\"]/div/table/tbody/tr/td[3]"));
//						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#proSpecInStabSelGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("proSpecInStabSelGrid"));// Document Tree approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}
