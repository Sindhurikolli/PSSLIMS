package com.pss.lims.SolutionManagement.RejectionFlow;

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
import com.pss.lims.SolutionManagement.LoginDetails.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class SolutionPreparationToBeReady extends LoginDetails {

	@Test
	public void solutionPreparationToBeReady() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "SolutionPreparationToBePrepared"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Solution Preparation To Be Prepared", "PSS-LIMS-004",
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
		WebDriverWait wiat = new WebDriverWait(driver, 240);
		wiat.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='solutionPreparation.do'")));
		driver.findElement(By.cssSelector("a[href='solutionPreparation.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Solution Preparation", sno, false);
		wiat.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#solutionPreparationTableCreate > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		methodToPrepareSolution();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToPrepareSolution() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 200);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("solution_Number");
		isRecordSelected1 = selectRecord(count1, isRecordSelected1, name1);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse12 = (JavascriptExecutor) driver;
			WebElement element12 = driver.findElement(By.id("selAppFromUserInSolutionPreparation"));
			jse12.executeScript("arguments[0].click();", element12);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			driver.findElement(By.id("locTreeInCalPmBdm_2_switch")).click();
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
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
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
			WebElement element1112 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse1112.executeScript("arguments[0].click();", element1112);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("solventInSolutionPreparation")).sendKeys(properties.getProperty("SolventName"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Solvent", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("solventQuantitySolutionPreparation"))
					.sendKeys(properties.getProperty("QuantityOfEachContainer"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Solvent Quantity", sno, false);
			Thread.sleep(2000);
			sno++;
			Select uom = new Select(driver.findElement(By.id("uomUnitsInSolutionPreparation")));
			Thread.sleep(1000);
			uom.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse11512 = (JavascriptExecutor) driver;
			WebElement element11512 = driver.findElement(By.id("toBePreparedInSolutionPreparationAction"));
			jse11512.executeScript("arguments[0].click();", element11512);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on To Be Prepared", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("totalQuantityToBePrepared"))
					.sendKeys(properties.getProperty("TotalQuantityRequired"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Total Quantity Required", sno,
					false);
			Thread.sleep(2000);
			sno++;
			Select uom1 = new Select(driver.findElement(By.id("uomUnitsInToBePrepared")));
			Thread.sleep(2000);
			uom1.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,350)", "");
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"addingChemicalsSolutionPreparation\"]/div/div[3]/div[2]/span/span[2]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Chemicals", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("chemicalSelSearchBtn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#chemicalNameDetailsInSelectWndwTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(2000);
			int count2 = 0;
			boolean isRecordSelected2 = false;
			String name2 = properties.getProperty("ChemicalName");
			isRecordSelected2 = selectChemical(count2, isRecordSelected2, name2);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selectBtnInChemicalWin")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"addingChemicalsSolutionPreparation\"]/div/table/tbody/tr/td[5]/input"))
					.sendKeys(properties.getProperty("TotalQuantityRequired"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Quantity ", sno, false);
			Thread.sleep(2000);
			sno++;
			Select uom11 = new Select(driver.findElement(
					By.xpath("//*[@id=\"addingChemicalsSolutionPreparation\"]/div/table/tbody/tr/td[6]/select")));
			Thread.sleep(2000);
			uom11.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor js11 = (JavascriptExecutor) driver;
			js11.executeScript("window.scrollBy(0,350)", "");
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"addingReagentsIndicBuffersPreparation\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Reagents", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("reagentSelSearchBtn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#reagentInSelectWndwTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(2000);
			int count21 = 0;
			boolean isRecordSelected21 = false;
			String name21 = properties.getProperty("SolutionName");
			isRecordSelected21 = selectRegents(count21, isRecordSelected21, name21);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selectBtnInReagentWin")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"addingReagentsIndicBuffersPreparation\"]/div/table/tbody/tr/td[3]/input"))
					.sendKeys(properties.getProperty("BatchNumber"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batch No/Solution NO ", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"addingReagentsIndicBuffersPreparation\"]/div/table/tbody/tr/td[6]/input"))
					.sendKeys(properties.getProperty("TotalQuantityRequired"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Quantity ", sno, false);
			Thread.sleep(2000);
			sno++;
			Select uom111 = new Select(driver.findElement(
					By.xpath("//*[@id=\"addingReagentsIndicBuffersPreparation\"]/div/table/tbody/tr/td[7]/select")));
			Thread.sleep(2000);
			uom111.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor js111 = (JavascriptExecutor) driver;
			js111.executeScript("window.scrollBy(0,350)", "");
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"addingInstrumentSolutionPreparation\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Click on Add Instrument used for Solution Preparation", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("instrumentSelSearchBtn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#instrumentDetailsInSelectWndwTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(2000);
			int count211 = 0;
			boolean isRecordSelected211 = false;
			String name211 = properties.getProperty("InstrumentName");
			isRecordSelected211 = selectInstrument(count211, isRecordSelected211, name211);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selectBtnInInstrumentWin")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor js1111 = (JavascriptExecutor) driver;
			js1111.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(2000);
			driver.findElement(By.linkText("Finish")).click();
			Thread.sleep(3000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInVmsWnd")).sendKeys(properties.getProperty("Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse11520 = (JavascriptExecutor) driver;
			WebElement element11520 = driver.findElement(By.id("subBtnInValidateESignVms"));
			jse11520.executeScript("arguments[0].click();", element11520);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
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

	private boolean selectInstrument(int count211, boolean isRecordSelected211, String name211) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("instrumentDetailsInSelectWndwTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(
							By.xpath("//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count211 == 0) {
			if ((totalNoOfRecords > 1) && ((name211 == null) || ("".equalsIgnoreCase(name211)))) {
				name211 = driver
						.findElement(By.xpath(
								"//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name211 == null) || ("".equalsIgnoreCase(name211))) {
				name211 = driver
						.findElement(By.xpath(
								"//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();// document
									// type
			}
			++count211;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[ "
										+ i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name211.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[ " + i
											+ " ]/td[2]"))
									.click();
							isRecordSelected211 = true;
							break;
						}
					}
					if (isRecordSelected211) {
						break;
					}
				} else {
					String DevNumberSequence = driver.findElement(By
							.xpath("//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name211.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelected211 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected211) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#instrumentDetailsInSelectWndwTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("instrumentDetailsInSelectWndwTableContainer"));// Document Tree
					// approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected211;
	}

	private boolean selectRegents(int count21, boolean isRecordSelected21, String name21) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("reagentInSelectWndwTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"reagentInSelectWndwTableContainer\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count21 == 0) {
			if ((totalNoOfRecords > 1) && ((name21 == null) || ("".equalsIgnoreCase(name21)))) {
				name21 = driver
						.findElement(
								By.xpath("//*[@id=\"reagentInSelectWndwTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name21 == null) || ("".equalsIgnoreCase(name21))) {
				name21 = driver
						.findElement(
								By.xpath("//*[@id=\"reagentInSelectWndwTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();// document
									// type
			}
			++count21;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"reagentInSelectWndwTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name21.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"reagentInSelectWndwTableContainer\"]/div/table/tbody/tr[ " + i
											+ " ]/td[2]"))
									.click();
							isRecordSelected21 = true;
							break;
						}
					}
					if (isRecordSelected21) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(
									By.xpath("//*[@id=\"reagentInSelectWndwTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name21.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"reagentInSelectWndwTableContainer\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelected21 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected21) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#reagentInSelectWndwTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("reagentInSelectWndwTableContainer"));// Document Tree
					// approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected21;
	}

	private boolean selectChemical(int count2, boolean isRecordSelected2, String name2) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("chemicalNameDetailsInSelectWndwTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By
							.xpath("//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
			if ((totalNoOfRecords > 1) && ((name2 == null) || ("".equalsIgnoreCase(name2)))) {
				name2 = driver.findElement(By.xpath(
						"//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name2 == null) || ("".equalsIgnoreCase(name2))) {
				name2 = driver
						.findElement(By.xpath(
								"//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// document
									// type
			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By
								.xpath("//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[ "
										+ i + " ]/td[3]"))
								.getText();// documentTypeName
						if (name2.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[ "
											+ i + " ]/td[3]"))
									.click();
							isRecordSelected2 = true;
							break;
						}
					}
					if (isRecordSelected2) {
						break;
					}
				} else {
					String DevNumberSequence = driver.findElement(By.xpath(
							"//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name2.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected2 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected2) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#chemicalNameDetailsInSelectWndwTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("chemicalNameDetailsInSelectWndwTableContainer"));// Document Tree
					// approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected2;
	}

	private boolean selectRecord(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("solutionPreparationTableCreate"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/div[4]/div[2]/span"))
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
			if ((totalNoOfRecords > 1) && ((name1 == null) || ("".equalsIgnoreCase(name1)))) {
				name1 = driver
						.findElement(
								By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver
						.findElement(By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr/td[4]"))
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
								"//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr[ " + i
											+ " ]/td[4]"))
									.click();
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
									By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr/td[4]"))
								.click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#solutionPreparationTableCreate > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("solutionPreparationTableCreate"));// Document Tree
																						// approve table
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
