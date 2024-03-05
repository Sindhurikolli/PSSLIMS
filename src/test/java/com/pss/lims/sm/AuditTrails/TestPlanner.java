package com.pss.lims.sm.AuditTrails;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;


public class TestPlanner extends SMLoginDetails {

	@Test
	public void testPlanner() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("FirstName"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[4]/a/map/area")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("auditTrialsTabInSampleMngt")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("testPlannerInAuditForm")).click();
		Thread.sleep(3000);
		methodFortestPlanner();

	}

	private void methodFortestPlanner() throws Exception {

		driver.findElement(By.id("searchBtnInTestPlannerStatusAuditForm")).click();
		Thread.sleep(3000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String product = properties.getProperty("Description_of_reading");
		isRecordSelected1 = selectRecordForProduct(count1, isRecordSelected1, product);
		if (isRecordSelected1) {
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"cancelBtnInDialogForTestDetails\"]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("saveAsExcelBtnInTestPlannerStatusAuditForm")).click();
			Thread.sleep(6000);
			String mainWindow = driver.getWindowHandle();
			System.out.println("mainwindow" + mainWindow);
			Thread.sleep(3000);
			Set<String> set = driver.getWindowHandles();
			java.util.Iterator<String> itr = set.iterator();
			while (itr.hasNext()) {
				String childWindow = itr.next();
				System.out.println("childwindow" + childWindow);
				if (!mainWindow.equals(childWindow)) {
//					JavascriptExecutor js = (JavascriptExecutor) driver;
//					js.executeScript("window.scrollBy(0,1000)");
					driver.switchTo().window(childWindow);
					System.out.println(driver.switchTo().window(childWindow).getTitle());
					Thread.sleep(3000);
					driver.close();
				}
			}
			driver.switchTo().window(mainWindow);
			Thread.sleep(3000);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordForProduct(int count1, boolean isRecordSelected1, String product) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("TestPlannerAuditTabInTestPlannerStatusAuditForm"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath(
							"//*[@id=\"TestPlannerAuditTabInTestPlannerStatusAuditForm\"]/div/div[4]/div[2]/span"))
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
			if ((totalNoOfRecords > 1) && ((product == null) || ("".equalsIgnoreCase(product)))) {
//				System.out.println("hi this is ravi");
				product = driver.findElement(By.xpath(
						"//*[@id=\"TestPlannerAuditTabInTestPlannerStatusAuditForm\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((product == null) || ("".equalsIgnoreCase(product))) {
				product = driver.findElement(By
						.xpath("//*[@id=\"TestPlannerAuditTabInTestPlannerStatusAuditForm\"]/div/table/tbody/tr/td[3]"))
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
								"//*[@id=\"TestPlannerAuditTabInTestPlannerStatusAuditForm\"]/div/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (product.contains(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"TestPlannerAuditTabInTestPlannerStatusAuditForm\"]/div/table/tbody/tr[ "
											+ i + " ]/td[16]/button"))
									.click();
//							JavascriptExecutor jse9 = (JavascriptExecutor) driver;
//							WebElement element9 = driver.findElement(By.xpath(
//									"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"));
//							jse9.executeScript("arguments[0].click();", element9);
////							Thread.sleep(2000);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver.findElement(By.xpath(
							"//*[@id=\"TestPlannerAuditTabInTestPlannerStatusAuditForm\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (product.contains(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"TestPlannerAuditTabInTestPlannerStatusAuditForm\"]/div/table/tbody/tr/td[16]/button"))
								.click();
//						JavascriptExecutor jse11 = (JavascriptExecutor) driver;
//						WebElement element11 = driver
//								.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"));
//						jse11.executeScript("arguments[0].click();", element11);
////						Thread.sleep(2000);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#TestPlannerAuditTabInTestPlannerStatusAuditForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("TestPlannerAuditTabInTestPlannerStatusAuditForm"));// Document
																											// Tree
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
