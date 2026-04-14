package Tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import Base.DriverManager;
import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductPage;

public class EndToEndTest extends DriverManager{


	@Test
	public void fullE2EFlow() throws Exception {

		LoginPage login = new LoginPage();
		ProductPage product = new ProductPage();
		CartPage cart = new CartPage();

		String username = Utils.PropertyReader.readKey("username");
		String password = Utils.PropertyReader.readKey("password");
		String searchKey = Utils.PropertyReader.readKey("search");

		login.login(username, password);
		Assert.assertTrue(login.isLoginSuccessful());

		product.searchForProduct(searchKey);
		DriverManager.getDriver().navigate().back();
		Assert.assertTrue(product.isProductDisplayedInSearchResults(searchKey));
		DriverManager.getDriver().navigate().back();

		product.productDetails(searchKey);
		Assert.assertTrue(product.getProductTitle().toLowerCase().contains(searchKey.toLowerCase()));

		cart.addMultipleProductsSimple(2);

		cart.removeAllItemsFromCart();
		Assert.assertEquals(cart.getCartCount(), 0);
	}

}
