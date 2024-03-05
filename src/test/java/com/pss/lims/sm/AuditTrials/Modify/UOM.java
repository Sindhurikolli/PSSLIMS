package com.pss.lims.sm.AuditTrials.Modify;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;

public class UOM extends SMLoginDetails {

	@Test
	public void checkuom() throws Exception {

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
		methodTocheckuom();

	}

	private void methodTocheckuom() throws Exception {

		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("modifyExpandMenu"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(3000);
		JavascriptExecutor jse11 = (JavascriptExecutor) driver;
		WebElement element11 = driver.findElement(By.id("limsUomModifyAuditForm"));
		jse11.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse11.executeScript("arguments[0].click();", element11);
		Thread.sleep(3000);
		driver.findElement(By.id("searchBtnInUomModifyAudit")).click();
		Thread.sleep(3000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String product = properties.getProperty("UOM");
		isRecordSelected1 = selectRecordForProduct(count1, isRecordSelected1, product);
		if (isRecordSelected1) {
			Thread.sleep(5000);
//			driver.findElement(By.id("cancelBtnInDialogForProductDetails")).click();
			JavascriptExecutor jse111 = (JavascriptExecutor) driver;
			WebElement element111 = driver.findElement(By.id("closeBtnInLimsUomModifyAuditWinForm"));
			jse111.executeScript("arguments[0].click();", element111);
//			Thread.sleep(3000);
////			driver.findElement(By.id("saveAsExcelBtnInSpecificationModifyAuditForm")).click();
//			JavascriptExecutor jse1111 = (JavascriptExecutor) driver;
//			WebElement element1111 = driver.findElement(By.id("saveAsExcelBtnInSpecificationModifyAuditForm"));
//			jse1111.executeScript("arguments[0].click();", element1111);
//			Thread.sleep(3000);
			Thread.sleep(5000);

		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordForProduct(int count1, boolean isRecordSelected1, String product) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("uomDetailsJtableInModifyAuditForm"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"uomDetailsJtableInModifyAuditForm\"]/div/div[4]/div[2]/span"))
					.getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
//							System.out.println("hi:" + a);
			String[] parts = a.split(" of ");
//							System.out.println("parts:" + parts.toString());
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println(totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
//							System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((product == null) || ("".equalsIgnoreCase(product)))) {
//								System.out.println("hi this is ravi");
				product = driver
						.findElement(
								By.xpath("//*[@id=\"uomDetailsJtableInModifyAuditForm\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((product == null) || ("".equalsIgnoreCase(product))) {
				product = driver
						.findElement(
								By.xpath("//*[@id=\"uomDetailsJtableInModifyAuditForm\"]/div/table/tbody/tr/td[3]"))
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
								"//*[@id=\"uomDetailsJtableInModifyAuditForm\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (product.contains(DevNumberSequence)) {
							driver.findElement(
									By.id("renderViewLimsUomModifyAuditDetails"))
									.click();
//											JavascriptExecutor jse9 = (JavascriptExecutor) driver;
//											WebElement element9 = driver.findElement(By.xpath(
//													"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"));
//											jse9.executeScript("arguments[0].click();", element9);
////											Thread.sleep(2000);
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
									By.xpath("//*[@id=\"uomDetailsJtableInModifyAuditForm\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (product.contains(DevNumberSequence)) {
						driver.findElement(
								By.id("renderViewLimsUomModifyAuditDetails"))
								.click();
//										JavascriptExecutor jse11 = (JavascriptExecutor) driver;
//										WebElement element11 = driver
//												.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"));
//										jse11.executeScript("arguments[0].click();", element11);
////										Thread.sleep(2000);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#uomDetailsJtableInModifyAuditForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("uomDetailsJtableInModifyAuditForm"));// Document
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
