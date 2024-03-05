package com.pss.lims.sm.InternalRejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.configuration.PropertiesConfiguration;
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
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class SampleRegistrationForMicroTest extends SMLoginDetails {

	@Test
	public void createSampleRegistrationForMicroTest() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateSampleRegistrationForMicroTest"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Sample Registration", "PSS-LIMS-091", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='sampleRegPageInSample.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='sampleRegPageInSample.do']"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Sample Registration", sno, false);
		Thread.sleep(4000);
		methodTocreateSampleRegistrationForMicroTest();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodTocreateSampleRegistrationForMicroTest() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		sno++;
		JavascriptExecutor jse11 = (JavascriptExecutor) driver;
		WebElement element11 = driver.findElement(By.linkText("Next"));
		jse11.executeScript("arguments[0].scrollIntoView(true);", element11);
		Thread.sleep(1000);
		jse11.executeScript("arguments[0].click();", element11);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(5000);
		sno++;
		driver.findElement(By.id("selProdBtnInSampleReg")).click();
		Thread.sleep(5000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		
		sno++;
		driver.findElement(By.id("locTreeInLimsSmReg_2_switch")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#productsTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(2000);
		sno++;
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String product = properties.getProperty("Product_Code");
		isRecordSelected1 = selectRecordForProduct(count1, isRecordSelected1, product);
		if (isRecordSelected1) {
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Record", sno, false);
			sno++;
			Thread.sleep(3000);
			driver.findElement(By.id("productSelBtnInProdSelWin")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
			sno++;
			Select spec = new Select(driver.findElement(By.id("specInLimsSampleReg")));
			Thread.sleep(2000);
			spec.selectByVisibleText(properties.getProperty("Specification_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Specification", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selApprovedByBtnInLimsSampleReg")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("locTreeInCalPmBdm_2_switch")).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(3000);
			sno++;
			int count = 0;
			boolean isRecordSelected = false;
			String selectingUserSingleApproval = properties.getProperty("LastName");
			isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval,
					isRecordSelected, count);
			if (isRecordSelected) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Record", sno, false);
				Thread.sleep(3000);
				driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("numOfContInLimsSampleReg"))
						.sendKeys(properties.getProperty("No_Of_Containers"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, " Enter No of Containers", sno,
						false);
				Thread.sleep(2000);
				sno++;
				Select coaDateFormat = new Select(driver.findElement(By.id("coaDateFormatInLimsSampleReg")));
				Thread.sleep(2000);
				coaDateFormat.selectByIndex(3);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, " Enter COA Date Foprmat", sno,
						false);
//				sno++;
//				Thread.sleep(2000);
//				driver.findElement(By.id("manufactBrowseInLimsSampleReg")).click();
//				Thread.sleep(5000);
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, " Select Manufacturer", sno, false);
				Thread.sleep(5000);
//				sno++;
//				driver.findElement(By.id("searchBtnInlimsManufactDetailsSearchForm")).click();
//				Thread.sleep(5000);
//				int count111 = 0;
//				boolean isRecordSelected111 = false;
//				String name111 = properties.getProperty("Manufacturer_Code");
//				isRecordSelected111 = selectRecordForManufacturer(count111, isRecordSelected111, name111);
//				Thread.sleep(5000);
//				driver.findElement(By.id("selectBtnInlimsManufactDetailsSearchForm")).click();
//				Thread.sleep(3000);
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Manufacturer Selected", sno, false);
//				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("batchNoInLimsSampleReg")).sendKeys(properties.getProperty("Batch_No"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, " Enter Batch No", sno, false);
				Thread.sleep(3000);
				sno++;
				SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, 730); // number of days to add
				String futureDate = (String) (formattedDate.format(c.getTime()));
				driver.findElement(By.id("expiryDateInLimsSampleReg")).sendKeys(futureDate);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, " Enter Date", sno, false);
				Thread.sleep(3000);
				driver.findElement(By.id("batchSizeInLimsSampleReg")).click();
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("batchSizeInLimsSampleReg")).sendKeys(properties.getProperty("Batch_Size"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, " Enter Batch Size", sno, false);
				Thread.sleep(2000);
				sno++;
				Select batchSizeUOM = new Select(driver.findElement(By.id("UOMInLimsSampleReg")));
				Thread.sleep(1000);
				batchSizeUOM.selectByIndex(1);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, " Select UOM", sno, false);
				sno++;
				Thread.sleep(2000);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				Thread.sleep(3000);
				JavascriptExecutor jse1 = (JavascriptExecutor) driver;
				WebElement element1 = driver.findElement(By.id("manufactDateInLimsSampleReg"));
				jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
				element1.sendKeys(sdf.format(date));
				Thread.sleep(3000);
//				sno++;
//				driver.findElement(By.className("ui-datepicker-today")).click();//sendKeys(sdf.format(date));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, " Enter Date", sno, false);
				Thread.sleep(4000);
				sno++;
				// driver.findElement(By.id("effectiveDateInLimsSampleReg")).click();
				//Thread.sleep(4000);
				WebElement eDate = driver.findElement(By.id("effectiveDateInLimsSampleReg"));
				jse1.executeScript("arguments[0].click();", eDate);
				eDate.sendKeys(sdf.format(date));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, " Enter Date", sno, false);
				Thread.sleep(4000);
