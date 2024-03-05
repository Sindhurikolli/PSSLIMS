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
import com.pss.lims.util.Utilities;

public class SolutionSTDTest extends LoginDetails {

	@Test
	public void solutionSTDTest() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateSolutionSTDTest"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Solution STD Test", "LIMS-SM-001", "Pass");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='solStdzReStdzTestCompletion.do']")));
		driver.findElement(By.cssSelector("a[href='solStdzReStdzTestCompletion.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Sol Stdz/Re-Stdz Test ...", sno,
				false);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#SolutionStdReStdTestCompletionTable > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		methodToCreateSolutionSTDTest();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToCreateSolutionSTDTest() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("SolutionName");
		isRecordSelected1 = selectRecord(count1, isRecordSelected1, name1);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(1000);
			sno++;
			driver.findElement(By.linkText("Next")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(
					By.xpath("//*[@id=\"addingChemicalsSolutionPreparationTestComp\"]/div/div[3]/div[2]/span/span[2]"))
					.click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Chemicals", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("chemicalSelSearchBtn")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#chemicalNameDetailsInSelectWndwTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			int count2 = 0;
			boolean isRecordSelected2 = false;
			String name2 = properties.getProperty("ChemicalName");
			isRecordSelected2 = selectChemicals(count2, isRecordSelected2, name2);
			if (isRecordSelected2) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("selectBtnInChemicalWin")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.xpath(
						"//*[@id=\"addingChemicalsSolutionPreparationTestComp\"]/div/table/tbody/tr/td[5]/input"))
						.sendKeys(properties.getProperty("QuantityOfEachContainer"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Quantity", sno, false);
				Thread.sleep(2000);
				sno++;
				Select uom = new Select(driver.findElement(By.xpath(
						"//*[@id=\"addingChemicalsSolutionPreparationTestComp\"]/div/table/tbody/tr/td[6]/select")));
				Thread.sleep(1000);
				uom.selectByIndex(1);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
				Thread.sleep(2000);
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("window.scrollBy(0,350)", "");
				Thread.sleep(1000);
				sno++;
				driver.findElement(By.linkText("Next")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(4000);
				sno++;
				driver.findElement(By
						.xpath("//*[@id=\"addingReagentsSolutionPreparationTestComp\"]/div/div[3]/div[2]/span/span[2]"))
						.click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Reagents", sno, false);
				Thread.sleep(2000);
				Select re = new Select(driver.findElement(By.id("solutionTypeInReagentSelectDlgTestComp")));
				Thread.sleep(1000);
				re.selectByIndex(2);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Reagent", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("reagentSelSearchBtnTestCom")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
				Thread.sleep(2000);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
						"#reagentInSelectWndwTableContainerForTestComp > div > div.jtable-busy-message[style='display: none;']")));
				int count3 = 0;
				boolean isRecordSelected3 = false;
				String name3 = properties.getProperty("solution_Number");
				isRecordSelected3 = selectReagents(count3, isRecordSelected3, name3);
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("selectBtnInReagentWinTestComp")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By
						.xpath("//*[@id=\"addingReagentsSolutionPreparationTestComp\"]/div/table/tbody/tr/td[6]/input"))
						.sendKeys(properties.getProperty("QuantityOfEachContainer"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Quantity", sno, false);
				Thread.sleep(2000);
				sno++;
				Select uom1 = new Select(driver.findElement(By.xpath(
						"//*[@id=\"addingReagentsSolutionPreparationTestComp\"]/div/table/tbody/tr/td[7]/select")));
				Thread.sleep(1000);
				uom1.selectByIndex(1);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
				Thread.sleep(2000);
				JavascriptExecutor js11 = (JavascriptExecutor) driver;
				js11.executeScript("window.scrollBy(0,350)", "");
				Thread.sleep(1000);
				sno++;
				driver.findElement(By.linkText("Next")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(4000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"addingInstrumentSolutionPrepTestComp\"]/div/div[3]/div[2]/span/span[2]"))
						.click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add Instruments", sno,
						false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("instrumentSelSearchBtn")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
				Thread.sleep(2000);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
						"#instrumentDetailsInSelectWndwTableContainer > div > div.jtable-busy-message[style='display: none;']")));
				int count31 = 0;
				boolean isRecordSelected31 = false;
				String name31 = properties.getProperty("InstrumentName");
				isRecordSelected31 = selectInstrument(count31, isRecordSelected31, name31);
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("selectBtnInInstrumentWin")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("accpcriteriaForSolStd")).sendKeys(properties.getProperty("AcceptCriteria"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Acceptance Criteria", sno,
						false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("trailForWaterContent1")).sendKeys(properties.getProperty("Trail-1"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Trail-1", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("trailForWaterContent2")).sendKeys(properties.getProperty("Trail-1"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Trail-2", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("trailForWaterContent3")).sendKeys(properties.getProperty("Trail-1"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Trail-3", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("trailForWaterContent4")).sendKeys(properties.getProperty("Trail-1"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Trail-4", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("trailForWaterContent5")).sendKeys(properties.getProperty("Trail-1"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Trail-5", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("trailForWaterContent6")).sendKeys(properties.getProperty("Trail-1"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Trail-5", sno, false);
				Thread.sleep(2000);
				JavascriptExecutor js111 = (JavascriptExecutor) driver;
				js111.executeScript("window.scrollBy(0,350)", "");
				Thread.sleep(1000);
				sno++;
				driver.findElement(By.linkText("Next")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(4000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"titrationTable1InSolutionTestComp\"]/div/table/tbody/tr[1]/td[3]/input"))
						.sendKeys(properties.getProperty("Value1"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Value1", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"titrationTable1InSolutionTestComp\"]/div/table/tbody/tr[2]/td[3]/input"))
						.sendKeys(properties.getProperty("Value2"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Value2", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"titrationTable2InSolutionTestComp\"]/div/table/tbody/tr[1]/td[3]/input"))
						.sendKeys(properties.getProperty("Value1"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Value1", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(
						By.xpath("//*[@id=\"titrationTable2InSolutionTestComp\"]/div/table/tbody/tr[2]/td[3]/input"))
						.sendKeys(properties.getProperty("Value2"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Value2", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("commentsInVmsJobResInTestCompletion"))
						.sendKeys(properties.getProperty("Comments"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
				Thread.sleep(2000);
				JavascriptExecutor js1111 = (JavascriptExecutor) driver;
				js1111.executeScript("window.scrollBy(0,350)", "");
				Thread.sleep(1000);
				sno++;
				driver.findElement(By.linkText("Finish")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("eSignPwdInVmsWnd")).sendKeys(properties.getProperty("Password"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password",
						sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("subBtnInValidateESignVms")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-btn")));
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

			} else {
				System.out.println("Record is not Selected");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectInstrument(int count31, boolean isRecordSelected31, String name31) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("instrumentDetailsInSelectWndwTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(1000);
			String a = driver
					.findElement(
							By.xpath("//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count31 == 0) {
			if ((totalNoOfRecords > 1) && ((name31 == null) || ("".equalsIgnoreCase(name31)))) {
				name31 = driver
						.findElement(By.xpath(
								"//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name31 == null) || ("".equalsIgnoreCase(name31))) {
				name31 = driver
						.findElement(By.xpath(
								"//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[2]"))
						.getText();// document
									// type
			}
			++count31;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[ "
										+ i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name31.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[ " + i
											+ " ]/td[2]"))
									.click();
							isRecordSelected31 = true;
							break;
						}
					}
					if (isRecordSelected31) {
						break;
					}
				} else {
					String DevNumberSequence = driver.findElement(By
							.xpath("//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name31.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"instrumentDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[2]"))
								.click();
						isRecordSelected31 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected31) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#instrumentDetailsInSelectWndwTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("instrumentDetailsInSelectWndwTableContainer"));// Document Tree
					// approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected31;
	}

	private boolean selectReagents(int count3, boolean isRecordSelected3, String name3) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("reagentInSelectWndwTableContainerForTestComp"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(1000);
			String a = driver
					.findElement(By
							.xpath("//*[@id=\"reagentInSelectWndwTableContainerForTestComp\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
			if ((totalNoOfRecords > 1) && ((name3 == null) || ("".equalsIgnoreCase(name3)))) {
				name3 = driver.findElement(By
						.xpath("//*[@id=\"reagentInSelectWndwTableContainerForTestComp\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((name3 == null) || ("".equalsIgnoreCase(name3))) {
				name3 = driver
						.findElement(By.xpath(
								"//*[@id=\"reagentInSelectWndwTableContainerForTestComp\"]/div/table/tbody/tr/td[5]"))
						.getText();// document
									// type
			}
			++count3;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By
								.xpath("//*[@id=\"reagentInSelectWndwTableContainerForTestComp\"]/div/table/tbody/tr[ "
										+ i + " ]/td[5]"))
								.getText();// documentTypeName
						if (name3.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"reagentInSelectWndwTableContainerForTestComp\"]/div/table/tbody/tr[ " + i
											+ " ]/td[5]"))
									.click();
							isRecordSelected3 = true;
							break;
						}
					}
					if (isRecordSelected3) {
						break;
					}
				} else {
					String DevNumberSequence = driver.findElement(By.xpath(
							"//*[@id=\"reagentInSelectWndwTableContainerForTestComp\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (name3.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"reagentInSelectWndwTableContainerForTestComp\"]/div/table/tbody/tr/td[5]"))
								.click();
						isRecordSelected3 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected3) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#reagentInSelectWndwTableContainerForTestComp > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("reagentInSelectWndwTableContainerForTestComp"));// Document Tree
					// approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected3;
	}

	private boolean selectChemicals(int count2, boolean isRecordSelected2, String name2) throws Exception {
		WebElement table = driver.findElement(By.id("chemicalNameDetailsInSelectWndwTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(1000);
			String a = driver
					.findElement(By
							.xpath("//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/div[4]/div[2]/span"))
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
			if ((totalNoOfRecords > 1) && ((name2 == null) || ("".equalsIgnoreCase(name2)))) {
				name2 = driver.findElement(By.xpath(
						"//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();// documentType
			} else if ((name2 == null) || ("".equalsIgnoreCase(name2))) {
				name2 = driver
						.findElement(By.xpath(
								"//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[3]"))
						.getText();// document
									// type
			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By
								.xpath("//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[ "
										+ i + " ]/td[3]"))
								.getText();// documentTypeName
						if (name2.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(By.xpath(
									"//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr[ "
											+ i + " ]/td[3]"))
									.click();
							isRecordSelected2 = true;
							break;
						}
					}
					if (isRecordSelected2) {
						break;
					}
				} else {
					String DevNumberSequence = driver.findElement(By.xpath(
							"//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (name2.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(By.xpath(
								"//*[@id=\"chemicalNameDetailsInSelectWndwTableContainer\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelected2 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected2) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#chemicalNameDetailsInSelectWndwTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("chemicalNameDetailsInSelectWndwTableContainer"));// Document Tree
					// approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected2;
	}

	private boolean selectRecord(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("SolutionStdReStdTestCompletionTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,350)", "");
			Thread.sleep(1000);
			String a = driver
					.findElement(By.xpath("//*[@id=\"SolutionStdReStdTestCompletionTable\"]/div/div[4]/div[2]/span"))
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
						.findElement(By
								.xpath("//*[@id=\"SolutionStdReStdTestCompletionTable\"]/div/table/tbody/tr[1]/td[4]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver
						.findElement(
								By.xpath("//*[@id=\"SolutionStdReStdTestCompletionTable\"]/div/table/tbody/tr/td[4]"))
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
								By.xpath("//*[@id=\"SolutionStdReStdTestCompletionTable\"]/div/table/tbody/tr[ " + i
										+ " ]/td[4]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"SolutionStdReStdTestCompletionTable\"]/div/table/tbody/tr[ " + i
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
							.findElement(By
									.xpath("//*[@id=\"SolutionStdReStdTestCompletionTable\"]/div/table/tbody/tr/td[4]"))
							.getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"SolutionStdReStdTestCompletionTable\"]/div/table/tbody/tr/td[4]"))
								.click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#SolutionStdReStdTestCompletionTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("SolutionStdReStdTestCompletionTable"));// Document Tree
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
