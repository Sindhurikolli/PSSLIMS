package com.pss.lims.LM.RejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.pss.lims.LM.LoginDetails.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class LabelTemplate extends LoginDetails {

	@Test
	public void createLabelTemplate() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "LabelTemplateCreate"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Label Template", "LIMS-LM-001", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='labelTemplateCreateForm.do'")));
		driver.findElement(By.cssSelector("a[href='labelTemplateCreateForm.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Label Template", sno, false);
		Thread.sleep(4000);
		methodToCreateLabelTemplate();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreateLabelTemplate() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 200);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("createLabelTemplateAction")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create", sno, false);
		Thread.sleep(2000);
//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
//		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.linkText("Next"));
		jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
		Thread.sleep(1000);
		jse2.executeScript("arguments[0].click();", element2);
		Thread.sleep(3000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("templateNameInLabelNameForm")).sendKeys(properties.getProperty("Template_Name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Template Name", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse12 = (JavascriptExecutor) driver;
		WebElement element12 = driver.findElement(By.id("selAppFromUserInLabelTemp"));
		jse12.executeScript("arguments[0].click();", element12);
//		driver.findElement(By.id("selAppFromUserInLabelTemp")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(4000);
		sno++;
		JavascriptExecutor jse112 = (JavascriptExecutor) driver;
		WebElement element112 = driver.findElement(By.id("locTreeInCalPmBdm_2_switch"));
		jse112.executeScript("arguments[0].click();", element112);
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(2000);
		int count = 0;
		boolean isRecordSelected = false;
		String selectingUserSingleApproval = properties.getProperty("LastName");
		isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval, isRecordSelected,
				count);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			sno++;
			JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
			WebElement element1112 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse1112.executeScript("arguments[0].click();", element1112);
//			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("noOfHeaderLinesInLabelNameForm"))
					.sendKeys(properties.getProperty("No.of_HeadLines"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of HeadLines", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("line1HeaderText")).sendKeys(properties.getProperty("Line1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Line1", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("noOfFooterLinesInLabelNameForm"))
					.sendKeys(properties.getProperty("No.ofFooterLines"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of FooterLines", sno, false);
			Thread.sleep(2000);
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			WebElement element1 = driver.findElement(By.id("line1FooterText"));
			jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("line1FooterText")).sendKeys(properties.getProperty("Line1Footer"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Line1", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse11121 = (JavascriptExecutor) driver;
			WebElement element11121 = driver.findElement(By.linkText("Next"));
			jse11121.executeScript("arguments[0].scrollIntoView(true);", element11121);
			Thread.sleep(1000);
			jse11121.executeScript("arguments[0].click();", element11121);
//			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(5000);
//			jse1.executeScript("window.scrollBy(0,-250)");
			sno++;
			Select sampleType = new Select(driver.findElement(By.id("sampTypeInLabelTemplate")));
			Thread.sleep(1000);
			sampleType.selectByVisibleText(properties.getProperty("SampleType"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Sample Type", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("noOfFieldsOnLabelInLabelNameForm"))
					.sendKeys(properties.getProperty("No.ofFieldsOnLabel"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Field Labels", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("fieldLabelInLabelNameForm1")).sendKeys(properties.getProperty("NameOfTheLabel"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Name of The Label1", sno, false);
			Thread.sleep(2000);
			sno++;
			Select set = new Select(driver.findElement(By.id("fieldSetNameInLabelNameForm1")));
			set.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			JavascriptExecutor jse11 = (JavascriptExecutor) driver;
			WebElement element11 = driver.findElement(By.id("line1FooterText"));
			jse11.executeScript("arguments[0].scrollIntoView(true);", element11);
			Thread.sleep(5000);
			WebElement element = driver.findElement(By.id("wefDateInLT"));
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", element);
			sno++;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			driver.findElement(By.id("wefDateInLT")).sendKeys(sdf.format(date));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Date", sno, false);
			Thread.sleep(2000);
			JavascriptExecutor jse141 = (JavascriptExecutor) driver;
			WebElement element141 = driver.findElement(By.id("reAnalysisDateRequired"));
			jse141.executeScript("arguments[0].click();", element141);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("referenceNumberValueInLT")).sendKeys(properties.getProperty("RefNo"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Ref.NO", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element5 = driver.findElement(By.linkText("Finish"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element5);
			Thread.sleep(1000);
			jse.executeScript("arguments[0].click();", element5);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor j1se = (JavascriptExecutor) driver;
			WebElement e1lement = driver.findElement(By.id("subBtnInValidateESign"));
			j1se.executeScript("arguments[0].click();", e1lement);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
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

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
