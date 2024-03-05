package com.pss.lims.IM.AdhocScheduleRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class InstrumentRegister extends LoginDetails {

	@Test
	public void registerInstrument() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InstrumentRegister"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Instrument Register", "LIMS-IMS-003", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='instRegistration.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='instRegistration.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Instrument Registration", sno,
				false);
		Thread.sleep(4000);
		methodToregisterInstrument();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToregisterInstrument() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		sno++;
		driver.findElement(By.id("createInstRegistrationAction")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Instrument Registration", sno,
				false);
		Thread.sleep(3000);
//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
//		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver
				.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(6000);
		sno++;
		JavascriptExecutor jse3 = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.id("selInstCategoryInInstReg"));
		jse3.executeScript("arguments[0].click();", element3);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(4000);
		sno++;
		JavascriptExecutor jse31 = (JavascriptExecutor) driver;
		WebElement element31 = driver.findElement(By.id("srchBtnInInstCategorySelWindow"));
		jse31.executeScript("arguments[0].click();", element31);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#tableInInstCategorySelectionWindow > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(5000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String category = properties.getProperty("Category_Name");
		isRecordSelected1 = selectRecordForInstrument(count1, isRecordSelected1, category);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse311 = (JavascriptExecutor) driver;
			WebElement element311 = driver.findElement(By.id("selectBtnInInstCategorySelWindow"));
			jse311.executeScript("arguments[0].click();", element311);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse11 = (JavascriptExecutor) driver;
			WebElement element11 = driver.findElement(By.id("selLocationBtnInInstReg"));
			jse11.executeScript("arguments[0].click();", element11);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse10 = (JavascriptExecutor) driver;
			WebElement element10 = driver.findElement(By.id("locSelectInSm_2_switch"));
			jse10.executeScript("arguments[0].click();", element10);
			Thread.sleep(3000);
			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse0 = (JavascriptExecutor) driver;
			WebElement element0 = driver.findElement(By.id("locSelBtnInInstReg"));
			jse0.executeScript("arguments[0].click();", element0);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			Thread.sleep(2000);
			driver.findElement(By.id("QualificationDateInInstReg")).sendKeys(sdf.format(date));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Qualification Date", sno,
					false);
			Thread.sleep(2000);
			sno++;
			Thread.sleep(2000);
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 100);
			String output = sdf1.format(cal.getTime());
			driver.findElement(By.id("previousAmcDateInInstReg")).sendKeys(output);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Previous Date", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse3111 = (JavascriptExecutor) driver;
			WebElement element3111 = driver.findElement(By.id("instrumentNameInInstReg"));
			jse3111.executeScript("arguments[0].click();", element3111);
			Thread.sleep(2000);
			driver.findElement(By.id("instrumentNameInInstReg")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("instrumentNameInInstReg")).sendKeys(properties.getProperty("InstrumentName"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Instrument Name", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("AmcIntervalInInstReg")).sendKeys(properties.getProperty("AMCInterval"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter AMC Interval", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("makeInInstReg")).sendKeys(properties.getProperty("Make"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Make", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("SopNoInInstReg")).sendKeys(properties.getProperty("SOPNO"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter SOP NO", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("modelInInstReg")).sendKeys(properties.getProperty("Model"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Model", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("manufacturerSerialNoInInstReg"))
					.sendKeys(properties.getProperty("MfgSerialNumber"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter MFG Serial Number", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse110 = (JavascriptExecutor) driver;
			WebElement element110 = driver.findElement(By.id("selSuppliedByFromUserInInstReg"));
			jse110.executeScript("arguments[0].click();", element110);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse1140 = (JavascriptExecutor) driver;
			WebElement element1140 = driver.findElement(By.id("searchBtnInlimsSupplierDetailsSearchForm"));
			jse1140.executeScript("arguments[0].click();", element1140);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.cssSelector("#supplierDetailsSelJtable > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(5000);
			int count11 = 0;
			boolean isRecordSelected11 = false;
			String category1 = properties.getProperty("SuppliedBy");
			isRecordSelected11 = selectRecordForInstrumentCategory(count11, isRecordSelected11, category1);
			if (isRecordSelected11) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
				Thread.sleep(3000);
				sno++;
				JavascriptExecutor jse140 = (JavascriptExecutor) driver;
				WebElement element140 = driver.findElement(By.id("selectBtnInlimsSupplierDetailsSearchForm"));
				jse140.executeScript("arguments[0].click();", element140);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("docUploadBtnInInstReg")).sendKeys(properties.getProperty("Document-1"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload Document", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("locationNameInInstReg")).sendKeys(properties.getProperty("Location"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Location Name", sno, false);
				Thread.sleep(3000);
				sno++;
				JavascriptExecutor jse40 = (JavascriptExecutor) driver;
				WebElement element40 = driver.findElement(By.id("selAppFromUserInInstReg"));
				jse40.executeScript("arguments[0].click();", element40);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(5000);
				sno++;
				JavascriptExecutor jse410 = (JavascriptExecutor) driver;
				WebElement element410 = driver.findElement(By.id("locTreeInCalPmBdm_2_switch"));
				jse410.executeScript("arguments[0].click();", element410);
				Thread.sleep(3000);
				driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
				Thread.sleep(5000);
				int count = 0;
				boolean isRecordSelected = false;
				String selectingUserSingleApproval = properties.getProperty("LastName");
				isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval,
						isRecordSelected, count);
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
				Thread.sleep(3000);
				sno++;
				JavascriptExecutor jse4210 = (JavascriptExecutor) driver;
				WebElement element4210 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
				jse4210.executeScript("arguments[0].click();", element4210);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				sno++;
				Thread.sleep(3000);
				JavascriptExecutor jse42110 = (JavascriptExecutor) driver;
				WebElement element42110 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
				jse42110.executeScript("arguments[0].scrollIntoView(true);", element42110);
				Thread.sleep(2000);
				jse42110.executeScript("arguments[0].click();", element42110);
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
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
				Thread.sleep(3000);
				if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).isDisplayed()) {
					sno++;
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno,
							false);
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
				System.out.println("Supplier is not Selected");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecordForInstrumentCategory(int count11, boolean isRecordSelected11, String category1)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("supplierDetailsSelJtable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"supplierDetailsSelJtable\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count11 == 0) {
			if ((totalNoOfRecords > 1) && ((category1 == null) || ("".equalsIgnoreCase(category1)))) {
				category1 = driver
						.findElement(By.xpath("//*[@id=\"supplierDetailsSelJtable\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((category1 == null) || ("".equalsIgnoreCase(category1))) {
				category1 = driver
						.findElement(By.xpath("//*[@id=\"supplierDetailsSelJtable\"]/div/table/tbody/tr/td[3]"))
						.getText();// document
									// type
			}
			++count11;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"supplierDetailsSelJtable\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (category1.equalsIgnoreCase(DevNumberSequence)) {
//							driver.findElement(By.xpath(
//									"//*[@id=\"supplierDetailsSelJtable\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
//									.click();
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(By.xpath(
									"//*[@id=\"supplierDetailsSelJtable\"]/div/table/tbody/tr[" + i + "]/td[3]"));
							jse8.executeScript("arguments[0].click();", element8);
							isRecordSelected11 = true;
							break;
						}
					}
					if (isRecordSelected11) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"supplierDetailsSelJtable\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (category1.equalsIgnoreCase(DevNumberSequence)) {
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver.findElement(
								By.xpath("//*[@id=\"supplierDetailsSelJtable\"]/div/table/tbody/tr/td[3]"));
						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected11 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected11) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#supplierDetailsSelJtable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("supplierDetailsSelJtable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected11;
	}

	private boolean selectRecordForInstrument(int count1, boolean isRecordSelected1, String category) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("tableInInstCategorySelectionWindow"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((category == null) || ("".equalsIgnoreCase(category)))) {
				category = driver
						.findElement(
								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((category == null) || ("".equalsIgnoreCase(category))) {
				category = driver
						.findElement(
								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr/td[3]"))
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
								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (category.equalsIgnoreCase(DevNumberSequence)) {
//							driver.findElement(
//									By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr[ " + i
//											+ " ]/td[3]"))
//									.click();
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(
									By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr[" + i
											+ "]/td[3]"));
							jse8.executeScript("arguments[0].click();", element8);
//							Thread.sleep(4000);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By
									.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (category.equalsIgnoreCase(DevNumberSequence)) {
//						driver.findElement(
//								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr/td[3]"))
//								.click();
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver.findElement(
								By.xpath("/*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr/td[3]"));
						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#tableInInstCategorySelectionWindow > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("tableInInstCategorySelectionWindow"));// Document Tree approve
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
