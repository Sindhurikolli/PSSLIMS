package com.pss.lims.SolutionManagement.VolumetricSolutionForwardFlow;

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

public class MasterOfSolutionSTD extends LoginDetails {

	@Test
	public void createMasterOfSolutionSTD() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateMasterOfSolutionSTD"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Master Of Solution STD", "LIMS-SM-001", "Pass");
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
		wiat.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='masterOfSolStdReStdRDS.do']")));
		driver.findElement(By.cssSelector("a[href='masterOfSolStdReStdRDS.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Master Of Sol Std/Re-S...", sno,
				false);
		Thread.sleep(4000);
		methodToCreateMasterOfSolution();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreateMasterOfSolution() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 200);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(1000);
		sno++;
		driver.findElement(By.linkText("Next")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("selMasterSolutionStdzReStdzDetlsBtn")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.findElement(By.id("solPrepStdReStdRDSSearchBtnInSampleSolPrpDlg")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#solutionPrepStdReStdRDSWindow > div > div.jtable-busy-message[style='display: none;']")));
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("SolutionName");
		isRecordSelected1 = selectSolutionName(count1, isRecordSelected1, name1);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("masterSolprepStdReStdRDSSelBtnInSelectionWin")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("testNameInMasterSolutionStdReStdzRDS"))
					.sendKeys(properties.getProperty("TestName"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Test Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("selMasterSolutionStdzReStdzDetlsApproveFromSelBtn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(4000);
			driver.findElement(By.id("locTreeInCalPmBdm_2_switch")).click();
			Thread.sleep(3000);
			sno++;
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
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("commentInMasterSolutionStdzReStdzRDS"))
					.sendKeys(properties.getProperty("Comments"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("numberOfVariablesInMasterSolutionStdReStdzRDS"))
					.sendKeys(properties.getProperty("No.ofVariables"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Variables", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("addNumberOfVariablesForm")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Load", sno, false);
			Thread.sleep(2000);
			int noOfVariables = Integer.parseInt(properties.getProperty("No.ofVariables"));
			String[] Variables = properties.getProperty("Variable").split(",");
			String[] Symbol = properties.getProperty("Symbol").split(",");
			for (int i = 1; i <= noOfVariables; i++) {
				sno++;
				driver.findElement(By.xpath("//*[@id=\"variablesTableInMasterSolStdzReStdzRDS\"]/div/table/tbody/tr["
						+ i + "]/td[2]/input")).sendKeys(Variables[i - 1]);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Variable", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.xpath("//*[@id=\"variablesTableInMasterSolStdzReStdzRDS\"]/div/table/tbody/tr["
						+ i + "]/td[3]/input")).sendKeys(Symbol[i - 1]);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Symbol", sno, false);
				Thread.sleep(2000);
				sno++;
				Select uom = new Select(driver
						.findElement(By.xpath("//*[@id=\"variablesTableInMasterSolStdzReStdzRDS\"]/div/table/tbody/tr["
								+ i + "]/td[4]/select")));
				Thread.sleep(1000);
				uom.selectByIndex(1);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
				Thread.sleep(2000);
			}
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("formulaInMasterSolutionStdReStdzRDS"))
					.sendKeys(properties.getProperty("Formula"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Formula", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("descriptionInMasterSolutionStdzReStdzRDS"))
					.sendKeys(properties.getProperty("Comments"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description Of Reading", sno,
					false);
			Thread.sleep(2000);
			sno++;
			Select uom = new Select(driver.findElement(By.id("uomUnitsInMasterSolutionStdzReStdzRDS")));
			Thread.sleep(1000);
			uom.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("numberOfDecimalsInMasterSolutionStdReStdzRDS"))
					.sendKeys(properties.getProperty("No.ofDecimals"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Number Of Decimals", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("minimumChkBoxInMasterSolutionStdReStdzRDS")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Minimum", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("minimumLimitInMasterSolutionStdReStdzRDS"))
					.sendKeys(properties.getProperty("MinLimit"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Minimum Limit", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("maximumChkBoxInMasterSolutionStdReStdzRDS")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Maximum", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("maximumLimitInMasterSolutionStdReStdzRDS"))
					.sendKeys(properties.getProperty("MaxLimit"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Maximum Limit", sno, false);
			Thread.sleep(2000);
			JavascriptExecutor js21 = (JavascriptExecutor) driver;
			js21.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.id("passLimitDescInMasterSolutionStdzReStdzRDS"))
					.sendKeys(properties.getProperty("Comments"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Pass Limit Description", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("evaluateBtnInMasterSolutionReStdRDSCreate")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath(
					"//*[@id=\"variablesTableInMastSolStdzReStdzRDSEvaluateTable\"]/div/table/tbody/tr[1]/td[4]/input"))
					.sendKeys(properties.getProperty("Value1"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Value 1", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.xpath(
					"//*[@id=\"variablesTableInMastSolStdzReStdzRDSEvaluateTable\"]/div/table/tbody/tr[2]/td[4]/input"))
					.sendKeys(properties.getProperty("Value2"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Value 2", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("verifybtnInEvaluateWndMasterSolRDS")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Verify", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("closebtnInEvaluateWndMasterSolRDS")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Close", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("testProcedureInMasterSolutionStdzReStdzRDS_ifr"))
					.sendKeys(properties.getProperty("Comments"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Enter Standardization/Re-Standardization Procedure", sno, false);
			Thread.sleep(2000);
			JavascriptExecutor js111 = (JavascriptExecutor) driver;
			js111.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.linkText("Finish")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("eSignPwdInVmsWnd")).sendKeys(properties.getProperty("Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESignVms")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
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

	private boolean selectSolutionName(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("solutionPrepStdReStdRDSWindow"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(1000);
			String a = driver.findElement(By.xpath("//*[@id=\"solutionPrepStdReStdRDSWindow\"]/div/div[4]/div[2]/span"))
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
						.findElement(By.xpath("//*[@id=\"solutionPrepStdReStdRDSWindow\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver
						.findElement(By.xpath("//*[@id=\"solutionPrepStdReStdRDSWindow\"]/div/table/tbody/tr/td[2]"))
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
								"//*[@id=\"solutionPrepStdReStdRDSWindow\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"solutionPrepStdReStdRDSWindow\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
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
									By.xpath("//*[@id=\"solutionPrepStdReStdRDSWindow\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"solutionPrepStdReStdRDSWindow\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#solutionPrepStdReStdRDSWindow > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("solutionPrepStdReStdRDSWindow"));// Document Tree
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
