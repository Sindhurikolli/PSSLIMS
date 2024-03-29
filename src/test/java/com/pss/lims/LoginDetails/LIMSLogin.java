package com.pss.lims.LoginDetails;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.pss.lims.util.DynamicBrowser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author likhitha
 */
public class LIMSLogin {

	public static String finalDeviationNumber = "";

	public static Properties properties;
	public static WebDriver driver;
	private static RemoteWebDriver driver1;

	private static String browserName = System.getProperty("browser");
	private static String url = System.getProperty("url");
	private static String loginUserName = System.getProperty("username");
	private static String loginUserPassword = System.getProperty("userpassword");
	private static String driverPath = System.getProperty("driverPath");
	private String propertiesFilePath = System.getProperty("propertiesPathFile");

	protected byte[] input;
	protected Document document;
	protected String output;
	protected FileOutputStream fos;
	protected Integer sno = 1;
	protected PdfWriter writer;
	protected Image im;

	@BeforeClass

	public void setUp() throws Exception {

		System.out.println("Before Login");
		System.out.println("browser");
		String propertyFilePathName = "";
		if (propertiesFilePath != null) {
			propertyFilePathName = propertiesFilePath;
		} else {
			propertyFilePathName = "src/test/java/LIMSUIProperties/LIMS.properties";
		}
		final String propertyFilePath = propertyFilePathName;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("LIMSUIProperties.properties not found at " + propertyFilePath);
		}

		Thread.sleep(2000);
		System.out.println("browerName : " + browserName);
		if ((url != null) && (browserName != null) && (loginUserName != null) && (loginUserPassword != null)
				&& (driverPath != null)) {
			System.out.println("With Command Prompt");
			if (browserName.equalsIgnoreCase("IEDriverServer")) {
				System.setProperty("webdriver.ie.driver", driverPath);
				driver = new InternetExplorerDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {

				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			} else if (browserName.equalsIgnoreCase("chromedriver")) {
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}
			driver.get(url);
			driver.findElement(By.name("loginUserName")).sendKeys(loginUserName);
			Thread.sleep(1000);
			driver.findElement(By.name("loginPassword")).sendKeys(loginUserPassword);
			Thread.sleep(1000);
		} else if (DynamicBrowser.browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			WebDriverWait wait = new WebDriverWait(driver, 120);
			driver.get(properties.getProperty("LIMSLoginUrl"));
			driver.manage().window().maximize();
		} else if (DynamicBrowser.browserName.equalsIgnoreCase("Remote")) {
			DesiredCapabilities dr = DesiredCapabilities.chrome();

			dr.setBrowserName("chrome");
//			 dr.setPlatform(Platform.WIN10); 
			dr.setPlatform(Platform.ANY);

//			 dr.setVersion("90.0.4430.212");
			RemoteWebDriver driver1 = new RemoteWebDriver(new URL("http://10.0.0.17:4444/wd/hub"), dr);
			driver = driver1;

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 120);
			driver.get(properties.getProperty("LIMSLoginUrl"));
			driver.manage().window().maximize();
		} else {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			WebDriverWait wait = new WebDriverWait(driver, 120);
			driver.get(properties.getProperty("LIMSLoginUrl"));
			driver.manage().window().maximize();
		}

		System.out.println("url : " + url);
		System.out.println("loginUserName : " + loginUserName);
		System.out.println("loginUserPassword : " + loginUserPassword);
		System.out.println("propertiesFilePath: " + propertiesFilePath);

		System.out.println("LoginDetails --- END");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println(" After Logout");
		Thread.sleep(2000);

		// Thread.sleep(2000);
		driver.quit();

	}
}
