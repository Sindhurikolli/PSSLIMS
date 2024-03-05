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


public class OrganizationNodeCreation extends AdminLoginDetails {

    @Test
    public void toOrganizationNodeCreation() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME1"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PASSWORD"));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#loginform > div.row > button:nth-child(1)")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[href='createOrganization.do']")).click();
        Thread.sleep(2000);
        toCreateOrganizationNodes();
        
            }

    private void toCreateOrganizationNodes() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.linkText(properties.getProperty("Node_Tree"))).click();
        Thread.sleep(3000);
        driver.findElement(By.id("orgNodeName")).sendKeys(properties.getProperty("Node_Name"));
        Thread.sleep(1000);
        if(driver.findElement(By.id("orgNodeShortCode")).isDisplayed()) {
        driver.findElement(By.id("orgNodeShortCode")).sendKeys(properties.getProperty("Node_Code"));
        Thread.sleep(1000);
        }
        driver.findElement(By.id("orgNodeDescription")).sendKeys(properties.getProperty("Node_Description"));
        Thread.sleep(1000);
        driver.findElement(By.id("orgApprovalUserSelectionBtn")).click();
        Thread.sleep(6000);
        driver.findElement(By.id("locTreeInMenuBaseuserSelectionWindow_1_span")).click();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#usersTableContainerInUserMenuBaseSel > div > div.jtable-busy-message[style='display: none;']")));
        boolean isRecordSelectedForAdmin = false;
        String AdminECode = properties.getProperty("Admin_ECode");
      int count5=0;
      isRecordSelectedForAdmin=selectingTheApprover(AdminECode,isRecordSelectedForAdmin,count5);
      Thread.sleep(2000);
if(isRecordSelectedForAdmin)
{
        driver.findElement(By.id("usersSelBtnInMenuBasedUser")).click();
        Thread.sleep(3000);
       driver.findElement(By.id("createBtnInCreateSubNode")).click();
        Thread.sleep(2000);
        
	   wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
            Thread.sleep(1000);
       driver.findElement(By.id("subBtnInValidateESign")).click();
       Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("body > div.container > header > nav > ul > li.dropdown > a > span")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("body > div.container > header > nav > ul > li.dropdown > ul > li:nth-child(3) > a")).click();
    }
else
{
	System.out.println("Approver Not Selected");
}
    }

	private boolean selectingTheApprover(String AdminECode, boolean isRecordSelectedForAdmin, int count5) throws InterruptedException {
		 WebElement table = driver.findElement(By.id("usersTableContainerInUserMenuBaseSel"));
	        WebElement tableBody = table.findElement(By.tagName("tbody"));
	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	        int totalNoOfRecords = 0;
	        int noOfRecordsChecked = 0;
	        if (perPageNoOfRecordsPresent > 0) {
	            String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainerInUserMenuBaseSel\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
	            String[] parts = a.split(" of ");
	            try {
	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	     	        if (perPageNoOfRecordsPresent > 0 && count5 == 0) {
	            if (((AdminECode == null) || ("".equalsIgnoreCase(AdminECode)))) {
	            	AdminECode = driver.findElement(By.xpath("//*[@id=\"usersTableContainerInUserMenuBaseSel\"]/div/table/tbody/tr[1]/td[4]")).getText();//documentType
	            } else if ((AdminECode == null) || ("".equalsIgnoreCase(AdminECode))) {
	            	AdminECode = driver.findElement(By.xpath("//*[@id=\"usersTableContainerInUserMenuBaseSel\"]/div/table/tbody/tr/td[4]")).getText();//documentType

	            }
	            ++count5;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
//	            	System.out.println("While Loop........");
	                if (perPageNoOfRecordsPresent > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String AppAdminECode = driver.findElement(By.xpath("//*[@id=\"usersTableContainerInUserMenuBaseSel\"]/div/table/tbody/tr[ " + i + " ]/td[4]")).getText();//documentTypeName
//	                        System.out.println("AdminECode: "+AdminECode);
	                        if (AdminECode.equalsIgnoreCase(AppAdminECode)) {
	                            driver.findElement(By.xpath("//*[@id=\"usersTableContainerInUserMenuBaseSel\"]/div/table/tbody/tr[ " + i + " ]/td[4]")).click();
	                            isRecordSelectedForAdmin = true;
	                            break;
	                        }
	                    }
	                   
	                } else {
	                    String AppAdminECode = driver.findElement(By.xpath("//*[@id=\"usersTableContainerInUserMenuBaseSel\"]/div/table/tbody/tr[1]/td[4]")).getText();
	                    if (AdminECode.equalsIgnoreCase(AppAdminECode)) {
	                        driver.findElement(By.xpath("//*[@id=\"usersTableContainerInUserMenuBaseSel\"]/div/table/tbody/tr[1]/td[4]")).click();
	                        isRecordSelectedForAdmin = true;
	                        
	                    }
	                }
	                noOfRecordsChecked += perPageNoOfRecordsPresent;
	                if ((!isRecordSelectedForAdmin) && (noOfRecordsChecked < totalNoOfRecords)) {
	                    driver.findElement(By.cssSelector("#usersTableContainerInUserMenuBaseSel > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                    Thread.sleep(3000);
	                    table = driver.findElement(By.id("usersTableContainerInUserMenuBaseSel"));//Document Tree approve table
	                    tableBody = table.findElement(By.tagName("tbody"));
	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	                }
	            }
	        }
	       
	    
	        return isRecordSelectedForAdmin;
	}
}
