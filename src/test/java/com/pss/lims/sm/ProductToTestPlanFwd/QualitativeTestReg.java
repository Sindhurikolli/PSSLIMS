package com.pss.lims.sm.ProductToTestPlanFwd;

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
import com.pss.lims.login.SMLoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;

public class QualitativeTestReg extends SMLoginDetails {

	@Test
	public void createQualitativeTest() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateQualitativeTest"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Qualitative Test", "LIMS-SM-056", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='limsSmTest.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='limsSmTest.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on TEST", sno, false);
		Thread.sleep(4000);
		methodTocreateTest();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodTocreateTest() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		sno++;
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
		Thread.sleep(1000);
		jse2.executeScript("arguments[0].click();", element2);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(5000);
		sno++;
		JavascriptExecutor jse112 = (JavascriptExecutor) driver;
		WebElement element112 = driver.findElement(By.id("selProdBtnInSmTest"));
		jse112.executeScript("arguments[0].click();", element112);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse3 = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.id("locTreeInLimsSmReg_2_switch"));
		jse3.executeScript("arguments[0].click();", element3);
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#productsTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String product = properties.getProperty("Product_Code");
		isRecordSelected = selectRecordForProduct(count, isRecordSelected, product);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse4 = (JavascriptExecutor) driver;
			WebElement element4 = driver.findElement(By.id("productSelBtnInProdSelWin"));
			jse4.executeScript("arguments[0].click();", element4);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			Select spec = new Select(driver.findElement(By.id("specificationInSmTest")));
			Thread.sleep(2000);
			spec.selectByVisibleText(properties.getProperty("Specification_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Specification", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("testNameInSmTest")).sendKeys(properties.getProperty("Test_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Test Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("methodNumberInSmTest")).sendKeys(properties.getProperty("Method_No"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Method Number", sno, false);
			Thread.sleep(2000);
			sno++;
			Select testCategory = new Select(driver.findElement(By.id("testCategoryInSmTest")));
			Thread.sleep(2000);
			testCategory.selectByVisibleText(properties.getProperty("Category_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Test Category", sno, false);
			Thread.sleep(2000);
			sno++;
			Select testType = new Select(driver.findElement(By.id("testCodeForSelCatInSmTest")));
			Thread.sleep(2000);
			testType.selectByVisibleText(properties.getProperty("Category_TestCode"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Test Code", sno, false);
			Thread.sleep(2000);
			sno++;
			Select lifeCycle = new Select(driver.findElement(By.id("appLifeCycleInSmTest")));
			Thread.sleep(1000);
			lifeCycle.selectByVisibleText(properties.getProperty("LifeCycle_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Life Cycle", sno, false);
			Thread.sleep(2000);
			sno++;
			Select type = new Select(driver.findElement(By.id("testTypeInSmTest")));
			Thread.sleep(2000);
			type.selectByVisibleText(properties.getProperty("TestTypeforQualitative"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Test Type", sno, false);
			Thread.sleep(2000);
			sno++;
			jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
			Thread.sleep(1000);
			jse2.executeScript("arguments[0].click();", element2);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("numberOfSubTestsInSubTestQualitative"))
					.sendKeys(properties.getProperty("Qualitative_Test_No_of_variables"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Variables", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("loadBtnInSubTestQualitative")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Load", sno, false);
			Thread.sleep(2000);
			int noofVariables = Integer.parseInt(properties.getProperty("Qualitative_Test_No_of_variables"));
			String[] testName = properties.getProperty("Qualitative_Test_Name").split(",");
			String[] value1 = properties.getProperty("Compliance_Value1").split(",");
			String[] value2 = properties.getProperty("Compliance_Value2").split(",");
			String[] value3 = properties.getProperty("Compliance_Value3").split(",");
			String[] value4 = properties.getProperty("Compliance_Value4").split(",");
			String[] nonCompliance = properties.getProperty("Non_Compliance_Value").split(",");
			for (int i = 1; i <= noofVariables; i++) {
				sno++;
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement elemen = driver.findElement(
						By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[" + i + "]/td[1]"));
				js.executeScript("arguments[0].click();", elemen);
				Thread.sleep(2000);//*[@id="qualitativeSubTestJsGrid"]/div[2]/table/tbody/tr[1]/td[1]/input
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Edit", sno, false);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[" + i + "]/td[1]/input"))
						.sendKeys(testName[i -1]);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Name", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[" + i + "]/td[2]/input"))
						.sendKeys(value1[i -1]);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Complaince Value 1", sno,
						false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[" + i + "]/td[3]/input"))
						.sendKeys(value2[i - 1]);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Complaince Value 2", sno,
						false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[" + i + "]/td[4]/input"))
						.sendKeys(value3[i - 1]);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Complaince Value 3", sno,
						false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[" + i + "]/td[5]/input"))
						.sendKeys(value4[i - 1]);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Complaince Value 4", sno,
						false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[" + i + "]/td[6]/input"))
						.sendKeys(nonCompliance[i - 1]);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Non-Complaince Value", sno,
						false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[" + i + "]/td[7]/input"))
						.click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Update", sno, false);
				Thread.sleep(3000);
			}
			sno++;
			driver.findElement(By.id("passLimitDescInSubTestQualitative"))
					.sendKeys(properties.getProperty("Pass_Limit_Description"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Pass Limit Description", sno,
					false);
			Thread.sleep(2000);
			sno++;
			jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
			Thread.sleep(1000);
			jse2.executeScript("arguments[0].click();", element2);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(5000);
			sno++;
			driver.findElement(By.id("testProcedureInSmTest_ifr"))
					.sendKeys(properties.getProperty("Qualitative_Test_Procedure"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Test Procedure", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse15 = (JavascriptExecutor) driver;
			WebElement element15 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse15.executeScript("arguments[0].scrollIntoView(true);", element15);
			Thread.sleep(1000);
			jse15.executeScript("arguments[0].click();", element15);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse16 = (JavascriptExecutor) driver;
			WebElement element16 = driver.findElement(By.id("subBtnInValidateESign"));
			jse16.executeScript("arguments[0].click();", element16);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(3000);
			WebDriverWait wait1 = new WebDriverWait(driver, 70);
			wait1.until(
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

	private boolean selectRecordForProduct(int count, boolean isRecordSelected, String product) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("productsTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For
			// Ex:
			// Showing
			// 1-1
			// of
			// 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((product == null) || ("".equalsIgnoreCase(product)))) {
				product = driver
						.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((product == null) || ("".equalsIgnoreCase(product))) {
				product = driver.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();// document
									// type
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (product.equalsIgnoreCase(DevNumberSequence)) {
//							driver.findElement(By.xpath(
//									"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
//									.click();
							JavascriptExecutor jse9 = (JavascriptExecutor) driver;
							WebElement element9 = driver.findElement(By.xpath(
									"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"));
							jse9.executeScript("arguments[0].click();", element9);
//							Thread.sleep(2000);
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (product.equalsIgnoreCase(DevNumberSequence)) {
//						driver.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"))
//								.click();
						JavascriptExecutor jse11 = (JavascriptExecutor) driver;
						WebElement element11 = driver
								.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"));
						jse11.executeScript("arguments[0].click();", element11);
//						Thread.sleep(2000);
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#productsTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("productsTableContainer"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelected;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}