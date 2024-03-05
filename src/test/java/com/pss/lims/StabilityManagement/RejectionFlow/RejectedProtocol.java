package com.pss.lims.StabilityManagement.RejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
import com.pss.lims.Satbility.Login.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;

public class RejectedProtocol extends LoginDetails {

	@Test
	public void reinitiateprotocolRejected() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ReinitiateRejectedProtocol"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Reinitiate Rejected Protocol", "PSS-LIMS-006", "Pass");
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
		Thread.sleep(3000);
		sno++;
		WebDriverWait wiat = new WebDriverWait(driver, 240);
		wiat.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='createProtocolForm.do'")));
		driver.findElement(By.cssSelector("a[href='createProtocolForm.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create", sno, false);
		Thread.sleep(4000);
		methodForRejectedProtocol();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
	}

	private void methodForRejectedProtocol() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 240);
		sno++;
		driver.findElement(By.id("rejectedProtocolStabilityAction")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Rejected", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#protocolStabilityReqRejectedGrid > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(2000);
		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("Protocol_Number");
		isRecordSelected = selectRejectedRecord(count, isRecordSelected, name);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
//			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
//			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse25 = (JavascriptExecutor) driver;
			WebElement element25 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse25.executeScript("arguments[0].scrollIntoView(true);", element25);
			Thread.sleep(2000);
			jse25.executeScript("arguments[0].click();", element25);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.switchTo().frame("purposeInProtocolForm_ifr");
			driver.findElement(By.cssSelector("body[data-id='purposeInProtocolForm']")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Objective", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("body[data-id='purposeInProtocolForm']")).sendKeys(properties.getProperty("Objective"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Objective", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.switchTo().defaultContent();
			driver.switchTo().frame("productInformationProtocolForm_ifr");
			driver.findElement(By.cssSelector("body[data-id='productInformationProtocolForm']")).clear();			
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Responsibilities", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("body[data-id='productInformationProtocolForm']")).sendKeys(properties.getProperty("Responsibilities"));
//			driver.findElement(By.id("productInformationProtocolForm_ifr")).sendKeys(properties.getProperty("Responsibilities"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Responsibilities", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.switchTo().defaultContent();
			driver.switchTo().frame("productDescProtocolForm_ifr");
			driver.findElement(By.cssSelector("body[data-id='productDescProtocolForm']")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Stability Plan", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("body[data-id='productDescProtocolForm']")).sendKeys(properties.getProperty("StabilityPlan"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Stability Plan", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.switchTo().defaultContent();
			driver.switchTo().frame("selOfBatchesInProtocolForm_ifr");
			driver.findElement(By.cssSelector("body[data-id='selOfBatchesInProtocolForm']")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Procedure", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("body[data-id='selOfBatchesInProtocolForm']")).sendKeys(properties.getProperty("Procedure"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Procedure", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.switchTo().defaultContent();
			jse25.executeScript("arguments[0].scrollIntoView(true);", element25);
			Thread.sleep(2000);
			jse25.executeScript("arguments[0].click();", element25);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
			Date date = new Date();
			String mfgDate= dateFormat.format(date);
			int quantity =6;
			date = DateUtils.addYears(date, quantity);
			String expDate = dateFormat.format(date);
			int quantityapi =6;
			date = DateUtils.addYears(date, quantityapi);
			String expDateapi = dateFormat.format(date);
			driver.findElement(By.id("mfgDateInProtForm_1")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Manufacturing Date", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("mfgDateInProtForm_1")).sendKeys(mfgDate);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Manufacturing Date", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("expDateInProtForm_1")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Expiry Date", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("expDateInProtForm_1")).sendKeys(expDate);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Expiry Date", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("manufactDateInProtFormAPI_1")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Manufacturer Date", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("manufactDateInProtFormAPI_1")).sendKeys(mfgDate);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Manufacturer Date", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("expDateInProtFormAPI_1")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Expiry Date", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("expDateInProtFormAPI_1")).sendKeys(expDateapi);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Expiry Date", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse13 = (JavascriptExecutor) driver;
			WebElement element13 = driver
					.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
			jse13.executeScript("arguments[0].click();", element13);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			jse25.executeScript("arguments[0].scrollIntoView(true);", element25);
			Thread.sleep(2000);
			jse25.executeScript("arguments[0].click();", element25);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(By.id("samplingInProtocolForm")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Sampling & Labeling", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("samplingInProtocolForm")).sendKeys(properties.getProperty("Description"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Sampling & Labeling", sno, false);
			Thread.sleep(3000);
			sno++;
			jse25.executeScript("arguments[0].scrollIntoView(true);", element25);
			Thread.sleep(2000);
			jse25.executeScript("arguments[0].click();", element25);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(By.id("noOfRowsForSDKTInProtForm")).sendKeys("1");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Rows", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse30 = (JavascriptExecutor) driver;
			WebElement element30 = driver.findElement(By.id("addNumberOfRowsSDKTProtForm"));
			jse30.executeScript("arguments[0].click();", element30);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"sampleDetailsKeptStabilityTableContainer\"]/div/table/tbody/tr/td[8]/button")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "select Batch", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsSampleDialog\"]/div[2]/div/button")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("quantityChrgdInProtSampleDKT_1")).sendKeys("3");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Quantity Charged", sno,
					false);
			Thread.sleep(2000);
			sno++;
			Select uom = new Select(driver.findElement(By.id("uomDrpDownSDKT_1")));
			Thread.sleep(2000);
			uom.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(4000);
			sno++;
			
			driver.findElement(By.id("protFileUploadNewInRej")).sendKeys(properties.getProperty("Document-2"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload Document1", sno, false);
			Thread.sleep(2000);
			sno++;
			jse25.executeScript("arguments[0].scrollIntoView(true);", element25);
			Thread.sleep(2000);
			jse25.executeScript("arguments[0].click();", element25);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(By.id("noOfRowsInScIntStrCondBoxForm")).sendKeys("1");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No of rows for Samples", sno, false);
			sno++;
			driver.findElement(By.id("addNumberOfRowsInSchStoCondJtable")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Button", sno, false);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"newschIntervalStorageCondDetTableContainer\"]/div/table/tbody/tr/td[3]/button")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSchStrCondFormDialog\"]/div/table/tbody/tr")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "select Batch", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsSchStorageCondDialog\"]/div[2]/div/button")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button", sno, false);
			Thread.sleep(2000);
			sno++;
			Select packingType = new Select(driver.findElement(By.id("packingTypeInPPPF_New_1")));
			Thread.sleep(2000);
			packingType.selectByVisibleText(properties.getProperty("Packing_Type_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Packing Type", sno, false);
			Thread.sleep(2000);
			sno++;
			Select intervalType = new Select(driver.findElement(By.id("scheduleIntervalInPPFF_New_1")));
			intervalType.selectByVisibleText(properties.getProperty("Interval_Type"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Schedule Interval Type", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"newschIntervalStorageCondDetTableContainer\"]/div/table/tbody/tr/td[18]/button")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on view", sno, false);				
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.id("rackNumberInScheduleStorageConditionsForm")).sendKeys(properties.getProperty("Interval"));
			driver.findElement(By.id("addRackToJtableInScheduleStorageConditionSForm")).click();
			driver.findElement(By.id("selectInScheduleStorageConditionSForm")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Interval then add and click on select", sno, false);				
			sno++;
			Thread.sleep(3000);
			jse25.executeScript("arguments[0].scrollIntoView(true);", element25);
			Thread.sleep(2000);
			jse25.executeScript("arguments[0].click();", element25);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			JavascriptExecutor jse5110 = (JavascriptExecutor) driver;
			WebElement element5110 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse5110.executeScript("arguments[0].scrollIntoView(true);", element5110);
			Thread.sleep(2000);
			jse5110.executeScript("arguments[0].click();", element5110);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse7 = (JavascriptExecutor) driver;
			WebElement element7 = driver.findElement(By.id("subBtnInValidateESign"));
			jse7.executeScript("arguments[0].click();", element7);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(3000);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
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

		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRejectedRecord(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("protocolStabilityReqRejectedGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"protocolStabilityReqRejectedGrid\"]/div/div[4]/div[2]/span"))
					.getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
			String[] parts = a.split(" of ");
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
								By.xpath("//*[@id=\"protocolStabilityReqRejectedGrid\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver
						.findElement(By.xpath("//*[@id=\"protocolStabilityReqRejectedGrid\"]/div/table/tbody/tr/td[1]"))
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
								"//*[@id=\"protocolStabilityReqRejectedGrid\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
								.getText();// documentTypeName
						if (name.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"protocolStabilityReqRejectedGrid\"]/div/table/tbody/tr[ " + i
											+ " ]/td[1]"))
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
									By.xpath("//*[@id=\"protocolStabilityReqRejectedGrid\"]/div/table/tbody/tr/td[1]"))
							.getText();
					if (name.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"protocolStabilityReqRejectedGrid\"]/div/table/tbody/tr/td[1]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#protocolStabilityReqRejectedGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("protocolStabilityReqRejectedGrid"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
