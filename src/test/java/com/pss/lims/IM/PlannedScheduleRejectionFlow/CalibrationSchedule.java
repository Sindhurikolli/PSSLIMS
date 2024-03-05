package com.pss.lims.IM.PlannedScheduleRejectionFlow;

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

public class CalibrationSchedule extends LoginDetails {

	@Test
	public void scheduleCalibration() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateCalibrationSchedule"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Calibration Schedule", "LIMS-IMS-011", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='instCalSch.do'")));
		driver.findElement(By.cssSelector("a[href='instCalSch.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Calibration Schedule", sno, false);
		Thread.sleep(4000);
		methodForscheduleCalibration();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodForscheduleCalibration() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		sno++;
		driver.findElement(By.id("createInstCalibrationScheduleAction")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create Calibration Schedule", sno,
				false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver
				.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("scheduleNameInCalSchedule")).sendKeys(properties.getProperty("ScheduleName"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Schedule Name", sno, false);
		Thread.sleep(2000);
		sno++;
		Select category = new Select(driver.findElement(By.id("instCategoryInCalSchedule")));
		Thread.sleep(2000);
		category.selectByVisibleText(properties.getProperty("Category_Name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Instrument Catgepory", sno, false);
		Thread.sleep(3000);
		sno++;
		Select category1 = new Select(driver.findElement(By.id("instrumentInCalSchedule")));
		Thread.sleep(2000);
		category1.selectByVisibleText(properties.getProperty("Instrument_Id"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Instrument ID", sno, false);
		Thread.sleep(5000);
//		int count1 = 0;
//		boolean isRecordSelected1 = false;
//		String test = properties.getProperty("CalibrationDesign");
//		isRecordSelected1 = selectRecordForTest(count1, isRecordSelected1, test);
//		if (isRecordSelected1) {
		int noofTests = Integer.parseInt(properties.getProperty("No.ofTemplates"));
		for (int i = 1; i <= noofTests; i++) {
			sno++;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//*[@id=\"testTemplateDetailsGrid\"]/div/table/tbody/tr[" + i + "]/td[7]/input"))
					.sendKeys(sdf.format(date));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Start Date", sno, false);
			Thread.sleep(3000);
		}
		driver.findElement(By.id("scheduleNameInCalSchedule")).click();
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse40 = (JavascriptExecutor) driver;
		WebElement element40 = driver.findElement(By.id("selAppFromUserInCalibSchedule"));
		jse40.executeScript("arguments[0].click();", element40);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse410 = (JavascriptExecutor) driver;
		WebElement element410 = driver.findElement(By.id("locTreeInCalPmBdm_2_switch"));
		jse410.executeScript("arguments[0].click();", element410);
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("usersSearchBtnInRepProb")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
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
		JavascriptExecutor jse42110 = (JavascriptExecutor) driver;
		WebElement element42110 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
		jse42110.executeScript("arguments[0].scrollIntoView(true);", element42110);
		Thread.sleep(1000);
		jse42110.executeScript("arguments[0].click();", element42110);
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).isDisplayed()) {
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

//		} 
//			else {
//			System.out.println("Record is not Selected");
//			Assert.assertTrue(false);
//		}
	}

	private boolean selectRecordForTest(int count1, boolean isRecordSelected1, String test) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("testTemplateDetailsGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver.findElement(By.xpath("//*[@id=\"testTemplateDetailsGrid\"]/div/div[4]/div[2]/span"))
//					.getText();// For
//			// Ex:
//			// Showing
//			// 1-1
//			// of
//			// 1
//			String[] parts = a.split(" of ");
//			try {
//				totalNoOfRecords = Integer.parseInt(parts[1].trim());
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((test == null) || ("".equalsIgnoreCase(test)))) {
				test = driver.findElement(By.xpath("//*[@id=\"testTemplateDetailsGrid\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((test == null) || ("".equalsIgnoreCase(test))) {
				test = driver.findElement(By.xpath("//*[@id=\"testTemplateDetailsGrid\"]/div/table/tbody/tr/td[4]"))
						.getText();// document
									// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"testTemplateDetailsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();// documentTypeName
						if (test.contains(DevNumberSequence)) {
//							driver.findElement(By.xpath(
//									"//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
//									.click();
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(By
									.xpath("//*[@id=\"testTemplateDetailsGrid\"]/div/table/tbody/tr[" + i + "]/td[4]"));
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
							.findElement(By.xpath("//*[@id=\"testTemplateDetailsGrid\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (test.contains(DevNumberSequence)) {
//						driver.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr/td[4]"))
//								.click();
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver
								.findElement(By.xpath("//*[@id=\"testTemplateDetailsGrid\"]/div/table/tbody/tr/td[4]"));
						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#testTemplateDetailsGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("testTemplateDetailsGrid"));// Document Tree approve table
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
