package com.pss.lims.AssignRoles;

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

import com.pss.lims.LoginDetails.LIMSLogin;


public class MultiUsersAssignRoleLMTest {

	static File resultFile = new File("LIMS User Details For Automation.xlsx");
	public static DataFormatter formatter = new DataFormatter();

	@Test(dataProvider = "readExcelFile")
	public void toMultiUserCreation(String FirstName, String LastName, String RoleName) throws Throwable {

		Thread.sleep(5000);
		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src\\test\\java\\LIMSUIProperties\\LIMS.properties");
		properties.setProperty("FirstName", FirstName);
		properties.setProperty("LastName", LastName);
		properties.setProperty("RoleName", RoleName);
		properties.save();

		Thread.sleep(5000);
		LIMSLogin login = new LIMSLogin();
		login.setUp();
		System.out.println("Role Assigned for - " + LastName);
		LMAssignRoles role = new LMAssignRoles();
		role.LMAssignRoleMethod();
		LIMSLogin.tearDown();

	}

	@DataProvider
	public static Object[][] readExcelFile() throws InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream(resultFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("LMRole");

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
