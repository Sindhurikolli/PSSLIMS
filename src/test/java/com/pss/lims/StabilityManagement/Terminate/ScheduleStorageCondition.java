package com.pss.lims.StabilityManagement.Terminate;

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
import com.pss.lims.Satbility.Login.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class ScheduleStorageCondition extends LoginDetails {

	@Test
	public void terminateScheduleStorageConditionDeactivitaed() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "TerminateScheduleStorageCondition"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Terminate Schedule Storage Conditi...", "LIMS-SM-004",
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
		Thread.sleep(3000);
		sno++;
		WebDriverWait wiat = new WebDriverWait(driver, 240);
		wiat.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='terminatedStorageConditionsForm.do'")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = driver.findElement(By.cssSelector("a[href='terminatedStorageConditionsForm.do'"));
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ele);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Schedule Storage Conditi...", sno,
				false);
		Thread.sleep(4000);
		methodToterminateScheduleStorageConditionDeactivitaed();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToterminateScheduleStorageConditionDeactivitaed() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		sno++;
		driver.findElement(By.id("terminateActionInTermntStrgCond")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Terminate", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("searchBtnInTermntStrgCondSearch")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("#resultsTableInTermntStrgCond > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(3000);
		JavascriptExecutor jse12 = (JavascriptExecutor) driver;
		WebElement element12 = driver
				.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
		jse12.executeScript("arguments[0].scrollIntoView(true);", element12);
		Thread.sleep(2000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("Protocol_Number");
		isRecordSelected1 = selectRecordForStorage(count1, isRecordSelected1, name1);
		if (isRecordSelected1) {
			Thread.sleep(1000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
//			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
			Thread.sleep(3000);
			sno++;
			jse12.executeScript("arguments[0].click();", element12);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#tableForSelectedResultInTermntStrgCondWindow > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(5000);
			int count2 = 0;
			boolean isRecordSelected2 = false;
			String name2 = properties.getProperty("packingType");
			isRecordSelected2 = selectRecordForStorageCondition(count2, isRecordSelected2, name2);
			if (isRecordSelected2) {
				Thread.sleep(1000);
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
				Thread.sleep(2000);
				sno++;
				JavascriptExecutor jse11 = (JavascriptExecutor) driver;
				WebElement element11 = driver.findElement(By.id("apprFromSelectBtnInTermntStrgCond"));
				jse11.executeScript("arguments[0].click();", element11);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(5000);
				sno++;
				JavascriptExecutor jse10 = (JavascriptExecutor) driver;
				WebElement element10 = driver.findElement(By.id("locTreeInCalPmBdm_2_switch"));
				jse10.executeScript("arguments[0].click();", element10);
				Thread.sleep(3000);
				driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
				Thread.sleep(3000);
				int count = 0;
				boolean isRecordSelected = false;
				String selectingUserSingleApproval = properties.getProperty("LastName");
				isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval,
						isRecordSelected, count);
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
				Thread.sleep(2000);
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
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
				Thread.sleep(1000);
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
				System.out.println("Packing type is not Selected");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecordForStorageCondition(int count2, boolean isRecordSelected2, String name2)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("tableForSelectedResultInTermntStrgCondWindow"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By
							.xpath("//*[@id=\"tableForSelectedResultInTermntStrgCondWindow\"]/div/div[4]/div[2]/span"))
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
				name2 = driver.findElement(By
						.xpath("//*[@id=\"tableForSelectedResultInTermntStrgCondWindow\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name2 == null) || ("".equalsIgnoreCase(name2))) {
				name2 = driver
						.findElement(By.xpath(
								"//*[@id=\"tableForSelectedResultInTermntStrgCondWindow\"]/div/table/tbody/tr/td[2]"))
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
								.xpath("//*[@id=\"tableForSelectedResultInTermntStrgCondWindow\"]/div/table/tbody/tr[ "
										+ i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name2.equalsIgnoreCase(DevNumberSequence)) {
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(By.xpath(
									"//*[@id=\"tableForSelectedResultInTermntStrgCondWindow\"]/div/table/tbody/tr[" + i
											+ "]/td[2]"));
							jse8.executeScript("arguments[0].click();", element8);
							Thread.sleep(4000);
							isRecordSelected2 = true;
							break;
						}
					}
					if (isRecordSelected2) {
						break;
					}
				} else {
					String DevNumberSequence = driver.findElement(By.xpath(
							"//*[@id=\"tableForSelectedResultInTermntStrgCondWindow\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name2.equalsIgnoreCase(DevNumberSequence)) {
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver.findElement(By.xpath(
								"//*[@id=\"tableForSelectedResultInTermntStrgCondWindow\"]/div/table/tbody/tr/td[2]"));
						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected2 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected2) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#tableForSelectedResultInTermntStrgCondWindow > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("tableForSelectedResultInTermntStrgCondWindow"));// Document Tree
																										// approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected2;
	}

	private boolean selectRecordForStorage(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("resultsTableInTermntStrgCond"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"resultsTableInTermntStrgCond\"]/div/div[4]/div[2]/span"))
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
						.findElement(By.xpath("//*[@id=\"resultsTableInTermntStrgCond\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver
						.findElement(By.xpath("//*[@id=\"resultsTableInTermntStrgCond\"]/div/table/tbody/tr/td[2]"))
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
								"//*[@id=\"resultsTableInTermntStrgCond\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"resultsTableInTermntStrgCond\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
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
							.findElement(By.xpath("//*[@id=\"resultsTableInTermntStrgCond\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"resultsTableInTermntStrgCond\"]/div/table/tbody/tr/td[2]")).click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#resultsTableInTermntStrgCond > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("resultsTableInTermntStrgCond"));// Document Tree approve
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
