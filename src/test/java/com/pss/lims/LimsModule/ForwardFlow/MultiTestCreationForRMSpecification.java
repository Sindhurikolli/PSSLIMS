package com.pss.lims.LimsModule.ForwardFlow;

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


public class MultiTestCreationForRMSpecification {
static File resultFile = new File("LIMS User Details for Automation.xlsx");
	
    public static DataFormatter formatter = new DataFormatter();

    @Test(dataProvider = "readExcelFile")
    public void toMultiTestCreation(String TestName, String TestType) throws Throwable {
        Thread.sleep(1000);
		Thread.sleep(5000);
        PropertiesConfiguration properties = new PropertiesConfiguration("src/test/java/LIMSUIProperties/Registration.properties");
		
        Thread.sleep(5000);
        RegistrationLoginDetails login = new RegistrationLoginDetails();
        login.setUp();
        if(TestType.equalsIgnoreCase("Qualitative")) {
        	System.out.println("Qualitative Test");
//        properties.setProperty("Test_Name_Qualitative", TestName);
        properties.setProperty("Test_Name", TestName);
    	properties.setProperty("TestTypeforQualitative", TestType);
    	properties.save();
    	QualitativeTestRMSpecification createQualitativeTest = new QualitativeTestRMSpecification();
        createQualitativeTest.createQualitativeTest();
		
		  TestApproval qualitativeTestApproval= new TestApproval();
		  qualitativeTestApproval.approveTest();
		 
        }
        else if(TestType.equalsIgnoreCase("Quantitative")) {
        	System.out.println("Quantitative Test");
//        	properties.setProperty("Test_Name_Quantitative", TestName);
            properties.setProperty("Test_Name", TestName);
        	properties.setProperty("TestTypeforQuantitative", TestType);
        	properties.save();
        	QuantativeTestRegRMSpecification createQuantativeTestReg=new QuantativeTestRegRMSpecification();
        createQuantativeTestReg.createTest();
		
		  TestApproval quantitativeTestApproval= new TestApproval();
		  quantitativeTestApproval.approveTest();
		 
        }
        else{
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

        String[][] xlData = new String[RowNum-1][ColNum];

        for (int i = 0; i < RowNum - 1; i++) 
        {
            XSSFRow row = sh.getRow(i + 1);
            for (int j = 0; j < ColNum; j++) 
            {
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


