package com.pss.lims.IM.AuditTrials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pss.lims.IM.Login.LoginDetails;


public class TerminateInstrumentSchedule extends LoginDetails {

	@Test
	public void instrumentScheduleTerminate() throws Exception {

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
		driver.findElement(By.id("termInstSchdInImsAuditForm")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("searchBtnInImsInstrumentScheduleAuditTrailsForm")).click();
		Thread.sleep(4000);
		methodForsingleScheduleTerminate();

	}

	private void methodForsingleScheduleTerminate() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String record = properties.getProperty("Terminate_id");
		isRecordSelected = selectRecordForTerminateSingleSchedule(count, isRecordSelected, record);
		if (isRecordSelected) {
			Thread.sleep(4000);
			driver.findElement(By.id("closeBtnInHistoryViewInSingleSchdTermWindow")).click();
			Thread.sleep(4000);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordForTerminateSingleSchedule(int count, boolean isRecordSelected, String record)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("imsTerminateInstrumentScheduleInAuditTrailId"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By
							.xpath("//*[@id=\"imsTerminateInstrumentScheduleInAuditTrailId\"]/div/div[3]/div[2]/span"))
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
				record = driver.findElement(By
						.xpath("//*[@id=\"imsTerminateInstrumentScheduleInAuditTrailId\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((record == null) || ("".equalsIgnoreCase(record))) {
				record = driver
						.findElement(By.xpath(
								"//*[@id=\"imsTerminateInstrumentScheduleInAuditTrailId\"]/div/table/tbody/tr/td[4]"))
						.getText();// document
									// type
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By
								.xpath("//*[@id=\"imsTerminateInstrumentScheduleInAuditTrailId\"]/div/table/tbody/tr[ "
										+ i + " ]/td[4]"))
								.getText();// documentTypeName
						if (record.contains(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"imsTerminateInstrumentScheduleInAuditTrailId\"]/div/table/tbody/tr[ " + i
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
					String DevNumberSequence = driver.findElement(By.xpath(
							"//*[@id=\"imsTerminateInstrumentScheduleInAuditTrailId\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (record.contains(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"imsTerminateInstrumentScheduleInAuditTrailId\"]/div/table/tbody/tr/td[15]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#imsTerminateInstrumentScheduleInAuditTrailId > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("imsTerminateInstrumentScheduleInAuditTrailId"));// Document Tree
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
