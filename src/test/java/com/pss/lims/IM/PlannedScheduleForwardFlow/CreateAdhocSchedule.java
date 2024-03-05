package com.pss.lims.IM.PlannedScheduleForwardFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.lims.ExtentTestNGPkg.Utility;
import com.pss.lims.IM.Login.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class CreateAdhocSchedule extends LoginDetails {

	@Test
	public void createAdhocSchedule() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateAdhocSchedule"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Adhoc  Schedule", "PSS-LIMS-019", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Initiator_Login"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("limsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("limsModule"));
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/input")).click();
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno + "." + "Enter the username, password, Select Module and click on login button"
				+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 240);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='adhocScheduleInIMS.do'")));
		driver.findElement(By.cssSelector("a[href='adhocScheduleInIMS.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Adhoc Calibration Schedule", sno,
				false);
		Thread.sleep(4000);
		methodToCreateAdhocSchedule();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreateAdhocSchedule() throws Exception {

		sno++;
		driver.findElement(By.id("createAdhocScheduleAction")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create Adhoc Calibration Schedule",
				sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver
				.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("scheduleNameInAdhocSchedule")).sendKeys(properties.getProperty("AdhocScheduleName"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Schedule Name", sno, false);
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.id("scheduleDateInAdhocSchedule"));
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", element);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		sno++;
		driver.findElement(By.id("scheduleDateInAdhocSchedule")).sendKeys(sdf.format(date));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Schedule Date", sno, false);
		Thread.sleep(2000);
		sno++;
		Select category = new Select(driver.findElement(By.id("instCategoryInAdhocSchedule")));
		Thread.sleep(1000);
		category.selectByVisibleText(properties.getProperty("Category_Name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Instrument Category", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("selAssignToBtnInAdhocSchedule")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("locTreeInCalPmBdm_2_span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(5000);
		int count = 0;
		boolean isRecordSelected = false;
		String selectingUserSingleApproval = properties.getProperty("LastName");
		isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval, isRecordSelected,
				count);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse4210 = (JavascriptExecutor) driver;
		WebElement element4210 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
		jse4210.executeScript("arguments[0].click();", element4210);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(3000);
		sno++;
		Select id = new Select(driver.findElement(By.id("instIdInAdhocSchedule")));
		id.selectByIndex(1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Instrument Id", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse40 = (JavascriptExecutor) driver;
		WebElement element40 = driver.findElement(By.id("selApprovedByBtnInAdhocSchedule"));
		jse40.executeScript("arguments[0].click();", element40);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse410 = (JavascriptExecutor) driver;
		WebElement element410 = driver.findElement(By.id("locTreeInCalPmBdm_2_span"));
		jse410.executeScript("arguments[0].click();", element410);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(3000);
		int count2 = 0;
		boolean isRecordSelected2 = false;
		String name2 = properties.getProperty("LastName");
		isRecordSelected2 = selectUser(count2, isRecordSelected2, name2);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse42110 = (JavascriptExecutor) driver;
		WebElement element42110 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
		jse42110.executeScript("arguments[0].click();", element42110);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("commentsInAdhocSchedule")).sendKeys(properties.getProperty("Comments_150"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse11 = (JavascriptExecutor) driver;
		WebElement element11 = driver
				.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
		jse11.executeScript("arguments[0].click();", element11);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("addTestsBtnInAdhocSchedule")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("searchBtnInImsSrch")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		Thread.sleep(3000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("TestName");
		isRecordSelected1 = selectTest(count1, isRecordSelected1, name1);
		sno++;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse421110 = (JavascriptExecutor) driver;
		WebElement element421110 = driver.findElement(By.id("SelectBtnOfTestsMultiSelWndInIms"));
		jse421110.executeScript("arguments[0].click();", element421110);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse421150 = (JavascriptExecutor) driver;
		WebElement element421150 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
		jse421150.executeScript("arguments[0].click();", element421150);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse7 = (JavascriptExecutor) driver;
		WebElement element7 = driver.findElement(By.id("subBtnInValidateESign"));
		jse7.executeScript("arguments[0].click();", element7);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).getText()
				.equalsIgnoreCase("Submitted successfully.")) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
			driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
		}
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.className("username")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

	}

	private boolean selectUser(int count2, boolean isRecordSelected2, String name2) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("usersTableContainer"));// Single Selection User Table
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
			String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span")).getText()
					.toString();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			totalNoOfRecords = Integer.parseInt(parts[1].trim());
		}
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
			if ((totalNoOfRecords > 1) && ((name2 == null) || ("".equalsIgnoreCase(name2)))) {
				name2 = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();
			} else if (name2 == null || ("".equalsIgnoreCase(name2))) {
				name2 = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();
			}
			++count2;
		}

		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String selectingUser = driver
								.findElement(By
										.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[2]"))
								.getText();
						if (name2.equalsIgnoreCase(selectingUser)) {
//                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[1]")).click();
							JavascriptExecutor jse4 = (JavascriptExecutor) driver;
							WebElement element4 = driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[2]"));
							jse4.executeScript("arguments[0].click();", element4);
							isRecordSelected2 = true;
							break;
						}
						if (isRecordSelected2) {
							break;
						}
					}
				} else {
					String selectingUser = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name2.equalsIgnoreCase(selectingUser)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelected2 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected2) && (noOfRecordsChecked < totalNoOfRecords)) {
					JavascriptExecutor jse112 = (JavascriptExecutor) driver;
					WebElement element112 = driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
					jse112.executeScript("arguments[0].click();", element112);
					// next page in single approval
					Thread.sleep(3000);
					table = driver.findElement(By.id("usersTableContainer"));// single approval table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected2;
	}

	private boolean selectTest(int count1, boolean isRecordSelected1, String name1) throws Exception {
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
			if ((totalNoOfRecords > 1) && ((name1 == null) || ("".equalsIgnoreCase(name1)))) {
//				System.out.println("hi this is ravi");
				name1 = driver
						.findElement(By.xpath(
								"//*[@id=\"testDetailsSelGridOfTestDetlsWndInIms\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver
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
						if (name1.contains(DevNumberSequence)) {
//							driver.findElement(By.xpath(
//									"//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
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
					if (name1.contains(DevNumberSequence)) {
//						driver.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr/td[4]"))
//								.click();
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
							"#testDetailsSelGridOfTestDetlsWndInIms > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("testDetailsSelGridOfTestDetlsWndInIms"));// Document Tree approve
																								// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected1;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
