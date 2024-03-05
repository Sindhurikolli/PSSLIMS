package com.pss.lims.sm.AdminTab;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.lims.login.SMLoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;

public class RoleBasePageAccess extends SMLoginDetails {
	@Test
	public void toReviewLIMS() throws InterruptedException, IOException, DocumentException, Exception {
		try {
			document = new Document();

			Font font = new Font(Font.FontFamily.TIMES_ROMAN);
			output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "RoleBasePageAccess"
					+ (new Random().nextInt()) + ".pdf";
			fos = new FileOutputStream(output);

			writer = PdfWriter.getInstance(document, fos);
			writer.setStrictImageSequence(true);

			writer.open();
			HeaderFooterPageEvent event = new HeaderFooterPageEvent("RoleBasePageAccess", "PSS-LIMS-005", "Pass");
			writer.setPageEvent(event);
			document.open();

			Thread.sleep(1000);
			driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("MasterCreator"));

			driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
			input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
			im = Image.getInstance(input);
			im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
					(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
			document.add(new Paragraph(sno + "." + "Enter the username, password and click on login button"
					+ Utilities.prepareSSNumber(sno), font));
			document.add(im);

			document.add(new Paragraph("                                     "));
			document.add(new Paragraph("                                     "));
			sno++;
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div/div[4]/a/map/area")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Sample Management module", sno,
					false);
			sno++;
			Thread.sleep(3000);
			driver.findElement(By.id("adminTabInSampleMngt")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Admin Tab", sno, false);
			sno++;
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[@href='limsRoleBaseAccessPage.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on RoleBase Page access menu",
					sno, false);
			todoRoleBasePageAccess();
			document.close();
			writer.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(output);
			//desktop.open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void todoRoleBasePageAccess() throws Exception {
		sno++;
		Thread.sleep(4000);
		Select Roles = new Select(driver.findElement(By.id("rolesInLimsRoleBaseAccess")));
		Roles.selectByValue("2");
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the role from the drop down", sno,
				false);
		sno++;
		Thread.sleep(4000);
		driver.findElement(By.id("limsApprovalList")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Check the approval Check Box", sno, false);
		sno++;
		Thread.sleep(4000);
//        Scroll Down
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(By.id("submitButtonInLimsRoleBaseAccess"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
		driver.findElement(By.id("submitButtonInLimsRoleBaseAccess")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on submit button", sno, false);
		Thread.sleep(3000);
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 70);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[10]/a/span")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On UserName ", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.xpath("/html/body/div[1]/header/nav/ul/li[10]/ul/li[2]/a")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On LogOut ", sno, true);

	}

}
