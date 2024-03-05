package com.pss.lims.sm.InternalForwardFlow;

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
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class MutiTestsJobResultsSampleManagementFinishedProduct extends SMLoginDetails {

	@Test
	public void approveJobResults() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "JobResults" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("JobResults", "LIMS-SM-097", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("Analyst"));
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='jobResultsPageInSample.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='jobResultsPageInSample.do']"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Job Results", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#jobResultsJTable > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		sno++;
		methodToapproveJobResults();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodToapproveJobResults() throws Exception {
		
		driver.findElement(By.id("ARNumSearchInJobResults")).sendKeys(properties.getProperty("AR_Number"));
		Thread.sleep(1000);
		driver.findElement(By.id("searchBtnInJobResults")).click();
		Thread.sleep(4000);
		Helper.waitLoadRecords(driver, By.cssSelector("#jobResultsJTable > div > div.jtable-busy-message[style='display: none;']"));
		int count = 0;
		boolean isRecordSelected = false;
//		String arNumber = properties.getProperty("AR_Number");
		String arNumber = null;
		String testType = null;
		String testTypeforEval = selectRecordForJobResults(count, isRecordSelected, arNumber, testType);
		System.out.println("TestType-"+ testTypeforEval);
//		if(isRecordSelected)
//		{
		if (testTypeforEval!=null && testTypeforEval.equalsIgnoreCase("Qualitative")) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Record", sno, false);
			Thread.sleep(3000);
			sno++;
			Helper.clickElement(driver, By.id("evaluateBtnInSampleResult"));
//			driver.findElement(By.id("evaluateBtnInSampleResult")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("option1_InQualJobResult1")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Compliance Value", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("submitBtnInQualTestInJobResults")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(2000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
			Thread.sleep(2000);
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			WebDriverWait wait1 = new WebDriverWait(driver, 70);
			wait1.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
			if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).isDisplayed()) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			}
			Thread.sleep(3000);
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Yes,Sure!", sno, false);
			driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a[1]")).click();
			Thread.sleep(3000);
			if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).isDisplayed()) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			}
			Thread.sleep(3000);
			}
		

		else if (testTypeforEval!=null && testTypeforEval.equalsIgnoreCase("Quantitative")) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Record", sno, false);
			Thread.sleep(3000);
			sno++;
			Helper.clickElement(driver, By.id("evaluateBtnInSampleResult"));
//			driver.findElement(By.id("evaluateBtnInSampleResult")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
			Thread.sleep(3000);
			sno++;
			int noofVariables = Integer.parseInt(properties.getProperty("No_of_variables"));
			String[] Variables = properties.getProperty("Verify_Value").split(",");
			for (int i = 1; i <= noofVariables; i++) {
				sno++;
				driver.findElement(By
						.xpath("//*[@id=\"variableGridStoreInJobResult1\"]/div/table/tbody/tr[" + i + "]/td[5]/input"))
						.sendKeys(Variables[i - 1]);
				Thread.sleep(2000);
	
			}
			
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("submitBtnInMultStatTestInJobResults")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(4000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
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
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Yes,Sure!", sno, false);
			driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a[1]")).click();
			Thread.sleep(3000);
			if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).isDisplayed()) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			}
			Thread.sleep(3000);
		}
		
		
		
		else if (testTypeforEval!=null && testTypeforEval.equalsIgnoreCase("Statistical")) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Record", sno, false);
			Thread.sleep(3000);
			sno++;
			Helper.clickElement(driver, By.id("evaluateBtnInSampleResult"));
//			driver.findElement(By.id("evaluateBtnInSampleResult")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
			Thread.sleep(3000);
			sno++;
			int noofVariables = Integer.parseInt(properties.getProperty("No_of_variables_Statistical"));
			String[] Variables = properties.getProperty("Verify_Value_Statistical").split(",");
			for (int i = 1; i <= noofVariables; i++) {
				sno++;
//				driver.findElement(By
//						.xpath("//*[@id=\"noOfVarSetsCompositStartId\"]/div[" + i + "]/div/div[5]/div/input"))
//						.sendKeys(Variables[i - 1]);
				
				driver.findElement(By.id("StatisticalValueInJobResult"+i)).sendKeys(Variables[i - 1]);
				Thread.sleep(2000);
			}
			
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("submitBtnInStatTestInJobResults")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(4000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
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
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Yes,Sure!", sno, false);
			driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a[1]")).click();
			Thread.sleep(3000);
			if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).isDisplayed()) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			}
			Thread.sleep(3000);
		}		
		
		
		else if (testTypeforEval!=null && testTypeforEval.equalsIgnoreCase("Multi Quantitative")) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Record", sno, false);
			Thread.sleep(3000);
			sno++;
			Helper.clickElement(driver, By.id("evaluateBtnInSampleResult"));
