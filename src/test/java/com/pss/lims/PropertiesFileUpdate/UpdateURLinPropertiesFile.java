package com.pss.lims.PropertiesFileUpdate;

	 
	import org.apache.commons.configuration.ConfigurationException;
	import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
	 
	public class UpdateURLinPropertiesFile {
		
		
		@Test
		@Parameters({"ADMINURL", "LIMSLoginUrl"})
		public static void main(String ADMINURL, String LIMSLoginUrl ) {
	        try {
            PropertiesConfiguration adminproperties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\adminproperties.properties");
            adminproperties.setProperty("ADMINURL", ADMINURL);
            adminproperties.save();
	        System.out.println("adminproperties.properties updated Successfully!!");
	        
	        PropertiesConfiguration Chemicalproperties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\ChemicalManagement.properties");	        
	        Chemicalproperties.setProperty("LIMSLoginUrl", LIMSLoginUrl);     
	        Chemicalproperties.setProperty("ADMINURL", ADMINURL);
	        Chemicalproperties.save();
	        System.out.println("ChemicalManagement.properties updated Successfully!!");
	        
	        PropertiesConfiguration InstrumentManagementproperties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\InstrumentManagement.properties");	        
	        InstrumentManagementproperties.setProperty("LIMSLoginUrl", LIMSLoginUrl);
	        InstrumentManagementproperties.setProperty("ADMINURL", ADMINURL);
	        InstrumentManagementproperties.save();
	        System.out.println("InstrumentManagement.properties updated Successfully!!");
	        
	        PropertiesConfiguration LabelManagementproperties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\LabelManagement.properties");	        
	        LabelManagementproperties.setProperty("LIMSLoginUrl", LIMSLoginUrl);
	        LabelManagementproperties.setProperty("ADMINURL", ADMINURL);
	        LabelManagementproperties.save();
	        System.out.println("LabelManagement.properties updated Successfully!!");
	        
//	        PropertiesConfiguration devproperties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\Deviation.properties");	        
//	        devproperties.setProperty("URL", LIMSLoginUrl);     
//	        devproperties.save();
//	        System.out.println("Deviation.properties updated Successfully!!");
	        
	        PropertiesConfiguration LIMSproperties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\LIMS.properties");	        
	        LIMSproperties.setProperty("LIMSLoginUrl", LIMSLoginUrl);
	        LIMSproperties.setProperty("ADMINURL", ADMINURL);
	        LIMSproperties.save();
	        System.out.println("LIMS.properties.properties updated Successfully!!");
	        
	        PropertiesConfiguration SampleManagementproperties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\SampleManagement.properties");	        
	        SampleManagementproperties.setProperty("LIMSLoginUrl", LIMSLoginUrl);     
	        SampleManagementproperties.setProperty("ADMINURL", ADMINURL);
	        SampleManagementproperties.save();
	        System.out.println("SampleManagement.properties updated Successfully!!");
	        
	        PropertiesConfiguration SolutionManagementproperties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\SolutionManagement.properties");	        
	        SolutionManagementproperties.setProperty("LIMSLoginUrl", LIMSLoginUrl);
	        SolutionManagementproperties.setProperty("ADMINURL", ADMINURL);
	        SolutionManagementproperties.save();
	        System.out.println("SolutionManagement.properties updated Successfully!!");

	        
	        PropertiesConfiguration StabilityManagementproperties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\StabilityManagement.properties");	        
	        StabilityManagementproperties.setProperty("LIMSLoginUrl", LIMSLoginUrl);
	        StabilityManagementproperties.setProperty("ADMINURL", ADMINURL);
	        StabilityManagementproperties.save();
	        System.out.println("StabilityManagement.properties updated Successfully!!");
	        
	        PropertiesConfiguration RegistrationProperties = new PropertiesConfiguration("src\\test\\java\\LIMSUIProperties\\Registration.properties");	        
	        RegistrationProperties.setProperty("LIMSLoginUrl", LIMSLoginUrl); 
	        RegistrationProperties.setProperty("ADMINURL", ADMINURL);
	        RegistrationProperties.save();
	        System.out.println("Registration.properties updated Successfully!!");
	        
	        
	     
	        
	        } catch (ConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
    }
	 
}

