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
import com.pss.lims.login.RegistrationLoginDetails;
import com.pss.lims.login.SMLoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;

public class CreateUserGroup extends RegistrationLoginDetails {

	@Test
	public void createUserGroup() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateUserGroup" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create User Group", "LIMS-SM-001", "Pass");
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
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[4]/button[1]")).click();
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
		methodToCreateUserGroup();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
//		//desktop.open(file);

	}

	public void methodToCreateUserGroup() throws Exception {

		sno++;
		driver.findElement(By.cssSelector("a[href='userGroupPageInSample.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Group", sno, false);
		Thread.sleep(3000);
		
		sno++;
		driver.findElement(By.id("createUserGroupForm")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create User Group Radio Button", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = driver.findElement(By.linkText("Next"));
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", ele);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("groupNameInUserGroupForm")).sendKeys(properties.getProperty("User_Group_Name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Group Name", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("groupDescriptionInUserGroupForm"))
				.sendKeys(properties.getProperty("User_Group_Description"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Group Description", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("selBtnForLocInUserGrp")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(5000);
		driver.findElement(By.id("locSelectInLims_2_switch")).click();
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("locSelectBtnInLocationTreeSelWnd")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(2000);
		
		Select users = new Select(driver.findElement(By.id("bootstrap-duallistbox-nonselected-list_")));
		Thread.sleep(2000);
		String UsersforUserGroup = properties.getProperty("UsersforUserGroup");
		String userug[] = UsersforUserGroup.split(",");
		for(int i=0;i<userug.length;i++)
		{
			users.selectByVisibleText(userug[i]);
		}
		sno++;
//		users.selectByVisibleText(properties.getProperty("UserSelection"));
//		Thread.sleep(1000);
//		users.selectByVisibleText(properties.getProperty("UserSelection1"));
//		Thread.sleep(1000);
//		users.selectByVisibleText(properties.getProperty("UserSelection11"));
		Thread.sleep(1000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Users", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement ele1 = driver.findElement(By.linkText("Finish"));
		js1.executeScript("arguments[0].scrollIntoView(true);", ele1);
		Thread.sleep(1000);
	
		js.executeScript("arguments[0].click();", ele1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
		Thread.sleep(3000);
		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
		Thread.sleep(1000);
		driver.findElement(By.id("subBtnInValidateESign")).click();
		Thread.sleep(6000);
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
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

	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
