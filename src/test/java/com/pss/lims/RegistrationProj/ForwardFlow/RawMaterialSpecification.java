package com.pss.lims.RegistrationProj.ForwardFlow;

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
import com.pss.lims.login.RegistrationLoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;
import com.pss.regproject.RegistrationDetails.RegistrationDetails;

public class RawMaterialSpecification extends RegistrationDetails {

	@Test
	public void createSpecification() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateSpecification"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Specification", "LIMS-SM-059", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='qmsSpecificationReg.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='qmsSpecificationReg.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on SPECIFICATION", sno, false);
		Thread.sleep(4000);
		driver.findElement(By.id("newSelectionInSpecification")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Creation of Specification", sno, false);
		methodTocreateSpecification();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodTocreateSpecification() throws Exception {

//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
//		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse12 = (JavascriptExecutor) driver;
		WebElement element12 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse12.executeScript("arguments[0].scrollIntoView(true);", element12);
		Thread.sleep(1000);
		jse12.executeScript("arguments[0].click();", element12);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("specNameInRegSpecification"))
				.sendKeys(properties.getProperty("RMSpecification_Name"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Specification Name", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.findElement(By.id("specIdInRegSpecification")).sendKeys(properties.getProperty("RMSpec_No"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Specification Number", sno,
				false);
		Thread.sleep(2000);
		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 70);
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.id("proBrowBtnInRegSpecificationAdd"));
		jse2.executeScript("arguments[0].click();", element2);
		driver.findElement(By.xpath(".//*[@id='ProSelRegSpecAddWinTable']/div/div[4]/table/tbody/tr[1]/td[3]")).click();
		driver.findElement(By.id("prodSelBtnIdInSpecReg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Customer Details", sno,
				false);
		Thread.sleep(3000);	
		sno++;
//		driver.findElement(By.id("locTreeInLimsSmReg_2_switch")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
//		Thread.sleep(2000);
		
		sno++;
		JavascriptExecutor jse3 = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.id("selAppBtnInRegSpecAdd"));
		jse3.executeScript("arguments[0].click();", element3);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(5000);
		sno++;
		JavascriptExecutor jse4 = (JavascriptExecutor) driver;
		WebElement element4 = driver.findElement(By.id("locTreeInQmsProdReg_2_switch"));
		jse4.executeScript("arguments[0].click();", element4);
		Thread.sleep(3000);
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
			JavascriptExecutor jse5 = (JavascriptExecutor) driver;
			WebElement element5 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse5.executeScript("arguments[0].click();", element5);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			Select spec = new Select(driver.findElement(By.id("sampleTypeInViewSpecEdit")));
			Thread.sleep(2000);
			spec.selectByVisibleText(properties.getProperty("RMSampleType_In_Spection"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Sample Type", sno, false);
			Thread.sleep(2000);
			
			sno++;
			driver.findElement(By.id("stpNoInRegSpecification"))
					.sendKeys(properties.getProperty("STP_No"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Standard Test Procedure No", sno,
					false);
			Thread.sleep(2000);
			
			sno++;
			Select specType = new Select(driver.findElement(By.id("specificationTypeInViewSpecEdit")));
			Thread.sleep(2000);
			specType.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Specification Type", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("descInRegSpecification")).sendKeys(properties.getProperty("Spec_Description"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse16 = (JavascriptExecutor) driver;
			WebElement element16 = driver.findElement(By.id("submitBtnInRegSpecificationAdd"));
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

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}

		
		
//		JavascriptExecutor jse13 = (JavascriptExecutor) driver;
//		WebElement element13 = driver.findElement(By.id("selAppBtnInRegSpecAdd"));
//		jse13.executeScript("arguments[0].click();", element13);
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
//		Thread.sleep(5000);
//		sno++;
//		JavascriptExecutor jse14 = (JavascriptExecutor) driver;
//		WebElement element14 = driver.findElement(By.id("locTreeInQmsProdReg_2_switch"));
//		jse14.executeScript("arguments[0].click();", element14);
//		Thread.sleep(3000);
//		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
//		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
//		WebDriverWait wait1 = new WebDriverWait(driver, 70);
//		wait1.until(ExpectedConditions.presenceOfElementLocated(
//				By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
//		Thread.sleep(2000);
		
		
//		int count = 0;
//		boolean isRecordSelected = false;
//		String selectingUserSingleApproval = properties.getProperty("LastName");
//		isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval, isRecordSelected,
//				count);
//		if (isRecordSelected) {
//			sno++;
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
//			Thread.sleep(2000);
//			sno++;
//			JavascriptExecutor jse4 = (JavascriptExecutor) driver;
//			WebElement element4 = driver.findElement(By.id("productSelBtnInProdSelWin"));
//			jse4.executeScript("arguments[0].click();", element4);
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
//			Thread.sleep(2000);
//			sno++;
//			Select spec = new Select(driver.findElement(By.id("sampleTypeInViewSpecEdit")));
//			Thread.sleep(2000);
//			spec.selectByVisibleText(properties.getProperty("SampleType_In_Spection"));
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Sample Type", sno, false);
//			Thread.sleep(2000);
//			
//			sno++;
//			driver.findElement(By.id("standardTestProcedureNoInViewSpecEdit"))
//					.sendKeys(properties.getProperty("STP_No"));
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Standard Test Procedure No", sno,
//					false);
//			Thread.sleep(2000);
//			
//			sno++;
//			Select specType = new Select(driver.findElement(By.id("specificationTypeInViewSpecEdit")));
//			Thread.sleep(2000);
//			specType.selectByIndex(1);
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Specification Type", sno, false);
//			Thread.sleep(2000);
//			sno++;
//			driver.findElement(By.id("descriptionInViewSpecEdit")).sendKeys(properties.getProperty("Spec_Description"));
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description", sno, false);
//			Thread.sleep(2000);
//			sno++;
//			Select lifeCycle = new Select(driver.findElement(By.id("lifeCycleInViewSpecEdit")));
//			Thread.sleep(2000);
//			lifeCycle.selectByVisibleText(properties.getProperty("LifeCycle_Name"));
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Approval Cycle", sno, false);
//			Thread.sleep(3000);
//			sno++;
//			JavascriptExecutor jse5 = (JavascriptExecutor) driver;
//			WebElement element5 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
//			jse5.executeScript("arguments[0].scrollIntoView(true);", element5);
//			Thread.sleep(1000);
//			jse5.executeScript("arguments[0].click();", element5);
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
//			Thread.sleep(2000);
//			sno++;
//			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
//					false);
//			Thread.sleep(3000);
//			sno++;
//			driver.findElement(By.id("subBtnInValidateESign")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
//			wait.until(
//					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
//			Thread.sleep(3000);
//			if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).isDisplayed()) {
//				sno++;
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
//				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
//			}
//			Thread.sleep(3000);
//			sno++;
//			driver.findElement(By.className("username")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
//			Thread.sleep(2000);
//			sno++;
//			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);
//
//		} else {
//			System.out.println("Record is not Selected");
//			Assert.assertTrue(false);
//		}
//	}
//
//	private boolean selectRecordForProduct(int count, boolean isRecordSelected, String productCode) throws Exception {
//		WebElement table = driver.findElement(By.id("ProSelRegSpecAddWinTable"));
//		WebElement tableBody = table.findElement(By.tagName("tbody"));
//		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//		int totalNoOfRecords = 0;
//		int NoOfRecordsChecked = 0;
//		System.out.println(productCode);
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver.findElement(By.xpath("//*[@id=\"ProSelRegSpecAddWinTable\"]/div/div[5]/div[2]/span"))
//					.getText();
//			String[] parts = a.split("of");
//			try {
//				totalNoOfRecords = Integer.parseInt(parts[1].trim());
//				System.out.println(totalNoOfRecords);
//			} catch (Exception e) {
//
//				System.out.println(e.getMessage());
//			}
//		}
//
//		if (perPageNoOfRecordsPresent > 0 && count == 0) {
//			if ((totalNoOfRecords > 1) && ((productCode == null) || ("".equalsIgnoreCase(productCode)))) {
//				productCode = driver
//						.findElement(By.xpath("//*[@id=\"ProSelRegSpecAddWinTable\"]/div/div[4]/table/tbody/tr[4]/td[3]"))
//						.getText();
//				System.out.println(productCode);
//			} else if ((productCode == null) || ("".equalsIgnoreCase(productCode))) {
//				productCode = driver.findElement(By.id("//*[@id=\"ProSelRegSpecAddWinTable\"]/div/div[4]/table/tbody/tr[4]/td[3]"))
//						.getText();
//				System.out.println(productCode);
//			}
//			++count;
//
//		}
//		if (perPageNoOfRecordsPresent > 0) {
//			while (NoOfRecordsChecked < totalNoOfRecords) {
//				if (totalNoOfRecords > 1) {
//					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
//						String productCodeSequence = driver
//								.findElement(By.xpath(
//										"//*[@id=\"ProSelRegSpecAddWinTable\"]/div/div[4]/table/tbody/tr[" + i + "]/td[3]"))
//								.getText();
//						if (productCode.equalsIgnoreCase(productCodeSequence)) {
//							driver.findElement(
//									By.xpath("//*[@id=\"ProSelRegSpecAddWinTable\"]/div/div[4]/table/tbody/tr[" + i + "]/td[3]"))
//									.click();
//							isRecordSelected = true;
//							break;
//						}
//					}
//					if (isRecordSelected) {
//						break;
//					}
//				} else {
//					String productCodeSequence = driver
//							.findElement(By.xpath("//*[@id=\"ProSelRegSpecAddWinTable\"]/div/div[4]/table/tbody/tr/td[2]"))
//							.getText();
//					if (productCode.equalsIgnoreCase(productCodeSequence)) {
//						driver.findElement(By.xpath("//*[@id=\"ProSelRegSpecAddWinTable\"]/div/div[4]/table/tbody/tr/td[2]"))
//								.click();
//						isRecordSelected = true;
//						break;
//					}
//				}
//				NoOfRecordsChecked = NoOfRecordsChecked + perPageNoOfRecordsPresent;
//				if ((!isRecordSelected) && (NoOfRecordsChecked < totalNoOfRecords)) {
//					driver.findElement(By.cssSelector(
//							"#ProSelRegSpecAddWinTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
//							.click();
//					Thread.sleep(4000);
//					table = driver.findElement(By.id("ProSelRegSpecAddWinTable"));
//					tableBody = table.findElement(By.tagName("tbody"));
//					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//
//				}
//
//			}
//		}
//
//		return isRecordSelected;
//	}
//
//	@AfterMethod
//	public void testIT(ITestResult result)
//
//	{
//		if (ITestResult.FAILURE == result.getStatus()) {
//			Utility.captureScreenshot(driver, result.getName());
//		}
//
//	}
//}
