package com.pss.lims.PropertiesFileUpdate;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SampleManagementPropertyFileUpdate {

	@Test
	@Parameters({ "AR_Number" })
	public void StabiltiyManagementModuleUpdate(String AR_Number)
			throws Exception {

		PropertiesConfiguration prop = new PropertiesConfiguration(
				"src//test//java//LIMSUIProperties//SampleManagement.properties");
		String arnumber=AR_Number;
		prop.setProperty("AR_Number",arnumber);
		prop.save();

	}

}
