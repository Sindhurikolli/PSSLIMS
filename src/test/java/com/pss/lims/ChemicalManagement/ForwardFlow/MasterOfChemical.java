package com.pss.lims.ChemicalManagement.ForwardFlow;

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
import com.pss.lims.CM.CMLoginDetails.LoginDetails;
import com.pss.lims.ExtentTestNGPkg.Utility;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class MasterOfChemical extends LoginDetails {

	@Test
	public void createMasterOfChemical() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "MasterOfChemical" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Master Of Chemical", "LIMS-CM-001", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='masterOfChemInRegiter.do'")));
		driver.findElement(By.cssSelector("a[href='masterOfChemInRegiter.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Master Of Chemical", sno, false);
		Thread.sleep(2000);
		methodToCreateMasterOfChemical();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreateMasterOfChemical() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 200);
//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
		Thread.sleep(1000);
		jse2.executeScript("arguments[0].click();", element2);
		Thread.sleep(2000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("nameInMasterOFChemRegiseterForm")).sendKeys(properties.getProperty("Name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Name", sno, false);
		Thread.sleep(2000);
		sno++;
		Select category = new Select(driver.findElement(By.id("categoryInMasterOFChemRegiseterForm")));
		Thread.sleep(1000);
		category.selectByVisibleText(properties.getProperty("Category_Name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Category", sno, false);
		Thread.sleep(2000);
		sno++;
		Select grade = new Select(driver.findElement(By.id("gradeInMasterOFChemRegiseterForm")));
		Thread.sleep(1000);
		grade.selectByVisibleText(properties.getProperty("Grade"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Grade", sno, false);
		Thread.sleep(2000);
		sno++;
		Select sym = new Select(driver.findElement(By.id("handlingSymbolInMasterOFChemRegiseterForm")));
		Thread.sleep(1000);
		sym.selectByVisibleText(properties.getProperty("Handling_Symbol"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Handling Symbol", sno, false);
		Thread.sleep(2000);
		sno++;
		Select con = new Select(driver.findElement(By.id("storageConditionInMasterOFChemRegiseterForm")));
		Thread.sleep(1000);
		con.selectByVisibleText(properties.getProperty("Storage_Condition"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Storage Condition", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse12 = (JavascriptExecutor) driver;
		WebElement element12 = driver.findElement(By.id("selBtnForAppFromInMasOfChem"));
		jse12.executeScript("arguments[0].click();", element12);
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
		Thread.sleep(5000);
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
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("minimumQtyInMasterOFChemRegiseterForm"))
					.sendKeys(properties.getProperty("Minimum_QTY"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Minimum Quantity", sno, false);
			Thread.sleep(2000);
			sno++;
			Select uom = new Select(driver.findElement(By.id("minimumQtyUOMInMasterOFChemRegiseterForm")));
			Thread.sleep(1000);
			uom.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("natureOfChemicalInMasterOFChemRegiseterForm"))
					.sendKeys(properties.getProperty("NatureOfChemical"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Nature Of Chemical", sno, false);
			Thread.sleep(2000);
			sno++;
			Select type = new Select(driver.findElement(By.id("typeOfChemicalInMasterOFChemRegiseterForm")));
			Thread.sleep(1000);
			type.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Type Of Chemical", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("uploadBtn")).sendKeys(properties.getProperty("Document-1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload Document", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse11121 = (JavascriptExecutor) driver;
			WebElement element11121 = driver.findElement(By.id("selBtnForLocationInMasOfChem"));
			jse11121.executeScript("arguments[0].click();", element11121);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(5000);
			sno++;
			wait.until(ExpectedConditions.elementToBeClickable(By.id("locSelectInLims_2_switch")));
			JavascriptExecutor jse02 = (JavascriptExecutor) driver;
			WebElement elemen2 = driver.findElement(By.id("locSelectInLims_2_switch"));
			jse02.executeScript("arguments[0].click();", elemen2);
			Thread.sleep(3000);
			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse102 = (JavascriptExecutor) driver;
			WebElement elemen21 = driver.findElement(By.id("locSelectBtnInLocationTreeSelWnd"));
			jse102.executeScript("arguments[0].click();", elemen21);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element5 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse.executeScript("arguments[0].scrollIntoView(true);", element5);
			Thread.sleep(1000);
			jse.executeScript("arguments[0].click();", element5);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(3000);
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
