package com.pss.admin.AdminUserCreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MultiOrganizationNodeCreationAndApproveTest{


	static File resultFile = new File("LIMS User Details For Automation.xlsx");
	
    public static DataFormatter formatter = new DataFormatter();

    @Test(dataProvider = "readExcelFile")
    public void toMultiOrgCreation(String Node_Tree, String Node_Name,String Node_Code, String Node_Description ) throws Throwable {
        Thread.sleep(1000);


		Thread.sleep(5000);
        PropertiesConfiguration properties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\adminproperties.properties");
		properties.setProperty("Node_Tree", Node_Tree);
		properties.setProperty("Node_Name", Node_Name);
		properties.setProperty("Node_Code", Node_Code);
		properties.setProperty("Node_Description", Node_Description);

		properties.save();
		
        Thread.sleep(5000);
        AdminLoginDetails login = new AdminLoginDetails();
        login.setUp();
        OrganizationNodeCreation node = new OrganizationNodeCreation();
        node.toOrganizationNodeCreation(); 
        OrganizationNodeApproval nodeApprove = new OrganizationNodeApproval();
        nodeApprove.toOrganizationNodeApproval();
        AdminLoginDetails.tearDown();
        
             
            }

   
	
	@DataProvider
    public static Object[][] readExcelFile() throws InvalidFormatException, IOException {
        FileInputStream fis = new FileInputStream(resultFile);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sh = wb.getSheet("OrgNode");

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
