package com.pss.lims.IM.Report;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pss.lims.IM.Login.LoginDetails;


public class UtilizationReport extends LoginDetails {

	@Test
	public void utilizationReport() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ASSIGN_ROLE_USER_FIRST_NAME"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[11]/a/map/area")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("reportsTabInInstMngt")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("instrmntUtilizationReportInIMS")).click();
		Thread.sleep(5000);
		methodForInstrumentIndex();

	}

	private void methodForInstrumentIndex() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		Thread.sleep(2000);
		driver.findElement(By.id("usedFromInUtilReportInIMSSrch")).sendKeys(sdf.format(date));
		Thread.sleep(2000);
		driver.findElement(By.id("instrmntNameInUtilReportInIMSSrch")).click();
		Thread.sleep(3000);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);
		String pdf = sdf1.format(cal.getTime());
		Thread.sleep(2000);
		driver.findElement(By.id("usedToInUtilReportInIMSSrch")).sendKeys(pdf);
		Thread.sleep(2000);
		driver.findElement(By.id("srchBtnInUtilReportInIMSSrch")).click();
		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("StageName_30");
		System.out.println("name is :" + name);
		isRecordSelected = selectRecordForRawDataSheet(count, isRecordSelected, name);
		if (isRecordSelected) {
			Thread.sleep(4000);
			driver.findElement(By.id("viewReportBtnInUtilReportInIMSSrch")).click();
			Thread.sleep(10000);
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
					Thread.sleep(4000);
				} else {
					System.out.println("child window is not closed");
				}
			}
			driver.switchTo().window(mainWindow);
			Thread.sleep(5000);
//			driver.findElement(By.id("saveAsExcelBtnInInstrIndexRepsrch")).click();
//			JavascriptExecutor jse3 = (JavascriptExecutor) driver;
//			WebElement element3 = driver.findElement(By.id("saveAsExcelBtnInInstrIndexRepsrch"));
//			jse3.executeScript("arguments[0].click();", element3);
//			Thread.sleep(10000);
		} else {
			System.out.println("Record is not selected");
		}
	}

	private boolean selectRecordForRawDataSheet(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("instrmntUtilDtlsGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"instrmntUtilDtlsGrid\"]/div/div[4]/div[2]/span"))
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
				name = driver.findElement(By.xpath("//*[@id=\"instrmntUtilDtlsGrid\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"instrmntUtilDtlsGrid\"]/div/table/tbody/tr/td[2]"))
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
										"//*[@id=\"instrmntUtilDtlsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name.contains(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"instrmntUtilDtlsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
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
							.findElement(By.xpath("//*[@id=\"instrmntUtilDtlsGrid\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name.contains(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"instrmntUtilDtlsGrid\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#instrmntUtilDtlsGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("instrmntUtilDtlsGrid"));// Document
					// Tree
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
