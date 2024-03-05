package com.pss.lims.IM.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pss.lims.IM.Login.LoginDetails;


public class RoleBaseAccess extends LoginDetails {

	@Test
	public void roleBaseAccess() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("CreateRole"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[11]/a/map/area")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("adminTabInInstMngt")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a")).click();
		Thread.sleep(4000);
		methodForRoleBaseAccess();

	}

	private void methodForRoleBaseAccess() throws Exception {

		Select role = new Select(driver.findElement(By.id("rolesInIMRoleBaseAccess")));
		Thread.sleep(3000);
		role.selectByIndex(2);
		Thread.sleep(2000);
		driver.findElement(By.id("selectAllForIMMyActivities")).click();
		Thread.sleep(3000);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.id("selectAllForIMRegistration"));
		jse1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		jse1.executeScript("arguments[0].click();", element1);
		Thread.sleep(3000);
		JavascriptExecutor jse12 = (JavascriptExecutor) driver;
		WebElement element12 = driver.findElement(By.id("submitButtonInIMRoleBaseAccess"));
		jse12.executeScript("arguments[0].scrollIntoView(true);", element12);
		Thread.sleep(2000);
//		jse1.executeScript("arguments[0].click();", element12);
//		Thread.sleep(3000);
		driver.findElement(By.id("selectAllForIMAuditTrails")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("selectAllForIMReports")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("submitButtonInIMRoleBaseAccess")).click();
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 70);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")));
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
		Thread.sleep(4000);
	}
}
