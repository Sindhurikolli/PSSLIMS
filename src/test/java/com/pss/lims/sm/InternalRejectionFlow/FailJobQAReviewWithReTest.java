package com.pss.lims.sm.InternalRejectionFlow;

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
import com.pss.lims.login.SMLoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;

public class FailJobQAReviewWithReTest extends SMLoginDetails {

	@Test
	public void failJobQAReviewWithReTest() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "FailJobQAReviewWithReTest" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Fail Job QA Review With ReTest", "PSS-LIMS-114", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Approver_Login"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("limsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("Lims_Module_Name1"));
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
		Thread.sleep(6000);
		sno++;
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='oosQAReviewPageInSample.do']"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on QA Review", sno, false);
		Thread.sleep(4000);
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#oosQaAppGrid > div > div.jtable-busy-message[style=\"display: none;\"]")));
		Thread.sleep(4000);
		methodToFailJobQAReviewWithReTest();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToFailJobQAReviewWithReTest() throws Exception {

//		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//		WebElement element1 = driver.findElement(By.cssSelector("a[href='arNumberAppPageInSample.do']"));
//		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
//		Thread.sleep(2000);
//		jse1.executeScript("arguments[0].click();", element1);
//		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String arNumber = properties.getProperty("AR_Number");
		isRecordSelected = selectRecordForarNumber(count, isRecordSelected, arNumber);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno,
					false);
			Thread.sleep(3000);
			driver.findElement(By.id("retestActionOosQaAppRadBtn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Retest", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("reasonInOOSQaAppReview")).sendKeys(properties.getProperty("ReTest_Comments"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement submit = driver.findElement(By.id("submitBtnInOosQaAppGridReview"));
			js.executeScript("arguments[0].scrollIntoView();",submit);
			driver.findElement(By.id("submitBtnInOosQaAppGridReview")).click();
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
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK", sno, false);
			driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			
		 Thread.sleep(3000);
		 
		sno++;
		JavascriptExecutor jse208 = (JavascriptExecutor) driver;
		WebElement element208 =driver.findElement(By.className("username"));
		jse208.executeScript("arguments[0].click();", element208);;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on UserName",sno,false);
		 Thread.sleep(3000);
		 sno++;
		 JavascriptExecutor jse209 = (JavascriptExecutor) driver;
		 WebElement element209 =driver.findElement(By.cssSelector("a[href='Logout.do']"));
		jse209.executeScript("arguments[0].click();", element209);
		 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout",sno,true);
		 Thread.sleep(3000);

		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecordForarNumber(int count, boolean isRecordSelected, String arNumber) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("oosQaAppGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"oosQaAppGrid\"]/div/div[4]/div[2]/span")).getText();// For
			///*[@id="oosQaAppGrid"]/div/table/tbody/tr/td[4]
			System.out.println("hi:" + a);
			String[] parts = a.split(" of ");
			System.out.println("parts:" + parts.toString());
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println(totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
//			System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((arNumber == null) || ("".equalsIgnoreCase(arNumber)))) {
//				//*[@id="oosQaAppGrid"]/div/table/tbody/tr[2]/td[4]
				arNumber = driver.findElement(By.xpath("//*[@id=\"oosQaAppGrid\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType//*[@id="oosQaAppGrid"]/div/table/tbody/tr/td[4]
			} else if ((arNumber == null) || ("".equalsIgnoreCase(arNumber))) {
				arNumber = driver.findElement(By.xpath("//*[@id=\"oosQaAppGrid\"]/div/table/tbody/tr/td[4]"))
						.getText();// document type
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						WebElement record = driver.findElement(By.xpath("//*[@id=\"oosQaAppGrid\"]/div/table/tbody/tr[ " + i + " ]/td[4]"));
						js.executeScript("arguments[0].scrollIntoView();",record);
						String arNumberSequence = driver
								.findElement(By
										.xpath("//*[@id=\"oosQaAppGrid\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();// documentTypeName
						if (arNumber.equalsIgnoreCase(arNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"oosQaAppGrid\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					WebElement record = driver.findElement(By.xpath("//*[@id=\"oosQaAppGrid\"]/div/table/tbody/tr/td[4]"));
					js.executeScript("arguments[0].scrollIntoView();",record);
					String arNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"oosQaAppGrid\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (arNumber.equalsIgnoreCase(arNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"oosQaAppGrid\"]/div/table/tbody/tr/td[4]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#oosQaAppGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					WebDriverWait wait = new WebDriverWait(driver, 90);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#oosQaAppGrid > div > div.jtable-busy-message[style=\"display: none;\"]")));
					Thread.sleep(4000);
					table = driver.findElement(By.id("oosQaAppGrid"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelected;
	}
}
