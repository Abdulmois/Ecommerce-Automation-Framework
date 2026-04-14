package Base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	//Driver
	public WebDriver getDriver() {
		return DriverManager.getDriver();
	}

	//Common Wait
	public WebDriverWait getWait() {
		return new WebDriverWait(getDriver(), Duration.ofSeconds(15));
	}

	//Click (safe click)
	public void click(By locator) {
		getWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public void clickElement(WebElement element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	//Enter input
	public void enterInput(By locator, String input) {
		WebElement ele = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
		ele.clear(); // optional but better
		ele.sendKeys(input);
	}

	//Wait for single element (visible)
	public WebElement waitForElement(By locator) {
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	//Wait for all elements
	public List<WebElement> waitForAllElements(By locator) {
		return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public List<WebElement> waitForAllElementspresence(By locator) {
		return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	//Wait for clickable element
	public WebElement waitForClickable(By locator) {
		return getWait().until(ExpectedConditions.elementToBeClickable(locator));
	}

	//Check element displayed
	public boolean isElementDisplayed(By locator) {
		try {
			return getDriver().findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	//Wait for invisibility
	public void waitForInvisibility(By locator) {
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
}