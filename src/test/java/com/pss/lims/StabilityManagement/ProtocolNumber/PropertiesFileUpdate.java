package com.pss.lims.StabilityManagement.ProtocolNumber;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class PropertiesFileUpdate {

	@Test
	@Parameters("Protocol_Number")
	public void parameterTest(String Protocol_Number) throws Exception {

		try {

			Protocol_Number = System.getProperty("Protocol_Number");
			PropertiesConfiguration properties = new PropertiesConfiguration(
					"src/test/java/LIMSUIProperties/StabilityManagement.properties");
			properties.setProperty("Protocol_Number", Protocol_Number);
			properties.save();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
