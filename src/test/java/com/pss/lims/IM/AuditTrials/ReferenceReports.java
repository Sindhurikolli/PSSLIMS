package com.pss.lims.IM.AuditTrials;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pss.lims.IM.Login.LoginDetails;


public class ReferenceReports extends LoginDetails {

	@Test
	public void referenceReport() throws Exception {

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
		driver.findElement(By.id("referenceReportsInImsAuditForm")).click();
		Thread.sleep(4000);
		methodFordestroyInstrument();

	}

	private void methodFordestroyInstrument() throws Exception {

		driver.findElement(By.id("srchBtnInRefRepAuditFormInIMSSrch")).click();
		Thread.sleep(4000);
		int count = 0;
		boolean isRecordSelected = false;
		String record = properties.getProperty("Instrument_Id");
		isRecordSelected = selectRecordForInstrumentCatgeory(count, isRecordSelected, record);
		if (isRecordSelected) {
			Thread.sleep(5000);
			int count1 = 0;
			boolean isRecordSelected1 = false;
			String name = properties.getProperty("document");
			isRecordSelected1 = selectRecordForInstrument(count1, isRecordSelected1, name);
			Thread.sleep(8000);
			String mainWindow = driver.getWindowHandle();
			System.out.println("main window is :" + mainWindow);
			Set<String> set = driver.getWindowHandles();
			Iterator<String> itr = set.iterator();
			while (itr.hasNext()) {
				String childWindow = itr.next();
				System.out.println("child window is:" + childWindow);
				if (!mainWindow.equals(childWindow)) {
					driver.switchTo().window(childWindow);
					System.out.println(driver.switchTo().window(childWindow).getTitle());
					Thread.sleep(3000);
					driver.close();
				} else {
					System.out.println("child window is not closed");
				}
			}
			driver.switchTo().window(mainWindow);
			Thread.sleep(5000);
			driver.findElement(By.id("clsBtnInRefRepauditFrmDlg")).click();
			Thread.sleep(4000);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordForInstrument(int count1, boolean isRecordSelected1, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("refFilesGridInIMSRefRepAuditForm"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"refFilesGridInIMSRefRepAuditForm\"]/div/div[4]/div[2]/span"))
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
						.findElement(
								By.xpath("//*[@id=\"refFilesGridInIMSRefRepAuditForm\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver
						.findElement(By.xpath("//*[@id=\"refFilesGridInIMSRefRepAuditForm\"]/div/table/tbody/tr/td[1]"))
						.getText();// document
									// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"refFilesGridInIMSRefRepAuditForm\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
								.getText();// documentTypeName
						if (name.contains(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"refFilesGridInIMSRefRepAuditForm\"]/div/table/tbody/tr[ " + i
											+ " ]/td[2]/button"))
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
							.findElement(
									By.xpath("//*[@id=\"refFilesGridInIMSRefRepAuditForm\"]/div/table/tbody/tr/td[1]"))
							.getText();
					if (name.contains(DevNumberSequence)) {
						driver.findElement(By
								.xpath("//*[@id=\"refFilesGridInIMSRefRepAuditForm\"]/div/table/tbody/tr/td[2]/button"))
								.click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#refFilesGridInIMSRefRepAuditForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("refFilesGridInIMSRefRepAuditForm"));// Document Tree
					// approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}

		}
		return isRecordSelected1;
	}

	private boolean selectRecordForInstrumentCatgeory(int count, boolean isRecordSelected, String record)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("refReportsGridInRefRepAuditFormInIMS"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"refReportsGridInRefRepAuditFormInIMS\"]/div/div[4]/div[2]/span"))
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
								.xpath("//*[@id=\"refReportsGridInRefRepAuditFormInIMS\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((record == null) || ("".equalsIgnoreCase(record))) {
				record = driver
						.findElement(
								By.xpath("//*[@id=\"refReportsGridInRefRepAuditFormInIMS\"]/div/table/tbody/tr/td[4]"))
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
								By.xpath("//*[@id=\"refReportsGridInRefRepAuditFormInIMS\"]/div/table/tbody/tr[ " + i
										+ " ]/td[4]"))
								.getText();// documentTypeName
						if (record.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"refReportsGridInRefRepAuditFormInIMS\"]/div/table/tbody/tr[ "
											+ i + " ]/td[7]/button"))
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
									"//*[@id=\"refReportsGridInRefRepAuditFormInIMS\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (record.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"refReportsGridInRefRepAuditFormInIMS\"]/div/table/tbody/tr/td[7]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#refReportsGridInRefRepAuditFormInIMS> div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("refReportsGridInRefRepAuditFormInIMS"));// Document Tree
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