//			driver.findElement(By.id("evaluateBtnInSampleResult")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Evaluate", sno, false);
			Thread.sleep(3000);
			sno++;
			int noofVariables = Integer.parseInt(properties.getProperty("No_of_variables_MultiQuantitaive"));
			String[] Variables = properties.getProperty("Verify_Value_Multi_QUantitative").split(",");
			for (int i = 1; i <= noofVariables; i++) {
				sno++;
//				driver.findElement(By
//						.xpath("//*[@id=\"noOfVarSetsCompositStartId\"]/div[" + i + "]/div/div[5]/div/input"))
//						.sendKeys(Variables[i - 1]);
				
				driver.findElement(By.id("QuantitativeValueInJobResult"+i)).sendKeys(Variables[i - 1]);
				Thread.sleep(2000);
			}
			
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("submitBtnInMultQuantTestInJobResults")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(4000);
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
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
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Yes,Sure!", sno, false);
			driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a[1]")).click();
			Thread.sleep(3000);
			if (driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).isDisplayed()) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno, false);
				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
			}
			Thread.sleep(3000);
		}		
		
		
		
//		}
		else
		{
			System.out.println("Record Not Selected");
			Assert.assertTrue(false);
		}
			sno++;
			driver.findElement(By.className("username")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

		

	}

	private String selectRecordForJobResults(int count, boolean isRecordSelected, String arNumber, String testType) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("jobResultsJTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"jobResultsJTable\"]/div/div[4]/div[2]/span")).getText();// For

			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((arNumber == null) || ("".equalsIgnoreCase(arNumber)))) {
				arNumber = driver.findElement(By.xpath("//*[@id=\"jobResultsJTable\"]/div/table/tbody/tr[1]/td[6]"))
						.getText();
			} else if ((arNumber == null) || ("".equalsIgnoreCase(arNumber))) {
				arNumber = driver.findElement(By.xpath("//*[@id=\"jobResultsJTable\"]/div/table/tbody/tr/td[6]"))
						.getText();// document
									// type
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						Helper.scrollElement(driver, By.xpath("//*[@id=\"jobResultsJTable\"]/div/table/tbody/tr[ " + i + " ]/td[6]"));
						String arNumberSequence = driver.findElement(By.xpath("//*[@id=\"jobResultsJTable\"]/div/table/tbody/tr[ " + i + " ]/td[6]")).getText();// documentTypeName
						if (arNumber.contains(arNumberSequence)) {
							Helper.clickElement(driver, By.xpath("//*[@id=\"jobResultsJTable\"]/div/table/tbody/tr[" + i + "]/td[6]"));
//							driver.findElement(By.xpath("//*[@id=\"jobResultsJTable\"]/div/table/tbody/tr[" + i + "]/td[6]")).click();
							isRecordSelected = true;
							testType = driver.findElement(By.xpath("//*[@id=\"jobResultsJTable\"]/div/table/tbody/tr[ " + i + " ]/td[23]")).getText();
							
							break;
						}
					}
					if (isRecordSelected) {
						break;
					}
				} else {
					String arNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"jobResultsJTable\"]/div/table/tbody/tr/td[6]")).getText();
					if (arNumber.contains(arNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"jobResultsJTable\"]/div/table/tbody/tr/td[6]")).click();
						isRecordSelected = true;
						testType = driver.findElement(By.xpath("//*[@id=\"jobResultsJTable\"]/div/table/tbody/tr/td[23]")).getText();
						
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#jobResultsJTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("jobResultsJTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return testType;
	}
	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
