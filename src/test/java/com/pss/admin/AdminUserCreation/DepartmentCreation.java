package com.pss.admin.AdminUserCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DepartmentCreation extends AdminLoginDetails {

    @Test
    public void toDepartmentCreation() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME1"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PASSWORD"));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#loginform > div.row > button:nth-child(1)")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[href='departmentForm.do']")).click();
        Thread.sleep(2000);
        toCreateOrganizationNodes();
        
            }

    private void toCreateOrganizationNodes() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("depNameInDepartmet")).sendKeys(properties.getProperty("Department_Name"));
        Thread.sleep(2000);
        driver.findElement(By.id("selPlantBtnInDept")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText(properties.getProperty("Plant"))).click();
        Thread.sleep(1000);
        driver.findElement(By.id("selBtnInPlantWin")).click();
        Thread.sleep(4000);
      driver.findElement(By.id("descrNameInDepartmet")).sendKeys(properties.getProperty("Department_Description"));
      Thread.sleep(1000);
       driver.findElement(By.id("submitEditUser")).click();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
        Thread.sleep(1000);
        driver.findElement(By.id("subBtnInValidateESign")).click();
          Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("body > div.container > header > nav > ul > li.dropdown > a > span")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("body > div.container > header > nav > ul > li.dropdown > ul > li:nth-child(2) > a")).click();

    }

}
