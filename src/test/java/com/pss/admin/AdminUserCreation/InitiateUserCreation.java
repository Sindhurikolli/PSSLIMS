package com.pss.admin.AdminUserCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class InitiateUserCreation extends AdminLoginDetails {

    @Test
    public void methodtoCreateUsers() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME1"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PASSWORD"));
		driver.findElement(By.cssSelector("#loginform > div.row > button:nth-child(1)")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"createUsersInAdminMyActivities\"]")).click();
        Thread.sleep(2000);
        toCreateUsers();
        
            }

    private void toCreateUsers() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("firstNameInCreateUser")).sendKeys(properties.getProperty("First_Name"));
        Thread.sleep(1000);
        driver.findElement(By.id("lastNameInCreateUser")).sendKeys(properties.getProperty("lastName_InCreateUser"));
        Thread.sleep(1000);
        driver.findElement(By.id("emailInCreateUser")).sendKeys(properties.getProperty("email_InCreateUser"));
        Thread.sleep(1000);
        driver.findElement(By.id("selectRepToInCreateUser")).click();
        Thread.sleep(6000);
        driver.findElement(By.id("locTreeInuserSelectionWindow_1_span")).click();
        Thread.sleep(3000);
        boolean isRecordSelectedForUser = false;
        String UserFirstName = properties.getProperty("User_Name");
//      String regLastName  = "reviewer3";
      String UserFullName = UserFirstName;
      int count5=0;
      isRecordSelectedForUser=selectingTheUser(UserFullName,isRecordSelectedForUser,count5);
      Thread.sleep(2000);
//      driver.findElement(By.xpath("//*[@id=\"regulatoryTeamReAddDetailsWindowTableInDev\"]/div/table/tbody/tr[2]/td[2]")).click();
//      Thread.sleep(3000);
//      driver.findElement(By.id("addBtnInCftDeptAdd")).click();
//      Thread.sleep(3000);
        driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("empCodeInCreateUser")).sendKeys(properties.getProperty("empCode_InCreateUser"));
        Thread.sleep(2000);
        Select Department = new Select(driver.findElement(By.id("departmentInCreateUser")));
        Department.selectByVisibleText(properties.getProperty("department_InCreateUser"));
        Thread.sleep(2000);
        Select User_Type = new Select(driver.findElement(By.id("userTypeInCreateUser")));
        User_Type.selectByIndex(1);
        Thread.sleep(2000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");

        driver.findElement(By.id("locSelectInCreateUser")).click();
        Thread.sleep(2000);
//        Actions act = new Actions(driver);
//       WebElement ele = driver.findElement(By.linkText(properties.getProperty("Plant")));
//       act.doubleClick(ele).build().perform();
        driver.findElement(By.id("tree-container_2_switch")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText(properties.getProperty("Location"))).click();
//        driver.findElement(By.id("tree-container_4_span")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("selBtnInLocSelForCreateUser")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("qualificationInCreateUser")).sendKeys(properties.getProperty("qualification_InCreateUser"));
        Thread.sleep(2000);
        Select designation_InCreateUser = new Select(driver.findElement(By.id("designationInCreateUser")));
        designation_InCreateUser.selectByVisibleText(properties.getProperty("designation_InCreateUser"));
        Thread.sleep(2000);
        driver.findElement(By.id("dojInCreateUser")).sendKeys(properties.getProperty("doj_InCreateUser"));
        Thread.sleep(2000);
        driver.findElement(By.id("userNameInCreateUser")).sendKeys(properties.getProperty("userName_InCreateUser"));
        Thread.sleep(2000);
        driver.findElement(By.id("passwordInCreateUser")).sendKeys(properties.getProperty("password_InCreateUser"));
        Thread.sleep(2000);
        driver.findElement(By.id("eSignPwdInCreateUser")).sendKeys(properties.getProperty("eSignPwd_InCreateUser"));
        Thread.sleep(2000);
       driver.findElement(By.id("submitCreateUser")).click();
        Thread.sleep(2000);
         WebDriverWait wait = new WebDriverWait(driver, 60);
	   wait.until(ExpectedConditions.presenceOfElementLocated(By.id("eSignPwdInWnd"))).sendKeys(properties.getProperty("Esign_Password"));
            Thread.sleep(1000);
       driver.findElement(By.id("subBtnInValidateESign")).click();
       Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='modal-window']/div/div/div[3]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("username")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[onclick='logOutInPssAdmin()']")).click();
    }

	private boolean selectingTheUser(String UserFullName, boolean isRecordSelectedForUser, int count5) throws InterruptedException {
		 WebElement table = driver.findElement(By.id("usersTableContainer"));
	        WebElement tableBody = table.findElement(By.tagName("tbody"));
	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	        int totalNoOfRecords = 0;
	        int noOfRecordsChecked = 0;
	        if (perPageNoOfRecordsPresent > 0) {
	            String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
	            String[] parts = a.split(" of ");
	            try {
	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        //*[@id="cftTeamTableWindowContainer"]/div/table/tbody/tr[2]/td[2]
	        if (perPageNoOfRecordsPresent > 0 && count5 == 0) {
	            if (((UserFullName == null) || ("".equalsIgnoreCase(UserFullName)))) {
	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[1]")).getText();//documentType
	            } else if ((UserFullName == null) || ("".equalsIgnoreCase(UserFullName))) {
	            	UserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();//documentType

	            }
	            ++count5;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
//	            while (noOfRecordsChecked < totalNoOfRecords) {
//	            	System.out.println("While Loop........");
	                if (perPageNoOfRecordsPresent > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String AppUserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).getText();//documentTypeName
	                        System.out.println("UserFullName: "+AppUserFullName);
	                        if (UserFullName.equalsIgnoreCase(UserFullName)) {
	                            driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]")).click();
	                            isRecordSelectedForUser = true;
	                            break;
	                        }
	                    }
	                   
	                } else {
	                    String AppUserFullName = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[1]")).getText();
	                    if (UserFullName.equalsIgnoreCase(UserFullName)) {
	                        driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[1]")).click();
	                        isRecordSelectedForUser = true;
	                        
	                    }
	                }
	                noOfRecordsChecked += perPageNoOfRecordsPresent;
	                if ((!isRecordSelectedForUser) && (noOfRecordsChecked < totalNoOfRecords)) {
	                    driver.findElement(By.cssSelector("#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                    Thread.sleep(3000);
	                    table = driver.findElement(By.id("usersTableContainer"));//Document Tree approve table
	                    tableBody = table.findElement(By.tagName("tbody"));
	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	                }
	            }
//	        }
	       
	    
	        return isRecordSelectedForUser;
	}
}
