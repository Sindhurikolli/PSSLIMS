package com.pss.lims.sm.Registration.ModifyForwardFlow;

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

public class ModifyTest extends SMLoginDetails {
	@Test
	public void modifyExtTest() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ModifyTest" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Modify Test", "LIMS-SM-077", "Pass");
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
		methodToModifiyExtTest();
		Thread.sleep(5000);
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToModifiyExtTest() throws Exception {

		sno++;
		WebDriverWait wait = new WebDriverWait(driver, 150);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("modifyTestSelInSmTest")));
		driver.findElement(By.id("modifyTestSelInSmTest")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Modification of Existing Tests",
				sno, false);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#modifiedTestDetailsTableInSmTest > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(5000);
		int count = 0;
		boolean isRecordSelected = false;
		String test = properties.getProperty("Test_Name");
		isRecordSelected = selectExtTestRecord(count, isRecordSelected, test);
		if (isRecordSelected) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(5000);
			sno++;
//			JavascriptExecutor jse2 = (JavascriptExecutor) driver;
//			WebElement element2 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
			jse1.executeScript("arguments[0].click();", element1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(4000);
//			sno++;
//			Select lc = new Select(driver.findElement(By.id("appLifeCycleInSmTest")));
//			Thread.sleep(1000);
//			lc.selectByVisibleText(properties.getProperty("LifeCycle_Name"));
//			Thread.sleep(1000);
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Approval Cycle", sno, false);
//			Thread.sleep(2000);
//			sno++;
//			driver.findElement(By.id("selProdBtnInSmTest")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
//			Thread.sleep(5000);
//			sno++;
//			driver.findElement(By.id("locTreeInLimsSmReg_2_switch")).click();
//			Thread.sleep(3000);
//			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
//			wait.until(ExpectedConditions.presenceOfElementLocated(
//					By.cssSelector("#productsTableContainer > div > div.jtable-busy-message[style='display: none;']")));
//			Thread.sleep(2000);
//			sno++;
//			int count1 = 0;
//			boolean isRecordSelected1 = false;
//			String product = properties.getProperty("Product_Code");
//			isRecordSelected1 = selectRecordForProduct(count1, isRecordSelected1, product);
//			if (isRecordSelected1) {
//				sno++;
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
//				Thread.sleep(2000);
//				sno++;
//				JavascriptExecutor jse4 = (JavascriptExecutor) driver;
//				WebElement element4 = driver.findElement(By.id("productSelBtnInProdSelWin"));
//				jse4.executeScript("arguments[0].click();", element4);
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
//				Thread.sleep(2000);
//				sno++;
//				Select spec = new Select(driver.findElement(By.id("specificationInSmTest")));
//				Thread.sleep(2000);
//				spec.selectByVisibleText(properties.getProperty("Specification_Name"));
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Specification", sno, false);
//				Thread.sleep(2000);
			sno++;
			jse1.executeScript("arguments[0].click();", element1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(5000);
			sno++;
			jse1.executeScript("arguments[0].click();", element1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(5000);
			sno++;
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("TotalContent-p-3"))));
			Thread.sleep(3000);
			JavascriptExecutor jse11 = (JavascriptExecutor) driver;
			WebElement element11 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse11.executeScript("arguments[0].scrollIntoView(true);", element11);
			Thread.sleep(1000);
			jse11.executeScript("arguments[0].click();", element11);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(4000);
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
			System.out.println("Product is not Selected ");
			Assert.assertTrue(false);
		}
//	}
	}

	private boolean selectExtTestRecord(int count, boolean isRecordSelected, String test) throws Exception {
		WebElement table = driver.findElement(By.id("modifiedTestDetailsTableInSmTest"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"modifiedTestDetailsTableInSmTest\"]/div/div[4]/div[2]/span"))
					.getText();
			String parts[] = a.split("of");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println(totalNoOfRecords);
			} catch (Exception e) {

				System.out.println(e.getMessage());
			}
		}

		if (perPageNoOfRecordsPresent > 0 && (count == 0)) {
			if ((totalNoOfRecords > 1) && ((test == null) || ("".equalsIgnoreCase(test)))) {
				test = driver
						.findElement(By.xpath("//*[@id=\"modifiedTestDetailsTableInSmTest\"]/div/table/tbody/tr/td[3]"))
						.getText();
			} else if ((test == null) || ("".equalsIgnoreCase(test))) {

				test = driver
						.findElement(By.xpath("//*[@id=\"modifiedTestDetailsTableInSmTest\"]/div/table/tbody/tr/td[3]"))
						.getText();
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String testSequence = driver.findElement(By.xpath(
								"//*[@id=\"modifiedTestDetailsTableInSmTest\"]/div/table/tbody/tr[" + i + "]/td[3]"))
								.getText();
						if (test.contains(testSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"modifiedTestDetailsTableInSmTest\"]/div/table/tbody/tr[" + i
											+ "]/td[3]"))
									.click();
							isRecordSelected = true;
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {

					String testSequence = driver
							.findElement(
									By.xpath("//*[@id=\"modifiedTestDetailsTableInSmTest\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (test.contains(testSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"modifiedTestDetailsTableInSmTest\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}

				noOfRecordsChecked = noOfRecordsChecked + perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked > totalNoOfRecords)) {

					driver.findElement(By.cssSelector(
							"#modifiedTestDetailsTableInSmTest > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();
					Thread.sleep(4000);
					table = driver.findElement(By.id("modifiedTestDetailsTableInSmTest"));
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	private boolean selectRecordForProduct(int count1, boolean isRecordSelected1, String product) throws Exception {
		WebElement table = driver.findElement(By.id("productsTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
				System.out.println(totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((product == null) || ("".equalsIgnoreCase(product)))) {
				product = driver
						.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((product == null) || ("".equalsIgnoreCase(product))) {
				product = driver.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();// document
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String productSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (product.contains(productSequence)) {
							JavascriptExecutor jse9 = (JavascriptExecutor) driver;
							WebElement element9 = driver.findElement(By.xpath(
									"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"));
							jse9.executeScript("arguments[0].click();", element9);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String productSequence = driver
							.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (product.contains(productSequence)) {
						JavascriptExecutor jse11 = (JavascriptExecutor) driver;
						WebElement element11 = driver
								.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"));
						jse11.executeScript("arguments[0].click();", element11);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
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
