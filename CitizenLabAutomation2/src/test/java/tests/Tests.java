
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tests extends BaseClass {	

	ExtentTest test;

	@Test
	public void login() {	
		
		test = extent.createTest("#1 Happy path : Launch Citizenlab site and Login", "...");
		
		this.driver = new ChromeDriver();
		//Launch url
		driver.get("https://qa-assignment.citizenlab.co");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//Login
		driver.findElement(By.cssSelector(".kGPhzt")).click();
		driver.findElement(By.id("email")).sendKeys("jane.johnson@example.com");
		driver.findElement(By.id("password")).sendKeys("iamnotarobot");
		driver.findElement(By.id("e2e-signin-password-submit-button")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Assert.assertEquals(driver.findElement(By.id("e2e-user-menu-container")).isDisplayed(),true);
		test.log(Status.PASS, "Citizenlab launch and login is successful");
			
		
	}
	
	@Test 
	public void submitAnIdea() {
		
		test = extent.createTest("#2 Failure path : Projects, Submit an idea ", "...");
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		  try { driver.findElement(By.cssSelector(".hDcRwf")).click();
		  
		  } catch(NoSuchElementException e) { e.getStackTrace();
		  test.log(Status.FAIL,"Test case: Submit an idea failed with an error");
		  
		  }
		 
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("project-ideabutton")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector(".hGBmel")).sendKeys("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector(".fYKmYr .ql-toolbar.ql-snow + .ql-container.ql-snow")).sendKeys("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. ");
			driver.findElement(By.id("e2e-idea-new-submit-button")).click();
			
			//expected error text
		      String exp = "There was an issue submitting the form. Please check for any errors and try again.";
		      //identify actual error message
		      WebElement m = driver.findElement(By.cssSelector(".byUIC"));
		      String act = m.getText();
		      System.out.println("Error message is: "+ act);
		      //verify error message with Assertion
		      Assert.assertEquals(exp, act);
		      test.log(Status.FAIL,"Test case: Submit an idea failed with an error");	  
		
	}
	
	
	  @Test public void logout() {
	  
	  test = extent.createTest("#3 Logout ", "...");
	  driver.findElement(By.id("e2e-user-menu-container")).click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.id("e2e-sign-out-link")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector(".kGPhzt")).isDisplayed
	  (),true); test.log(Status.PASS, "Logout is successful");
	  
	  
	  }	 	
	
}
	









