package Pages;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Base.BaseClass;
import Base.DriverManager;

public class ProductPage extends BaseClass {

	// Locators
	By searchField = By.id("twotabsearchtextbox");
	By searchButton = By.id("nav-search-submit-button");
	By allResults = By.xpath("//a[contains(@class,'a-link-normal')]//h2");
	By productTitle = By.id("productTitle");

	
	// Reusable search method
	public void searchForProduct(String keys) {
		click(searchField);
		enterInput(searchField, keys);
		click(searchButton);
	}
	

	public void productDetails(String keys) {

		searchForProduct(keys); //reuse instead of duplicate code

		WebElement firstElement = waitForClickable(allResults);

		firstElement.click();

		switchToChildWindow();
	}


	public void switchToChildWindow() {

		WebDriver driver = DriverManager.getDriver();
		String parent = driver.getWindowHandle();

		for (String window : driver.getWindowHandles()) {
			if (!window.equals(parent)) {
				driver.switchTo().window(window);
				break;
			}
		}

		System.out.println("Now in window: " + driver.getTitle());
	}


	public boolean isProductDisplayedInSearchResults(String productName) {

		searchForProduct(productName); //reuse

		List<WebElement> titles =  waitForAllElements(allResults);

		for (WebElement ele : titles) {

			String text = ele.getText().toLowerCase();

			if (text.contains("sponsored")) continue;

			if (text.contains(productName.toLowerCase())) {
				System.out.println("Found product: " + text);
				return true;
			}
		}

		return false;
	}
	
	
	public String getProductTitle() {

		WebElement title = waitForElement(productTitle);

		return title.getText();
	}
}