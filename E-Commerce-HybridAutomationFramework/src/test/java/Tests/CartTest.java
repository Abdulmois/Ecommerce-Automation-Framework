package Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.DriverManager;
import Pages.CartPage;

public class CartTest extends DriverManager{
	
	
	@Test(priority = 1)
	public void addToCartTest() {

	    CartPage page = new CartPage();

	    page.searchForProduct("iPhone");  
	    
	    WebDriverWait wait = new WebDriverWait(
	            DriverManager.getDriver(), Duration.ofSeconds(10));

	    List<WebElement> products = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                    By.xpath("//a[contains(@class,'a-link-normal')]//h2")));

	    products.get(0).click();

	    page.switchToChildWindow();

	    page.addProductToCartFromDetailsPage();

	    String cartCount = DriverManager.getDriver()
	            .findElement(By.id("nav-cart-count"))
	            .getText();
	    
	    System.out.println(cartCount);

	    Assert.assertTrue(Integer.parseInt(cartCount) > 0, "Cart is empty!");
	}
	
	
	@Test(priority = 2)
	public void addMultipleTest() {

	    CartPage page = new CartPage();

	    page.addMultipleProductsSimple(2);

	    DriverManager.getDriver().navigate().refresh();

	    WebDriverWait wait = new WebDriverWait(
	            DriverManager.getDriver(), Duration.ofSeconds(10));

	    WebElement cart = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count"))
	    );

	    int count = Integer.parseInt(cart.getText());

	    System.out.println("Final Count: " + count);

	    Assert.assertTrue(count >= 2, "Cart count incorrect!");
	}
	
	
	
	@Test(priority = 3)
	public void clearCartTest() {

	    CartPage page = new CartPage();
	    page.removeAllItemsFromCart();

	    String cartCount = DriverManager.getDriver()
	            .findElement(By.id("nav-cart-count"))
	            .getText();

	    Assert.assertEquals(cartCount, "0", "Cart is not empty!");
	}

}
