package com.pss.lims.StabilityManagementWithSampleManagementQuantitativeInternalFlow;

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
import com.pss.lims.util.Utilities;

public class StorageCondition extends LoginDetails {

	@Test
	public void createStorageCondition() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "StorageCondition" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Storage Condition", "LIMS-SM-006", "Pass");
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
		WebDriverWait wiat = new WebDriverWait(driver, 240);
		wiat.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("a[href='createScheduleStorageConditionsForm.do'")));
		driver.findElement(By.cssSelector("a[href='createScheduleStorageConditionsForm.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Storage Condition", sno, false);
		wiat.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#scheduleStorageConditionsTable > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		methodToCreateStorageCondition();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreateStorageCondition() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("Product_name");
		isRecordSelected = selectRecordForStorage(count, isRecordSelected, name);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("noOfRowsInScheduleStorageConditionsForm"))
					.sendKeys(properties.getProperty("NoofRowsStorageCondition"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Rows", sno, false);
			Thread.sleep(3000);
//			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
//			Thread.sleep(3000);

			JavascriptExecutor jse12 = (JavascriptExecutor) driver;
			WebElement element12 = driver
					.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
			jse12.executeScript("arguments[0].scrollIntoView(true);", element12);
			Thread.sleep(1000);
			jse12.executeScript("arguments[0].click();", element12);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(5000);
			sno++;
			int NoofRowsStorageCondition = Integer.parseInt(properties.getProperty("NoofRowsStorageCondition"));
			for (int i = 1; i <= NoofRowsStorageCondition; i++) {
				Select type = new Select(driver.findElement(By
						.xpath("//*[@id=\"storageCondInPPEditorTable\"]/div/table/tbody/tr[" + i + "]/td[2]/select")));
				Thread.sleep(2000);
				type.selectByVisibleText(properties.getProperty("Packing_Type_Name"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Packing Type", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By
						.xpath("//*[@id=\"storageCondInPPEditorTable\"]/div/table/tbody/tr[" + i + "]/td[3]/textarea"))
						.sendKeys("544");
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batch NO", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By
						.xpath("//*[@id=\"storageCondInPPEditorTable\"]/div/table/tbody/tr[" + i + "]/td[4]/textarea"))
						.sendKeys("10");
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batch Size", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By
						.xpath("//*[@id=\"storageCondInPPEditorTable\"]/div/table/tbody/tr[" + i + "]/td[5]/textarea"))
						.sendKeys("23-01-2021");
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Mfg Date", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By
						.xpath("//*[@id=\"storageCondInPPEditorTable\"]/div/table/tbody/tr[" + i + "]/td[6]/textarea"))
						.sendKeys("23-11-2023");
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Expiry Date", sno, false);
				Thread.sleep(2000);
				sno++;
				Select condition = new Select(driver.findElement(By
						.xpath("//*[@id=\"storageCondInPPEditorTable\"]/div/table/tbody/tr[" + i + "]/td[7]/select")));
				Thread.sleep(2000);
				condition.selectByVisibleText(properties.getProperty("Storage_Condition_Name"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Storage Condition", sno,
						false);
				Thread.sleep(2000);
				sno++;
				Select schedule = new Select(driver.findElement(By
						.xpath("//*[@id=\"storageCondInPPEditorTable\"]/div/table/tbody/tr[" + i + "]/td[8]/select")));
				Thread.sleep(2000);
				schedule.selectByVisibleText(properties.getProperty("Interval_Type"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Schedule Type Interval", sno,
						false);
				Thread.sleep(2000);
				sno++;
				JavascriptExecutor jse10 = (JavascriptExecutor) driver;
				WebElement element10 = driver.findElement(By
						.xpath("//*[@id=\"storageCondInPPEditorTable\"]/div/table/tbody/tr[" + i + "]/td[10]/button"));
				jse10.executeScript("arguments[0].click();", element10);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on View", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("rackNumberInScheduleStorageConditionsForm")).sendKeys("44");
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Rack Number", sno, false);
				Thread.sleep(3000);
				sno++;
				JavascriptExecutor jse0 = (JavascriptExecutor) driver;
				WebElement element0 = driver.findElement(By.id("addRackToJtableInScheduleStorageConditionSForm"));
				jse0.executeScript("arguments[0].click();", element0);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
				Thread.sleep(5000);
				sno++;
				JavascriptExecutor jse01 = (JavascriptExecutor) driver;
				WebElement element01 = driver.findElement(By.id("selectInScheduleStorageConditionSForm"));
				jse01.executeScript("arguments[0].click();", element01);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(3000);
				sno++;
			}
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
			WebDriverWait wait = new WebDriverWait(driver, 90);
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

	private boolean selectRecordForStorage(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("scheduleStorageConditionsTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"scheduleStorageConditionsTable\"]/div/div[4]/div[2]/span"))
					.getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
//			System.out.println("hi:" + a);
			String[] parts = a.split(" of ");
//			System.out.println("parts:" + parts.toString());
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
								By.xpath("//*[@id=\"scheduleStorageConditionsTable\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver
						.findElement(By.xpath("//*[@id=\"scheduleStorageConditionsTable\"]/div/table/tbody/tr/td[4]"))
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
								"//*[@id=\"scheduleStorageConditionsTable\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();// documentTypeName
						if (name.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"scheduleStorageConditionsTable\"]/div/table/tbody/tr[ " + i
											+ " ]/td[4]"))
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
									By.xpath("//*[@id=\"scheduleStorageConditionsTable\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (name.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"scheduleStorageConditionsTable\"]/div/table/tbody/tr/td[4]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#scheduleStorageConditionsTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("scheduleStorageConditionsTable"));// Document Tree approve table
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
