package com.pss.lims.IM.PlannedScheduleForwardFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
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
import com.pss.lims.IM.Login.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;

public class CalibrationWorkSheet extends LoginDetails {

	@Test
	public void approveCalibrationWorkSheet() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CalibrationWorkSheetDetails"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Calibration Work Sheet Details", "LIMS-IMS-014",
				"Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Reviewer_Login"));
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
		WebDriverWait wiat = new WebDriverWait(driver, 240);
		wiat.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='calibrationWorkSheetInInstMngmt.do'")));
		driver.findElement(By.cssSelector("a[href='calibrationWorkSheetInInstMngmt.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Calibration Work Sheet Details",
				sno, false);
		wiat.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#calibrateWorkSheetTable > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		methodToapproveCalibrationWorkSheet();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToapproveCalibrationWorkSheet() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String record = properties.getProperty("Instrument_Id");
		isRecordSelected = selectRecordForWorkSheet(count, isRecordSelected, record);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("printWrkShtBtnInCalWorkSheetForm")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Print Work Sheet", sno, false);
			Thread.sleep(10000);
			String mainWindow = driver.getWindowHandle();
			System.out.println("main window is :" + mainWindow);
			Set<String> set = driver.getWindowHandles();
			Iterator<String> itr = set.iterator();
			while (itr.hasNext()) {
				String childWindow = itr.next();
				System.out.println("child window is:" + childWindow);
				if (!mainWindow.equals(childWindow)) {
					driver.switchTo().window(childWindow);
					System.out.println(driver.switchTo().window(childWindow).getTitle());
					Thread.sleep(3000);
					driver.close();
				} else {
					System.out.println("child window is not closed");
				}
			}
			driver.switchTo().window(mainWindow);
			Thread.sleep(2000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Close the PDF Window", sno, false);
			Thread.sleep(2000);
			WebDriverWait wait1 = new WebDriverWait(driver, 70);
			wait1.until(
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

	private boolean selectRecordForWorkSheet(int count, boolean isRecordSelected, String record) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("calibrateWorkSheetTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"calibrateWorkSheetTable\"]/div/div[4]/div[2]/span"))
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
			if ((totalNoOfRecords > 1) && ((record == null) || ("".equalsIgnoreCase(record)))) {
				record = driver
						.findElement(By.xpath("//*[@id=\"calibrateWorkSheetTable\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((record == null) || ("".equalsIgnoreCase(record))) {
				record = driver.findElement(By.xpath("//*[@id=\"calibrateWorkSheetTable\"]/div/table/tbody/tr/td[3]"))
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
										"//*[@id=\"calibrateWorkSheetTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (record.contains(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"calibrateWorkSheetTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
//							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
//							WebElement element8 = driver.findElement(By.xpath(
//									"//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[" + i + "]/td[4]"));
//							jse8.executeScript("arguments[0].click();", element8);
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"calibrateWorkSheetTable\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (record.contains(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"calibrateWorkSheetTable\"]/div/table/tbody/tr/td[3]"))
								.click();
//						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
//						WebElement element8 = driver.findElement(
//								By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr/td[3]"));
//						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#calibrateWorkSheetTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("calibrateWorkSheetTable"));// Document Tree
																					// approve table
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
