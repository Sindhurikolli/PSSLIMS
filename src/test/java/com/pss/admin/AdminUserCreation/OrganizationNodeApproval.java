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


public class OrganizationNodeApproval extends AdminLoginDetails {

    @Test
    public void toOrganizationNodeApproval() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("ADMIN_USERNAME1"));
		Thread.sleep(1000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("PASSWORD"));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#loginform > div.row > button:nth-child(1)")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[href='organizationApproval.do']")).click();
        Thread.sleep(2000);
        toApproveOrganizationNodes();
        
            }

    private void toApproveOrganizationNodes() throws InterruptedException {
    	 Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#approvalOrganizationTable > div > div.jtable-busy-message[style='display: none;']")));
        boolean isRecordSelectedForNode = false;
        String NodeName = properties.getProperty("Node_Name");
      int count5=0;
      isRecordSelectedForNode=selectingTheNode(NodeName,isRecordSelectedForNode,count5);
      Thread.sleep(2000);
if(isRecordSelectedForNode)
{
      
        Thread.sleep(3000);
       driver.findElement(By.id("approveBtnInOrgApproval")).click();
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

	private boolean selectingTheNode(String NodeName, boolean isRecordSelectedForNode, int count5) throws InterruptedException {
		 WebElement table = driver.findElement(By.id("approvalOrganizationTable"));
	        WebElement tableBody = table.findElement(By.tagName("tbody"));
	        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	        int totalNoOfRecords = perPageNoOfRecordsPresent;
	        int noOfRecordsChecked = 0;
//	        if (perPageNoOfRecordsPresent > 0) {
//	            String a = driver.findElement(By.xpath("//*[@id=\"approvalOrganizationTable\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//	            String[] parts = a.split(" of ");
//	            try {
//	                totalNoOfRecords = Integer.parseInt(parts[1].trim());
//	            } catch (Exception e) {
//	                System.out.println(e.getMessage());
//	            }
//	        }
	        	if (perPageNoOfRecordsPresent > 0 && count5 == 0) {
	            if (((NodeName == null) || ("".equalsIgnoreCase(NodeName)))) {
	            	NodeName = driver.findElement(By.xpath("//*[@id=\"approvalOrganizationTable\"]/div/table/tbody/tr[1]/td[3]")).getText();//documentType
	            } else if ((NodeName == null) || ("".equalsIgnoreCase(NodeName))) {
	            	NodeName = driver.findElement(By.xpath("//*[@id=\"approvalOrganizationTable\"]/div/table/tbody/tr/td[3]")).getText();//documentType

	            }
	            ++count5;
	        }
	        if (perPageNoOfRecordsPresent > 0) {
	            while (noOfRecordsChecked < totalNoOfRecords) {
//	            	System.out.println("While Loop........");
	                if (perPageNoOfRecordsPresent > 1) {
	                    for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
	                        String AppNodeName = driver.findElement(By.xpath("//*[@id=\"approvalOrganizationTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).getText();//documentTypeName
//	                        System.out.println("NodeName: "+NodeName);
	                        if (NodeName.equalsIgnoreCase(AppNodeName)) {
	                            driver.findElement(By.xpath("//*[@id=\"approvalOrganizationTable\"]/div/table/tbody/tr[ " + i + " ]/td[3]")).click();
	                            isRecordSelectedForNode = true;
	                            break;
	                        }
	                    }
	                   
	                } else {
	                    String AppNodeName = driver.findElement(By.xpath("//*[@id=\"approvalOrganizationTable\"]/div/table/tbody/tr[1]/td[3]")).getText();
	                    if (NodeName.equalsIgnoreCase(AppNodeName)) {
	                        driver.findElement(By.xpath("//*[@id=\"approvalOrganizationTable\"]/div/table/tbody/tr[1]/td[3]")).click();
	                        isRecordSelectedForNode = true;
	                        
	                    }
	                }
	                noOfRecordsChecked += perPageNoOfRecordsPresent;
	                if ((!isRecordSelectedForNode) && (noOfRecordsChecked < totalNoOfRecords)) {
	                    driver.findElement(By.cssSelector("#approvalOrganizationTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next")).click();//next page in Document approve list
	                    Thread.sleep(3000);
	                    table = driver.findElement(By.id("approvalOrganizationTable"));//Document Tree approve table
	                    tableBody = table.findElement(By.tagName("tbody"));
	                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
	                }
	            }
	        }
	       
	    
	        return isRecordSelectedForNode;
	}
}
