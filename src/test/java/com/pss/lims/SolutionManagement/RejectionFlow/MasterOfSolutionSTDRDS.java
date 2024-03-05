package com.pss.lims.SolutionManagement.RejectionFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.lims.SolutionManagement.LoginDetails.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class MasterOfSolutionSTDRDS extends LoginDetails {

	@Test
	public void createMasterOfSolutionSTDRDS() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateMasterOfSolutionSTDRDS"
				+ (new Random().nextInt()) + ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);

		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Master Of Solution STD/RDS", "PSS-LIMS-006",
				"Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("TestUser_Login"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		im = Image.getInstance(input);
		im.scaleToFit((PageSize.A4.getWidth() - (PageSize.A4.getWidth() / 8)),
				(PageSize.A4.getHeight() - (PageSize.A4.getHeight() / 8)));
		document.add(new Paragraph(
				sno + "." + "Enter the username, password and click on login button" + Utilities.prepareSSNumber(sno),
				font));
		document.add(im);
		document.add(new Paragraph("                                     "));
		document.add(new Paragraph("                                     "));
		sno++;
		Thread.sleep(5000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Solution Management module", sno,
				false);
		driver.findElement(By.xpath("/html/body/div/div[9]/a/map/area")).click();
		Thread.sleep(3000);
		sno++;
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"SolMgntBodySection\"]/a[4]/div")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on volSolTestRegMenu", sno, false);
		Thread.sleep(4000);
		methodToCreateMasterOfSolutionSTDRDS();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);
	}

	private void methodToCreateMasterOfSolutionSTDRDS() throws Exception {

		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		WebElement element2 = driver.findElement(By.linkText("Next"));
		jse2.executeScript("arguments[0].click();", element2);
		Thread.sleep(3000);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(4000);
		sno++;
		JavascriptExecutor jse12 = (JavascriptExecutor) driver;
		WebElement element12 = driver.findElement(By.id("selMasterSolutionStdzReStdzDetlsBtn"));
		jse12.executeScript("arguments[0].click();", element12);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
		Thread.sleep(2000);
		sno++;
		JavascriptExecutor jse112 = (JavascriptExecutor) driver;
		WebElement element112 = driver.findElement(By.id("solPrepStdReStdRDSSearchBtnInSampleSolPrpDlg"));
		jse112.executeScript("arguments[0].click();", element112);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Search", sno, false);
		Thread.sleep(4000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("SolutionName");
		isRecordSelected1 = selectRecord(count1, isRecordSelected1, name1);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			JavascriptExecutor jse1112 = (JavascriptExecutor) driver;
			WebElement element1112 = driver.findElement(By.id("masterSolprepStdReStdRDSSelBtnInSelectionWin"));
			jse1112.executeScript("arguments[0].click();", element1112);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("testNameInMasterSolutionStdReStdzRDS"))
					.sendKeys(properties.getProperty("TestNaame"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Test Name", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse152 = (JavascriptExecutor) driver;
			WebElement element152 = driver.findElement(By.id("selMasterSolutionStdzReStdzDetlsApproveFromSelBtn"));
			jse152.executeScript("arguments[0].click();", element152);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse1152 = (JavascriptExecutor) driver;
			WebElement element1152 = driver.findElement(By.id("locTreeInCalPmBdm_2_span"));
			jse1152.executeScript("arguments[0].click();", element1152);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
			Thread.sleep(4000);
			int count = 0;
			boolean isRecordSelected = false;
			String selectingUserSingleApproval = properties.getProperty("FirstName");
			isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval,
					isRecordSelected, count);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse11512 = (JavascriptExecutor) driver;
			WebElement element11512 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse11512.executeScript("arguments[0].click();", element11512);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("commentInMasterSolutionStdzReStdzRDS"))
					.sendKeys(properties.getProperty("Comments_100"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Comments", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("numberOfVariablesInMasterSolutionStdReStdzRDS"))
					.sendKeys(properties.getProperty("TotalQuantityRequired"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Variables", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse115102 = (JavascriptExecutor) driver;
			WebElement element115102 = driver.findElement(By.id("addNumberOfVariablesForm"));
			jse115102.executeScript("arguments[0].click();", element115102);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("variableInMultiStatistical_1")).sendKeys("A");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Variable", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("variableSymbolInMultiStatistical_1")).sendKeys("a");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Symbol", sno, false);
			Thread.sleep(2000);
			sno++;
			Select uom = new Select(driver.findElement(By.id("uomUnitsInTableInMultiStatistical_1")));
			Thread.sleep(1000);
			uom.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("variableInMultiStatistical_2")).sendKeys("B");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Variable", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("variableSymbolInMultiStatistical_2")).sendKeys("b");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Symbol", sno, false);
			Thread.sleep(2000);
			sno++;
			Select uom1 = new Select(driver.findElement(By.id("uomUnitsInTableInMultiStatistical_2")));
			Thread.sleep(1000);
			uom1.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse21 = (JavascriptExecutor) driver;
			WebElement element21 = driver.findElement(By.linkText("Next"));
			jse21.executeScript("arguments[0].click();", element21);
			Thread.sleep(3000);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(4000);
			sno++;
			driver.findElement(By.id("formulaInMasterSolutionStdReStdzRDS"))
					.sendKeys(properties.getProperty("Formula"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Fomula", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("descriptionInMasterSolutionStdzReStdzRDS"))
					.sendKeys(properties.getProperty("comments_200"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description Of Reading", sno,
					false);
			Thread.sleep(2000);
			sno++;
			Select uom12 = new Select(driver.findElement(By.id("uomUnitsInMasterSolutionStdzReStdzRDS")));
			Thread.sleep(1000);
			uom12.selectByIndex(1);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("numberOfDecimalsInMasterSolutionStdReStdzRDS")).sendKeys("2");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Decimals", sno, false);
			Thread.sleep(2000);
			
		} else {
			System.out.println("Record is not Selected");
		}
	}

	private boolean selectRecord(int count1, boolean isRecordSelected1, String name1) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("solutionPrepStdReStdRDSWindow"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
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
								"//*[@id=\"solutionPrepStdReStdRDSWindow\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
							JavascriptExecutor jse112 = (JavascriptExecutor) driver;
							WebElement element112 = driver.findElement(
									By.xpath("//*[@id=\"solutionPrepStdReStdRDSWindow\"]/div/table/tbody/tr[ " + i
											+ " ]/td[2]"));
							jse112.executeScript("arguments[0].click();", element112);
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
						JavascriptExecutor jse112 = (JavascriptExecutor) driver;
						WebElement element112 = driver.findElement(
								By.xpath("//*[@id=\"solutionPrepStdReStdRDSWindow\"]/div/table/tbody/tr/td[2]"));
						jse112.executeScript("arguments[0].click();", element112);
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					JavascriptExecutor jse112 = (JavascriptExecutor) driver;
					WebElement element112 = driver.findElement(By.cssSelector(
							"#solutionPrepStdReStdRDSWindow > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-last.jtable-page-number-disabled"));// next
																																																			// page
																																																			// in
																																																			// Document
																																																			// approve
					jse112.executeScript("arguments[0].click();", element112); // list
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
}
