package com.pss.lims.Stability.AuditTrials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pss.lims.Satbility.Login.LoginDetails;

public class MasterOfChamber extends LoginDetails {

	@Test
	public void masterChamberAudit() throws Exception {

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
		driver.findElement(By.id("smChamberAuditForm")).click();
		Thread.sleep(3000);
		methodForMasterChamber();

	}

	private void methodForMasterChamber() throws Exception {

		driver.findElement(By.id("searchBtnInChamberAuditForm")).click();
		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("MasterChamber");
		isRecordSelected = selectRecordForMasterChamber(count, isRecordSelected, name);
		if (isRecordSelected) {
			Thread.sleep(5000);
			driver.findElement(By.id("renderInChamberAuditBtn")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("closeBtnInChamberRackAuditForm")).click();
			Thread.sleep(4000);

		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordForMasterChamber(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("chamberAuditGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"chamberAuditGrid\"]/div/div[4]/div[2]/span")).getText();// For
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
				name = driver.findElement(By.xpath("//*[@id=\"chamberAuditGrid\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"chamberAuditGrid\"]/div/table/tbody/tr/td[2]")).getText();// document
																														// type
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver
								.findElement(
										By.xpath("//*[@id=\"chamberAuditGrid\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name.contains(DevNumberSequence)) {
							driver.findElement(By.id("renderInChamberAuditBtn")).click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"chamberAuditGrid\"]/div/table/tbody/tr/td[2]")).getText();
					if (name.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.id("renderInChamberAuditBtn")).click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#chamberAuditGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("chamberAuditGrid"));// Document Tree approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}
