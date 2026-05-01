package org.myntra.home_menu;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.myntra.base.TestBase;
import com.myntra.homemenupages.CartPage;
import com.myntra.homemenupages.HomePage;
import com.myntra.homemenupages.PlaceOrderPage;
import com.myntra.homemenupages.ProductDetailsPage;
import com.myntra.homemenupages.ProductListingPage;
import com.myntra.listeners.MyListeners;
import com.myntra.utils.LoggerUtil;

import static com.myntra.base.Keyword.driver;


/**
 * Test class for validating Product Listing page functionalty.
 */
@Listeners(MyListeners.class)
public class ProductListingPageTest extends TestBase {
	private static final Logger log = (Logger) LoggerUtil.getLogger(ProductListingPageTest.class);

	@Test
	public void verifyTotalProductCountGreaterThanZero() {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductListingPage plp = new ProductListingPage();
		int totalProduct = plp.getTotalProductCount();
		log.info("Total Products Found: " + totalProduct);
		Assert.assertTrue(totalProduct > 0, "Product count should be greater than 0 but was: " + totalProduct);
	}

	@Test
	public void verifyFirstProductImageIsDisplayed() {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductListingPage plp = new ProductListingPage();
		boolean firstProductImage = plp.isFirstProductImageDisplayed();
		Assert.assertTrue(firstProductImage, "First product image is not displayed on the Bed Runners PLP");
	}

	@Test
	public void verifyAllProductImagesAreDisplayed() {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductListingPage plp = new ProductListingPage();
		boolean allImagesDisplayed = plp.verifyProductImages();
		Assert.assertTrue(allImagesDisplayed, "One or more product images are not displayed on the PLP");
	}

	@Test
	public void verifyFirstProductImageClickNavigatesToPDP() {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductListingPage plp = new ProductListingPage();
		Assert.assertTrue(plp.isFirstProductImageDisplayed(), "First product image is not visible to click");
		String plpUrl = driver.getCurrentUrl();
		plp.clickOnFirstProductImage();
		String pdpUrl = driver.getCurrentUrl();
		Assert.assertNotEquals(plpUrl, pdpUrl, "Clicking first product image did not navigate away from PLP");
	}

	@Test
	public void verifyProductNamesAreDisplayed() {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductListingPage plp = new ProductListingPage();
		boolean allNamesDisplayed = plp.verifyAllProductNamesDisplayed();
		Assert.assertTrue(allNamesDisplayed, "One or more product names are missing on the Bed Runners PLP");
	}

	@Test
	public void verifyBackNavigationFromPDPReturnsToPLP() {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductListingPage plp = new ProductListingPage();
		plp.navigatesBackAfterClickOnFirstProductImage();
		plp = new ProductListingPage();
		int totalProduct = plp.getTotalProductCount();
		Assert.assertTrue(totalProduct > 0, "Products not displayed after navigating back to PLP from PDP");
	}

	@Test(timeOut = 10000)
	public void verifyPLPLoadsWithinAcceptableTime() {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductListingPage plp = new ProductListingPage();
		int totalProduct = plp.getTotalProductCount();
		Assert.assertTrue(totalProduct > 0, "PLP did not load products within the acceptable time limit");
	}

	@Test
	public void verifyProductListingPageFunctionality() {
		HomePage homepage = new HomePage();
		homepage.hoverOnHomeMenu();
		homepage.clickOnBedRunners();
		ProductListingPage plp = new ProductListingPage();
		int totalProduct = plp.getTotalProductCount();
		log.info("Total Bed Runner Products: " + totalProduct);
		Assert.assertTrue(totalProduct > 0, "No products are displayed on Bed Runners page");
		boolean imagesDisplayed = plp.verifyProductImages();
		Assert.assertTrue(imagesDisplayed, "The images are not displayed.");
		boolean firstProductImage = plp.isFirstProductImageDisplayed();
		Assert.assertTrue(firstProductImage);
		plp.clickOnFirstProductImage();
		ProductDetailsPage pdp = new ProductDetailsPage();
		String currentUrl = driver.getCurrentUrl();
		log.info("currentURL : " + currentUrl);
		Assert.assertNotNull(currentUrl, "URL should not be null after navigation");
		Assert.assertFalse(currentUrl.contains("listing") || currentUrl.contains("category"),
				"URL should point to PDP, not PLP. Current URL: " + currentUrl);
		boolean productName = pdp.isProductNameDisplayed();
		Assert.assertTrue(productName, "Product name is not displayed on the ProductDetailsPage");
		boolean productImage = pdp.isProductImageDisplayed();
		Assert.assertTrue(productImage, "Product image should be displayed with a valid src attribute");
		boolean productPrice = pdp.isproductPriceDisplyed();
		Assert.assertTrue(productPrice, "Product price is not displayed on the ProductDetailsPage");
		boolean addToBagButton = pdp.isAddToBagButtonEnabled();
		Assert.assertTrue(addToBagButton, "Add to Bag button is not enabled on the ProductDetailPage");
		CartPage cart = new CartPage();
		cart.clickOnAddToBagButton();
		cart.clickOnBagIcon();
		boolean status = cart.verifyProductAddedToBag1();
		Assert.assertTrue(status, "Product is not added to bag");
		PlaceOrderPage placeOrder = new PlaceOrderPage();
		placeOrder.clickOnPlaceOrderButton();
		boolean loginPage = placeOrder.verifyLoginPageDisplayed();
		Assert.assertTrue(loginPage, "Login page is not displayed");

	}
}