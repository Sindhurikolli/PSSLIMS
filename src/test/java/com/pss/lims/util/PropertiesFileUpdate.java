package com.pss.lims.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PropertiesFileUpdate {

	@Test
	@Parameters({ "First_Name", "lastName_InCreateUser", "empCode_InCreateUser", "userName_InCreateUser", "Location",
			"FirstTimeUserID" })
	public void AdminParameterTest(String First_Name, String lastName_InCreateUser, String empCode_InCreateUser,
			String userName_InCreateUser, String Location, String FirstTimeUserID) throws Exception {

		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src//test//java//CALPMUIProperties//adminproperties.properties");
		properties.setProperty("First_Name", First_Name);
		properties.setProperty("lastName_InCreateUser", lastName_InCreateUser);
		properties.setProperty("empCode_InCreateUser", empCode_InCreateUser);
		properties.setProperty("userName_InCreateUser", userName_InCreateUser);
		properties.setProperty("Location", Location);
		properties.setProperty("FirstTimeUserID", FirstTimeUserID);
		properties.save();

	}

	@Test
	@Parameters({ "CreateRole", "Description", "LastName" })
	public void LMCreateRoles(String CreateRole, String Description, String LastName)
			throws InterruptedException, ConfigurationException {

		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src//test//java//LIMSUIProperties//LabelManagement.properties");
		properties.setProperty("CreateRole", CreateRole);
		properties.setProperty("Description", Description);
		properties.setProperty("LastName", LastName);
		properties.save();

	}

	@Test
	@Parameters({ "LastName", "Location_Name", "CreateRole" })
	public void LMAssignRoles(String LastName, String Location_Name, String CreateRole) throws Exception {

		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src//test//java//LIMSUIProperties//LabelManagement.properties");
		properties.setProperty("LastName", LastName);
		properties.setProperty("Location_Name", Location_Name);
		properties.setProperty("CreateRole", CreateRole);
		properties.save();

	}

	@Test
	@Parameters({ "Role_Name", "Role_Description", "LastName" })
	public void StabilityCreateRoles(String Role_Name, String Role_Description, String LastName) throws Exception {

		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src//test//java//LIMSUIProperties//StabilityManagement.properties");
		properties.setProperty("Role_Name", Role_Name);
		properties.setProperty("Role_Description", Role_Description);
		properties.setProperty("LastName", LastName);
		properties.save();

	}

	@Test
	@Parameters({ "Role_Name", "Role_Description", "LastName" })
	public void IMSCreateRoles(String Role_Name, String Role_Description, String LastName) throws Exception {

		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src//test//java//LIMSUIProperties//InstrumentManagement.properties");
		properties.setProperty("Role_Name", Role_Name);
		properties.setProperty("Role_Description", Role_Description);
		properties.setProperty("LastName", LastName);
		properties.save();

	}

	@Test
	@Parameters({ "Role_Name", "Role_Description", "LastName" })
	public void CMCreateRoles(String Role_Name, String Role_Description, String LastName) throws Exception {

		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src//test//java//LIMSUIProperties//ChemicalManagement.properties");
		properties.setProperty("Role_Name", Role_Name);
		properties.setProperty("Role_Description", Role_Description);
		properties.setProperty("LastName", LastName);
		properties.save();

	}

	@Test
	@Parameters({ "Role_Name", "Role_Description", "LastName" })
	public void SolutionCreateRoles(String Role_Name, String Role_Description, String LastName) throws Exception {

		PropertiesConfiguration properties = new PropertiesConfiguration(
				"src//test//java//LIMSUIProperties//SolutionManagement.properties");
		properties.setProperty("Role_Name", Role_Name);
		properties.setProperty("Role_Description", Role_Description);
		properties.setProperty("LastName", LastName);
		properties.save();
		
	}
}