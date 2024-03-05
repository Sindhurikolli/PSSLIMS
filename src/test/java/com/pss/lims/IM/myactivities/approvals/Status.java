package com.pss.lims.IM.myactivities.approvals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pss.lims.IM.Login.LoginDetails;


public class Status extends LoginDetails {

	@Test
	public void status() throws Exception {

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
		WebElement element1 = driver.findElement(By.id("instrumentStatusInIMS"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(5000);
		methodForStatus();

	}

	private void methodForStatus() throws Exception {

		driver.findElement(By.id("searchBtnInInstStatusForm")).click();
		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String record = properties.getProperty("Instrument_Id");
		isRecordSelected = selectRecordForStatus(count, isRecordSelected, record);
		if (isRecordSelected) {
			Thread.sleep(3000);
//			driver.findElement(By.id("cancelBtnForCalibAdhocSubScheduleDetailsDialog")).click();
			JavascriptExecutor jse31 = (JavascriptExecutor) driver;
			WebElement element31 = driver.findElement(By.id("cancelBtnForCalibAdhocSubScheduleDetailsDialog"));
			jse31.executeScript("arguments[0].click();", element31);
//			Thread.sleep(3000);
			Thread.sleep(4000);
		}
	}

	private boolean selectRecordForStatus(int count, boolean isRecordSelected, String record) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("calibrationSchDetailsTableInInstStatusForm"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(
							By.xpath("//*[@id=\"calibrationSchDetailsTableInInstStatusForm\"]/div/div[4]/div[2]/span"))
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
								"//*[@id=\"calibrationSchDetailsTableInInstStatusForm\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((record == null) || ("".equalsIgnoreCase(record))) {
				record = driver
						.findElement(By.xpath(
								"//*[@id=\"calibrationSchDetailsTableInInstStatusForm\"]/div/table/tbody/tr/td[5]"))
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
								By.xpath("//*[@id=\"calibrationSchDetailsTableInInstStatusForm\"]/div/table/tbody/tr[ "
										+ i + " ]/td[5]"))
								.getText();// documentTypeName
						if (record.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"calibrationSchDetailsTableInInstStatusForm\"]/div/table/tbody/tr[ " + i
											+ " ]/td[15]/button"))
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
							.findElement(By.xpath(
									"//*[@id=\"calibrationSchDetailsTableInInstStatusForm\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (record.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"calibrationSchDetailsTableInInstStatusForm\"]/div/table/tbody/tr/td[15]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#calibrationSchDetailsTableInInstStatusForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("calibrationSchDetailsTableInInstStatusForm"));// Document Tree
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
