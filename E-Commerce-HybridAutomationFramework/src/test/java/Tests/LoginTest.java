package Tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginPage;

public class LoginTest extends Base.DriverManager{


	@Test(priority = 1)
	public void verifyValidLogin() throws Exception {
		LoginPage loginPage=new LoginPage();
		loginPage.login(Utils.PropertyReader.readKey("username"), Utils.PropertyReader.readKey("password"));
		Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed!");
	}


	@Test(priority = 2)
	public void verifyInvalidLogin() throws Exception {
		LoginPage a=new LoginPage();
		a.login(Utils.PropertyReader.readKey("usernamew1"), Utils.PropertyReader.readKey("passwordw1"));
		String actualText =a.getErrorMessage1();
		String expectedText="Your password is incorrect";
		Assert.assertEquals(actualText, expectedText,"Password error message mismatch!");
	}


	@Test(priority = 3)
	public void verifyEmptyPasswordLogin() throws Exception {
		LoginPage a=new LoginPage();
		a.login(Utils.PropertyReader.readKey("usernamew2"), Utils.PropertyReader.readKey("passwordw2"));
		String actualText = a.getErrorMessage2();
		String expectedText="Enter your password";
		Assert.assertEquals(actualText, expectedText,"Password error message mismatch!");
	}


}
