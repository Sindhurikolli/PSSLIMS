package com.pss.lims.Registration.RegistrationForwardFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
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
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;
import com.pss.regproject.RegistrationDetails.RegistrationDetails;

public class Product extends RegistrationDetails {

	@Test
	public void createProduct() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateProduct" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Product", "LIMS-SM-056", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Initiator_Login"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
//		Select module = new Select(driver.findElement(By.id("limsModule")));
//		Thread.sleep(1000);
//		module.selectByVisibleText(properties.getProperty("Lims_Module_Name1"));
		Thread.sleep(1000);
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id='loginform']/div[3]/button[1]")).click();
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='qmsProductReg.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='qmsProductReg.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on PRODUCT", sno, false);
		Thread.sleep(4000);
		driver.findElement(By.id("newSelectionInProduct")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Creation of Product", sno, false);
		Thread.sleep(4000);
		methodTocreateProduct();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodTocreateProduct() throws Exception {

//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
		Thread.sleep(1000);
		jse2.executeScript("arguments[0].click();", element2);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("productNameInRegisterProdInQms")).sendKeys(properties.getProperty("Product_name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product Name", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("productCodeInRegisterProdInQms")).sendKeys(properties.getProperty("Product_Code"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product Code", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("genericNameInRegisterProdInQms")).sendKeys(properties.getProperty("Genaric_name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Generic Name", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("packSizeInRegisterProdInQms")).sendKeys(properties.getProperty("Pack_Size"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Generic Name", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("strengthInRegisterProdInQms")).sendKeys(properties.getProperty("Pack_Strength"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Generic Name", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse3 = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.id("selplantBtnInProdReg"));
		jse3.executeScript("arguments[0].click();", element3);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(5000);
		sno++;
		JavascriptExecutor jse4 = (JavascriptExecutor) driver;
		WebElement element4 = driver.findElement(By.id("treeContainer_2_switch"));
		jse4.executeScript("arguments[0].click();", element4);
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse5 = (JavascriptExecutor) driver;
		WebElement element5 = driver.findElement(By.id("selectBtnInLocSelect"));
		jse5.executeScript("arguments[0].click();", element5);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(2000);
		
		sno++;
		Select productType = new Select(driver.findElement(By.id("productTypeInRegisterProdInQms")));
		Thread.sleep(2000);
		productType.selectByIndex(1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Product Type", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("prodDescriptionInRegisterProdInQms")).sendKeys(properties.getProperty("Product_Discription"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse13 = (JavascriptExecutor) driver;
		WebElement element13 = driver.findElement(By.id("productRegSelect"));
		jse13.executeScript("arguments[0].click();", element13);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(5000);
		sno++;
		JavascriptExecutor jse14 = (JavascriptExecutor) driver;
		WebElement element14 = driver.findElement(By.id("locTreeInQmsProdReg_2_switch"));
		jse14.executeScript("arguments[0].click();", element14);
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		WebDriverWait wait = new WebDriverWait(driver, 70);
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
			JavascriptExecutor jse15 = (JavascriptExecutor) driver;
			WebElement element15 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse15.executeScript("arguments[0].click();", element15);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			
			sno++;
			JavascriptExecutor jse6 = (JavascriptExecutor) driver;
			WebElement element6 = driver.findElement(By.id("selBtnToAddCustomersInProdReg"));
			jse6.executeScript("arguments[0].click();", element6);
			sno++;
			driver.findElement(By.id("customerNameInSelCustWdw")).sendKeys(properties.getProperty("Customer_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Customer Name", sno, false);
			JavascriptExecutor jse61 = (JavascriptExecutor) driver;
			WebElement element61 = driver.findElement(By.id("searchBtnInCustomerWin"));
			jse61.executeScript("arguments[0].click();", element61);
			Thread.sleep(2000);
			
			driver.findElement(By.xpath(".//*[@id='customerTableInProdRegnWin']/div/div[4]/table/tbody/tr/td[3]")).click();
			driver.findElement(By.id("SelBtnInCustomerWin")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Customer Details", sno,
					false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse16 = (JavascriptExecutor) driver;
			WebElement element16 = driver.findElement(By.id("regProdSubBtnId"));
			jse16.executeScript("arguments[0].click();", element16);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click On Submit", sno,
					false);
			Thread.sleep(3000);
//			sno++;
//			JavascriptExecutor jse6 = (JavascriptExecutor) driver;
//			WebElement element6 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
//			jse6.executeScript("arguments[0].scrollIntoView(true);", element6);
//			Thread.sleep(2000);
//			jse6.executeScript("arguments[0].click();", element6);
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
//			Thread.sleep(2000);
			
			
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
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

		
//		sno++;
//		Select lifeCycle = new Select(driver.findElement(By.id("lifeCycleInEditProduct")));
//		Thread.sleep(2000);
//		lifeCycle.selectByVisibleText(properties.getProperty("LifeCycle_Name"));
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Life Cycle", sno, false);
//		Thread.sleep(2000);
		
//		sno++;
//		JavascriptExecutor jse6 = (JavascriptExecutor) driver;
//		WebElement element6 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
//		jse6.executeScript("arguments[0].scrollIntoView(true);", element6);
//		Thread.sleep(1000);
//		jse6.executeScript("arguments[0].click();", element6);
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
//		Thread.sleep(2000);
//		sno++;
//		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno, false);
//		Thread.sleep(3000);
//		sno++;
//		JavascriptExecutor jse7 = (JavascriptExecutor) driver;
//		WebElement element7 = driver.findElement(By.id("subBtnInValidateESign"));
//		jse7.executeScript("arguments[0].click();", element7);
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
//		WebDriverWait wait1 = new WebDriverWait(driver, 70);
//		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
//		Thread.sleep(3000);
//		if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).isDisplayed()) {
//			sno++;
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
//			driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
//		}
//		Thread.sleep(3000);
//		sno++;
//		driver.findElement(By.className("username")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
//		Thread.sleep(2000);
//		sno++;
//		driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);
//
//	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
