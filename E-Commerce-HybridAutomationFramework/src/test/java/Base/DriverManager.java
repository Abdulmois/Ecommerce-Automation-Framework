package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DriverManager {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	// Get driver
	public static WebDriver getDriver() {
		return driver.get();
	}

	// Before test
	@BeforeMethod
	public void setup() {
		WebDriver dr = new ChromeDriver();
		dr.manage().window().maximize();
		driver.set(dr);
		dr.get("https://www.amazon.in/");
	}

	// After test
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
		driver.remove();
	}
}