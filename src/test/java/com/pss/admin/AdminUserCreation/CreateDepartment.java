package com.pss.admin.AdminUserCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;

public class CreateDepartment extends SMLoginDetails {

	@Test
	public void createDepartment() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys("admin");
		Thread.sleep(3000);
		driver.findElement(By.name("loginPassword")).sendKeys("demo1");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[2]/button[1]")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Department")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("depNameInDepartmet")).sendKeys(properties.getProperty("dept"));
		Thread.sleep(3000);
		methodToCreateDepartment();
	}

	private void methodToCreateDepartment() throws Exception {

		driver.findElement(By.id("selPlantBtnInDept")).click();
		Thread.sleep(5000);
		driver.findElement(By.className("node_name")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("selBtnInPlantWin")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("descrNameInDepartmet")).sendKeys(properties.getProperty("Comments_150"));
		Thread.sleep(3000);
		driver.findElement(By.id("submitEditUser")).click();
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
