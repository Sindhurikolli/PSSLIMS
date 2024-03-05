package com.pss.lims.LM.RejectionFlow;

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
import com.pss.lims.LM.LoginDetails.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class LabelData extends LoginDetails {

	@Test
	public void createLabelData() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "LabelDataCreate" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Label Data", "LIMS-LM-006", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='labelDataCreationForm.do'")));
		driver.findElement(By.cssSelector("a[href='labelDataCreationForm.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Label Data", sno, false);
		Thread.sleep(4000);
		methodToCreateLabelData();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreateLabelData() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 200);
		Thread.sleep(3000);
		sno++;
		driver.findElement(By.id("createLabelData")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create", sno, false);
		Thread.sleep(2000);
//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
//		Thread.sleep(3000);
		sno++;
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.linkText("Next"));
		jse2.executeScript("arguments[0].scrollIntoView(true);", element2);
		Thread.sleep(1000);
		jse2.executeScript("arguments[0].click();", element2);
		Thread.sleep(3000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(4000);
		sno++;
		JavascriptExecutor jse12 = (JavascriptExecutor) driver;
		WebElement element12 = driver.findElement(By.id("selBtnForTemplateInLabelDataForm"));
		jse12.executeScript("arguments[0].click();", element12);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(4000);
		sno++;
		JavascriptExecutor jse112 = (JavascriptExecutor) driver;
		WebElement element112 = driver.findElement(By.id("searchBtnInTemplateSelectionForm"));
		jse112.executeScript("arguments[0].click();", element112);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("#templateNameSelectionJTable > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(2000);
		int count2 = 0;
		boolean isRecordSelected2 = false;
		String name = properties.getProperty("Template_Name");
		isRecordSelected2 = selectRecord(count2, isRecordSelected2, name);
		if (isRecordSelected2) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
			WebElement element1112 = driver.findElement(By.id("selectBtnInTemplateSelectionForm"));
			jse1112.executeScript("arguments[0].click();", element1112);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse11121 = (JavascriptExecutor) driver;
			WebElement element11121 = driver.findElement(By.id("selBtnForLabelDataInLabelDataForm"));
			jse11121.executeScript("arguments[0].click();", element11121);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse1121 = (JavascriptExecutor) driver;
			WebElement element1121 = driver.findElement(By.id("searchBtnInInstSelectionForm"));
			jse1121.executeScript("arguments[0].click();", element1121);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.cssSelector("#labelDataSelectionJTable > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(4000);
			int count1 = 0;
			boolean isRecordSelected1 = false;
			String name1 = properties.getProperty("ARNumber");
			isRecordSelected1 = selectRecordForLabel(count1, isRecordSelected1, name1);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse11211 = (JavascriptExecutor) driver;
			WebElement element11211 = driver.findElement(By.id("selectBtnInInstSelectionForm"));
			jse11211.executeScript("arguments[0].click();", element11211);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse211 = (JavascriptExecutor) driver;
			WebElement element211 = driver.findElement(By.id("selBtnForAppInLabelDataForm"));
			jse211.executeScript("arguments[0].click();", element211);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse21 = (JavascriptExecutor) driver;
			WebElement element21 = driver.findElement(By.id("locTreeInCalPmBdm_2_switch"));
			jse21.executeScript("arguments[0].click();", element21);
			Thread.sleep(3000);
			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("#usersTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(2000);
			int count = 0;
			boolean isRecordSelected = false;
			String selectingUserSingleApproval = properties.getProperty("LastName");
			isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval,
					isRecordSelected, count);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse2111 = (JavascriptExecutor) driver;
			WebElement element2111 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse2111.executeScript("arguments[0].click();", element2111);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			WebElement element1 = driver.findElement(By.linkText("Finish"));
			jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
			Thread.sleep(1000);
			jse1.executeScript("arguments[0].click();", element1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor j1se = (JavascriptExecutor) driver;
			WebElement e1lement = driver.findElement(By.id("subBtnInValidateESign"));
			j1se.executeScript("arguments[0].click();", e1lement);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
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

	private boolean selectRecordForLabel(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("labelDataSelectionJTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"labelDataSelectionJTable\"]/div/div[4]/div[2]/span"))
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
			if ((totalNoOfRecords > 1) && ((name1 == null) || ("".equalsIgnoreCase(name1)))) {
				name1 = driver
						.findElement(By.xpath("//*[@id=\"labelDataSelectionJTable\"]/div/table/tbody/tr[1]/td[7]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"labelDataSelectionJTable\"]/div/table/tbody/tr/td[7]"))
						.getText();// document
									// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver
								.findElement(By.xpath(
										"//*[@id=\"labelDataSelectionJTable\"]/div/table/tbody/tr[ " + i + " ]/td[7]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
							WebElement element1112 = driver.findElement(By.xpath(
									"//*[@id=\"labelDataSelectionJTable\"]/div/table/tbody/tr[ " + i + " ]/td[7]"));
							jse1112.executeScript("arguments[0].click();", element1112);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"labelDataSelectionJTable\"]/div/table/tbody/tr/td[7]"))
							.getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
						JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
						WebElement element1112 = driver.findElement(
								By.xpath("//*[@id=\"labelDataSelectionJTable\"]/div/table/tbody/tr/td[7]"));
						jse1112.executeScript("arguments[0].click();", element1112);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
					WebElement element1112 = driver.findElement(By.cssSelector(
							"#labelDataSelectionJTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));// next
																																										// //
																																										// page
					jse1112.executeScript("arguments[0].scrollIntoView(true);", element1112);
					Thread.sleep(2000);
					jse1112.executeScript("arguments[0].click();", element1112); // in
					// Document
					// approve
					// list
					Thread.sleep(4000);
					table = driver.findElement(By.id("labelDataSelectionJTable"));// Document Tree approve
																					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected1;
	}

	private boolean selectRecord(int count2, boolean isRecordSelected2, String name) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("templateNameSelectionJTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"templateNameSelectionJTable\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
			if ((totalNoOfRecords > 1) && ((name == null) || ("".equalsIgnoreCase(name)))) {
				name = driver
						.findElement(By.xpath("//*[@id=\"templateNameSelectionJTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name == null) || ("".equalsIgnoreCase(name))) {
				name = driver.findElement(By.xpath("//*[@id=\"templateNameSelectionJTable\"]/div/table/tbody/tr/td[2]"))
						.getText();// document
									// type
			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"templateNameSelectionJTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name.contains(DevNumberSequence)) {
							JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
							WebElement element1112 = driver.findElement(By.xpath(
									"//*[@id=\"templateNameSelectionJTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"));
							jse1112.executeScript("arguments[0].click();", element1112);
							isRecordSelected2 = true;
							break;
						}
					}
					if (isRecordSelected2) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"templateNameSelectionJTable\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name.contains(DevNumberSequence)) {
						JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
						WebElement element1112 = driver.findElement(
								By.xpath("//*[@id=\"templateNameSelectionJTable\"]/div/table/tbody/tr/td[2]"));
						jse1112.executeScript("arguments[0].click();", element1112);
						isRecordSelected2 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected2) && (noOfRecordsChecked < totalNoOfRecords)) {
					JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
					WebElement element1112 = driver.findElement(By.cssSelector(
							"#labelTemplateApprovalLabelMngmtTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"));// next
																																													// //
																																													// page
					jse1112.executeScript("arguments[0].click();", element1112); // in
					// Document
					// approve
					// list
					Thread.sleep(4000);
					table = driver.findElement(By.id("templateNameSelectionJTable"));// Document Tree approve
																						// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected2;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
