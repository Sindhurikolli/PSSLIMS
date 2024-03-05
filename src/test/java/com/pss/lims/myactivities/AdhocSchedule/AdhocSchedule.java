package com.pss.lims.myactivities.AdhocSchedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pss.lims.IM.Login.LoginDetails;
import com.pss.lims.util.Helper;

public class AdhocSchedule extends LoginDetails {

	@Test
	public void adhocSchedule() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ASSIGN_ROLE_USER_FIRST_NAME"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[11]/a/map/area")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("myActTabInInstMngt")).click();
		Thread.sleep(4000);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("adhocScheduleInIMS"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(5000);
		methodForadhocSchedule();

	}

	private void methodForadhocSchedule() throws Exception {

		driver.findElement(By.id("createAdhocScheduleAction")).click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
		Thread.sleep(5000);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver
				.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(6000);
		driver.findElement(By.id("scheduleNameInAdhocSchedule"))
				.sendKeys(properties.getProperty("StorageCondition_30"));
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.id("scheduleDateInAdhocSchedule"));
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", element);
		Thread.sleep(2000);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		Thread.sleep(2000);
		driver.findElement(By.id("scheduleDateInAdhocSchedule")).sendKeys(sdf.format(date));
		Thread.sleep(2000);
		Select category = new Select(driver.findElement(By.id("instCategoryInAdhocSchedule")));
		Thread.sleep(2000);
		category.selectByIndex(5);
		Thread.sleep(2000);
		JavascriptExecutor jse71 = (JavascriptExecutor) driver;
		WebElement element71 = driver.findElement(By.id("selAssignToBtnInAdhocSchedule"));
		jse71.executeScript("arguments[0].click();", element71);
		Thread.sleep(3000);
		JavascriptExecutor jse711 = (JavascriptExecutor) driver;
		WebElement element711 = driver.findElement(By.id("locTreeInCalPmBdm_2_span"));
		jse711.executeScript("arguments[0].click();", element711);
		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String selectingUserSingleApproval = properties.getProperty("FirstName");
		isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval, isRecordSelected,
				count);
		Thread.sleep(3000);
		JavascriptExecutor jse4210 = (JavascriptExecutor) driver;
		WebElement element4210 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
		jse4210.executeScript("arguments[0].click();", element4210);
		Thread.sleep(3000);
		Select id = new Select(driver.findElement(By.id("instIdInAdhocSchedule")));
		Thread.sleep(2000);
		id.selectByIndex(1);
		Thread.sleep(2000);
		JavascriptExecutor jse11 = (JavascriptExecutor) driver;
		WebElement element11 = driver.findElement(By.id("selApprovedByBtnInAdhocSchedule"));
		jse11.executeScript("arguments[0].click();", element11);
		Thread.sleep(3000);
		JavascriptExecutor jse7111 = (JavascriptExecutor) driver;
		WebElement element7111 = driver.findElement(By.id("locTreeInCalPmBdm_2_span"));
		jse7111.executeScript("arguments[0].click();", element7111);
		Thread.sleep(3000);
		isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval, isRecordSelected,
				count);
		Thread.sleep(3000);
		JavascriptExecutor jse421 = (JavascriptExecutor) driver;
		WebElement element421 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
		jse421.executeScript("arguments[0].click();", element421);
		Thread.sleep(3000);
		driver.findElement(By.id("commentsInAdhocSchedule")).sendKeys(properties.getProperty("Create_CheckPoints_250"));
		Thread.sleep(2000);
		JavascriptExecutor jse42110 = (JavascriptExecutor) driver;
		WebElement element42110 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse42110.executeScript("arguments[0].click();", element42110);
		Thread.sleep(5000);
		JavascriptExecutor jse4211 = (JavascriptExecutor) driver;
		WebElement element4211 = driver.findElement(By.id("addTestsBtnInAdhocSchedule"));
		jse4211.executeScript("arguments[0].click();", element4211);
		Thread.sleep(3000);
		JavascriptExecutor jse41211 = (JavascriptExecutor) driver;
		WebElement element41211 = driver.findElement(By.id("searchBtnInImsSrch"));
		jse41211.executeScript("arguments[0].click();", element41211);
		Thread.sleep(3000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String record = properties.getProperty("StageDescription_50");
		isRecordSelected1 = SelectRecordForAdhoc(count1, isRecordSelected1, record);
		if (isRecordSelected1) {
			Thread.sleep(4000);
			JavascriptExecutor jse411 = (JavascriptExecutor) driver;
			WebElement element411 = driver.findElement(By.id("SelectBtnOfTestsMultiSelWndInIms"));
			jse411.executeScript("arguments[0].click();", element411);
			Thread.sleep(3000);
			JavascriptExecutor jse4121111 = (JavascriptExecutor) driver;
			WebElement element4121111 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse4121111.executeScript("arguments[0].click();", element4121111);
			Thread.sleep(3000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			Thread.sleep(2000);
//	driver.findElement(By.id("subBtnInValidateESign")).click();
			JavascriptExecutor jse7 = (JavascriptExecutor) driver;
			WebElement element7 = driver.findElement(By.id("subBtnInValidateESign"));
			jse7.executeScript("arguments[0].click();", element7);
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
//	driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			JavascriptExecutor jse8 = (JavascriptExecutor) driver;
			WebElement element8 = driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a"));
			Thread.sleep(2000);
			jse8.executeScript("arguments[0].click();", element8);
			Thread.sleep(4000);
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean SelectRecordForAdhoc(int count1, boolean isRecordSelected1, String record) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("testDetailsSelGridOfTestDetlsWndInIms"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"testDetailsSelGridOfTestDetlsWndInIms\"]/div/div[4]/div[2]/span"))
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
			if ((totalNoOfRecords > 1) && ((record == null) || ("".equalsIgnoreCase(record)))) {
//				System.out.println("hi this is ravi");
				record = driver
						.findElement(By.xpath(
								"//*[@id=\"testDetailsSelGridOfTestDetlsWndInIms\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((record == null) || ("".equalsIgnoreCase(record))) {
				record = driver
						.findElement(
								By.xpath("//*[@id=\"testDetailsSelGridOfTestDetlsWndInIms\"]/div/table/tbody/tr/td[3]"))
						.getText();// document
									// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"testDetailsSelGridOfTestDetlsWndInIms\"]/div/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (record.equalsIgnoreCase(DevNumberSequence)) {
//							driver.findElement(
//									By.xpath("//*[@id=\"imsQCApproveOfSchedulePassedResultsForm\"]/div/table/tbody/tr[ "
//											+ i + " ]/td[3]"))
//									.click();
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(
									By.xpath("//*[@id=\"testDetailsSelGridOfTestDetlsWndInIms\"]/div/table/tbody/tr["
											+ i + "]/td[3]"));
							jse8.executeScript("arguments[0].click();", element8);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath(
									"//*[@id=\"testDetailsSelGridOfTestDetlsWndInIms\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (record.equalsIgnoreCase(DevNumberSequence)) {
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver.findElement(By
								.xpath("//*[@id=\"testDetailsSelGridOfTestDetlsWndInIms\"]/div/table/tbody/tr/td[3]"));
						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#testDetailsSelGridOfTestDetlsWndInIms > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("testDetailsSelGridOfTestDetlsWndInIms"));// Document Tree
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
