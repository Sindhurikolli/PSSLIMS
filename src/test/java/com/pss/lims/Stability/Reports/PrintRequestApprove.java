package com.pss.lims.Stability.Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pss.lims.Satbility.Login.LoginDetails;

public class PrintRequestApprove extends LoginDetails {

	@Test
	public void approvePrintRequest() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("FirstName"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[5]/a/map/area")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("reportsTabInStabilityMngt")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("stmtCompilationReport")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("stmtPrintApproveReport")).click();
		Thread.sleep(3000);
		methodToApprovePrintRequest();

	}

	private void methodToApprovePrintRequest() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("product");
		isRecordSelected = selectRecordToApprovePrintRequest(count, isRecordSelected, name);
		if (isRecordSelected) {
			Thread.sleep(3000);
			driver.findElement(By.id("commentsInProjectRejApp")).sendKeys(properties.getProperty("Comments_150"));
			Thread.sleep(3000);
			driver.findElement(By.id("approveBtnInCompPrintReqAppForm")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			Thread.sleep(2000);
//			driver.findElement(By.id("subBtnInValidateESign")).click();
			JavascriptExecutor jse7 = (JavascriptExecutor) driver;
			WebElement element7 = driver.findElement(By.id("subBtnInValidateESign"));
			jse7.executeScript("arguments[0].click();", element7);
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
//			driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			JavascriptExecutor jse8 = (JavascriptExecutor) driver;
			WebElement element8 = driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a"));
			Thread.sleep(2000);
			jse8.executeScript("arguments[0].click();", element8);
			Thread.sleep(4000);

		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordToApprovePrintRequest(int count, boolean isRecordSelected, String name)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("tableInCompilationApprovalForm"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"tableInCompilationApprovalForm\"]/div/div[4]/div[2]/span"))
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
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
				name = driver
						.findElement(
								By.xpath("//*[@id=\"tableInCompilationApprovalForm\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver
						.findElement(By.xpath("//*[@id=\"tableInCompilationApprovalForm\"]/div/table/tbody/tr/td[3]"))
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
								"//*[@id=\"tableInCompilationApprovalForm\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (name.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"tableInCompilationApprovalForm\"]/div/table/tbody/tr[ " + i
											+ " ]/td[3]"))
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
							.findElement(
									By.xpath("//*[@id=\"tableInCompilationApprovalForm\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"tableInCompilationApprovalForm\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#tableInCompilationApprovalForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("tableInCompilationApprovalForm"));// Document Tree approve
																						// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}
