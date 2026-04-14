package Tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.ProductPage;

public class ProductTest extends Base.DriverManager{

	@Test(priority = 1)
	public void verifyProductDetailsPage() throws Exception {

	    ProductPage a = new ProductPage();
	    String searchKey = Utils.PropertyReader.readKey("search");

	    a.productDetails(searchKey);

	    String text = a.getProductTitle().toLowerCase();

	    Assert.assertTrue(text.contains(searchKey.toLowerCase()),
	            "Product title does not contain: " + searchKey);
	}
	
	
    @Test(priority = 2)
	public void verifyProductDisplayedInSearchResults() {
		
		ProductPage a=new ProductPage();
		boolean productPresent = a.isProductDisplayedInSearchResults("Iphone");
		Assert.assertTrue(productPresent,"No iPhone products found in search results!");
		
	}

}
