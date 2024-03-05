package com.pss.lims.LM.Admin;

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
import com.pss.lims.LM.LoginDetails.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class AssignRole extends LoginDetails {

	@Test
	public void roleAssign() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "LMRoleAssign" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("LM Role Assign", "LIMS-LM-003", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("AdminUser_Login"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("limsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("limsModule"));
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/input[1]")).click();
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(sno + "." + "Enter the username, password, Select Module and click on login button"
				+ Utilities.prepareSSNumber(sno), font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 150);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='createRoleFormInLabelMngmt.do'")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = driver.findElement(By.cssSelector("a[href='createRoleFormInLabelMngmt.do'"));
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ele);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin", sno, false);
		Thread.sleep(4000);
		methodToAssignRole();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToAssignRole() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 200);
		sno++;
		driver.findElement(By.cssSelector("a[href='assignRoleFormInLabelMngmt.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Assign Role", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("seluserBtnInAssignRolesInLabelMngmt")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("locTreeInCalPmBdm_2_switch")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(5000);
		int count = 0;
		boolean isRecordSelected = false;
		String selectingUserSingleApproval = properties.getProperty("FirstName");
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
			Select role = new Select(driver.findElement(By.id("bootstrap-duallistbox-nonselected-list_")));
			Thread.sleep(1000);
			role.selectByVisibleText(properties.getProperty("CreateRole"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Role", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("submitBtnInLabelMngmtAssignRole")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
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
			com.pss.lims.ExtentTestNGPkg.Utility.captureScreenshot(driver, result.getName());
		}

	}
}
