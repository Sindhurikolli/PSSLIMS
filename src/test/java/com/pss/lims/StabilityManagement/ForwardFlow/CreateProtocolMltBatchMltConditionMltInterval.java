package com.pss.lims.StabilityManagement.ForwardFlow;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.time.DateUtils;
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
import com.pss.lims.util.Helper;
import com.pss.lims.util.Utilities;

public class CreateProtocolMltBatchMltConditionMltInterval extends LoginDetails {

	@Test
	public void createProtocolMltBatchMltConditionMltInterval() throws Exception {

		document = new Document();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN);
		output = System.getProperty("user.dir") + "\\" + "/TestReport/" + "CreateProtocol" + (new Random().nextInt())
				+ ".pdf";
		fos = new FileOutputStream(output);
		writer = PdfWriter.getInstance(document, fos);
		writer.setStrictImageSequence(true);
		writer.open();
		HeaderFooterPageEvent event = new HeaderFooterPageEvent("Create Protocol", "LIMS-SM-004", "Pass");
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
		Thread.sleep(3000);
		sno++;
		WebDriverWait wiat = new WebDriverWait(driver, 240);
		wiat.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='createProtocolForm.do'")));
		driver.findElement(By.cssSelector("a[href='createProtocolForm.do'")).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Create", sno, false);
		Thread.sleep(4000);
		methodForProtocolCreatemulti();
		document.close();
		writer.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(output);
		//desktop.open(file);

	}

	private void methodForProtocolCreatemulti() throws Exception {

//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
		WebDriverWait wait = new WebDriverWait(driver, 150);
		Thread.sleep(3000);
		sno++;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[2]/a"));
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", ele);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
		Thread.sleep(4000);
		sno++;
		driver.switchTo().frame("purposeInProtocolForm_ifr");
		driver.findElement(By.xpath("/html/body")).sendKeys(properties.getProperty("Objective"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Purpose", sno, false);
		Thread.sleep(2000);
		sno++;
		js.executeScript("window.scrollBy(0,250)");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("productInformationProtocolForm_ifr");
		driver.findElement(By.xpath("/html/body")).sendKeys(properties.getProperty("Responsibilities"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product Information", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.switchTo().defaultContent();
		driver.switchTo().frame("productDescProtocolForm_ifr");
		driver.findElement(By.xpath("/html/body")).sendKeys(properties.getProperty("StabilityPlan"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Product Description", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.switchTo().defaultContent();
		driver.switchTo().frame("selOfBatchesInProtocolForm_ifr");
		driver.findElement(By.xpath("/html/body")).sendKeys(properties.getProperty("Procedure"));
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Selection Of Batches", sno, false);
		Thread.sleep(2000);
		sno++;
		driver.switchTo().defaultContent();
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("selAppFromUserInProtocolForm"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		JavascriptExecutor jse31 = (JavascriptExecutor) driver;
		WebElement element31 = driver.findElement(By.id("selSingleProductBtnInProtocolForm"));
		jse31.executeScript("arguments[0].click();", element31);
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Browse", sno, false);
		Thread.sleep(5000);
		sno++;
		JavascriptExecutor jse311 = (JavascriptExecutor) driver;
		WebElement element311 = driver.findElement(By.id("locTreeInLimsSmReg_2_switch"));
		jse311.executeScript("arguments[0].click();", element311);
		Thread.sleep(3000);
		driver.findElement(By.linkText(properties.getProperty("Location_Name"))).click();
		document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Location", sno, false);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#productsTableContainer > div > div.jtable-busy-message[style='display: none;']")));
		Thread.sleep(3000);
		int count1 = 0;
		boolean isRecordSelected1 = false;
		String name1 = properties.getProperty("Product_code");
		isRecordSelected1 = selectRecordForProduct(count1, isRecordSelected1, name1);
		if (isRecordSelected1) {
			sno++;
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse3111 = (JavascriptExecutor) driver;
			WebElement element3111 = driver.findElement(By.id("productSelBtnInProdSelWin"));
			jse3111.executeScript("arguments[0].click();", element3111);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(2000);
			sno++;
			Select spec = new Select(driver.findElement(By.id("specInProtForm")));
			Thread.sleep(2000);
			spec.selectByVisibleText(properties.getProperty("Spec_No"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Specification", sno, false);
			Thread.sleep(2000);
			sno++;
			Select customer = new Select(driver.findElement(By.id("customerNameInProtocolForm")));
			Thread.sleep(2000);
			customer.selectByVisibleText(properties.getProperty("Customer_Name"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Customer", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse11 = (JavascriptExecutor) driver;
			WebElement element11 = driver.findElement(By.id("selAppFromUserInProtocolForm"));
			jse11.executeScript("arguments[0].click();", element11);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(5000);
			sno++;
			JavascriptExecutor jse10 = (JavascriptExecutor) driver;
			WebElement element10 = driver.findElement(By.id("locTreeInCalPmBdm_2_switch"));
			jse10.executeScript("arguments[0].click();", element10);
			Thread.sleep(3000);
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
			Thread.sleep(3000);
			sno++;
			JavascriptExecutor jse4210 = (JavascriptExecutor) driver;
			WebElement element4210 = driver.findElement(By.id("usersSelBtnInLocaBasedUser"));
			jse4210.executeScript("arguments[0].click();", element4210);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
			Thread.sleep(3000);
			sno++;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ele);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(By.id("selOfBatchesInProtForm")).sendKeys(properties.getProperty("NoOfBatches"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Batches", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse42110 = (JavascriptExecutor) driver;
			WebElement element42110 = driver.findElement(By.id("addNumberOfBatchesForm"));
			jse42110.executeScript("arguments[0].click();", element42110);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
			Thread.sleep(3000);
			sno++;
			int noOfBatches = Integer.parseInt(properties.getProperty("NoOfBatches"));
			for(int i=1;i<=noOfBatches; i++)
			{
			driver.findElement(By.id("batchNoInProtForm_"+i)).sendKeys(properties.getProperty("Batch"+i));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batch No", sno, false);
			Thread.sleep(2000);
			sno++;
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
//			String mfgDate= dateFormat.format(date);
//			driver.findElement(By.id("mfgDateInProtForm_"+i)).sendKeys(mfgDate);
			driver.findElement(By.id("mfgDateInProtForm_"+i)).click();
			driver.findElement(By.className("ui-datepicker-today")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Manufacturing Date", sno, false);
			Thread.sleep(2000);
			sno++;
			int quantity =5;
			date = DateUtils.addYears(date, quantity);
			String expDate = dateFormat.format(date);
			driver.findElement(By.id("expDateInProtForm_"+i)).sendKeys(expDate);
			driver.findElement(By.id("batchSizeInProtForm_"+i)).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Expiry Date", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("batchSizeInProtForm_"+i)).sendKeys("100");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batch Size", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("customerInProtForm_"+i)).sendKeys("soft sol");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Customer", sno, false);
			Thread.sleep(2000);
			sno++;
			}
			
			driver.findElement(By.id("noOfRowsForAPIInProtForm")).sendKeys(properties.getProperty("NoOfAPIs"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document,
					"Enter No.of Rows For Product Information", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse110 = (JavascriptExecutor) driver;
			WebElement element110 = driver.findElement(By.id("addNumberOfRowsAPIForm"));
			jse110.executeScript("arguments[0].click();", element110);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
			Thread.sleep(3000);
			sno++;
			int noOfAPIs = Integer.parseInt(properties.getProperty("NoOfAPIs"));
			for(int j=1; j<=noOfAPIs; j++ )
			{
			driver.findElement(By.id("batchNoInProtFormAPI_"+j)).sendKeys(properties.getProperty("Batch"+j));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batche No", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("nameOfMaterialUsedInProtFormAPI_"+j)).sendKeys("APImaterial");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Name of Material Used", sno,
					false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("batchNumberInProtFormAPI_"+j)).sendKeys("APIBatch13");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batche Number", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("arNoInProtFormAPI_"+j)).sendKeys("PSS-01");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter AR No", sno, false);
			Thread.sleep(2000);
			sno++;
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
//			String mfgDate= dateFormat.format(date);
			driver.findElement(By.id("manufactDateInProtFormAPI_"+j)).click();
			driver.findElement(By.className("ui-datepicker-today")).click();
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Manufacturer Date", sno, false);
			Thread.sleep(2000);
			sno++;
			int quantity =3;
			date = DateUtils.addYears(date, quantity);
			String expDate = dateFormat.format(date);
			driver.findElement(By.id("expDateInProtFormAPI_"+j)).sendKeys(expDate);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Expiry Date", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("manuFactByInProtFormAPI_"+j)).click();
			driver.findElement(By.id("manuFactByInProtFormAPI_"+j)).sendKeys("Pharma");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Manufactured By", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse01 = (JavascriptExecutor) driver;
			WebElement element01 = driver.findElement(By.id("isAPIInProtFormAPI_"+j));
			jse01.executeScript("arguments[0].click();", element01);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on API", sno, false);
			Thread.sleep(3000);
			sno++;
			}
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ele);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			driver.findElement(By.id("noOfRowsForPPMInProtForm")).sendKeys(properties.getProperty("NoOfPrimaryPackingMaterials"));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Rows", sno, false);
			Thread.sleep(2000);
			sno++;
			JavascriptExecutor jse0 = (JavascriptExecutor) driver;
			WebElement element0 = driver.findElement(By.id("addNumberOfRowsPPM"));
			jse0.executeScript("arguments[0].click();", element0);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
			Thread.sleep(3000);
			sno++;
			int noOfPPMs = Integer.parseInt(properties.getProperty("NoOfPrimaryPackingMaterials"));
			for (int k=1; k<=noOfPPMs;k++)
			{
			driver.findElement(By.id("batchNoInProtFormPPM_"+k)).sendKeys(properties.getProperty("Batch"+k));
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Batche No", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("modeOfPackInProtFormPPM_"+k)).sendKeys("Glass_1_mm");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Packing Component", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("contInProtFormPPM_"+k)).sendKeys("2");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Description", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("typeOfPackMaterialInProtFormPPM_"+k)).sendKeys("Glass");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Type Of Packing Material Used", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("manufactBySuppByInProtFormPPM_"+k)).sendKeys("biological");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Manufactured By", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("resinInProtFormPPM_"+k)).sendKeys("MTL00N");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Material Code", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("arNoInProtFormPPM_"+k)).sendKeys("PP-01");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter AR NO", sno, false);
			Thread.sleep(2000);
			sno++;
			driver.findElement(By.id("quantityContInProtFormPPM_"+k)).sendKeys("10");
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Quantity Per Container", sno,
					false);
			Thread.sleep(2000);
			sno++;
			
			}
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", ele);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
			Thread.sleep(6000);
			sno++;
			JavascriptExecutor jse3 = (JavascriptExecutor) driver;
			WebElement element3 = driver.findElement(By.id("selectStorageConditions"));
			jse3.executeScript("arguments[0].click();", element3);
			document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on Select", sno, false);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
					"#multiSelectStorageConditionsTableContainer > div > div.jtable-busy-message[style='display: none;']")));
			Thread.sleep(5000);
			int noOfStorageConditions = Integer.parseInt(properties.getProperty("NoOfStorageConditions"));
			int count2 = 0;
			boolean isRecordSelected2 = false;
			for (int sc=1; sc<=noOfStorageConditions;sc++)
			{

				String name2 = properties.getProperty("StorageCondition"+sc);
				String nameori = properties.getProperty("SampleOrientation"+sc);
				String namestcon = properties.getProperty("StabilityCondition"+sc);
				isRecordSelected2 = selectRecordForMultiStorage(count2, isRecordSelected2, name2, nameori, namestcon);
				if(sc<noOfStorageConditions)
				{
				count2 = 0;
				isRecordSelected2 = false;
				}
			}
//			int count2 = 0;
//			boolean isRecordSelected2 = false;
//			String name2 = properties.getProperty("Storage_Condition_Name");
//			isRecordSelected2 = selectRecordForStorage(count2, isRecordSelected2, name2);
			if (isRecordSelected2) {
				sno++;
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select a Record", sno, false);
				Thread.sleep(3000);
				sno++;
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				WebElement element = driver.findElement(By.id("multiSelBtnInStorageConditionSelectionWin"));
				jse.executeScript("arguments[0].click();", element);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("samplingInProtocolForm")).sendKeys(properties.getProperty("Description"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Sampling & Labeling", sno,
						false);
				Thread.sleep(3000);
				sno++;
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", ele);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(6000);
				sno++;
				driver.findElement(By.id("noOfRowsForSDKTInProtForm")).sendKeys(properties.getProperty("NoOfSampleDetails"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter No.of Rows", sno, false);
				Thread.sleep(3000);
				sno++;
				JavascriptExecutor jse30 = (JavascriptExecutor) driver;
				WebElement element30 = driver.findElement(By.id("addNumberOfRowsSDKTProtForm"));
				jse30.executeScript("arguments[0].click();", element30);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
				Thread.sleep(2000);
				sno++;
				
				int noOfSampleDetails = Integer.parseInt(properties.getProperty("NoOfSampleDetails"));
				int count3 = 0;
				boolean isRecordSelected3 = false;
				int Batches = Integer.parseInt(properties.getProperty("NoOfBatches"));
				int r = 1;
				for(int sm=1; sm<=Batches; sm++)
				{
					
					int noOfStorageCons = Integer.parseInt(properties.getProperty("NoOfStorageConditions"));
					for(int sj=1; sj<=noOfStorageCons;sj++)
					{
						Helper.clickElement(driver, By.xpath("//*[@id=\"sampleDetailsKeptStabilityTableContainer\"]/div/table/tbody/tr[" + r + "]/td[8]/button"));
//					driver.findElement(By.xpath("//*[@id=\"sampleDetailsKeptStabilityTableContainer\"]/div/table/tbody/tr[" + r + "]/td[8]/button")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);
					Thread.sleep(2000);
					sno++;
					String namebtch = properties.getProperty("Batch"+sm);
					String namestrgcon = properties.getProperty("StorageCondition"+sj);
					String nameori = properties.getProperty("SampleOrientation"+sj);
					String namestcon = properties.getProperty("StabilityCondition"+sj);
					isRecordSelected3 = selectRecordForMultiSample(count3, isRecordSelected3, namebtch, namestrgcon, nameori, namestcon);
					if(sm<noOfSampleDetails)
					{
					count3 = 0;
					isRecordSelected3 = false;
					}
					
				driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsSampleDialog\"]/div[2]/div/button")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Select Button", sno, false);
				Thread.sleep(2000);
				sno++;
//				Select storage = new Select(driver.findElement(By.id("strgConditionsDrpDownSDKT_1")));
//				Thread.sleep(2000);
//				storage.selectByIndex(1);
//				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Storage Condition", sno,
//						false);
//				Thread.sleep(2000);
//				sno++;
//				Helper.scrollElement(driver, By.id("quantityChrgdInProtSampleDKT_"+r));
				driver.findElement(By.id("quantityChrgdInProtSampleDKT_"+r)).sendKeys(properties.getProperty("QtyCharged"+r));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Quantity Charged", sno,
						false);
				Thread.sleep(2000);
				sno++;
				Select uom = new Select(driver.findElement(By.id("uomDrpDownSDKT_"+r)));
				Thread.sleep(2000);
				uom.selectByIndex(1);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select UOM", sno, false);
				Thread.sleep(4000);
				sno++;
				r++;
				}
					
				}
				
				driver.findElement(By.id("protocolSuppFileUpload")).sendKeys(properties.getProperty("Document-1"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Upload Document", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.id("remarksInProtocolForm")).sendKeys(properties.getProperty("Description"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Remarks", sno, false);
				Thread.sleep(3000);
				sno++;
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", ele);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(6000);
				sno++;
				driver.findElement(By.id("noOfRowsInScIntStrCondBoxForm")).sendKeys(properties.getProperty("NoOfSscheduleDetails"));
				driver.findElement(By.id("addNumberOfRowsInSchStoCondJtable")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter no of rows and click on Add", sno, false);				
				sno++;
				Thread.sleep(2000);
				
				int noOfSchedulesIntervals = Integer.parseInt(properties.getProperty("NoOfSscheduleDetails"));
				int rw=1;
				for(int si=1; si<=noOfSchedulesIntervals; si++)
				{
					driver.findElement(By.xpath("//*[@id=\"newschIntervalStorageCondDetTableContainer\"]/div/table/tbody/tr[" + rw + "]/td[3]")).click();
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Add", sno, false);				
					sno++;
					Thread.sleep(5000);
					Helper.waitLoadRecords(driver, By.cssSelector("#batchAndSCFieldsDetailsJtableInSchStrCondFormDialog > div > div.jtable-busy-message[style='display: none;']"));
				driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSchStrCondFormDialog\"]/div/table/tbody/tr[" + rw + "]")).click();
				driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsSchStorageCondDialog\"]/div[2]/div/button")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "click on record and select", sno, false);				
				sno++;
				Thread.sleep(2000);
				Select Packtype = new Select(driver.findElement(By.id("packingTypeInPPPF_New_"+rw)));
				Packtype.selectByVisibleText(properties.getProperty("Packing_Type_Name"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Packing Type", sno, false);				
				sno++;
				Thread.sleep(2000);
				Select Scheduleinterval = new Select(driver.findElement(By.id("scheduleIntervalInPPFF_New_"+rw)));
				Scheduleinterval.selectByVisibleText(properties.getProperty("Interval_Type"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Select Schedule Interval type", sno, false);				
				sno++;
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"newschIntervalStorageCondDetTableContainer\"]/div/table/tbody/tr[" + rw + "]/td[18]/button")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on view", sno, false);				
				sno++;
				Thread.sleep(2000);
				String ScheduleIntervals = properties.getProperty("ScheduleIntervals"+rw);
				String intervals[] = ScheduleIntervals.split(",");
				for(int it=0; it<intervals.length;it++)
				{
				driver.findElement(By.id("rackNumberInScheduleStorageConditionsForm")).sendKeys(intervals[it]);
				driver.findElement(By.id("addRackToJtableInScheduleStorageConditionSForm")).click();
				}
				driver.findElement(By.id("selectInScheduleStorageConditionSForm")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter Interval then add and click on select", sno, false);				
				sno++;
				rw++;
				}
				Thread.sleep(2000);
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", ele);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Next", sno, false);
				Thread.sleep(6000);
				sno++;
				JavascriptExecutor jse5110 = (JavascriptExecutor) driver;
				WebElement element5110 = driver.findElement(By.xpath("//*[@id=\"TotalContent\"]/div[3]/ul/li[3]/a"));
				jse5110.executeScript("arguments[0].scrollIntoView(true);", element5110);
				Thread.sleep(2000);
				jse5110.executeScript("arguments[0].click();", element5110);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Finish", sno, false);
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Enter E-Signature Password", sno,
						false);
				Thread.sleep(2000);
				sno++;
				JavascriptExecutor jse7 = (JavascriptExecutor) driver;
				WebElement element7 = driver.findElement(By.id("subBtnInValidateESign"));
				jse7.executeScript("arguments[0].click();", element7);
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Submit", sno, false);
				Thread.sleep(3000);
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
				Thread.sleep(2000);
				String SuccssMessage=driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[2]/center")).getText();
				String Protocol=SuccssMessage.split(":")[1];
				System.out.println("Protocol Number is -"+Protocol);
				PropertiesConfiguration properties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\StabilityManagement.properties");
				properties.setProperty("Protocol_Number", Protocol);				
				properties.save();
				
				sno++;
					document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on OK button", sno,
							false);
					driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
				
				Thread.sleep(3000);
				sno++;
				driver.findElement(By.className("username")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on User Name", sno, false);
				Thread.sleep(2000);
				sno++;
				driver.findElement(By.cssSelector("a[href='Logout.do']")).click();
				document = Utilities.getScreenShotAndAddInLogDoc(driver, document, "Click on Logout", sno, true);

			} else {
				System.out.println("storage condition is not Selected");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Record is not Selected");
			Assert.assertTrue(false);
		}
	}

	private boolean selectRecordForStorage(int count2, boolean isRecordSelected2, String name2) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("multiSelectStorageConditionsTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(
							By.xpath("//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
//			System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((name2 == null) || ("".equalsIgnoreCase(name2)))) {
//				System.out.println("hi this is ravi");
				name2 = driver
						.findElement(By.xpath(
								"//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr[1]/td[6]"))
						.getText();// documentType
			} else if ((name2 == null) || ("".equalsIgnoreCase(name2))) {
				name2 = driver
						.findElement(By.xpath(
								"//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr/td[6]"))
						.getText();// document
									// type
			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(
								By.xpath("//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr[ "
										+ i + " ]/td[6]"))
								.getText();// documentTypeName
						if (name2.equalsIgnoreCase(DevNumberSequence)) {
//							driver.findElement(
//									By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr[ " + i
//											+ " ]/td[3]"))
//									.click();
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(By
									.xpath("//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr["
											+ i + "]/td[6]"));
							jse8.executeScript("arguments[0].click();", element8);
//							Thread.sleep(4000);
							isRecordSelected2 = true;
							break;
						}
					}
					if (isRecordSelected2) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath(
									"//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr/td[6]"))
							.getText();
					if (name2.equalsIgnoreCase(DevNumberSequence)) {
//						driver.findElement(
//								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr/td[3]"))
//								.click();
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver.findElement(By.xpath(
								"//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr/td[6]"));
						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected2 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected2) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#multiSelectStorageConditionsTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("multiSelectStorageConditionsTableContainer"));// Document Tree
																									// approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected2;
	}

	private boolean selectRecordForMultiStorage(int count2, boolean isRecordSelected2, String name2, String nameori, String namestcon) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("multiSelectStorageConditionsTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver
					.findElement(
							By.xpath("//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
//			System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((name2 == null) || ("".equalsIgnoreCase(name2)))) {
//				System.out.println("hi this is ravi");
				name2 = driver
						.findElement(By.xpath(
								"//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr[1]/td[6]"))
						.getText();// documentType
			} else if ((name2 == null) || ("".equalsIgnoreCase(name2))) {
				name2 = driver
						.findElement(By.xpath(
								"//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr/td[6]"))
						.getText();// document
									// type
			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[6]")).getText();// documentTypeName
						String Orientation = driver.findElement(By.xpath("//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[7]")).getText();
						String StailityCondition = driver.findElement(By.xpath("//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[8]")).getText();
						if (name2.equalsIgnoreCase(DevNumberSequence) && nameori.equalsIgnoreCase(Orientation) && namestcon.equalsIgnoreCase(StailityCondition) ) {
//							driver.findElement(
//									By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr[ " + i
//											+ " ]/td[3]"))
//									.click();
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(By
									.xpath("//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr["
											+ i + "]/td[6]"));
							jse8.executeScript("arguments[0].click();", element8);
//							Thread.sleep(4000);
							isRecordSelected2 = true;
							break;
						}
					}
					if (isRecordSelected2) {
						break;
					}
				} else {
					String DevNumberSequence = driver.findElement(By.xpath("//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr/td[6]")).getText();
					String Orientation = driver.findElement(By.xpath("//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr/td[7]")).getText();
					String StailityCondition = driver.findElement(By.xpath("//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr/td[8]")).getText();
					if (name2.equalsIgnoreCase(DevNumberSequence) && nameori.equalsIgnoreCase(Orientation) && namestcon.equalsIgnoreCase(StailityCondition)) {
//						driver.findElement(
//								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr/td[3]"))
//								.click();
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver.findElement(By.xpath(
								"//*[@id=\"multiSelectStorageConditionsTableContainer\"]/div/table/tbody/tr/td[6]"));
						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected2 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected2) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#multiSelectStorageConditionsTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("multiSelectStorageConditionsTableContainer"));// Document Tree
																									// approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected2;
	}
	private boolean selectRecordForMultiSample(int count3, boolean isRecordSelected3, String namebtch, String namestrgcon, String nameori, String namestcon) throws Exception {
		// TODO Auto-generated method stub
		WebElement table = driver.findElement(By.id("batchAndSCFieldsDetailsJtableInSampleDialog"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = perPageNoOfRecordsPresent;
		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver
//					.findElement(
//							By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/div[4]/div[2]/span"))
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
		if (perPageNoOfRecordsPresent > 0 && count3 == 0) {
//			System.out.println(insid);
			if ((totalNoOfRecords > 1) && ((namestrgcon == null) || ("".equalsIgnoreCase(namestrgcon)))) {
//				System.out.println("hi this is ravi");
				namestrgcon = driver
						.findElement(By.xpath(
								"//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr[1]/td[6]"))
						.getText();// documentType
			} else if ((namestrgcon == null) || ("".equalsIgnoreCase(namestrgcon))) {
				namestrgcon = driver
						.findElement(By.xpath(
								"//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr/td[6]"))
						.getText();// document
									// type
			}
			++count3;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String BatchNo = driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();
						String StorageCondition = driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr[ " + i + " ]/td[8]")).getText();// documentTypeName
						String Orientation = driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr[ " + i + " ]/td[11]")).getText();
						String StailityCondition = driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr[ " + i + " ]/td[13]")).getText();
						if (namebtch.equalsIgnoreCase(BatchNo) && namestrgcon.equalsIgnoreCase(StorageCondition) && nameori.equalsIgnoreCase(Orientation) && namestcon.equalsIgnoreCase(StailityCondition) ) {
//							driver.findElement(
//									By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr[ " + i
//											+ " ]/td[3]"))
//									.click();
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr[" + i + "]/td[3]"));
							jse8.executeScript("arguments[0].click();", element8);
//							Thread.sleep(4000);
							isRecordSelected3 = true;
							break;
						}
					}
					if (isRecordSelected3) {
						break;
					}
				} else {
					String BatchNo = driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr/td[3]")).getText();
					String StorageCondition = driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr/td[8]")).getText();// documentTypeName
					String Orientation = driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr/td[11]")).getText();
					String StailityCondition = driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr/td[13]")).getText();
					if (namebtch.equalsIgnoreCase(BatchNo) && namestrgcon.equalsIgnoreCase(StorageCondition) && nameori.equalsIgnoreCase(Orientation) && namestcon.equalsIgnoreCase(StailityCondition)) {
//						driver.findElement(
//								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr/td[3]"))
//								.click();
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver.findElement(By.xpath("//*[@id=\"batchAndSCFieldsDetailsJtableInSampleDialog\"]/div/table/tbody/tr/td[3]"));
						jse8.executeScript("arguments[0].click();", element8);
						isRecordSelected3 = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected3) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#batchAndSCFieldsDetailsJtableInSampleDialog > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Document approve list
					Thread.sleep(4000);
					table = driver.findElement(By.id("batchAndSCFieldsDetailsJtableInSampleDialog"));// Document Tree
																									// approve
					// table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected3;
	}
	private boolean selectRecordForProduct(int count1, boolean isRecordSelected1, String name1) throws Exception {
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
			if ((totalNoOfRecords > 1) && ((name1 == null) || ("".equalsIgnoreCase(name1)))) {
//				System.out.println("hi this is ravi");
				name1 = driver.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[1]/td[2]"))
						.getText();// documentType
			} else if ((name1 == null) || ("".equalsIgnoreCase(name1))) {
				name1 = driver.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"))
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
										"//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[2]"))
								.getText();// documentTypeName
						if (name1.equalsIgnoreCase(DevNumberSequence)) {
//							driver.findElement(
//									By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr[ " + i
//											+ " ]/td[3]"))
//									.click();
							JavascriptExecutor jse8 = (JavascriptExecutor) driver;
							WebElement element8 = driver.findElement(By
									.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr[" + i + "]/td[2]"));
							jse8.executeScript("arguments[0].click();", element8);
//							Thread.sleep(4000);
							isRecordSelected1 = true;
							break;
						}
					}
					if (isRecordSelected1) {
						break;
					}
				} else {
					String DevNumberSequence = driver
							.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"))
							.getText();
					if (name1.equalsIgnoreCase(DevNumberSequence)) {
//						driver.findElement(
//								By.xpath("//*[@id=\"tableInInstCategorySelectionWindow\"]/div/table/tbody/tr/td[3]"))
//								.click();
						JavascriptExecutor jse8 = (JavascriptExecutor) driver;
						WebElement element8 = driver
								.findElement(By.xpath("//*[@id=\"productsTableContainer\"]/div/table/tbody/tr/td[2]"));
						jse8.executeScript("arguments[0].click();", element8);
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
					table = driver.findElement(By.id("productsTableContainer"));// Document Tree approve
																				// table
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
