package com.pss.lims.sm.InternalForwardFlow;

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
import com.pss.lims.login.SMLoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;

public class QualitativeExternalJobs extends SMLoginDetails {

	@Test
	public void externalJobs() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ExternalJobs" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("External Jobs", "LIMS-SM-096", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Analyst"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("limsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("Lims_Module_Name1"));
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id='loginform']/div[4]/button[1]")).click();
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='externalJobsPageInSample.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='externalJobsPageInSample.do']"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on External Jobs", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#limsExtJobsGrid > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		methodForExternalJobs();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodForExternalJobs() throws Exception {

		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String arNumber = properties.getProperty("AR_Number");
		isRecordSelected = selectRecordForWorkSheetPrint(count, isRecordSelected, arNumber);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("viewBtnInExtJobs")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on View", sno, false);
			Thread.sleep(3000);
			sno++;
			Select re = new Select(driver.findElement(By.id("resultInQualJobResult")));
			Thread.sleep(1000);
			re.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Result", sno, false);
			Thread.sleep(2000);
			int noofVariables = Integer.parseInt(properties.getProperty("Qualitative_Test_No_of_variables"));
			String[] Variables = properties.getProperty("FinalValue").split(",");
			for (int i = 1; i <= noofVariables; i++) {
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"viewVariableForQualitativeForm\"]/div[" + (i + 65)
								+ "]/div/div/div[2]/div/textarea"))
						.sendKeys(Variables[i - 1]);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Final Value", sno, false);
				Thread.sleep(2000);
			}
			sno++;
			driver.findElement(By.id("uploadBtnInQualitativeForm")).sendKeys(properties.getProperty("Document-2"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload Document", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("passLimitDescriptionInQualJobResult"))
					.sendKeys(properties.getProperty("Description"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Pass Limit Description", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("commentsInQualJobResult")).sendKeys(properties.getProperty("Description"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
//			Thread.sleep(2000);
//			driver.findElement(By.id("submitBtnInQualTestInJobResults")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.id("submitBtnInQualTestInJobResults")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.id("submitBtnInQualTestInJobResults")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.id("submitBtnInQualTestInJobResults")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.id("submitBtnInQualTestInJobResults")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.id("submitBtnInQualTestInJobResults")).click();
			Thread.sleep(2000);
//			driver.findElement(By.id("submitBtnInMultStatTestInJobResults")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.id("submitBtnInMultStatTestInJobResults")).click();
//			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("submitBtnInQualTestInJobResults")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(2000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
			Thread.sleep(2000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			WebDriverWait wait1 = new WebDriverWait(driver, 70);
			wait1.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).isDisplayed()) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			}
			Thread.sleep(3000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Yes,Sure!", sno, false);
			driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a[1]")).click();
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

	private boolean selectRecordForWorkSheetPrint(int count, boolean isRecordSelected, String arNumber)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("limsExtJobsGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"limsExtJobsGrid\"]/div/div[4]/div[2]/span")).getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((arNumber == null) || ("".equalsIgnoreCase(arNumber)))) {
				arNumber = driver.findElement(By.xpath("//*[@id=\"limsExtJobsGrid\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((arNumber == null) || ("".equalsIgnoreCase(arNumber))) {
				arNumber = driver.findElement(By.xpath("//*[@id=\"limsExtJobsGrid\"]/div/table/tbody/tr/td[5]"))
						.getText();
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String arNumberSequence = driver
								.findElement(
										By.xpath("//*[@id=\"limsExtJobsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
								.getText();// documentTypeName
						if (arNumber.equalsIgnoreCase(arNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"limsExtJobsGrid\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String arNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"limsExtJobsGrid\"]/div/table/tbody/tr/td[5]")).getText();
					if (arNumber.equalsIgnoreCase(arNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"limsExtJobsGrid\"]/div/table/tbody/tr/td[5]")).click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#limsExtJobsGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("limsExtJobsGrid"));// Document Tree approve table
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
