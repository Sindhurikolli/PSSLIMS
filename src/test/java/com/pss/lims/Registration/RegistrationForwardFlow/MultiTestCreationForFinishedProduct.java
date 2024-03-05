package com.pss.lims.Registration.RegistrationForwardFlow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pss.lims.Registration.RegistrationForwardFlow.QuantativeTestReg;
import com.pss.lims.login.RegistrationLoginDetails;

public class MultiTestCreationForFinishedProduct {
	static File resultFile = new File("LIMS User Details for Automation.xlsx");

	public static DataFormatter formatter = new DataFormatter();

	@Test(dataProvider = "readExcelFile")
	public void toMultiTestCreation(String TestName, String TestType, String NoOfStages) throws Throwable {
		Thread.sleep(1000);
		Thread.sleep(5000);
		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src/test/java/LIMSUIProperties/Registration.properties");

		Thread.sleep(5000);
		RegistrationLoginDetails login = new RegistrationLoginDetails();

		if (TestType.equalsIgnoreCase("Qualitative")) {
			System.out.println("Qualitative Test");
			properties.setProperty("Test_Name", TestName);
			properties.setProperty("TestTypeforQualitative", TestType);
			properties.setProperty("NoOfStages", NoOfStages);
			properties.save();
			login.setUp();

			QualitativeTestReg createQualitativeTest = new QualitativeTestReg();
			createQualitativeTest.createQualitativeTest();

			TestApproval qualitativeTestApproval = new TestApproval();
			if (Integer.parseInt(NoOfStages) != 0 && Integer.parseInt(NoOfStages) > 1) {
				for (int i = 0; i < Integer.parseInt(NoOfStages); i++) {
					qualitativeTestApproval.approveTest();
				}
			} else {
				qualitativeTestApproval.approveTest();
			}

		} else if (TestType.equalsIgnoreCase("Quantitative")) {
			System.out.println("Quantitative Test");
			properties.setProperty("Test_Name", TestName);
			properties.setProperty("TestTypeforQuantitative", TestType);
			properties.setProperty("NoOfStages", NoOfStages);
			properties.save();
			login.setUp();
			QuantativeTestReg createQuantativeTestReg = new QuantativeTestReg();
			createQuantativeTestReg.createTest();

			TestApproval quantitativeTestApproval = new TestApproval();
			if (Integer.parseInt(NoOfStages) != 0 && Integer.parseInt(NoOfStages) > 1) {
				for (int i = 0; i < Integer.parseInt(NoOfStages); i++) {
					quantitativeTestApproval.approveTest();
				}
			} else {
				quantitativeTestApproval.approveTest();
			}

		}

		else {
			System.out.println("Please Enter Test type Qualitative/Quantitative in Excel Sheet TestDetails");
		}

		RegistrationLoginDetails.tearDown();

	}

	@DataProvider
	public static Object[][] readExcelFile() throws InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream(resultFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("TestDetails");
		System.out.println(sh.getPhysicalNumberOfRows());
		System.out.println(sh.getRow(0).getPhysicalNumberOfCells());
		int RowNum = sh.getPhysicalNumberOfRows();
		int ColNum = sh.getRow(0).getPhysicalNumberOfCells();

		String[][] xlData = new String[RowNum - 1][ColNum];

		for (int i = 0; i < RowNum - 1; i++) {
			XSSFRow row = sh.getRow(i + 1);
			for (int j = 0; j < ColNum; j++) {
				if (row == null)
					xlData[i][j] = "";
				else {
					XSSFCell cell = row.getCell(j);
					if (cell == null)
						xlData[i][j] = "";
					else {
						String value = formatter.formatCellValue(cell);
						xlData[i][j] = value.trim();
					}
				}
			}
		}
		return xlData;
	}

}
