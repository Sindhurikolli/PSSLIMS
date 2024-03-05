package com.pss.lims.RegistrationProj.ForwardFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class RMSpecTestPlanner extends RegistrationLoginDetails {

	@Test
	public void createTestPlanner() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateTestPlanner" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create TestPlanner", "LIMS-SM-067", "Pass");
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
		driver.findElement(By.xpath("//*[@id='loginform']/div[4]/button[1]")).click();
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='testPlannerRegnPage.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='testPlannerRegnPage.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on TEST PLANNER", sno, false);
		Thread.sleep(4000);
		methodTocreateTestPlanner();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodTocreateTestPlanner() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 200);
		sno++;
		JavascriptExecutor jse12 = (JavascriptExecutor) driver;
		WebElement element12 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		jse12.executeScript("arguments[0].scrollIntoView(true);", element12);
		Thread.sleep(1000);
		jse12.executeScript("arguments[0].click();", element12);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(5000);
		sno++;
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.id("selProdBtnInTestPlannarReg"));
		jse2.executeScript("arguments[0].click();", element2);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse3 = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.id("locTreeInLimsSmReg_2_switch"));
		jse3.executeScript("arguments[0].click();", element3);
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Plant1", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#productsTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(3000);
		sno++;
		int count = 0;
		boolean isRecordSelected = false;
		String product = properties.getProperty("Product_Code");
		isRecordSelected = selectRecordForProduct(count, isRecordSelected, product);
		if (isRecordSelected) {
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Record", sno, false);
			Thread.sleep(2000);
			JavascriptExecutor jse4 = (JavascriptExecutor) driver;
			WebElement element4 = driver.findElement(By.id("productSelBtnInProdSelWin"));
			jse4.executeScript("arguments[0].click();", element4);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			Select spec = new Select(driver.findElement(By.id("specificationInLimsTestPlannarRegForm")));
			Thread.sleep(2000);
			spec.selectByVisibleText(properties.getProperty("RMSpecification_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Specification", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("rawDataSheetNoInLimsTestPlannarRegForm"))
					.sendKeys(properties.getProperty("Raw_Data_Sheet_No"));
			Thread.sleep(2000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Raw Data Sheet No", sno, false);
			sno++;
			driver.findElement(By.id("specVersionInLimsTestPlannarRegForm"))
					.sendKeys(properties.getProperty("Specification_Version_No"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Specification Version No", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("stpMethodNoInLimsTestPlannarRegForm"))
					.sendKeys(properties.getProperty("STP_Method_No"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter STP Method No", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("rdsVersionInLimsTestPlannarRegForm"))
					.sendKeys(properties.getProperty("RDS_Version_No"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter RDS Version No", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("stpVersionInLimsTestPlannarRegForm"))
					.sendKeys(properties.getProperty("STP_Version_No"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter STP Version No", sno, false);
			Thread.sleep(2000);
			// sno++;
			WebElement ele = driver.findElement(By.id("effectiveDateInLimsTestPlannarRegForm"));
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", ele);
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.id("effectiveDateInLimsTestPlannarRegForm"));
			jse.executeScript("arguments[0].click();", element);
			Thread.sleep(4000);
			sno++;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("effectiveDateInLimsTestPlannarRegForm")).sendKeys(sdf.format(date));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Date", sno, false);
			Thread.sleep(2000);
			driver.findElement(By.id("stpVersionInLimsTestPlannarRegForm")).click();
			Thread.sleep(2000);
			sno++;
			Select lifeCycle = new Select(driver.findElement(By.id("lifeCycleInTestPlanner")));
			Thread.sleep(2000);
			lifeCycle.selectByVisibleText(properties.getProperty("LifeCycle_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Life Cycle", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("uploadBtn")).sendKeys(properties.getProperty("Document-2"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Document", sno, false);
			Thread.sleep(2000);
			sno++;
			jse12.executeScript("arguments[0].scrollIntoView(true);", element12);
			Thread.sleep(1000);
			jse12.executeScript("arguments[0].click();", element12);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			JavascriptExecutor jse211 = (JavascriptExecutor) driver;
			WebElement element211 = driver.findElement(By.id("selTestsBtnInTestPlannarReg"));
			jse211.executeScript("arguments[0].click();", element211);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(6000);
			int count1 = 0;
			boolean isRecordSelected1 = false;
			String name1 = properties.getProperty("Test_Name");
			//isRecordSelected1 = selectTest(count1, isRecordSelected1, name1);
			driver.findElement(By.xpath("//*[@id=\"testsTableContainerInTestPlannarRegForm\"]/div/table/thead/tr/th[1]/div/input")).click();
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse2211 = (JavascriptExecutor) driver;
			WebElement element2211 = driver.findElement(By.id("testsSelBtnInTestsContainerTable"));
			jse2211.executeScript("arguments[0].click();", element2211);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
//		    Thread.sleep(2000);
//			JavascriptExecutor jse21111 = (JavascriptExecutor) driver;
//			WebElement element21111 = driver.findElement(
//					By.xpath("//*[@id=\"testsTableInLimsTestPlannarRegForm\"]/div[2]/table/tbody/tr/td[3]"));
//			jse21111.executeScript("arguments[0].click();", element21111);
//			Thread.sleep(3000);
//			sno++;
//			Select group = new Select(driver.findElement(
//					By.xpath("//*[@id=\"testsTableInLimsTestPlannarRegForm\"]/div[2]/table/tbody/tr[1]/td[3]/select")));
//			Thread.sleep(2000);
//			group.selectByVisibleText(properties.getProperty("Group_Name"));
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Group", sno, false);
//			Thread.sleep(2000);
//			sno++;
//			JavascriptExecutor jse11 = (JavascriptExecutor) driver;
//			WebElement element11 = driver.findElement(
//					By.xpath("//*[@id=\"testsTableInLimsTestPlannarRegForm\"]/div[2]/table/tbody/tr[1]/td[4]/input"));
//			jse11.executeScript("arguments[0].click();", element11);
//			Thread.sleep(3000);
//			
//			Helper.clickElement(driver, By.xpath("//*[@id=\"testsTableInLimsTestPlannarRegForm\"]/div[2]/table/tbody/tr[1]/td[5]/input[1]"));
//			Thread.sleep(3000);
//			Helper.clickElement(driver, By.xpath("//*[@id=\"testsTableInLimsTestPlannarRegForm\"]/div[2]/table/tbody/tr[2]/td[3]"));
//			Thread.sleep(3000);
//			sno++;
//			Select group1 = new Select(driver.findElement(By.xpath("//*[@id=\"testsTableInLimsTestPlannarRegForm\"]/div[2]/table/tbody/tr[2]/td[3]/select")));
//			Thread.sleep(2000);
//			group1.selectByVisibleText(properties.getProperty("Group_Name"));
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Group", sno, false);
//			Thread.sleep(2000);
//			sno++;
//			Helper.clickElement(driver, By.xpath("//*[@id=\"testsTableInLimsTestPlannarRegForm\"]/div[2]/table/tbody/tr[2]/td[4]/input"));
//			Thread.sleep(3000);
			
//			driver.findElement(By.cssSelector("[title='Update']")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("referenceNumInLimsTestPlannarReject"))
					.sendKeys(properties.getProperty("Reference_No"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Reference No", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("remarksInLimsTestPlannarReject"))
					.sendKeys(properties.getProperty("RemarksForTesPlanner"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Remarks", sno, false);
			Thread.sleep(2000);
			driver.findElement(By.id("footNoteCommInLimsTestPlannarReject"))
					.sendKeys(properties.getProperty("Foot_Note_Comments"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Foot Note Comments", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse15 = (JavascriptExecutor) driver;
			WebElement element15 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
			jse15.executeScript("arguments[0].scrollIntoView(true);", element15);
			Thread.sleep(1000);
			jse15.executeScript("arguments[0].click();", element15);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(2000);
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
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectTest(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("testsTableContainerInTestPlannarRegForm"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver.findElement(By.xpath("//*[@id=\"testsTableContainerInTestPlannarRegForm\"]/div/div[4]/div[2]/span"))
//					.getText();// For
//			// Ex:
//			// Showing
//			// 1-1
//			// of
//			// 1
////			System.out.println("hi:" + a);
//			String[] parts = a.split(" of ");
////			System.out.println("parts:" + parts.toString());
//			try {
//				totalNoOfRecords = Integer.parseInt(parts[1].trim());
//				System.out.println(totalNoOfRecords);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1) && ((name1 == null) || ("".equalsIgnoreCase(name1)))) {
				name1 = driver
						.findElement(By.xpath(
								"//*[@id=\"testsTableContainerInTestPlannarRegForm\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver
						.findElement(By
								.xpath("//*[@id=\"testsTableContainerInTestPlannarRegForm\"]/div/table/tbody/tr/td[3]"))
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
								By.xpath("//*[@id=\"testsTableContainerInTestPlannarRegForm\"]/div/table/tbody/tr[ " + i
										+ " ]/td[3]"))
								.getText();// documentTypeName
						if (name1.contains(DevNumberSequence)) {
//							driver.findElement(By.xpath(
//									"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
//									.click();
							JavascriptExecutor jse9 = (JavascriptExecutor) driver;
							WebElement element9 = driver.findElement(
									By.xpath("//*[@id=\"testsTableContainerInTestPlannarRegForm\"]/div/table/tbody/tr[ "
											+ i + " ]/td[3]"));
							jse9.executeScript("arguments[0].click();", element9);
//							Thread.sleep(2000);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath(
									"//*[@id=\"testsTableContainerInTestPlannarRegForm\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name1.contains(DevNumberSequence)) {
//						driver.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"))
//								.click();
						JavascriptExecutor jse11 = (JavascriptExecutor) driver;
						WebElement element11 = driver.findElement(By.xpath(
								"//*[@id=\"testsTableContainerInTestPlannarRegForm\"]/div/table/tbody/tr/td[3]"));
						jse11.executeScript("arguments[0].click();", element11);
//						Thread.sleep(2000);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#testsTableContainerInTestPlannarRegForm > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("testsTableContainerInTestPlannarRegForm"));// Document Tree
																									// approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected1;
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
