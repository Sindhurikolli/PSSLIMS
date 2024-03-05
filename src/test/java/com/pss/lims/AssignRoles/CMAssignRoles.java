package com.pss.lims.AssignRoles;

import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.pss.lims.LoginDetails.LIMSLogin;

public class CMAssignRoles extends LIMSLogin {

	@Test
	public void CMAssignRoleMethod() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("AdminUser_Login"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(1000);
		Select module = new Select(driver.findElement(By.id("limsModule")));
		Thread.sleep(1000);
		module.selectByVisibleText(properties.getProperty("CM_Module"));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[7]/input")).click();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='roleInChemMngt.do'")));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector("a[href='roleInChemMngt.do'"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
		toCreateUsers();

	}

	private void toCreateUsers() throws Exception {

		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a[href='assignRoleInChemMngt.do'")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("seluserBtnInAssignRolesInCms")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("locTreeInCalPmBdm_1_span")).click();
		Thread.sleep(5000);
//		driver.findElement(By.id("locTreeInCalAssignRoles_2_span")).sendKeys(properties.getProperty("Location_Name"));
		Thread.sleep(5000);
		driver.findElement(By.id("searchFirstName")).sendKeys(properties.getProperty("CM_FirstName"));
		Thread.sleep(1000);
		driver.findElement(By.id("searchLastName")).sendKeys(properties.getProperty("CM_LastName"));
		Thread.sleep(1000);
		driver.findElement(By.id("usersSearchBtnInRepProb")).click();
		Thread.sleep(5000);
		if (driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
			Thread.sleep(3000);
			String RoleName = properties.getProperty("CM_RoleName");
			Select Roletoselect = new Select(driver.findElement(By.id("bootstrap-duallistbox-nonselected-list_")));
			List<WebElement> allOptionstoselect = Roletoselect.getOptions();
			int jSize = allOptionstoselect.size();
			String[] arrrolestoselect = new String[jSize];
			Thread.sleep(3000);
			for (int j = 0; j < jSize; j++) {
				arrrolestoselect[j] = allOptionstoselect.get(j).getText();
			}
			Select Roleselected = new Select(driver.findElement(By.id("bootstrap-duallistbox-selected-list_")));
			List<WebElement> allOptionsselected = Roleselected.getOptions();
			int iSize = allOptionsselected.size();
			String[] arrrolesselected = new String[iSize];
			Thread.sleep(3000);
			for (int i = 0; i < iSize; i++) {
				arrrolesselected[i] = allOptionsselected.get(i).getText();
			}
			Thread.sleep(3000);
			if (ArrayUtils.contains(arrrolestoselect, RoleName)) {
				Roletoselect.selectByVisibleText(properties.getProperty("CM_RoleName"));
				Thread.sleep(2000);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,250)");
				Thread.sleep(1000);
				driver.findElement(By.id("submitBtnInCmsAssignRole")).click();
				Thread.sleep(3000);
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd")));
				driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
				Thread.sleep(1000);
				driver.findElement(By.id("subBtnInValidateESign")).click();
				Thread.sleep(6000);
				driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();

			} else if (ArrayUtils.contains(arrrolesselected, RoleName)) {
				System.out.println("Role is Already Selected");
			} else {
				System.out.println("Role Not Available to Select");
			}
			driver.findElement(By.className("username")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a[href='Logout.do']")).click();

		}

		else {
			System.out.println("User Not Selected");
			Assert.assertTrue(false);
		}
	}

}
