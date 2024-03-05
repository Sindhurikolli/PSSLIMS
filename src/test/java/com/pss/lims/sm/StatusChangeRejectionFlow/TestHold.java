package com.pss.lims.sm.StatusChangeRejectionFlow;

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
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class TestHold extends SMLoginDetails {

	@Test
	public void holdTest() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "HoldTest" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Hold Test", "LIMS-SM-013", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Initiator_Login"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("limsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("Lims_Module_Name1"));
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='testStatusChangeForm.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='testStatusChangeForm.do']"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Test", sno, false);
		Thread.sleep(4000);
		methodToholdTest();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToholdTest() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		sno++;
		driver.findElement(By.id("searchBtnInTestStatusChangeForm")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		Thread.sleep(2000);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("commentsInTestHoldForm"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#testStatusGrid > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(3000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String product = properties.getProperty("Product_name");
		isRecordSelected1 = selectRecordForProduct(count1, isRecordSelected1, product);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("selApprovedByInTestHoldForm")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("locTreeInCalPmBdm_2_switch")).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(3000);
			int count = 0;
			boolean isRecordSelected = false;
			String selectingUserSingleApproval = properties.getProperty("LastName");
			isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval,
					isRecordSelected, count);
			if (isRecordSelected) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("commentsInTestHoldForm")).sendKeys(properties.getProperty("Stage_Name"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("submitBtnInTestStatusChangeForm")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
						false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("subBtnInValidateESign")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
				Thread.sleep(3000);
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
				Thread.sleep(3000);
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
				System.out.println("User is not Selected");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecordForProduct(int count1, boolean isRecordSelected1, String product) throws Exception {
// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("testStatusGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"testStatusGrid\"]/div/div[4]/div[2]/span")).getText();// For
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
			if ((totalNoOfRecords > 1) && ((product == null) || ("".equalsIgnoreCase(product)))) {
				product = driver.findElement(By.xpath("//*[@id=\"testStatusGrid\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((product == null) || ("".equalsIgnoreCase(product))) {
				product = driver.findElement(By.xpath("//*[@id=\"testStatusGrid\"]/div/table/tbody/tr[1]/td[3]"))
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
										By.xpath("//*[@id=\"testStatusGrid\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (product.contains(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"testStatusGrid\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
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
							.findElement(By.xpath("//*[@id=\"testStatusGrid\"]/div/table/tbody/tr/td[3]")).getText();
					if (product.contains(DevNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"testStatusGrid\"]/div/table/tbody/tr/td[3]")).click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#testStatusGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("testStatusGrid"));// Document Tree approve
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
