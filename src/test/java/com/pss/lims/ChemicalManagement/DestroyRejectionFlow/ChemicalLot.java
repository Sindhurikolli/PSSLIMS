package com.pss.lims.ChemicalManagement.DestroyRejectionFlow;

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
import com.pss.lims.CM.CMLoginDetails.LoginDetails;
import com.pss.lims.ExtentTestNGPkg.Utility;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class ChemicalLot extends LoginDetails {

	@Test
	public void createChemicalLot() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateChemicalLot" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Chemical Lot Create", "PSS-CM-003", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='chemicalLotRegistrationForm.do'")));
		driver.findElement(By.cssSelector("a[href='chemicalLotRegistrationForm.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Chemical Lot", sno, false);
		Thread.sleep(4000);
		methodToCreateChemicalLot();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreateChemicalLot() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 200);
//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
		Thread.sleep(2000);
		jse2.executeScript("arguments[0].click();", element2);
		Thread.sleep(3000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(4000);
		sno++;
		JavascriptExecutor jse12 = (JavascriptExecutor) driver;
		WebElement element12 = driver.findElement(By.id("ChemSelectBtnInChemLotRegForm"));
		jse12.executeScript("arguments[0].click();", element12);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(4000);
		sno++;
		JavascriptExecutor jse20 = (JavascriptExecutor) driver;
		WebElement element20 = driver.findElement(By.id("srchBtnInChemSelectWnd"));
		jse20.executeScript("arguments[0].click();", element20);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#chemNameDtlsGrid > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(2000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("Name");
		isRecordSelected1 = selectRecord(count1, isRecordSelected1, name1);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			JavascriptExecutor jse120 = (JavascriptExecutor) driver;
			WebElement element120 = driver.findElement(By.id("chemSelBtnInChemSelectWnd"));
			jse120.executeScript("arguments[0].click();", element120);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("chemLotNumInChemLotRegForm"))
					.sendKeys(properties.getProperty("ChemicalLotNumber"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Chemical Lot Number", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("erpSapCodeInChemLotRegForm")).sendKeys(properties.getProperty("ERP_SAP_Code"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter ERP/SAP Code", sno, false);
			Thread.sleep(2000);
			WebElement element = driver.findElement(By.id("receivedOnInChemLotRegForm"));
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", element);
			sno++;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			driver.findElement(By.id("receivedOnInChemLotRegForm")).sendKeys(sdf.format(date));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click  Received On", sno, false);
			Thread.sleep(2000);
			driver.findElement(By.id("erpSapCodeInChemLotRegForm")).click();
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse152 = (JavascriptExecutor) driver;
			WebElement element152 = driver.findElement(By.id("recievedByUsrSelBtnInChemLotRegForm"));
			jse152.executeScript("arguments[0].click();", element152);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse112 = (JavascriptExecutor) driver;
			WebElement element112 = driver.findElement(By.id("locTreeInCalPmBdm_2_switch"));
			jse112.executeScript("arguments[0].click();", element112);
			Thread.sleep(3000);
			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(4000);
			int count = 0;
			boolean isRecordSelected = false;
			String selectingUserSingleApproval = properties.getProperty("LastName");
			isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval,
					isRecordSelected, count);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			sno++;
			JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
			WebElement element1112 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse1112.executeScript("arguments[0].click();", element1112);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse1152 = (JavascriptExecutor) driver;
			WebElement element1152 = driver.findElement(By.id("manufactSelBtnInChemLotRegForm"));
			jse1152.executeScript("arguments[0].click();", element1152);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse11512 = (JavascriptExecutor) driver;
			WebElement element1012 = driver.findElement(By.id("srchBtnInManufactSelWndSrch"));
			jse11512.executeScript("arguments[0].click();", element1012);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#manufactDetailsGrid > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(2000);
			int count2 = 0;
			boolean isRecordSelected2 = false;
			String name2 = properties.getProperty("ManufacturerName");
			isRecordSelected2 = SelectRecordForDetails(count2, isRecordSelected2, name2);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			sno++;
			JavascriptExecutor jse512 = (JavascriptExecutor) driver;
			WebElement element512 = driver.findElement(By.id("selectBtnInManufactSelWndSrch"));
			jse512.executeScript("arguments[0].click();", element512);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse52 = (JavascriptExecutor) driver;
			WebElement element52 = driver.findElement(By.id("supplierSelBtnInChemLotRegForm"));
			jse52.executeScript("arguments[0].click();", element52);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor js12 = (JavascriptExecutor) driver;
			WebElement elemen12 = driver.findElement(By.id("srchBtnInSupplierSelWndSrch"));
			js12.executeScript("arguments[0].click();", elemen12);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#supplierDetailsGrid > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(2000);
			int count3 = 0;
			boolean isRecorSelected3 = false;
			String name3 = properties.getProperty("SupplierName");
			isRecorSelected3 = selectSuppliedBy(count3, isRecorSelected3, name3);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			sno++;
			JavascriptExecutor jse5102 = (JavascriptExecutor) driver;
			WebElement element5102 = driver.findElement(By.id("selectBtnInSupplierSelWndSrch"));
			jse5102.executeScript("arguments[0].click();", element5102);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			Select uom = new Select(driver.findElement(By.id("storageLocInChemLotRegForm")));
			Thread.sleep(1000);
			uom.selectByVisibleText(properties.getProperty("Storage_Location"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Storage Location", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("noOfContainersInChemLotRegForm"))
					.sendKeys(properties.getProperty("No.ofContainers"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Containers", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("qtyOfEachContainerInChemLotRegForm"))
					.sendKeys(properties.getProperty("QuantityOfEachContainer"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Quality Of Each Container", sno,
					false);
			Thread.sleep(2000);
			Select uom1 = new Select(driver.findElement(By.id("qtyOfEachContainerUOMInChemLotRegForm")));
			Thread.sleep(1000);
			uom1.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			WebElement element1 = driver.findElement(By.id("expryDateInChemLotRegForm"));
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')",
					element1);
			sno++;
			driver.findElement(By.id("expryDateInChemLotRegForm")).sendKeys(properties.getProperty("ExpiryDate"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Expiry Date", sno, false);
			Thread.sleep(2000);
			driver.findElement(By.id("erpSapCodeInChemLotRegForm")).click();
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse121 = (JavascriptExecutor) driver;
			WebElement element121 = driver.findElement(By.id("apprSelBtnInChemLotRegForm"));
			jse121.executeScript("arguments[0].click();", element121);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse1121 = (JavascriptExecutor) driver;
			WebElement element1121 = driver.findElement(By.id("locTreeInCalPmBdm_2_switch"));
			jse1121.executeScript("arguments[0].click();", element1121);
			Thread.sleep(3000);
			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(2000);
			int count4 = 0;
			boolean isRecordSelected4 = false;
			String name4 = properties.getProperty("LastName");
			isRecordSelected4 = selectUser(count4, isRecordSelected4, name4);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			sno++;
			JavascriptExecutor jse02 = (JavascriptExecutor) driver;
			WebElement element02 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse02.executeScript("arguments[0].click();", element02);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element5 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element5);
			sno++;
			driver.findElement(By.id("commentsInChemLotRegForm")).sendKeys(properties.getProperty("Comments"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
			Thread.sleep(2000);
			sno++;
			jse.executeScript("arguments[0].scrollIntoView(true);", element5);
			Thread.sleep(1000);
			jse.executeScript("arguments[0].click();", element5);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor j1se = (JavascriptExecutor) driver;
			WebElement e1lement = driver.findElement(By.id("subBtnInValidateESign"));
			j1se.executeScript("arguments[0].click();", e1lement);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
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

	private boolean selectUser(int count4, boolean isRecordSelected4, String name4) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("usersTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count4 == 0) {
			if ((totalNoOfRecords > 1) && ((name4 == null) || ("".equalsIgnoreCase(name4)))) {
				name4 = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name4 == null) || ("".equalsIgnoreCase(name4))) {
				name4 = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();// document
									// type
			}
			++count4;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name4.equalsIgnoreCase(DevNumberSequence)) {
							JavascriptExecutor jse20 = (JavascriptExecutor) driver;
							WebElement element20 = driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"));
							jse20.executeScript("arguments[0].click();", element20);
							isRecordSelected4 = true;
							break;
						}
					}
					if (isRecordSelected4) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name4.equalsIgnoreCase(DevNumberSequence)) {
						JavascriptExecutor jse20 = (JavascriptExecutor) driver;
						WebElement element20 = driver
								.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]"));
						jse20.executeScript("arguments[0].click();", element20);
						isRecordSelected4 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected4) && (noOfRecordsChecked < totalNoOfRecords)) {
					JavascriptExecutor jse20 = (JavascriptExecutor) driver;
					WebElement element20 = driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
					jse20.executeScript("arguments[0].click();", element20);
					// next page in Document approve list
					Thread.sleep(7000);
					table = driver.findElement(By.id("usersTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected4;
	}

	private boolean selectSuppliedBy(int count3, boolean isRecorSelected3, String name3) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("supplierDetailsGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver
//					.findElement(By.xpath("//*[@id=\"masterOfChemicalApprRecordsGrid\"]/div/div[4]/div[2]/span"))
//					.getText();// For
//			// Ex:
//			// Showing
//			// 1-1
//			// of
//			// 1
//			String[] parts = a.split(" of ");
//			try {
//				totalNoOfRecords = Integer.parseInt(parts[1].trim());
//				System.out.println(totalNoOfRecords);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
		if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
			if ((totalNoOfRecords > 1) && ((name3 == null) || ("".equalsIgnoreCase(name3)))) {
				name3 = driver.findElement(By.xpath("//*[@id=\"supplierDetailsGrid\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name3 == null) || ("".equalsIgnoreCase(name3))) {
				name3 = driver.findElement(By.xpath("//*[@id=\"supplierDetailsGrid\"]/div/table/tbody/tr/td[3]"))
						.getText();// document
									// type
			}
			++count3;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"supplierDetailsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (name3.equalsIgnoreCase(DevNumberSequence)) {
							JavascriptExecutor jse20 = (JavascriptExecutor) driver;
							WebElement element20 = driver.findElement(
									By.xpath("//*[@id=\"supplierDetailsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[3]"));
							jse20.executeScript("arguments[0].click();", element20);
							isRecorSelected3 = true;
							break;
						}
					}
					if (isRecorSelected3) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"supplierDetailsGrid\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name3.equalsIgnoreCase(DevNumberSequence)) {
						JavascriptExecutor jse20 = (JavascriptExecutor) driver;
						WebElement element20 = driver
								.findElement(By.xpath("//*[@id=\"supplierDetailsGrid\"]/div/table/tbody/tr/td[3]"));
						jse20.executeScript("arguments[0].click();", element20);
						isRecorSelected3 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecorSelected3) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"##supplierDetailsGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-last.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("supplierDetailsGrid"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecorSelected3;
	}

	private boolean SelectRecordForDetails(int count2, boolean isRecordSelected2, String name2) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("manufactDetailsGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver
//					.findElement(By.xpath("//*[@id=\"masterOfChemicalApprRecordsGrid\"]/div/div[4]/div[2]/span"))
//					.getText();// For
//			// Ex:
//			// Showing
//			// 1-1
//			// of
//			// 1
//			String[] parts = a.split(" of ");
//			try {
//				totalNoOfRecords = Integer.parseInt(parts[1].trim());
//				System.out.println(totalNoOfRecords);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
			if ((totalNoOfRecords > 1) && ((name2 == null) || ("".equalsIgnoreCase(name2)))) {
				name2 = driver.findElement(By.xpath("//*[@id=\"manufactDetailsGrid\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name2 == null) || ("".equalsIgnoreCase(name2))) {
				name2 = driver.findElement(By.xpath("//*[@id=\"manufactDetailsGrid\"]/div/table/tbody/tr/td[3]"))
						.getText();// document
									// type
			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"manufactDetailsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (name2.equalsIgnoreCase(DevNumberSequence)) {
							JavascriptExecutor jse20 = (JavascriptExecutor) driver;
							WebElement element20 = driver.findElement(
									By.xpath("//*[@id=\"manufactDetailsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[3]"));
							jse20.executeScript("arguments[0].click();", element20);
							isRecordSelected2 = true;
							break;
						}
					}
					if (isRecordSelected2) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"manufactDetailsGrid\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name2.equalsIgnoreCase(DevNumberSequence)) {
						JavascriptExecutor jse20 = (JavascriptExecutor) driver;
						WebElement element20 = driver
								.findElement(By.xpath("//*[@id=\"manufactDetailsGrid\"]/div/table/tbody/tr/td[3]"));
						jse20.executeScript("arguments[0].click();", element20);
						isRecordSelected2 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected2) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"##manufactDetailsGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-last.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("manufactDetailsGrid"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected2;
	}

	private boolean selectRecord(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("chemNameDtlsGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver
//					.findElement(By.xpath("//*[@id=\"masterOfChemicalApprRecordsGrid\"]/div/div[4]/div[2]/span"))
//					.getText();// For
//			// Ex:
//			// Showing
//			// 1-1
//			// of
//			// 1
//			String[] parts = a.split(" of ");
//			try {
//				totalNoOfRecords = Integer.parseInt(parts[1].trim());
//				System.out.println(totalNoOfRecords);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((name1 == null) || ("".equalsIgnoreCase(name1)))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"chemNameDtlsGrid\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"chemNameDtlsGrid\"]/div/table/tbody/tr/td[3]"))
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
								.findElement(
										By.xpath("//*[@id=\"chemNameDtlsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							JavascriptExecutor jse20 = (JavascriptExecutor) driver;
							WebElement element20 = driver.findElement(
									By.xpath("//*[@id=\"chemNameDtlsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[3]"));
							jse20.executeScript("arguments[0].click();", element20);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"chemNameDtlsGrid\"]/div/table/tbody/tr/td[3]")).getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
						JavascriptExecutor jse20 = (JavascriptExecutor) driver;
						WebElement element20 = driver
								.findElement(By.xpath("//*[@id=\"chemNameDtlsGrid\"]/div/table/tbody/tr/td[3]"));
						jse20.executeScript("arguments[0].click();", element20);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"##chemNameDtlsGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-last.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("chemNameDtlsGrid"));// Document Tree approve table
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
