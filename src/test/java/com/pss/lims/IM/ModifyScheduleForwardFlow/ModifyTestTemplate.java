package com.pss.lims.IM.ModifyScheduleForwardFlow;

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

public class ModifyTestTemplate extends LoginDetails {

	@Test
	public void modifyTestTemplate() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ModifyTestTemplate"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Modify Test Template", "LIMS-IMS-007", "Pass");
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
		WebDriverWait wait = new WebDriverWait(driver, 240);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='testTemplateForm.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='testTemplateForm.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Test Template", sno, false);
		Thread.sleep(4000);
		methodForModifyRegisterTestTemplate();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
	}

	private void methodForModifyRegisterTestTemplate() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		sno++;
		driver.findElement(By.id("modifyInstTestTemplatection")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Modify Existing Test Templates",
				sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#testTemplModGrdInTestTempForm > div > div.jtable-busy-message[style='display: none;']")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver
				.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(5000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("CalibrationDesign");
		isRecordSelected1 = selectInstrument(count1, isRecordSelected1, name1);
		if (isRecordSelected1) {
			Thread.sleep(2000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			jse1.executeScript("arguments[0].click();", element1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(By.id("templateNameInImsTestTemplateForm")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Calibration Design", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("templateNameInImsTestTemplateForm"))
					.sendKeys(properties.getProperty("CalibrationDesign"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Calibration Design", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("templateDescInImsTestTemplateForm")).clear();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Clear Calibration Description", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("templateDescInImsTestTemplateForm"))
					.sendKeys(properties.getProperty("Description"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Calibration Description", sno,
					false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse42110 = (JavascriptExecutor) driver;
			WebElement element42110 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse42110.executeScript("arguments[0].scrollIntoView(true);", element42110);
			Thread.sleep(2000);
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

	private boolean selectInstrument(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("testTemplModGrdInTestTempForm"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"testTemplModGrdInTestTempForm\"]/div/div[4]/div[2]/span"))
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
						.findElement(By.xpath("//*[@id=\"testTemplModGrdInTestTempForm\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver
						.findElement(By.xpath("//*[@id=\"testTemplModGrdInTestTempForm\"]/div/table/tbody/tr/td[3]"))
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
								"//*[@id=\"testTemplModGrdInTestTempForm\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"testTemplModGrdInTestTempForm\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
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
							.findElement(
									By.xpath("//*[@id=\"testTemplModGrdInTestTempForm\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"testTemplModGrdInTestTempForm\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
//					driver.findElement(By.cssSelector(
//							"#testTemplModGrdInTestTempForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
//							.click();// next page in Document approve list
					JavascriptExecutor js = (JavascriptExecutor) driver;
					WebElement ele = driver.findElement(By.cssSelector(
							"#testTemplModGrdInTestTempForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));
					js.executeScript("arguments[0].click();", ele);
					Thread.sleep(4000);
					table = driver.findElement(By.id("testTemplModGrdInTestTempForm"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected1;
	}
}
