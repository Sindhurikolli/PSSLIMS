package com.pss.lims.StabilityManagementWithSampleManagementQuantativeExternalFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.Set;

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

public class MyJob extends LoginDetails {

	@Test
	public void approveMyJob() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "MyJob" + (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create My Job", "LIMS-SM-096", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Analyst"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("limsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("limsModule_SM"));
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id='loginform']/div[7]/input")).click();
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='myJobsListPageInSample.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='myJobsListPageInSample.do']"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on My Job", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#myJobListTable > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		methodToapproveMyJob();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToapproveMyJob() throws Exception {

		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String arNumber = properties.getProperty("Product_name");
		isRecordSelected = selectRecordForWorkSheetPrint(count, isRecordSelected, arNumber);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("workSheetMyJobActForm")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on WorkSheet", sno, false);
			Thread.sleep(20000);
			String mainWindow = driver.getWindowHandle();
			System.out.println("mainwindow" + mainWindow);
			Thread.sleep(10000);
			Set<String> set = driver.getWindowHandles();
			java.util.Iterator<String> itr = set.iterator();
			while (itr.hasNext()) {
				String childWindow = itr.next();
				if (!mainWindow.equals(childWindow)) {
					driver.switchTo().window(childWindow);
					sno++;
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "View Document", sno, false);
					System.out.println(driver.switchTo().window(childWindow).getTitle());
					driver.close();
				}
			}
			Thread.sleep(3000);
			driver.switchTo().window(mainWindow);
			Thread.sleep(3000);
//			if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).getText()
//					.equalsIgnoreCase("Submitted successfully.")) {
//				sno++;
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
//				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
//			}
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
		WebElement table = driver.findElement(By.id("myJobListTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"myJobListTable\"]/div/div[4]/div[2]/span")).getText();// For
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
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((arNumber == null) || ("".equalsIgnoreCase(arNumber)))) {
				arNumber = driver.findElement(By.xpath("//*[@id=\"myJobListTable\"]/div/table/tbody/tr[1]/td[8]"))
						.getText();// documentType
			} else if ((arNumber == null) || ("".equalsIgnoreCase(arNumber))) {
				arNumber = driver.findElement(By.xpath("//*[@id=\"myJobListTable\"]/div/table/tbody/tr/td[8]"))
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
										By.xpath("//*[@id=\"myJobListTable\"]/div/table/tbody/tr[ " + i + " ]/td[8]"))
								.getText();// documentTypeName
						if (arNumber.equalsIgnoreCase(arNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"myJobListTable\"]/div/table/tbody/tr[" + i + "]/td[3]/input"))
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
							.findElement(By.xpath("//*[@id=\"myJobListTable\"]/div/table/tbody/tr/td[8]")).getText();
					if (arNumber.equalsIgnoreCase(arNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"myJobListTable\"]/div/table/tbody/tr/td[3]/input"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#myJobListTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("myJobListTable"));// Document Tree approve table
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
