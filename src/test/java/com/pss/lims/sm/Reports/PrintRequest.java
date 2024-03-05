package com.pss.lims.sm.Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;


public class PrintRequest extends SMLoginDetails {

	@Test
	public void printRequest() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("FirstName"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[4]/a/map/area")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("reportsTabInSampleMngt")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("cOAPrintRequestAndViewForm")).click();
		Thread.sleep(4000);
		methodForprintRequest();
	}

	private void methodForprintRequest() throws Exception {

		driver.findElement(By.id("srchBtnInCOAPrntReqSrch")).click();
		Thread.sleep(4000);
		int count = 0;
		boolean isRecordSelected = false;
		String record = properties.getProperty("AR_Number");
		isRecordSelected = selectRecordForPrint(count, isRecordSelected, record);
		if (isRecordSelected) {
			Thread.sleep(5000);
			driver.findElement(By.id("COAPrntReqBtnInCOAPrntReqSrch")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			Thread.sleep(3000);
			JavascriptExecutor jse7 = (JavascriptExecutor) driver;
			WebElement element7 = driver.findElement(By.id("subBtnInValidateESign"));
			jse7.executeScript("arguments[0].click();", element7);
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
			JavascriptExecutor jse8 = (JavascriptExecutor) driver;
			WebElement element8 = driver.findElement(By.id("subBtnInValidateESign"));
			jse8.executeScript("arguments[0].click();", element8);
			Thread.sleep(4000);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordForPrint(int count, boolean isRecordSelected, String record) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("sampleDetailsGridForCOAPrntReq"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"sampleDetailsGridForCOAPrntReq\"]/div/div[4]/div[2]/span"))
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
			if ((totalNoOfRecords > 1) && ((record == null) || ("".equalsIgnoreCase(record)))) {
//						System.out.println("hi this is ravi");
				record = driver
						.findElement(
								By.xpath("//*[@id=\"sampleDetailsGridForCOAPrntReq\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((record == null) || ("".equalsIgnoreCase(record))) {
				record = driver
						.findElement(By.xpath("//*[@id=\"sampleDetailsGridForCOAPrntReq\"]/div/table/tbody/tr/td[2]"))
						.getText();// document
									// type
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"sampleDetailsGridForCOAPrntReq\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (record.contains(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"sampleDetailsGridForCOAPrntReq\"]/div/table/tbody/tr[ " + i
											+ " ]/td[2]"))
									.click();
//									JavascriptExecutor jse9 = (JavascriptExecutor) driver;
//									WebElement element9 = driver.findElement(By.xpath(
//											"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"));
//									jse9.executeScript("arguments[0].click();", element9);
////									Thread.sleep(2000);
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(
									By.xpath("//*[@id=\"sampleDetailsGridForCOAPrntReq\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (record.contains(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"sampleDetailsGridForCOAPrntReq\"]/div/table/tbody/tr/td[2]"))
								.click();
//								JavascriptExecutor jse11 = (JavascriptExecutor) driver;
//								WebElement element11 = driver
//										.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"));
//								jse11.executeScript("arguments[0].click();", element11);
////								Thread.sleep(2000);
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#sampleDetailsGridForCOAPrntReq > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("sampleDetailsGridForCOAPrntReq"));// Document
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
