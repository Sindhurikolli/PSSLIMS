package com.pss.lims.sm.Registration.Reject;

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
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.lims.login.SMLoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class OosCheckList extends SMLoginDetails {

	@Test
	public void createOosCheckList() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateOOSList" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create OOS List", "PSS-LIMS-048", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("MasterCreator"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("limsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("Lims_Module_Name1"));
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
		Thread.sleep(6000);
		sno++;
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("limsOosList"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OOS List", sno, false);
		Thread.sleep(4000);
		methodTocreateOosCheckList();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodTocreateOosCheckList() throws Exception {

		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("createOOSListAction"));
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Creation of OOS Check List", sno,
				false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse2.executeScript("arguments[0].click();", element2);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("oOSListNameInLims")).sendKeys(properties.getProperty("OosCheckList_Name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Name", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("oOSListDescriptionInLims"))
				.sendKeys(properties.getProperty("OosCheckList_Description"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse3 = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.id("selAppFromUserInOOSReg"));
		jse3.executeScript("arguments[0].click();", element3);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse4 = (JavascriptExecutor) driver;
		WebElement element4 = driver.findElement(By.id("locTreeInCalPmBdm_2_span"));
		jse4.executeScript("arguments[0].click();", element4);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(2000);
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
			JavascriptExecutor jse5 = (JavascriptExecutor) driver;
			WebElement element5 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse5.executeScript("arguments[0].click();", element5);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse6 = (JavascriptExecutor) driver;
			WebElement element6 = driver.findElement(By.id("addButtonInOOSCheckSelReg"));
			jse6.executeScript("arguments[0].click();", element6);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse16 = (JavascriptExecutor) driver;
			WebElement element16 = driver
					.findElement(By.xpath("//*[@id=\"oOSCheckListJsGrid\"]/div[2]/table/tbody/tr/td[2]/input[1]"));
			jse16.executeScript("arguments[0].click();", element16);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"oOSCheckListJsGrid\"]/div[2]/table/tbody/tr[1]/td[1]/input"))
					.sendKeys(properties.getProperty("OosCheckList_Point1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Check Point Name", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse17 = (JavascriptExecutor) driver;
			WebElement element17 = driver
					.findElement(By.xpath("//*[@id=\"oOSCheckListJsGrid\"]/div[2]/table/tbody/tr/td[2]/input[1]"));
			jse17.executeScript("arguments[0].click();", element17);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Tick Mark", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse06 = (JavascriptExecutor) driver;
			WebElement element06 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse06.executeScript("arguments[0].click();", element06);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse7 = (JavascriptExecutor) driver;
			WebElement element7 = driver.findElement(By.id("subBtnInValidateESign"));
			jse7.executeScript("arguments[0].click();", element7);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse8 = (JavascriptExecutor) driver;
			WebElement element8 = driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK", sno, false);
			jse8.executeScript("arguments[0].click();", element8);
			
		 Thread.sleep(3000);
		 
		sno++;
		JavascriptExecutor jse208 = (JavascriptExecutor) driver;
		WebElement element208 =driver.findElement(By.className("username"));
		jse208.executeScript("arguments[0].click();", element208);;
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on UserName",sno,false);
		 Thread.sleep(3000);
		 sno++;
		 JavascriptExecutor jse209 = (JavascriptExecutor) driver;
		 WebElement element209 =driver.findElement(By.cssSelector("a[href='Logout.do']"));
		jse209.executeScript("arguments[0].click();", element209);
		 document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout",sno,true);
		 Thread.sleep(3000);

		} else {
			System.out.println("Record is not Selected");
		}
	}
}
