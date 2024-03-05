package com.pss.lims.StabilityManagement.Terminate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.pss.lims.Satbility.Login.LoginDetails;

public class TerminateApproveSampleAcknowledgement extends LoginDetails {

	@Test
	public void approveTerminateSampleAcknowledgement() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("FirstName"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[5]/a/map/area")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("myActTabInStabilityMngt")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("terminateExpandMenu")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("sampleAcknowledgementTerminationForm")).click();
		Thread.sleep(4000);
		methodToapproveTerminateSampleAcknowledgement();

	}

	private void methodToapproveTerminateSampleAcknowledgement() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("StageName_30");
		isRecordSelected = selectRecordForAdhocApprove(count, isRecordSelected, name);
		if (isRecordSelected) {
			Thread.sleep(3000);
			driver.findElement(By.id("cmntsIfTerminatInSamplAckTermn"))
					.sendKeys(properties.getProperty("Comments_150"));
			Thread.sleep(2000);
			driver.findElement(By.id("terminateBtnInSampleAckTerminate")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			Thread.sleep(3000);
			driver.findElement(By.id("subBtnInValidateESign")).click();
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			Thread.sleep(4000);

		} else {
			System.out.println("Record is not Selected");
		}

	}

	private boolean selectRecordForAdhocApprove(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("terminatedSampleAckTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"terminatedSampleAckTable\"]/div/div[4]/div[2]/span"))
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
				name = driver.findElement(By.xpath("//*[@id=\"terminatedSampleAckTable\"]/div/table/tbody/tr[1]/td[9]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"terminatedSampleAckTable\"]/div/table/tbody/tr/td[9]"))
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
										"//*[@id=\"terminatedSampleAckTable\"]/div/table/tbody/tr[ " + i + " ]/td[9]"))
								.getText();// documentTypeName
						if (name.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath("//*[@id=\"terminatedSampleAckTable\"]/div/table/tbody/tr[ " + i
									+ " ]/td[17]/button")).click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"terminatedSampleAckTable\"]/div/table/tbody/tr/td[9]"))
							.getText();
					if (name.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"terminatedSampleAckTable\"]/div/table/tbody/tr/td[9]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#terminatedSampleAckTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("terminatedSampleAckTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}