//				driver.findElement(By.id("batchSizeInLimsSampleReg")).click();
//				driver.findElement(By.id("conditionInLimsSampleReg")).sendKeys(properties.getProperty("Condition"));
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, " Enter Condition", sno, false);
//				Thread.sleep(4000);
				sno++;
				jse11.executeScript("arguments[0].scrollIntoView(true);", element11);
				Thread.sleep(1000);
				jse11.executeScript("arguments[0].click();", element11);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(5000);
				int count11 = 0;
				boolean isRecordSelected11 = false;
				String name11 = properties.getProperty("Micro_Test_Name");
				isRecordSelected11 = selectMultiTest(count11, isRecordSelected11, name11);
				sno++;
				JavascriptExecutor jse6 = (JavascriptExecutor) driver;
				WebElement element6 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
				jse6.executeScript("arguments[0].scrollIntoView(true);", element6);
				Thread.sleep(1000);
				jse6.executeScript("arguments[0].click();", element6);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
						false);
				Thread.sleep(3000);
				sno++;
				JavascriptExecutor jse9 = (JavascriptExecutor) driver;
				WebElement element9 = driver.findElement(By.id("subBtnInValidateESign"));
				jse9.executeScript("arguments[0].click();", element9);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
				String arNumText = driver.findElement(By.xpath("//*[@id='modal-window']/div/div/div[2]/center"))
						.getText();
				String[] parts = arNumText.split(":");
				System.out.println(parts[1]);
				String arNo = parts[1].trim();
				System.out.println(arNo);
				PropertiesConfiguration properties = new PropertiesConfiguration(
						"src/test/java/LIMSUIProperties/SampleManagement.properties");
				properties.setProperty("AR_Number", arNo);
