package com.pss.lims.StabilityManagementWithSampleManagementQuantitativeInternalFlow;

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
import com.pss.lims.ExtentTestNGPkg.Utility;
import com.pss.lims.Satbility.Login.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class Loading extends LoginDetails {

	@Test
	public void createLoading() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateLoading" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Loading", "LIMS-SM-011", "Pass");
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
		WebDriverWait wiat = new WebDriverWait(driver, 240);
		wiat.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='loadingProtocol.do'")));
		driver.findElement(By.cssSelector("a[href='loadingProtocol.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Loading", sno, false);
		wiat.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#LoadingTable > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		methodToCreateLoading();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreateLoading() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("Product_name");
		isRecordSelected1 = selectRecordForLoading(count1, isRecordSelected1, name1);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
//			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
//			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse12 = (JavascriptExecutor) driver;
			WebElement element12 = driver
					.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
			jse12.executeScript("arguments[0].scrollIntoView(true);", element12);
			Thread.sleep(1000);
			jse12.executeScript("arguments[0].click();", element12);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"ProtocolRecordsTable\"]/div/table/tbody/tr/td[6]/input"))
					.sendKeys("2");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Analysis Quantity", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"ProtocolRecordsTable\"]/div/table/tbody/tr/td[7]/input"))
					.sendKeys("3");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reference Quantity", sno, false);
			Thread.sleep(2000);
			sno++;
			Select uom1 = new Select(
					driver.findElement(By.xpath("//*[starts-with(@id,'uomDrpDownInStabReqLoading')]")));
			Thread.sleep(2000);
			uom1.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"ProtocolRecordsTable\"]/div/table/tbody/tr/td[9]/input"))
					.sendKeys("3");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Analysis Quantity Count", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"ProtocolRecordsTable\"]/div/table/tbody/tr/td[10]/input"))
					.sendKeys("2");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reference Quantity Count", sno,
					false);
			Thread.sleep(2000);
			sno++;
			Select uom11 = new Select(
					driver.findElement(By.xpath("//*[starts-with(@id,'uomDrpDownCountInStabReqLoadin')]")));
			Thread.sleep(2000);
			uom11.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			WebElement element = driver
					.findElement(By.xpath("//*[@id=\"ProtocolRecordsTable\"]/div/table/tbody/tr/td[13]/input"));
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", element);
			Thread.sleep(2000);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"ProtocolRecordsTable\"]/div/table/tbody/tr/td[13]/input"))
					.sendKeys(sdf.format(date));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Start Date", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse31 = (JavascriptExecutor) driver;
			WebElement element31 = driver
					.findElement(By.xpath("//*[@id=\"ProtocolRecordsTable\"]/div/table/tbody/tr/td[14]/button"));
			jse31.executeScript("arguments[0].click();", element31);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(5000);
			sno++;
			Select chamber = new Select(driver.findElement(By.id("chamberNameInChamberRackWindw")));
			Thread.sleep(2000);
			chamber.selectByVisibleText(properties.getProperty("MasterChamber"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Chamber", sno, false);
			Thread.sleep(5000);
			sno++;
			JavascriptExecutor jse3111 = (JavascriptExecutor) driver;
			WebElement element3111 = driver.findElement(By.id("selectingRackNumber_1"));
			jse3111.executeScript("arguments[0].click();", element3111);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Check Box", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse10 = (JavascriptExecutor) driver;
			WebElement element10 = driver.findElement(By.id("selectionBtnInChamberRackerNumberWindow"));
			jse10.executeScript("arguments[0].click();", element10);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse110 = (JavascriptExecutor) driver;
			WebElement element110 = driver.findElement(By.id("selecttheApprovalInLoadingForm"));
			jse110.executeScript("arguments[0].click();", element110);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse1110 = (JavascriptExecutor) driver;
			WebElement element1110 = driver.findElement(By.id("locTreeInCalPmBdm_2_switch"));
			jse1110.executeScript("arguments[0].click();", element1110);
			Thread.sleep(3000);
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
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse4210 = (JavascriptExecutor) driver;
			WebElement element4210 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse4210.executeScript("arguments[0].click();", element4210);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse5110 = (JavascriptExecutor) driver;
			WebElement element5110 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse5110.executeScript("arguments[0].scrollIntoView(true);", element5110);
			Thread.sleep(1000);
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

	private boolean selectRecordForLoading(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("LoadingTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"LoadingTable\"]/div/div[4]/div[2]/span")).getText();// For
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
				name1 = driver.findElement(By.xpath("//*[@id=\"LoadingTable\"]/div/table/tbody/tr[1]/td[3]")).getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"LoadingTable\"]/div/table/tbody/tr/td[3]")).getText();// document
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
										By.xpath("//*[@id=\"LoadingTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"LoadingTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
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
							.findElement(By.xpath("//*[@id=\"LoadingTable\"]/div/table/tbody/tr/td[3]")).getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"LoadingTable\"]/div/table/tbody/tr/td[3]")).click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#LoadingTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("LoadingTable"));// Document Tree approve table
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
