package com.pss.lims.sm.AdminTab;

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

public class PlantCode extends SMLoginDetails {

	@Test
	public void createPlantCode() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreatePlantCode" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Plant Code", "LIMS-SM-003", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Admin_Login"));
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
		wait.until(ExpectedConditions.elementToBeClickable(By.id("adminTabInSampleMngt")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("adminTabInSampleMngt"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on ADMIN", sno, false);
		Thread.sleep(4000);
		methodToCreatePlantCode();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreatePlantCode() throws Exception {

		sno++;
		driver.findElement(By.cssSelector("a[href='plantCodePageInSample.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Plant Code", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("selTreeNodeInPlantCode")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(5000);
		driver.findElement(By.id("locSelectInPlantCode_2_switch")).click();
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("locSelBtnInPlantCodeLocSelWin")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("plantCodeInPlantCodeForm")).sendKeys(properties.getProperty("Plant_code"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Plant Code", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("submitBtnInLimsPlantCodeForm")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("subBtnInValidateESign")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
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

	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
