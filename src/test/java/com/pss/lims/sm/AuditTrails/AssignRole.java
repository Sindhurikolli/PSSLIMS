package com.pss.lims.sm.AuditTrails;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;
import com.pss.lims.util.Helper;

public class AssignRole extends SMLoginDetails {

	@Test
	public void assignRole() throws Exception {

		driver.findElement(By.name("loginUserName")).sendKeys(properties.getProperty("FirstName"));
		Thread.sleep(2000);
		driver.findElement(By.name("loginPassword")).sendKeys(properties.getProperty("Password"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div/div[4]/a/map/area")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("auditTrialsTabInSampleMngt")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("limsAssignRoleAuditForm")).click();
		Thread.sleep(3000);
		methodToAssignRole();

	}

	private void methodToAssignRole() throws Exception {

		driver.findElement(By.id("userBrowseBtnInAssignRoleAuditFormSrch")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("locTreeInCalPmBdm_2_span")).click();
		Thread.sleep(3000);
		int count = 0;
		boolean isRecordSelected = false;
		String selectingUserSingleApproval = properties.getProperty("FirstName");
		isRecordSelected = Helper.selectingSingleApprovalRecord(driver, selectingUserSingleApproval, isRecordSelected,
				count);
		Thread.sleep(3000);
		driver.findElement(By.id("usersSelBtnInLocaBasedUser")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("searchBtnInAssignRoleAuditFrmSrch")).click();
		Thread.sleep(3000);

	}
}
