package com.pss.admin.AdminUserCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;

public class CreateOrganization extends SMLoginDetails {

	@Test
	public void createOrg() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys("admin");
		Thread.sleep(3000);
		driver.findElement(By.name("loginPassword")).sendKeys("demo1");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[2]/button[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Organisation")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("tree-container_1_span")).click();
		Thread.sleep(3000);
		methodToCreateOrg();
	}

	private void methodToCreateOrg() throws Exception {

		driver.findElement(By.id("orgNodeName")).sendKeys(properties.getProperty("org"));
		Thread.sleep(3000);
		driver.findElement(By.id("orgNodeDescription")).sendKeys(properties.getProperty("Keyword_100"));
		Thread.sleep(3000);
		driver.findElement(By.id("createBtnInCreateSubNode")).click();
		Thread.sleep(3000);
//		driver.findElement(By.id("eSignPwdInWnd")).sendKeys(properties.getProperty("Esign_Password"));
//		Thread.sleep(3000);
//		driver.findElement(By.id("subBtnInValidateESign")).click();
//		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 70);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"modal-window\"]/div/div/div[3]/a")).click();
		Thread.sleep(3000);
		
	}

}
