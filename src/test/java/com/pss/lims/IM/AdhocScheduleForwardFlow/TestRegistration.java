package com.pss.lims.IM.AdhocScheduleForwardFlow;

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
import com.pss.lims.IM.Login.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class TestRegistration extends LoginDetails {

	@Test
	public void registerTest() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "TestRegistration" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Test Registration", "LIMS-IMS-005", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='testRegistrationInIMS.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='testRegistrationInIMS.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Test", sno, false);
		Thread.sleep(4000);
		methodForTestRegistration();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodForTestRegistration() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 150);
//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
//		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver
				.findElement(By.cssSelector("#TotalContent > div.actions.clearfix > ul > li:nth-child(2) > a"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("testNameInImsCreteTestForm")).sendKeys(properties.getProperty("TestName"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Test Name", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse3 = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.id("selInstrumentCategryDetlsBtnInTestForm"));
		jse3.executeScript("arguments[0].click();", element3);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse31 = (JavascriptExecutor) driver;
		WebElement element31 = driver.findElement(By.id("srchBtnInInstCategorySelWindow"));
		jse31.executeScript("arguments[0].click();", element31);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#tableInInstCategorySelectionWindow > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(5000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String category = properties.getProperty("Category_Name");
		isRecordSelected1 = selectRecordForTest(count1, isRecordSelected1, category);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse311 = (JavascriptExecutor) driver;
			WebElement element311 = driver.findElement(By.id("selectBtnInInstCategorySelWindow"));
			jse311.executeScript("arguments[0].click();", element311);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("testProcedureInImsCreateTestForm_ifr"))
					.sendKeys(properties.getProperty("Test_Procedure"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Test Procedure", sno, false);
			Thread.sleep(2000);
			jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
			Thread.sleep(2000);
			jse1.executeScript("arguments[0].click();", element1);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(5000);
			String type = properties.getProperty("Test_Type");
			if (type.equalsIgnoreCase("DESCRIPTIVE")) {
				sno++;
				Select testtype = new Select(driver.findElement(By.id("subTestTypeComboInIms")));
				Thread.sleep(1000);
				testtype.selectByVisibleText(properties.getProperty("Test_Type"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Type", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("noOfSubTestsInImsForm")).sendKeys(properties.getProperty("No_Tests"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Tests", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("commonSpecifCheckBoxIdInIms")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Check Box", sno, false);
				Thread.sleep(2000);
				sno++;
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement ele = driver.findElement(By.linkText("Next"));
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", ele);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(5000);
				sno++;
				driver.findElement(By.id("specDescInViewSubTestGridsForm"))
						.sendKeys(properties.getProperty("Description"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Specification Description",
						sno, false);
				Thread.sleep(2000);
				int nofTests = Integer.parseInt(properties.getProperty("No_Tests"));
				String[] name = properties.getProperty("SubTestName").split(",");
				for (int i = 1; i <= nofTests; i++) {
					sno++;
					driver.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
							+ i + "]/td[2]/textarea")).sendKeys(name[i - 1]);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Sub Test Type", sno,
							false);
					Thread.sleep(2000);
				}

			} else if (type.equalsIgnoreCase("NOT LESS THAN")) {
				sno++;
				Select testtype = new Select(driver.findElement(By.id("subTestTypeComboInIms")));
				Thread.sleep(1000);
				testtype.selectByVisibleText(properties.getProperty("Test_Type"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Type", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("noOfSubTestsInImsForm")).sendKeys(properties.getProperty("No_Tests"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Tests", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("commonSpecifCheckBoxIdInIms")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Check Box", sno, false);
				Thread.sleep(2000);
				sno++;
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement ele = driver.findElement(By.linkText("Next"));
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", ele);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(5000);
				sno++;
				driver.findElement(By.id("specDescInViewSubTestGridsForm"))
						.sendKeys(properties.getProperty("Description"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Specification Description",
						sno, false);
				Thread.sleep(2000);
				int nofTests = Integer.parseInt(properties.getProperty("No_Tests"));
				String[] name = properties.getProperty("SubTestName").split(",");
				String[] value = properties.getProperty("MinValue").split(",");
				for (int i = 1; i <= nofTests; i++) {
					sno++;
					driver.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
							+ i + "]/td[2]/textarea")).sendKeys(name[i - 1]);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Sub Test Type", sno,
							false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
							+ i + "]/td[4]/input")).sendKeys(value[i - 1]);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Min Value", sno, false);
					Thread.sleep(2000);
					sno++;
					Select uom = new Select(driver
							.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
									+ i + "]/td[5]/select")));
					Thread.sleep(1000);
					uom.selectByIndex(1);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
							+ i + "]/td[8]/button")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.id("maxValInEvaluate")).sendKeys(properties.getProperty("MaxValue"));
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Max Value", sno, false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.id("evaluateBtnInSubTestWindowDtls")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
					Thread.sleep(3000);
					sno++;
					driver.findElement(By.id("closeBtnInSubTestWindowDtls")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Close", sno, false);
					Thread.sleep(3000);
				}

			} else if (type.equalsIgnoreCase("NOT MORE THAN")) {
				sno++;
				Select testtype = new Select(driver.findElement(By.id("subTestTypeComboInIms")));
				Thread.sleep(1000);
				testtype.selectByVisibleText(properties.getProperty("Test_Type"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Type", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("noOfSubTestsInImsForm")).sendKeys(properties.getProperty("No_Tests"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Tests", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("commonSpecifCheckBoxIdInIms")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Check Box", sno, false);
				Thread.sleep(2000);
				sno++;
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement ele = driver.findElement(By.linkText("Next"));
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", ele);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(5000);
				sno++;
				driver.findElement(By.id("specDescInViewSubTestGridsForm"))
						.sendKeys(properties.getProperty("Description"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Specification Description",
						sno, false);
				Thread.sleep(2000);
				int nofTests = Integer.parseInt(properties.getProperty("No_Tests"));
				String[] name = properties.getProperty("SubTestName").split(",");
				String[] value = properties.getProperty("MaxValue").split(",");
				for (int i = 1; i <= nofTests; i++) {
					sno++;
					driver.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
							+ i + "]/td[2]/textarea")).sendKeys(name[i - 1]);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Sub Test Type", sno,
							false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
							+ i + "]/td[6]/input")).sendKeys(value[i - 1]);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Max Value", sno, false);
					Thread.sleep(2000);
					sno++;
					Select uom = new Select(driver
							.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
									+ i + "]/td[7]/select")));
					Thread.sleep(1000);
					uom.selectByIndex(1);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
							+ i + "]/td[8]/button")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.id("maxValInEvaluate")).sendKeys(properties.getProperty("MaxValue"));
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Max Value", sno, false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.id("evaluateBtnInSubTestWindowDtls")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
					Thread.sleep(3000);
					sno++;
					driver.findElement(By.id("closeBtnInSubTestWindowDtls")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Close", sno, false);
					Thread.sleep(3000);
				}

			} else if (type.equalsIgnoreCase("RANGE")) {
				sno++;
				Select testtype = new Select(driver.findElement(By.id("subTestTypeComboInIms")));
				Thread.sleep(1000);
				testtype.selectByVisibleText(properties.getProperty("Test_Type"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Type", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("noOfSubTestsInImsForm")).sendKeys(properties.getProperty("No_Tests"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Tests", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("commonSpecifCheckBoxIdInIms")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Check Box", sno, false);
				Thread.sleep(2000);
				sno++;
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement ele = driver.findElement(By.linkText("Next"));
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", ele);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(5000);
				sno++;
				driver.findElement(By.id("specDescInViewSubTestGridsForm"))
						.sendKeys(properties.getProperty("Description"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Specification Description",
						sno, false);
				Thread.sleep(2000);
				int nofTests = Integer.parseInt(properties.getProperty("No_Tests"));
				String[] name = properties.getProperty("SubTestName").split(",");
				String[] minvalue = properties.getProperty("MaxValue").split(",");
				String[] maxvalue = properties.getProperty("MaxValue").split(",");
				for (int i = 1; i <= nofTests; i++) {
					sno++;
					driver.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
							+ i + "]/td[2]/textarea")).sendKeys(name[i - 1]);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Sub Test Type", sno,
							false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
							+ i + "]/td[4]/input")).sendKeys(minvalue[i - 1]);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Min Value", sno, false);
					Thread.sleep(2000);
					sno++;
					Select uom = new Select(driver
							.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
									+ i + "]/td[5]/select")));
					Thread.sleep(1000);
					uom.selectByIndex(1);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
							+ i + "]/td[6]/input")).sendKeys(maxvalue[i - 1]);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Max Value", sno, false);
					Thread.sleep(2000);
					sno++;
					Select uom1 = new Select(driver
							.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
									+ i + "]/td[7]/select")));
					Thread.sleep(1000);
					uom1.selectByIndex(1);
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.xpath("//*[@id=\"tesTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr["
							+ i + "]/td[8]/button")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.id("rangeValInEvaluate")).sendKeys(properties.getProperty("MaxValue"));
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Max Value", sno, false);
					Thread.sleep(2000);
					sno++;
					driver.findElement(By.id("evaluateBtnInSubTestWindowDtls")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
					Thread.sleep(3000);
					sno++;
					driver.findElement(By.id("closeBtnInSubTestWindowDtls")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Close", sno, false);
					Thread.sleep(3000);
				}
			} else {
				sno++;
				Select testtype = new Select(driver.findElement(By.id("subTestTypeComboInIms")));
				Thread.sleep(1000);
				testtype.selectByVisibleText(properties.getProperty("Test_Type"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Type", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("noOfSubTestsInImsForm")).sendKeys(properties.getProperty("No_Tests"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Tests", sno, false);
				Thread.sleep(2000);
				sno++;
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement ele = driver.findElement(By.linkText("Next"));
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", ele);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(5000);
				sno++;
				Select stype = new Select(driver.findElement(By.id("subTestTypeDrpDown_1")));
				Thread.sleep(1000);
				stype.selectByIndex(2);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Sub TestType", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("subTestNameInOthers_1")).sendKeys(properties.getProperty("SubTestName"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Sub Test Type", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("specDescInOthers_1")).sendKeys(properties.getProperty("Description"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Specification Description",
						sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("specOrMinValInOthers_1")).sendKeys(properties.getProperty("MinValue"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Min Value", sno, false);
				Thread.sleep(2000);
				sno++;
				Select uom = new Select(driver.findElement(By.id("specOrMinValUOMDrpDown_1")));
				Thread.sleep(1000);
				uom.selectByIndex(1);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
				Thread.sleep(2000);
//				sno++;
//				driver.findElement(By.id("maxValSubTestInOthers_1")).sendKeys(properties.getProperty("MaxValue"));
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Max Value", sno, false);
//				Thread.sleep(2000);
//				sno++;
//				Select uom1 = new Select(driver.findElement(By.id("maxValUOMDrpDown_1")));
//				Thread.sleep(1000);
//				uom1.selectByIndex(1);
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
//				Thread.sleep(2000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"othersTypeSubTestDetailsTableContainer\"]/div/table/tbody/tr/td[9]/button"))
						.click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("maxValInEvaluate")).sendKeys(properties.getProperty("MaxValue"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Max Value", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("evaluateBtnInSubTestWindowDtls")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("closeBtnInSubTestWindowDtls")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Close", sno, false);
				Thread.sleep(3000);
			}

			sno++;
			JavascriptExecutor jse13 = (JavascriptExecutor) driver;
			WebElement element13 = driver.findElement(By.id("selecttheApprovalInSubTestType"));
			jse13.executeScript("arguments[0].click();", element13);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(5000);
			sno++;
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			Thread.sleep(2000);
			JavascriptExecutor jse3111 = (JavascriptExecutor) driver;
			WebElement element3111 = driver.findElement(By.id("locTreeInCalPmBdm_2_switch"));
			jse3111.executeScript("arguments[0].click();", element3111);
			Thread.sleep(3000);
			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("usersSearchBtnInRepProb")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(5000);
			int count = 0;
			boolean isRecordSelected = false;
			String selectingUserSingleApproval = properties.getProperty("LastName");
			isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval,
					isRecordSelected, count);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse5 = (JavascriptExecutor) driver;
			WebElement element5 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse5.executeScript("arguments[0].click();", element5);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse6 = (JavascriptExecutor) driver;
			WebElement element6 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse6.executeScript("arguments[0].scrollIntoView(true);", element6);
			Thread.sleep(1000);
			jse6.executeScript("arguments[0].click();", element6);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse7 = (JavascriptExecutor) driver;
			WebElement element7 = driver.findElement(By.id("subBtnInValidateESign"));
			jse7.executeScript("arguments[0].click();", element7);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(3000);
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

	private boolean selectRecordForTest(int count1, boolean isRecordSelected1, String category) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("tableInInstCategorySelectionWindow"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/div[4]/div[2]/span"))
					.getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println(totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((category == null) || ("".equalsIgnoreCase(category)))) {
				category = driver
						.findElement(
								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((category == null) || ("".equalsIgnoreCase(category))) {
				category = driver
						.findElement(
								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr/td[3]"))
						.getText();// document
									// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (category.equalsIgnoreCase(DevNumberSequence)) {
//							
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(
									By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr[" + i
											+ "]/td[3]"));
							jse8.executeScript("arguments[0].click();", element8);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By
									.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (category.equalsIgnoreCase(DevNumberSequence)) {
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver.findElement(
								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr/td[3]"));
						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#tableInInstCategorySelectionWindow > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("tableInInstCategorySelectionWindow"));// Document Tree approve
																							// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected1;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
