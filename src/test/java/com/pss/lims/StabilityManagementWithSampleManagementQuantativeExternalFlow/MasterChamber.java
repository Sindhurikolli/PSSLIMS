package com.pss.lims.StabilityManagementWithSampleManagementQuantativeExternalFlow;

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
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class MasterChamber extends LoginDetails {

	@Test
	public void createMasterChamber() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateMasterChamber"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Master Chamber", "LIMS-SM-009", "Pass");
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
		wiat.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='masterOfChamberForm.do'")));
		driver.findElement(By.cssSelector("a[href='masterOfChamberForm.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Master Of Chamber", sno, false);
		Thread.sleep(4000);
		methodForCreateMasterChamber();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodForCreateMasterChamber() throws Exception {

//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
//		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 150);
		sno++;
		JavascriptExecutor jse12 = (JavascriptExecutor) driver;
		WebElement element12 = driver
				.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
		jse12.executeScript("arguments[0].scrollIntoView(true);", element12);
		Thread.sleep(2000);
		jse12.executeScript("arguments[0].click();", element12);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("chamberNameInLims")).sendKeys(properties.getProperty("MasterChamber"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Chamber Name", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("rackNumberInLims")).sendKeys("134");
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Rack Number", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse7 = (JavascriptExecutor) driver;
		WebElement element7 = driver.findElement(By.id("addRackToJtable"));
		jse7.executeScript("arguments[0].click();", element7);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse71 = (JavascriptExecutor) driver;
		WebElement element71 = driver.findElement(By.id("selecttheApprovalInChamber"));
		jse71.executeScript("arguments[0].click();", element71);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(5000);
		sno++;
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("locTreeInCalPmBdm_2_switch"));
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("usersSearchBtnInRepProb")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(5000);
		int count = 0;
		boolean isRecordSelected = false;
		String selectingUserSingleApproval = properties.getProperty("LastName");
		isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval, isRecordSelected,
				count);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse4210 = (JavascriptExecutor) driver;
			WebElement element4210 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse4210.executeScript("arguments[0].click();", element4210);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
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
			JavascriptExecutor jse17 = (JavascriptExecutor) driver;
			WebElement element17 = driver.findElement(By.id("subBtnInValidateESign"));
			jse17.executeScript("arguments[0].click();", element17);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(1000);
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
			System.out.println("User is not Selected");
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
