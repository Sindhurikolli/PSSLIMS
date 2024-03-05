package com.pss.lims.sm.InternalForwardFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import org.openqa.selenium.By;
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

public class MicroJobAllotment extends SMLoginDetails {

	@Test
	public void createMicroJobAllotment() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "MicroJobAllotment" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Micro Job Allotment", "LIMS-SM-095", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("SampleManager_Login"));
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='microJobAllotmentInSample.do']")));
		driver.findElement(By.cssSelector("a[href='microJobAllotmentInSample.do']")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on MicroJobAllotment", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#limsMicroJobAllotmentGrid > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		methodTocreateMicroJobAllotment();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodTocreateMicroJobAllotment() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 150);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String arNumber = properties.getProperty("AR_Number");
		isRecordSelected1 = selectRecordForMicroJobAllotment(count1, isRecordSelected1, arNumber);
		if (isRecordSelected1) {
			sno++;
			Thread.sleep(3000);
//			driver.findElement(By.xpath("//*[@id=\"limsMicroJobAllotmentGrid\"]/div/table/tbody/tr/td[10]/button")).click();
//			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on View", sno, false);
//			Thread.sleep(4000);
//			String mainWindow = driver.getWindowHandle();
//			System.out.println("mainwindow" + mainWindow);
//			Thread.sleep(6000);
//			Set<String> set = driver.getWindowHandles();
//			java.util.Iterator<String> itr = set.iterator();
//			while (itr.hasNext()) {
//				String childWindow = itr.next();
//				if (!mainWindow.equals(childWindow)) {
//					driver.switchTo().window(childWindow);
//					System.out.println(driver.switchTo().window(childWindow).getTitle());
//					driver.close();
//				}
//			}
//			Thread.sleep(3000);
//			driver.switchTo().window(mainWindow);
//			Thread.sleep(2000);
//			isRecordSelected1 = selectRecordForMicroJobAllotment(count1, isRecordSelected1, arNumber);
			sno++;
			driver.findElement(By.id("viewAndAllotBtnInLimsMicroJobAllotment")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on View & Allot", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#TestGridInLimsJobAllotMent > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(2000);
			int count2 = 0;
			boolean isRecordSelected2 = false;
			String name2 = properties.getProperty("Micro_Test_Name");
			isRecordSelected2 = selectTest(count2, isRecordSelected2, name2);
			sno++;
//			driver.findElement(By.xpath("//*[starts-with(@id, \"checkboxInSampleAllotTestGrid\")]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on CheckBox", sno, false);
			Thread.sleep(3000);
			sno++;//*[@id="nextBtnInViewJobAllotmentForm"]/span
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(4000);
			
			 try   
			  { 
				
				if(driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/font/b")).isDisplayed()) {
					
					driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a[2]")).click();
				}
				
			  }      
			  catch(Exception e) {
				 {
					
					System.out.println("This Is Not First Sample Of The Year");
				}	
			  }			
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.cssSelector("#selInchargeInTestAllot1")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Browse", sno, false);
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
			String selectingUserSingleApproval = properties.getProperty("MyJobUserLastName");
			isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval,
					isRecordSelected, count);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Record", sno, false);
			sno++;
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			Select user = new Select(driver.findElement(By.id("reviewerIdInTestAllot1")));
			Thread.sleep(2000);
			user.selectByVisibleText(properties.getProperty("Reviewer_In_JobAllotment"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Reviewer", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("submitBtnInViewTestAllotWindow")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
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

	private boolean selectTest(int count2, boolean isRecordSelected2, String name2) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("TestGridInLimsJobAllotMent"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver.findElement(By.xpath("//*[@id=\"limsMicroJobAllotmentGrid\"]/div/div[4]/div[2]/span")).getText();// For
//			// Ex:
//			// Showing
//			// 1-1
//			// of
//			// 1//*[@id="TestGridInLimsJobAllotMent"]/div/table/tbody/tr/td[5]
//			String[] parts = a.split(" of ");
//			try {
//				totalNoOfRecords = Integer.parseInt(parts[1].trim());
//				System.out.println(totalNoOfRecords);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
			if ((totalNoOfRecords > 1) && ((name2 == null) || ("".equalsIgnoreCase(name2)))) {
				name2 = driver
						.findElement(By.xpath("//*[@id=\"TestGridInLimsJobAllotMent\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// documentType
			} else if ((name2 == null) || ("".equalsIgnoreCase(name2))) {
				name2 = driver.findElement(By.xpath("//*[@id=\"TestGridInLimsJobAllotMent\"]/div/table/tbody/tr/td[5]"))
						.getText();// document
									// type//*[@id="limsMicroJobAllotmentGrid"]/div/table/tbody/tr/td[10]
			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String arNumberSequence = driver.findElement(By
								.xpath("//*[@id=\"TestGridInLimsJobAllotMent\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
								.getText();// documentTypeName
						if (name2.equalsIgnoreCase(arNumberSequence)) {
							driver.findElement(By.xpath("//*[@id=\"TestGridInLimsJobAllotMent\"]/div/table/tbody/tr[ "
									+ i + " ]/td[2]/input")).click();
							isRecordSelected2 = true;
							break;
						}
					}
					if (isRecordSelected2) {
						break;
					}
				} else {
					String arNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"TestGridInLimsJobAllotMent\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (name2.equalsIgnoreCase(arNumberSequence)) {
						driver.findElement(
								By.xpath("//*[@id=\"TestGridInLimsJobAllotMent\"]/div/table/tbody/tr/td[2]/input"))
								.click();
						isRecordSelected2 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected2) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#limsMicroJobAllotmentGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("limsMicroJobAllotmentGrid"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelected2;
	}

	private boolean selectRecordForMicroJobAllotment(int count1, boolean isRecordSelected1, String arNumber)
			throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("limsMicroJobAllotmentGrid"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"limsMicroJobAllotmentGrid\"]/div/div[4]/div[2]/span")).getText();// For
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
			if ((totalNoOfRecords > 1) && ((arNumber == null) || ("".equalsIgnoreCase(arNumber)))) {
				arNumber = driver.findElement(By.xpath("//*[@id=\"limsMicroJobAllotmentGrid\"]/div/table/tbody/tr[ \" + i + \" ]/td[10]"))
						.getText();// documentType
			} else if ((arNumber == null) || ("".equalsIgnoreCase(arNumber))) {
				arNumber = driver.findElement(By.xpath("//*[@id=\"limsMicroJobAllotmentGrid\"]/div/table/tbody/tr/td[10]"))
						.getText();// document
									// type
			}
			++count1;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String arNumberSequence = driver
								.findElement(By
										.xpath("//*[@id=\"limsMicroJobAllotmentGrid\"]/div/table/tbody/tr[ " + i + " ]/td[10]"))
								.getText();// documentTypeName
						if (arNumber.equalsIgnoreCase(arNumberSequence)) {
							driver.findElement(
									By.xpath("//*[@id=\"limsMicroJobAllotmentGrid\"]/div/table/tbody/tr[ " + i + " ]/td[10]"))
									.click();
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String arNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"limsMicroJobAllotmentGrid\"]/div/table/tbody/tr[ \" + i + \" ]/td[10]"))
							.getText();
					if (arNumber.equalsIgnoreCase(arNumberSequence)) {
						driver.findElement(By.xpath("//*[@id=\"limsMicroJobAllotmentGrid\"]/div/table/tbody/tr[ \" + i + \" ]/td[10]"))
								.click();
						isRecordSelected1 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected1) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#limsMicroJobAllotmentGrid > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("limsMicroJobAllotmentGrid"));// Document Tree approve table
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
