package com.pss.lims.PropertiesFileUpdate;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LIMSPropertiesFileUpdate {

	@Test
	@Parameters({ "Description", "Product_code", "Product_name", "Storage_Condition_Name", "TestName", "MasterChamber",
			"AdhocScheduleName" })
	public void StabiltiyManagementModuleUpdate(String Description, String Product_code, String Product_name,
			String Storage_Condition_Name, String TestName, String MasterChamber, String AdhocScheduleName)
			throws Exception {

		PropertiesConfiguration prop = new PropertiesConfiguration(
				"src//test//java//LIMSUIProperties//StabilityManagement.properties");
		prop.setProperty("Description", Description);
		prop.setProperty("Product_code", Product_code);
		prop.setProperty("Product_name", Product_name);
		prop.setProperty("Storage_Condition_Name", Storage_Condition_Name);
		prop.setProperty("TestName", TestName);
		prop.setProperty("MasterChamber", MasterChamber);
		prop.setProperty("AdhocScheduleName", AdhocScheduleName);
		prop.save();

	}

}
