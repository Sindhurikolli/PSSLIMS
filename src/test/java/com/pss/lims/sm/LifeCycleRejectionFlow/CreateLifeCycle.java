package com.pss.lims.sm.LifeCycleRejectionFlow;

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
import org.openqa.selenium.WebDriver;
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

public class CreateLifeCycle extends SMLoginDetails {

	@Test
	public void createLifeCycle() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateLifeCycle" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Life Cycle", "PSS-LIMS-001", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='createLifeCycleSampleMangmt.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='createLifeCycleSampleMangmt.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create Life Cycle", sno, false);
		Thread.sleep(4000);
		methodToCreateLifeCycle();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreateLifeCycle() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 290);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
		Thread.sleep(2000);
		jse2.executeScript("arguments[0].click();", element2);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("nameOfCreateLifeCycleInLims")).sendKeys(properties.getProperty("LifeCycle_Name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Life Cycle Name", sno, false);
		Thread.sleep(3000);
		sno++;
		WebElement ele = driver.findElement(By.id("startDateOfLCInLims"));
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", ele);
		Thread.sleep(2000);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		driver.findElement(By.id("startDateOfLCInLims")).sendKeys(sdf.format(currentDate));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Start Date", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("locSelBtnInLifeCycleSampleManagmt")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("locSelectInSm_2_switch")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("locSelBtnInLifeCycle")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("appSelBtnInSampleManagmt")).click();
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
		isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval, isRecordSelected,
				count);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("descriptionOfCreateLifeCycleInLims"))
					.sendKeys(properties.getProperty("LifeCycle_Description"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description", sno, false);
			Thread.sleep(3000);
			jse2.executeScript("arguments[0].click();", element2);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"stageDetailsTableInLifeCycleSampleManagmt\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Stage", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("stageNameOfLifeCycle")).sendKeys(properties.getProperty("Stage_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Stage Name", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("sendEmailOfLifeCycle")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"click on Send Email to Stage Participants Check box", sno, false);
			Thread.sleep(3000);
			sno++;
			Select approveFrom = new Select(driver.findElement(By.id("RequiresApprovalOfLifeCycle")));
			Thread.sleep(1000);
			approveFrom.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Requires Approval From", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("stageDescriptionOfLifeCycle"))
					.sendKeys(properties.getProperty("Stage_Description"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Stage Description", sno, false);
			Thread.sleep(3000);
			sno++;
			Select userGroup = new Select(driver.findElement(By.id("userGroupOfStage")));
			Thread.sleep(1000);
			userGroup.selectByVisibleText(properties.getProperty("User_Group_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select User Group", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse111 = (JavascriptExecutor) driver;
			WebElement element111 = driver.findElement(By.id("createBtnInCreateLifeCycleSampleMangmt"));
			jse111.executeScript("arguments[0].scrollIntoView(true);", element111);
			Thread.sleep(1000);
			jse111.executeScript("arguments[0].click();", element111);
//			driver.findElement(By.id("createBtnInCreateLifeCycleSampleMangmt")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create", sno, false);
			Thread.sleep(3000);
			JavascriptExecutor jse11 = (JavascriptExecutor) driver;
			WebElement element11 = driver.findElement(By.linkText("Finish"));
			jse11.executeScript("arguments[0].scrollIntoView(true);", element11);
			Thread.sleep(1000);
			jse11.executeScript("arguments[0].click();", element11);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
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
					.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
			Thread.sleep(2000);
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
