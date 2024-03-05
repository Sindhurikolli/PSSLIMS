package com.pss.lims.Stability.Admin;

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
import com.pss.lims.Satbility.Login.LoginDetails;
import com.pss.lims.util.HeaderFooterPageEvent;
import com.pss.lims.util.Utilities;

public class ApproveRole extends LoginDetails {
	@Test
	public void approveRole() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "ApproveRole" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Approve Role", "PSS-LIMS-002", "Pass");
		writer.setPageEvent(event);
		document.open();
		Thread.sleep(1000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("AdminApprover_Login"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("limsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("limsModule"));
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='approvalRolesInSM.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='approvalRolesInSM.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		jse1.executeScript("arguments[0].click();", element1);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Role", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("#roleStabManagmtApprovalTable > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(4000);
		toApproveRole();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void toApproveRole() throws Exception {

		sno++;
		int count = 0;
		String AssignRoleRecord = properties.getProperty("Role_Name");
		boolean isRecordSelectedForAssignRole = false;
		isRecordSelectedForAssignRole = selectRecordToAssignRole(AssignRoleRecord, isRecordSelectedForAssignRole,
				count);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select the record", sno, false);
		if (isRecordSelectedForAssignRole) {
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("commentsInViewSMRolesApproval"))
					.sendKeys(properties.getProperty("Approval_Comments"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the approver comments", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("approveBtnInRoleApproval")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on approve button", sno, false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter the E-Signature password", sno,
					false);
			Thread.sleep(3000);
			sno++;
			driver.findElement(By.id("subBtnInValidateESign")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit button", sno, false);
			Thread.sleep(2000);
			sno++;
			WebDriverWait wait = new WebDriverWait(driver, 70);
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
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

	private boolean selectRecordToAssignRole(String AssignRoleRecord, boolean isRecordSelectedForAssignRole, int count)
			throws InterruptedException {
		WebElement table = driver.findElement(By.id("roleStabManagmtApprovalTable"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"roleStabManagmtApprovalTable\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((AssignRoleRecord == null) || ("".equalsIgnoreCase(AssignRoleRecord)))) {
				AssignRoleRecord = driver
						.findElement(By.xpath("//*[@id=\"roleStabManagmtApprovalTable\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((AssignRoleRecord == null) || ("".equalsIgnoreCase(AssignRoleRecord))) {
				AssignRoleRecord = driver
						.findElement(By.xpath("//*[@id=\"roleStabManagmtApprovalTable\"]/div/table/tbody/tr/td[2]"))
						.getText();// documentType
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String newDoctReqNameInApproval = driver.findElement(By.xpath(
								"//*[@id=\"roleStabManagmtApprovalTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (AssignRoleRecord.equalsIgnoreCase(newDoctReqNameInApproval)) {
							driver.findElement(By.xpath(
									"//*[@id=\"roleStabManagmtApprovalTable\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
									.click();
							isRecordSelectedForAssignRole = true;
							break;
						}
					}
					if (isRecordSelectedForAssignRole) {
						break;
					}
				} else {
					String newDoctReqNameInApproval = driver
							.findElement(By.xpath("//*[@id=\"roleStabManagmtApprovalTable\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (AssignRoleRecord.equalsIgnoreCase(newDoctReqNameInApproval)) {
						driver.findElement(
								By.xpath("//*[@id=\"roleStabManagmtApprovalTable\"]/div/table/tbody/tr/td[2]")).click();
						isRecordSelectedForAssignRole = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForAssignRole) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#roleStabManagmtApprovalTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next
					// page
					// in
					// Document
					// approve
					// list
					Thread.sleep(3000);
					table = driver.findElement(By.id("roleStabManagmtApprovalTable"));// Document Tree approve table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelectedForAssignRole;
	}

	@AfterMethod
	public void testIT(ITestResult result)

	{
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}

	}
}
