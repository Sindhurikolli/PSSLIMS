package com.pss.lims.IM.AdhocScheduleForwardFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
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

public class InstrumentTestTemplate extends LoginDetails {

	@Test
	public void registerInstrumentTestTemplate() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "InstrumentTestTemplate"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Instrument Test Template", "LIMS-IMS-009",
				"Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='instTestTemplate.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='instTestTemplate.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Inst.Test Template", sno, false);
		Thread.sleep(4000);
		methodToregisterInstrumentTestTemplate();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToregisterInstrumentTestTemplate() throws Exception {

		sno++;
		driver.findElement(By.id("createInstTestTemplateActionInReg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
				"Click on Registration of Instrument Test Template", sno, false);
		Thread.sleep(3000);
//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='75%';");
//		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 150);
		sno++;
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver
				.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(6000);
		sno++;
		JavascriptExecutor jse3 = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.id("selInstInInstTestTemplateReg"));
		jse3.executeScript("arguments[0].click();", element3);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(4000);
		sno++;
		JavascriptExecutor jse31 = (JavascriptExecutor) driver;
		WebElement element31 = driver.findElement(By.id("srchBtnInInstSelWindow"));
		jse31.executeScript("arguments[0].click();", element31);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		JavascriptExecutor jse311 = (JavascriptExecutor) driver;
		WebElement element311 = driver.findElement(By.id("selectBtnInInstSelWindow"));
		jse311.executeScript("arguments[0].scrollIntoView(true);", element311);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#tableInInstSelectionWindow > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(5000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String test = properties.getProperty("Instrument_Id");
		isRecordSelected1 = selectRecordForTest(count1, isRecordSelected1, test);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			jse311.executeScript("arguments[0].click();", element311);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
			sno++;
			jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
			Thread.sleep(1000);
			jse1.executeScript("arguments[0].click();", element1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(By.id("noOfRowsInScheduleStorageConditionsForm"))
					.sendKeys(properties.getProperty("No.ofTemplates"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Number of Templates", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse3111 = (JavascriptExecutor) driver;
			WebElement element3111 = driver.findElement(By.id("addRackToJtable"));
			jse3111.executeScript("arguments[0].click();", element3111);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
			Thread.sleep(3000);
			int nooftemplates = Integer.parseInt(properties.getProperty("No.ofTemplates"));
			String[] frequency = properties.getProperty("Frequency").split(",");
			String[] tolerance = properties.getProperty("Tolerance").split(",");
			for (int i = 1; i <= nooftemplates; i++) {
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"instTestTemplateEditorjTable\"]/div/table/tbody/tr[" + i + "]/td[2]/input"))
						.sendKeys(frequency[i - 1]);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Frequence Days", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"instTestTemplateEditorjTable\"]/div/table/tbody/tr[" + i + "]/td[3]/input"))
						.sendKeys(tolerance[i - 1]);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Tolerance Days", sno, false);
				Thread.sleep(2000);
				sno++;
				Select id = new Select(driver.findElement(By.xpath(
						"//*[@id=\"instTestTemplateEditorjTable\"]/div/table/tbody/tr[" + i + "]/td[4]/select")));
				Thread.sleep(2000);
//				id.selectByVisibleText(name[i - 1]);
				id.selectByVisibleText(properties.getProperty("CalibrationDesign"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Test Template", sno, false);
				Thread.sleep(2000);
			}
			sno++;
			JavascriptExecutor jse40 = (JavascriptExecutor) driver;
			WebElement element40 = driver.findElement(By.id("selAppFromUserInInstTestTemplate"));
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
				JavascriptExecutor jse71 = (JavascriptExecutor) driver;
				WebElement element71 = driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a"));
				jse71.executeScript("arguments[0].click();", element71);
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

	private boolean selectRecordForTest(int count1, boolean isRecordSelected1, String test) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("tableInInstSelectionWindow"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/div[4]/div[2]/span"))
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
			if ((totalNoOfRecords > 1) && ((test == null) || ("".equalsIgnoreCase(test)))) {
				test = driver
						.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((test == null) || ("".equalsIgnoreCase(test))) {
				test = driver.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr/td[4]"))
						.getText();// document
									// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By
								.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();// documentTypeName
						if (test.contains(DevNumberSequence)) {
//							driver.findElement(By.xpath(
//									"//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
//									.click();
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(By.xpath(
									"//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[" + i + "]/td[4]"));
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
							.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (test.contains(DevNumberSequence)) {
//						driver.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr/td[4]"))
//								.click();
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver.findElement(
								By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr/td[3]"));
						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#tableInInstSelectionWindow > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("tableInInstSelectionWindow"));// Document Tree approve table
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
