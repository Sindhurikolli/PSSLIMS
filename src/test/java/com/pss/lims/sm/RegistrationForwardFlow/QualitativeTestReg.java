package com.pss.lims.sm.RegistrationForwardFlow;

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

public class QualitativeTestReg extends RegistrationLoginDetails {

	@Test
	public void createTest() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "QualitativeTestReg"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Qualitative Test", "PSS-LIMS-063", "Pass");
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
		WebElement element1 = driver.findElement(By.cssSelector("a[href='limsSmTest.do']"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Test", sno, false);
		Thread.sleep(4000);
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
		JavascriptExecutor jse12 = (JavascriptExecutor) driver;
		WebElement element12 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse12.executeScript("arguments[0].click();", element12);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(5000);
		sno++;
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.id("selProdBtnInSmTest"));
		jse2.executeScript("arguments[0].click();", element2);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse3 = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.id("locTreeInLimsSmReg_2_span"));
		jse3.executeScript("arguments[0].click();", element3);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#productsTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(3000);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
		String product = properties.getProperty("RMProduct_Code");
		isRecordSelected = selectRecordForProduct(count, isRecordSelected, product);
		if (isRecordSelected) {
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Record", sno, false);
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
			driver.findElement(By.id("testNameInSmTest")).sendKeys(properties.getProperty("Qualitative_Test_Name"));
			Thread.sleep(2000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Test Name", sno, false);
			sno++;
			driver.findElement(By.id("methodNumberInSmTest")).sendKeys(properties.getProperty("Method_No"));
			Thread.sleep(2000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Method No", sno, false);
			sno++;
			Select testCategory = new Select(driver.findElement(By.id("testCategoryInSmTest")));
			Thread.sleep(2000);
			testCategory.selectByVisibleText(properties.getProperty("Category_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Category", sno, false);
			Thread.sleep(2000);
			sno++;
			Select testType = new Select(driver.findElement(By.id("testCodeForSelCatInSmTest")));
			Thread.sleep(2000);
			testType.selectByVisibleText(properties.getProperty("TestCode"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Test Code", sno, false);
			sno++;
			Select lifeCycle = new Select(driver.findElement(By.id("appLifeCycleInSmTest")));
			Thread.sleep(2000);
			lifeCycle.selectByVisibleText(properties.getProperty("LifeCycle_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select LifeCycle", sno, false);
			Thread.sleep(2000);
			sno++;
			Select type = new Select(driver.findElement(By.id("testTypeInSmTest")));
			Thread.sleep(2000);
			type.selectByVisibleText(properties.getProperty("TestType"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Test", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse.executeScript("arguments[0].click();", element);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("numberOfSubTestsInSubTestQualitative"))
					.sendKeys(properties.getProperty("Qualitative_Test_No_of_variables"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No. Variables", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse5 = (JavascriptExecutor) driver;
			WebElement element5 = driver.findElement(By.id("loadBtnInSubTestQualitative"));
			jse5.executeScript("arguments[0].click();", element5);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Load", sno, false);
			Thread.sleep(5000);
			sno++;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement elemen = driver
					.findElement(By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr/td[1]"));
			js.executeScript("arguments[0].click();", elemen);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[1]/td[1]/input"))
					.sendKeys(properties.getProperty("Qualitative_Test_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Test Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[1]/td[2]/input"))
					.sendKeys(properties.getProperty("Compliance_Value1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Compliance Value1", sno, false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[1]/td[3]/input"))
					.sendKeys(properties.getProperty("Compliance_Value2"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Compliance Value2", sno, false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[1]/td[4]/input"))
					.sendKeys(properties.getProperty("Compliance_Value3"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Compliance Value3", sno, false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[1]/td[5]/input"))
					.sendKeys(properties.getProperty("Compliance_Value4"));
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath("//*[@id=\"qualitativeSubTestJsGrid\"]/div[2]/table/tbody/tr[1]/td[6]/input"))
					.sendKeys(properties.getProperty("Non_Compliance_Value"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Non Compliance Value", sno,
					false);
			sno++;
			Thread.sleep(2000);
			driver.findElement(By.id("passLimitDescInSubTestQualitative"))
					.sendKeys(properties.getProperty("Pass_Limit_Description"));
			Thread.sleep(2000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Pass Limit Description", sno,
					false);
			sno++;
			JavascriptExecutor jse14 = (JavascriptExecutor) driver;
			WebElement element14 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse14.executeScript("arguments[0].click();", element14);
			Thread.sleep(2000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			sno++;
			driver.findElement(By.id("testProcedureInSmTest_ifr"))
					.sendKeys(properties.getProperty("Qualitative_Test_Procedure"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Qualitative Test Procedure", sno,
					false);
			sno++;
			JavascriptExecutor jse15 = (JavascriptExecutor) driver;
			WebElement element15 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse15.executeScript("arguments[0].click();", element15);
			Thread.sleep(2000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);

			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			Thread.sleep(3000);
			if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).getText()
					.equalsIgnoreCase("Submitted successfully.")) {
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
//			System.out.println("hi:" + a);
			String[] parts = a.split(" of ");
//			System.out.println("parts:" + parts.toString());
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println(totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
//			System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((product == null) || ("".equalsIgnoreCase(product)))) {
//				System.out.println("hi this is ravi");
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
						if (product.contains(DevNumberSequence)) {
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
					if (product.contains(DevNumberSequence)) {
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