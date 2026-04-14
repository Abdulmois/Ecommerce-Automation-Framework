package Pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Base.BaseClass;
import Base.DriverManager;

public class CartPage extends BaseClass {

	By search = By.xpath("//input[@id='twotabsearchtextbox']");
	By enter = By.id("nav-search-submit-button");
	By cartcontainer = By.id("nav-cart-count-container");
	By delete = By.xpath("//input[@value='Delete']");
	By addToCart = By.id("add-to-cart-button");
	By closeBtn = By.xpath("//a[contains(@aria-label,'Exit this panel')]");
	By allProducts = By.xpath("//a[contains(@class,'a-link-normal')]//h2");
	By productTitle = By.xpath("//span[@id='productTitle']");
	By cartCount = By.id("nav-cart-count");


	public void searchForProduct(String productName) {
		click(search);
		enterInput(search, productName);
		click(enter);
	}

	public void addProductToCartFromDetailsPage() {

	    WebDriver driver = DriverManager.getDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // correct wait for multiple elements
	    List<WebElement> buttons = wait.until(
	            ExpectedConditions.presenceOfAllElementsLocatedBy(addToCart)
	    );

	    for (WebElement btn : buttons) {

	        try {
	            if (btn.isDisplayed() && btn.isEnabled()) {

	                // wait until clickable
	                wait.until(ExpectedConditions.elementToBeClickable(btn)).click();

	                System.out.println("Clicked Add to Cart");
	                break;
	            }

	        } catch (Exception e) {
	            System.out.println("Not clickable, trying next...");
	        }
	    }
	}


	public void waitForCartCountUpdate() {

		WebDriverWait wait = new WebDriverWait(
				DriverManager.getDriver(), Duration.ofSeconds(10));

		wait.until(ExpectedConditions.not(
				ExpectedConditions.textToBe(cartCount, "0")
				));
	}


	public void addMultipleProductsSimple(int count) {

	    WebDriver driver = DriverManager.getDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    searchForProduct("iPhone");

	    int added = 0;

	    List<WebElement> products = wait.until(
	            ExpectedConditions.presenceOfAllElementsLocatedBy(allProducts)
	    );

	    for (int i = 0; i < products.size() && added < count; i++) {

	        String mainWindow = driver.getWindowHandle();

	        // click product
	        wait.until(ExpectedConditions.elementToBeClickable(products.get(i))).click();

	        // switch to new tab
	        for (String win : driver.getWindowHandles()) {
	            if (!win.equals(mainWindow)) {
	                driver.switchTo().window(win);
	                break;
	            }
	        }

	        try {
	            // same simple logic you used
	            List<WebElement> buttons = wait.until(
	                    ExpectedConditions.presenceOfAllElementsLocatedBy(addToCart)
	            );

	            for (WebElement btn : buttons) {
	                if (btn.isDisplayed() && btn.isEnabled()) {
	                    wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
	                    System.out.println("Added product: " + (added + 1));
	                    added++;
	                    break;
	                }
	            }

	        } catch (Exception e) {
	            System.out.println("Skipped product");
	        }

	        // close tab and go back
	        driver.close();
	        driver.switchTo().window(mainWindow);

	        // refresh product list (important)
	        products = wait.until(
	                ExpectedConditions.presenceOfAllElementsLocatedBy(allProducts)
	        );
	    }

	    System.out.println("Total products added: " + added);
	}


	public void removeAllItemsFromCart() {

		click(cartcontainer);

		List<WebElement> items = DriverManager.getDriver().findElements(delete);

		while (items.size() > 0) {

			items.get(0).click();

			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			items = DriverManager.getDriver().findElements(delete);
		}
	}


	public void switchToChildWindow() {

		Set<String> windowHandles = DriverManager.getDriver().getWindowHandles();
		String windowHandle = DriverManager.getDriver().getWindowHandle();

		for (String a : windowHandles) {
			if (!a.equals(windowHandle)) {
				DriverManager.getDriver().switchTo().window(a);
				break;
			}
		}
	}


	public int getCartCount() {

		String count = waitForElement(cartCount)
				.getText();

		return Integer.parseInt(count);
	}
}