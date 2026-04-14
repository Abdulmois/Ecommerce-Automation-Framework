package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.BaseClass;
import Base.DriverManager;

public class LoginPage extends BaseClass {
	
	// Locators
	By usernameField = By.id("ap_email_login");
	By continueButton = By.cssSelector("[aria-labelledby='continue-announce']");
	By passwordField = By.cssSelector("[name='password']");
	By signInButton = By.id("signInSubmit");
	By signInLink = By.cssSelector("[data-nav-role='signin']");
	
	// Error message locators (moved to top)
	By errorMessageCommon = By.xpath("//div[contains(@class,'a-alert-content')]");
	By errorMessagePassword = By.xpath("//div[contains(@class,'a-alert-content') and normalize-space()='Enter your password']");

	
	public void login(String user, String pass) {
		click(signInLink);
		enterInput(usernameField, user);
		click(continueButton);
		enterInput(passwordField, pass);
		click(signInButton);
	}
	
	
	public boolean isLoginSuccessful() {
	    return DriverManager.getDriver()
	            .getTitle()
	            .contains("Amazon.in");
	}
	
	
	public String getErrorMessage1() {

	    WebElement error = waitForElement(errorMessageCommon);

	    return error.getText().trim().replaceAll("\\s+", " ");
	}
	
	
	public String getErrorMessage2() {

	    WebElement error = waitForElement(errorMessagePassword);

	    return error.getText().trim().replaceAll("\\s+", " ");
	}
}