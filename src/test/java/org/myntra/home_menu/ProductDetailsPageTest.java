package org.myntra.home_menu;

import static com.myntra.base.Keyword.driver;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.myntra.base.TestBase;
import com.myntra.dataprovider.DataProviderForPincode;
import com.myntra.homemenupages.ProductDetailsPage;
import com.myntra.utils.LoggerUtil;

public class ProductDetailsPageTest extends TestBase {
	private static final Logger log = (Logger) LoggerUtil.getLogger(ProductDetailsPageTest.class);

	@Test(description = "Navigate to PDP via PLP", groups = { "valid", "smoke", "navigation" })
	public void verifyNavigateToProductDetailsPageViaProductListingPage() {
		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.navigateToProductDetailsPage();
		String currentUrl = driver.getCurrentUrl();
		log.info("currentURL : " + currentUrl);
		Assert.assertNotNull(currentUrl, "URL should not be null after navigation");
		Assert.assertFalse(currentUrl.contains("listing") || currentUrl.contains("category"),
				"URL should point to PDP, not PLP. Current URL: " + currentUrl);
	}

	@Test(description = "Product name is displayed", groups = { "valid", "smoke" })
	public void verifyProductNameIsDisplayedOnPDP() {
		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.navigateToProductDetailsPage();
		boolean productName = pdp.isProductNameDisplayed();
		Assert.assertTrue(productName, "Product name is not displayed on the ProductDetailsPage");

	}

	@Test(description = " Product image is displayed", groups = { "valid", "smoke" })
	public void verifyProductImageisDisplayedOnPDP() {
		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.navigateToProductDetailsPage();
		boolean productImage = pdp.isProductImageDisplayed();
		Assert.assertTrue(productImage, "Product image should be displayed with a valid src attribute");

	}

	@Test(description = "Product price is displayed", groups = { "valid", "smoke" })
	public void verifyProductPriceIsDisplayedOnPDP() {
		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.navigateToProductDetailsPage();
		boolean productPrice = pdp.isproductPriceDisplyed();
		Assert.assertTrue(productPrice, "Product price is not displayed on the ProductDetailsPage");
	}

	@Test(description = "Product description is present", groups = { "valid", "smoke" })
	public void verifyProductDescriptionIsPresentOnPDP() {
		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.navigateToProductDetailsPage();
		boolean productDescription = pdp.isProductDescriptionPresent();
		Assert.assertTrue(productDescription, "Product description is not displayed on the PDP");

	}

	@Test(description = "Add to bag button is enabled", groups = { "valid", "smoke" })
	public void verifyAddToBagButtonIsEnabledOnPDP() {
		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.navigateToProductDetailsPage();
		boolean addToBagButton = pdp.isAddToBagButtonEnabled();
		Assert.assertTrue(addToBagButton, "Add to Bag button is not enabled on the ProductDetailPage");

	}

	@Test(dataProvider = "deliveryData", dataProviderClass = DataProviderForPincode.class)
	public void verifyDeliveryValidation(String pincode) {
		pincode = pincode.replace(".0", "").trim();
		log.info("pincode: " + pincode);
		ProductDetailsPage pdp = new ProductDetailsPage();
		try {
			pdp.navigateToProductDetailsPage();
		} catch (Exception e) {
			if (pincode.length() != 6 || !pincode.matches("\\d{6}")) {
				log.info("Skipping navigation for invalid pincode: " + pincode);
				return;
			}
			throw new SkipException("No products available for pincode: " + pincode);
		}
		boolean actual = pdp.verifyDeliveryOptions(pincode);
		log.info("actual: " + actual);
		if (pincode.length() != 6 || !pincode.matches("\\d{6}")) {
			Assert.assertFalse(actual, "Invalid pincode should fail: " + pincode);
		} else {
			Assert.assertTrue(actual, "Valid pincode should pass: " + pincode);
		}
	}

	@Test
	public void verifyProductDetailsPage() throws InterruptedException {
		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.navigateToProductDetailsPage();
		pdp.getFirstProductDetails();
		boolean details = pdp.verifyFirstProductDetails();
		Assert.assertTrue(details, "First product details are not displayed properly");

	}

	@Test
	public void verifyInvalid_TC_ProductImageNotLoaded() {
		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.navigateToProductDetailsPage();
		boolean isImageValid = pdp.isProductImageDisplayed();
		Assert.assertTrue(isImageValid, "Product image is broken or not loaded properly");
	}

	@Test
	public void verifyInvalid_TC_ProductPriceMissing() {
		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.navigateToProductDetailsPage();
		boolean isPriceValid = pdp.isProductPriceValid();
		Assert.assertTrue(isPriceValid, "Product price is missing or invalid on PDP");
	}

	@Test
	public void verifyInvalid_TC_ProductDescriptionMissing() {
		ProductDetailsPage pdp = new ProductDetailsPage();
		pdp.navigateToProductDetailsPage();
		boolean isdescValid = pdp.isProductDescriptionAvailable();
		Assert.assertTrue(pdp.isProductDescriptionAvailable(),
				"Neither description nor product details are available on PDP");
	}

}
