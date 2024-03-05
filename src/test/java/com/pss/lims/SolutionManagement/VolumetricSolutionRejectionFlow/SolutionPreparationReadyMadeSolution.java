package com.pss.lims.SolutionManagement.VolumetricSolutionRejectionFlow;

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
import com.pss.lims.SolutionManagement.LoginDetails.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class SolutionPreparationReadyMadeSolution extends LoginDetails {

	@Test
	public void prepareSolution() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ReadyMadeSolutionPreparation"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Solution Preparation Ready Made", "PSS-LIMS-004",
				"Pass");
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
		WebDriverWait wiat = new WebDriverWait(driver, 240);
		wiat.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='solutionPreparation.do'")));
		driver.findElement(By.cssSelector("a[href='solutionPreparation.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Solution Preparation", sno, false);
		wiat.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#solutionPreparationTableCreate > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		methodToPrepareSolution();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToPrepareSolution() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 200);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("solution_Number");
		isRecordSelected1 = selectRecord(count1, isRecordSelected1, name1);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(4000);
			sno++;
			JavascriptExecutor jse12 = (JavascriptExecutor) driver;
			WebElement element12 = driver.findElement(By.id("selAppFromUserInSolutionPreparation"));
			jse12.executeScript("arguments[0].click();", element12);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			driver.findElement(By.id("locTreeInCalPmBdm_2_switch")).click();
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
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
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
			WebElement element1112 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse1112.executeScript("arguments[0].click();", element1112);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("solventInSolutionPreparation")).sendKeys(properties.getProperty("SolventName"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Solvent", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("solventQuantitySolutionPreparation"))
					.sendKeys(properties.getProperty("QuantityOfEachContainer"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Solvent Quantity", sno, false);
			Thread.sleep(2000);
			sno++;
			Select uom = new Select(driver.findElement(By.id("uomUnitsInSolutionPreparation")));
			Thread.sleep(1000);
			uom.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse11512 = (JavascriptExecutor) driver;
			WebElement element11512 = driver.findElement(By.id("readyMadeSolutionPreparationAction"));
			jse11512.executeScript("arguments[0].click();", element11512);
//			driver.findElement(By.id("readyMadeSolutionPreparationAction")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Ready Made Solution", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("batchNoInSolutionPreparation")).sendKeys(properties.getProperty("BatchNumber"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batch NO", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("totalQuantityRecvReadyPreparation"))
					.sendKeys(properties.getProperty("TotalQuantityRequired"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Total Quantity Required", sno,
					false);
			Thread.sleep(2000);
			Select uom1 = new Select(driver.findElement(By.id("uomUnitsInReadyPreparation")));
			Thread.sleep(2000);
			uom1.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,350)", "");
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			JavascriptExecutor jse1152 = (JavascriptExecutor) driver;
			WebElement element1152 = driver.findElement(By.linkText("Finish"));
			jse1152.executeScript("arguments[0].click();", element1152);
			Thread.sleep(3000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInVmsWnd")).sendKeys(properties.getProperty("Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse11520 = (JavascriptExecutor) driver;
			WebElement element11520 = driver.findElement(By.id("subBtnInValidateESignVms"));
			jse11520.executeScript("arguments[0].click();", element11520);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(2000);
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

	private boolean selectRecord(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("solutionPreparationTableCreate"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/div[4]/div[2]/span"))
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
						.findElement(
								By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver
						.findElement(By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr/td[4]"))
						.getText();// document
									// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By.xpath(
								"//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr[ " + i + " ]/td[4]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr[ " + i
											+ " ]/td[4]"))
									.click();
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(
									By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"solutionPreparationTableCreate\"]/div/table/tbody/tr/td[4]"))
								.click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#solutionPreparationTableCreate > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("solutionPreparationTableCreate"));// Document Tree
																						// approve table
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
