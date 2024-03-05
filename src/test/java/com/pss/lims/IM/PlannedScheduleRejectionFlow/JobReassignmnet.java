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

public class JobReassignmnet extends LoginDetails {

	@Test
	public void reassignJob() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "JobReAssignment" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Job ReAssignment", "LIMS-IMS-013", "Pass");
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
		wiat.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='jobReAssgnmentIms.do'")));
		driver.findElement(By.cssSelector("a[href='jobReAssgnmentIms.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Job Reassignment", sno, false);
		Thread.sleep(4000);
		methodForReassignJob();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodForReassignJob() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 240);
		sno++;
		driver.findElement(By.id("searchBtnInJobReAssignmentForm")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#jobReAssignmentJTableInIms > div > div.jtable-busy-message[style='display: none;']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"jobReAssignmentJTableInIms\"]/div/div[4]/div[2]/span"));
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(3000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String test = properties.getProperty("ScheduleName");
		isRecordSelected1 = selectRecordForTest(count1, isRecordSelected1, test);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record and Click on View", sno,
					false);
			Thread.sleep(3000);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			WebElement ele1 = driver.findElement(By.id("selReAssignBtnInJobReAssignmentForm"));
			js1.executeScript("arguments[0].scrollIntoView(true);", ele1);
			Thread.sleep(2000);
			js1.executeScript("arguments[0].click();", ele1);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("locTreeInCalPmBdm_2_switch")).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(5000);
			int count = 0;
			boolean isRecordSelected = false;
			String selectingUserSingleApproval = properties.getProperty("Reviewer");
			isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval,
					isRecordSelected, count);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("submitBtnInJobReAssignmentForm")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
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

	private boolean selectRecordForTest(int count1, boolean isRecordSelected1, String test) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("jobReAssignmentJTableInIms"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"jobReAssignmentJTableInIms\"]/div/div[4]/div[2]/span"))
					.getText();// For
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
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((test == null) || ("".equalsIgnoreCase(test)))) {
				test = driver
						.findElement(By.xpath("//*[@id=\"jobReAssignmentJTableInIms\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();// documentType
			} else if ((test == null) || ("".equalsIgnoreCase(test))) {
				test = driver.findElement(By.xpath("//*[@id=\"jobReAssignmentJTableInIms\"]/div/table/tbody/tr/td[1]"))
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
								.xpath("//*[@id=\"jobReAssignmentJTableInIms\"]/div/table/tbody/tr[ " + i + " ]/td[1]"))
								.getText();// documentTypeName
						if (test.contains(DevNumberSequence)) {
//							driver.findElement(By.xpath(
//									"//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
//									.click();
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(By.xpath(
									"//*[@id=\"jobReAssignmentJTableInIms\"]/div/table/tbody/tr[" + i + "]/td[1]"));
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
							.findElement(By.xpath("//*[@id=\"jobReAssignmentJTableInIms\"]/div/table/tbody/tr/td[1]"))
							.getText();
					if (test.contains(DevNumberSequence)) {
//						driver.findElement(By.xpath("//*[@id=\"tableInInstSelectionWindow\"]/div/table/tbody/tr/td[4]"))
//								.click();
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver.findElement(
								By.xpath("//*[@id=\"jobReAssignmentJTableInIms\"]/div/table/tbody/tr/td[1]"));
						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
//					driver.findElement(By.cssSelector(
//							"#jobReAssignmentJTableInIms > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
//							.click();// next page in Document approve list
					JavascriptExecutor jse8 = (JavascriptExecutor) driver;
					WebElement element8 = driver.findElement(By.cssSelector(
							"#jobReAssignmentJTableInIms > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
					jse8.executeScript("arguments[0].scrollIntoView(true);", element8);
					Thread.sleep(2000);
					jse8.executeScript("arguments[0].click();", element8);
					Thread.sleep(4000);
					table = driver.findElement(By.id("jobReAssignmentJTableInIms"));// Document Tree approve table
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
