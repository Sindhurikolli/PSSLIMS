package com.pss.lims.IM.PlannedScheduleRejectionFlow;

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
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.lims.IM.Login.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;

public class CalibrationResultsRecheck extends LoginDetails {

	@Test
	public void recheck() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QAReviewReCheckResults"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Qc Review of Pass Result Re Check", "PSS-LIMS-015",
				"Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Approver_Login"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("limsModule")));
		Thread.sleep(1000);
		module.selectByIndex(8);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno + "." + "Enter the username, password, Select Module and click on login button"
				+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.xpath("//*[@id=\"imResultReviewSectionBody\"]/a[1]"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		sno++;
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Qc Review of Pass Result", sno,
				false);
		Thread.sleep(4000);
		methodForRecheck();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodForRecheck() throws Exception {

		int count = 0;
		boolean isRecordSelected = false;
		String name = properties.getProperty("Instrument_Id");
		isRecordSelected = selectRecordForCalibration(count, isRecordSelected, name);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
//			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
//			Thread.sleep(5000);
			sno++;
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			WebElement element1 = driver
					.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
			jse1.executeScript("arguments[0].click();", element1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			JavascriptExecutor jse11 = (JavascriptExecutor) driver;
			WebElement element11 = driver.findElement(By.id("recheckActionInQCPassReviewId"));
			jse11.executeScript("arguments[0].scrollIntoView(true);", element11);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse31 = (JavascriptExecutor) driver;
			WebElement element31 = driver.findElement(By.id("recheckActionInQCPassReviewId"));
			jse31.executeScript("arguments[0].click();", element31);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Re Check", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("commentsInViewCalibrationSchedulePassedResultsFormForQCReview"))
					.sendKeys(properties.getProperty("comments_200"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse42110 = (JavascriptExecutor) driver;
			WebElement element42110 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
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
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
			JavascriptExecutor jse8 = (JavascriptExecutor) driver;
			WebElement element8 = driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a"));
			Thread.sleep(2000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK", sno, false);
			jse8.executeScript("arguments[0].click();", element8);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector(
					"body > div.container > header > nav > ul.menu.pull-right > li:nth-child(7) > ul > li:nth-child(2) > a"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordForCalibration(int count, boolean isRecordSelected, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("imsQCReviewOfSchedulePassedResultsForm"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"imsQCReviewOfSchedulePassedResultsForm\"]/div/div[4]/div[2]/span"))
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
//			System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
//				System.out.println("hi this is ravi");
				name = driver
						.findElement(By.xpath(
								"//*[@id=\"imsQCReviewOfSchedulePassedResultsForm\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver
						.findElement(By
								.xpath("//*[@id=\"imsQCReviewOfSchedulePassedResultsForm\"]/div/table/tbody/tr/td[3]"))
						.getText();// document
									// type
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"imsQCReviewOfSchedulePassedResultsForm\"]/div/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (name.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"imsQCReviewOfSchedulePassedResultsForm\"]/div/table/tbody/tr[ "
											+ i + " ]/td[3]"))
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
							.findElement(By.xpath(
									"//*[@id=\"imsQCReviewOfSchedulePassedResultsForm\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By
								.xpath("//*[@id=\"imsQCReviewOfSchedulePassedResultsForm\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#imsQCReviewOfSchedulePassedResultsForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next.jtable-page-number-disabled"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("imsQCReviewOfSchedulePassedResultsForm"));// Document Tree approve
																								// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}
}
