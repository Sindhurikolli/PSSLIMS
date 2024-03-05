package com.pss.lims.ChemicalManagement.DestroyRejectionFlow;

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
import com.pss.lims.CM.CMLoginDetails.LoginDetails;
import com.pss.lims.ExtentTestNGPkg.Utility;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;

public class RejectedChemicalLot extends LoginDetails {

	@Test
	public void chemicalLotRejected() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ReinitiateRejectedChemicalLot"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Reinitiate RejectedChemical Lot", "LIMS-CM-007",
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='chemicalLotRegistrationForm.do'")));
		driver.findElement(By.cssSelector("a[href='chemicalLotRegistrationForm.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Chemical Lot", sno, false);
		Thread.sleep(4000);
		methodForRejectedChemicalLot();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
	}

	private void methodForRejectedChemicalLot() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 200);
		sno++;
		driver.findElement(By.id("rejectRadioBtnInChemLotRegForm")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
				"Click on Reverted Chemical Lots by Approver", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#rejectedChemLotDetailsGrid > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(2000);
		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("Name");
		isRecordSelected = selectRejectedRecord(count, isRecordSelected, name);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
//			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
//			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse2 = (JavascriptExecutor) driver;
			WebElement element2 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
			Thread.sleep(1000);
			jse2.executeScript("arguments[0].click();", element2);
			Thread.sleep(3000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("chemLotNumInChemLotRegForm")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Chemical Lot Number", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("chemLotNumInChemLotRegForm"))
					.sendKeys(properties.getProperty("ChemicalLotNumber"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Chemical Lot Number", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("erpSapCodeInChemLotRegForm")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear ERP/SAP Code", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("erpSapCodeInChemLotRegForm")).sendKeys(properties.getProperty("ERP_SAP_Code"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter ERP/SAP Code", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("noOfContainersInChemLotRegForm")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear No.of Containers", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("noOfContainersInChemLotRegForm"))
					.sendKeys(properties.getProperty("No.ofContainers"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Containers", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("qtyOfEachContainerInChemLotRegForm")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Quality Of Each Container", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("qtyOfEachContainerInChemLotRegForm"))
					.sendKeys(properties.getProperty("QuantityOfEachContainer"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Quality Of Each Container", sno,
					false);
			Thread.sleep(2000);
			Select uom1 = new Select(driver.findElement(By.id("qtyOfEachContainerUOMInChemLotRegForm")));
			Thread.sleep(1000);
			uom1.selectByIndex(2);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element5 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
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

	private boolean selectRejectedRecord(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("rejectedChemLotDetailsGrid"));
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
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
				name = driver
						.findElement(By.xpath("//*[@id=\"rejectedChemLotDetailsGrid\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"rejectedChemLotDetailsGrid\"]/div/table/tbody/tr/td[5]"))
						.getText();// document
									// type
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By
								.xpath("//*[@id=\"rejectedChemLotDetailsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
								.getText();// documentTypeName
						if (name.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"rejectedChemLotDetailsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
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
							.findElement(By.xpath("//*[@id=\"rejectedChemLotDetailsGrid\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (name.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"rejectedChemLotDetailsGrid\"]/div/table/tbody/tr/td[5]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#rejectedChemLotDetailsGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("rejectedChemLotDetailsGrid"));// Document Tree approve table
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