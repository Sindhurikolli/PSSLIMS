package com.pss.lims.PropertiesFileUpdate;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UsersPropertiesFileUpdate {

	@Test
	@Parameters({ "Initiator_Login", "SampleManager_Login", "Reviewer", "Approver_Login", "Analyst", "MasterCreator_Login",
			"Reviewer_In_JobAllotment" , "First_Name", "LastName", "MyJobUserLastName"})
	public void UsersUpdate(String Initiator_Login, String SampleManager_Login, String Reviewer,
			String Approver_Login, String Analyst, String MasterCreator_Login, String Reviewer_In_JobAllotment,
	String First_Name, String LastName, String MyJobUserLastName)
			throws Exception {

		PropertiesConfiguration prop = new PropertiesConfiguration(
				"src//test//java//LIMSUIProperties//SampleManagement.properties");
		prop.setProperty("Initiator_Login", Initiator_Login);
		prop.setProperty("SampleManager_Login", SampleManager_Login);
		prop.setProperty("Reviewer", Reviewer);
		prop.setProperty("Approver_Login", Approver_Login);
		prop.setProperty("Analyst", Analyst);
		prop.setProperty("MasterCreator_Login", MasterCreator_Login);
		prop.setProperty("Reviewer_In_JobAllotment", Reviewer_In_JobAllotment);
		prop.setProperty("First_Name", First_Name);
		prop.setProperty("LastName", LastName);
		prop.setProperty("MyJobUserLastName", MyJobUserLastName);
		prop.save();
		System.out.println("Users.properties updated Successfully!!");

	}

}