//		        properties.setProperty("ACTION_ITEM_chgCtrlId", IncidentNo+"/A1");
				properties.save();
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
				Thread.sleep(3000);
				if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).isDisplayed()) {
					sno++;
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno,
							false);
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
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectMultiTest(int count11, boolean isRecordSelected11, String name11) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("testGridInLimsSampleReg"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver.findElement(By.xpath("//*[@id=\"testGridInLimsSampleReg\"]/div/div[4]/div[2]/span"))
//					.getText();// For
//			// Ex:
//			// Showing
//			// 1-1
//			// of
//			// 1
//			String[] parts = a.split(" of ");
//			try {
//				totalNoOfRecords = Integer.parseInt(parts[1].trim());
//				System.out.println(totalNoOfRecords);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
		if (perPageNoOfRecordsPresent > 0 && count11 == 0) {
			if ((totalNoOfRecords > 1) && ((name11 == null) || ("".equalsIgnoreCase(name11)))) {
				name11 = driver
						.findElement(By.xpath("//*[@id=\"testGridInLimsSampleReg\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name11 == null) || ("".equalsIgnoreCase(name11))) {
				name11 = driver.findElement(By.xpath("//*[@id=\"testGridInLimsSampleReg\"]/div/table/tbody/tr/td[3]"))
						.getText();// document
									// type
			}
			++count11;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String productCodeSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"testGridInLimsSampleReg\"]/div/table/tbody/tr[ " + i + " ]/td[3]"))
								.getText();// documentTypeName
						if (name11.contains(productCodeSequence)) {
							JavascriptExecutor jse9 = (JavascriptExecutor) driver;
							WebElement element9 = driver
									.findElement(By.xpath("//*[@id=\"testGridInLimsSampleReg\"]/div/table/tbody/tr[ "
											+ i + " ]/td[2]/input"));
							jse9.executeScript("arguments[0].click();", element9);
							isRecordSelected11 = true;
							break;
						}
					}
					if (isRecordSelected11) {
						break;
					}
				} else {
					String productCodeSequence = driver
							.findElement(By.xpath("//*[@id=\"testGridInLimsSampleReg\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name11.contains(productCodeSequence)) {
						JavascriptExecutor jse11 = (JavascriptExecutor) driver;
						WebElement element11 = driver.findElement(
								By.xpath("//*[@id=\"testGridInLimsSampleReg\"]/div/table/tbody/tr/td[2]/input"));
						jse11.executeScript("arguments[0].click();", element11);
						isRecordSelected11 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected11) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#testGridInLimsSampleReg > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("testGridInLimsSampleReg"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}

		}
		return isRecordSelected11;
	}

	private boolean selectRecordForProduct(int count1, boolean isRecordSelected1, String product) throws Exception {
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
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
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
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String productCodeSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (product.equalsIgnoreCase(productCodeSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
									.click();
//							JavascriptExecutor jse9 = (JavascriptExecutor) driver;
//							WebElement element9 = driver.findElement(By.xpath(
//									"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"));
//							jse9.executeScript("arguments[0].click();", element9);
//							Thread.sleep(2000);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String productCodeSequence = driver
							.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (product.equalsIgnoreCase(productCodeSequence)) {
						driver.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"))
								.click();
//						JavascriptExecutor jse11 = (JavascriptExecutor) driver;
//						WebElement element11 = driver
//								.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"));
//						jse11.executeScript("arguments[0].click();", element11);
//						Thread.sleep(2000);
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
	private boolean selectRecordForManufacturer(int count111, boolean isRecordSelected111, String Mfgcode) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("manufacturerDetailsSelJtable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver.findElement(By.xpath("//*[@id=\"manufacturerDetailsSelJtable\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count111 == 0) {
//			System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((Mfgcode == null) || ("".equalsIgnoreCase(Mfgcode)))) {
//				System.out.println("hi this is ravi");
				Mfgcode = driver
						.findElement(By.xpath("//*[@id=\"manufacturerDetailsSelJtable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((Mfgcode == null) || ("".equalsIgnoreCase(Mfgcode))) {
				Mfgcode = driver.findElement(By.xpath("//*[@id=\"manufacturerDetailsSelJtable\"]/div/table/tbody/tr/td[2]"))
						.getText();// document
									// type
			}
			++count111;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String MfgcodeSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"manufacturerDetailsSelJtable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (Mfgcode.equalsIgnoreCase(MfgcodeSequence)) {
//							driver.findElement(By.xpath(
//									"//*[@id=\"manufacturerDetailsSelJtable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
//									.click();
							JavascriptExecutor jse9 = (JavascriptExecutor) driver;
							WebElement element9 = driver.findElement(By.xpath(
									"//*[@id=\"manufacturerDetailsSelJtable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"));
							jse9.executeScript("arguments[0].click();", element9);
//							Thread.sleep(2000);
							isRecordSelected111 = true;
							break;
						}
					}
					if (isRecordSelected111) {
						break;
					}
				} else {
					String MfgcodeSequence = driver
							.findElement(By.xpath("//*[@id=\"manufacturerDetailsSelJtable\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (Mfgcode.equalsIgnoreCase(MfgcodeSequence)) {
//						driver.findElement(By.xpath("//*[@id=\"manufacturerDetailsSelJtable\"]/div/table/tbody/tr/td[2]"))
//								.click();
						JavascriptExecutor jse11 = (JavascriptExecutor) driver;
						WebElement element11 = driver
								.findElement(By.xpath("//*[@id=\"manufacturerDetailsSelJtable\"]/div/table/tbody/tr/td[2]"));
						jse11.executeScript("arguments[0].click();", element11);
//						Thread.sleep(2000);
						isRecordSelected111 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected111) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#manufacturerDetailsSelJtable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("manufacturerDetailsSelJtable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}

		}
		return isRecordSelected111;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
