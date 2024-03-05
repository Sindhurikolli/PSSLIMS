package com.pss.lims.RegistrationProj.ForwardFlow;

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
import com.pss.lims.login.RegistrationLoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;
import com.pss.regproject.RegistrationDetails.RegistrationDetails;

public class CustomerApproval extends RegistrationDetails {

	@Test
	public void approveCustomer() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ApproveCustomer" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Approve Customer", "LIMS-SM-041", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Approver_Login"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
//		Select module = new Select(driver.findElement(By.id("limsModule")));
//		Thread.sleep(1000);
//		module.selectByVisibleText(properties.getProperty("Lims_Module_Name1"));
//		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id='loginform']/div[3]/button[1]")).click();
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='qmsCustomerAppPage.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='qmsCustomerAppPage.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Customer", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#customerAppTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(2000);
		methodToApproveCustomer();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
//		//desktop.open(file);

	}

	private void methodToApproveCustomer() throws Exception {

		Thread.sleep(2000);
		int count = 0;
		boolean isRecordSelected = false;
		String customerName = properties.getProperty("Customer_Name");
		isRecordSelected = selectRecordForCustomer(count, isRecordSelected, customerName);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record and Click on View", sno,
					false);
			Thread.sleep(4000);
			sno++;
//			driver.findElement(By.id("closeBtnInCustomerApproval")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Close", sno, false);
//			Thread.sleep(3000);
//			sno++;
			
			driver.findElement(By.xpath("//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr/td[7]/button")).click();
			
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record and Click on View and Approve", sno,
					false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("customerCommentsInViewCustApp"))
					.sendKeys(properties.getProperty("Approval_Comments"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("appCusAppView")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Approve", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			WebDriverWait wait = new WebDriverWait(driver, 70);
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

	private boolean selectRecordForCustomer(int count, boolean isRecordSelected, String customerName) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("customerAppTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"customerAppTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();
			// For

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
			if ((totalNoOfRecords > 1) && ((customerName == null) || ("".equalsIgnoreCase(customerName)))) {
//				System.out.println("hi this is ravi");
				customerName = driver
						.findElement(By.xpath("//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((customerName == null) || ("".equalsIgnoreCase(customerName))) {
				customerName = driver
						.findElement(By.xpath("//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr/td[3]")).getText();// document
																														// type
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String customerNameSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (customerName.equalsIgnoreCase(customerNameSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String customerNameSequence = driver
							.findElement(By.xpath("//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (customerName.equalsIgnoreCase(customerNameSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"customerAppTableContainer\"]/div/table/tbody/tr/td[3]")).click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#customerApprovalTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("customerAppTableContainer"));// Document Tree approve table
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
