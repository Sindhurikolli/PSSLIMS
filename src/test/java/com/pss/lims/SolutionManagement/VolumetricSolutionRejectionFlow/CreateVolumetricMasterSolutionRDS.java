package com.pss.lims.SolutionManagement.VolumetricSolutionRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import com.pss.lims.SolutionManagement.LoginDetails.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class CreateVolumetricMasterSolutionRDS extends LoginDetails {

	@Test
	public void createVolumetricSolution() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateVolumetricMasterOfSolution"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Volumetric Master Of Solution RDS",
				"LIMS-SM-001", "Pass");
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
		wiat.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='masterSolutionPrepRDS.do'")));
		driver.findElement(By.cssSelector("a[href='masterSolutionPrepRDS.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Master Of Solution Pre...", sno,
				false);
		Thread.sleep(4000);
		methodToCreateMasterOfSolution();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreateMasterOfSolution() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 200);
		sno++;
		driver.findElement(By.id("createMasterSolutionPrepRdsAction")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
				"Click on Create Master Of Solution Preparation RDS", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.linkText("Next")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.name("solutionNameInMasterSolutionPrep"))
				.sendKeys(properties.getProperty("SolutionName"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Solution Name", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("durationExpiryInMasterSolutionPrep")).sendKeys(properties.getProperty("ExpiryDays"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Duration Of Expiry(Days)", sno,
				false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("prefixInMasterSolutionPrep")).sendKeys(properties.getProperty("Prefix"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Prefix", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("selLocationBtnInMasterSolPrepReg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("locSelectInSm_2_switch")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("locSelBtnInMasterPrepRegSolManagmt")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("gtpNumberInMasterSolutionPrep")).sendKeys(properties.getProperty("GTPNumber"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter GTP-Number", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("gtpRevisionNoInMasterSolutionPrep"))
				.sendKeys(properties.getProperty("GTPRevisionNumber"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter GTP-Number Revision Number", sno,
				false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("gtpDocInVolSolNameRegForm")).sendKeys(properties.getProperty("Document-2"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload Document", sno, false);
		Thread.sleep(2000);
		sno++;
		Select type = new Select(driver.findElement(By.id("solutionTypeInMasterSolutionPrep")));
		Thread.sleep(1000);
		type.selectByIndex(1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Solution Type", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("selAppFromUserInMasterSolPrepReg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(4000);
		driver.findElement(By.id("locTreeInCalPmBdm_2_switch")).click();
		Thread.sleep(3000);
		sno++;
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
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("strengthSolInMasterSolPrepRDSReg"))
					.sendKeys(properties.getProperty("StrengthOfSolution"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Strength Of Solution", sno,
					false);
			Thread.sleep(2000);
			sno++;
			Select uom = new Select(driver.findElement(By.id("uomUnitsInMasterSolutionPrep")));
			Thread.sleep(1000);
			uom.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("noOfTitrationsInMasterSolutionPrep"))
					.sendKeys(properties.getProperty("No.ofIterations"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Strength Of Solution", sno,
					false);
			Thread.sleep(2000);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("testProcedure_ifr")).sendKeys(properties.getProperty("TestProcedure"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Test Procedure", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.linkText("Finish")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInVmsWnd")).sendKeys(properties.getProperty("Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESignVms")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
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

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
