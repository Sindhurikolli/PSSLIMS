package com.pss.lims.sm.approvals.modify;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;

public class ModifyProductApproval extends SMLoginDetails {

	@Test
	public void approveProduct() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("FirstName"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[4]/a/map/area")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("myActTabInSampleMngt")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("approvalsGroupId")).click();
		Thread.sleep(3000);
		methodToapproveProduct();

	}

	private void methodToapproveProduct() throws Exception {

		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("productAppPageInSample"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String storageCondition = properties.getProperty("StageName_30");
		isRecordSelected = selectRecordForStorageLocation(count, isRecordSelected, storageCondition);
		if (isRecordSelected) {
			Thread.sleep(4000);
			driver.findElement(By.id("commentsInProductApp")).sendKeys(properties.getProperty("comments_200"));
			Thread.sleep(2000);
			driver.findElement(By.id("approveBtnInProdApproval")).click();
			Thread.sleep(2000);
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

	private boolean selectRecordForStorageLocation(int count, boolean isRecordSelected, String storageCondition)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("productTableInApproval"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"productTableInApproval\"]/div/div[4]/div[2]/span"))
					.getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
//				System.out.println("hi:" + a);
			String[] parts = a.split(" of ");
//				System.out.println("parts:" + parts.toString());
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println(totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
//				System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((storageCondition == null) || ("".equalsIgnoreCase(storageCondition)))) {
//					System.out.println("hi this is ravi");
				storageCondition = driver
						.findElement(By.xpath("//*[@id=\"productTableInApproval\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((storageCondition == null) || ("".equalsIgnoreCase(storageCondition))) {
				storageCondition = driver
						.findElement(By.xpath("//*[@id=\"productTableInApproval\"]/div/table/tbody/tr/td[3]"))
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
										"//*[@id=\"productTableInApproval\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (storageCondition.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath("//*[@id=\"productTableInApproval\"]/div/table/tbody/tr[ " + i
									+ " ]/td[11]/button")).click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"productTableInApproval\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (storageCondition.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"productTableInApproval\"]/div/table/tbody/tr/td[11]/button"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#productTableInApproval > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("productTableInApproval"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelected;
	}
}
