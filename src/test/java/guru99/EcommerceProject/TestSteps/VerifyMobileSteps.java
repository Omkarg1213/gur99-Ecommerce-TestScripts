package guru99.EcommerceProject.TestSteps;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import guru99.EcommerceProject.DriveFactory.DriverFactory;
import guru99.EcommerceProject.MobileSortingPage.MobileSortingPage;
import guru99.EcommerceProject.MobileSortingPage.CartPage;
import guru99.EcommerceProject.MobileSortingPage.MobilePage;

import guru99.EcommerceProject.MobileSortingPage.WishlistPage; // Import WishlistPage

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VerifyMobileSteps {
    private WebDriver driver;
    private Properties config;
    private SoftAssert softAssert;
    private MobileSortingPage mobileSortingPage;
    private MobilePage mobilePage;
    private CartPage cartPage;

    private WishlistPage wishlistPage; // Add WishlistPage

    private String listPagePrice;
    private String detailPagePrice;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        config = new Properties();
        softAssert = new SoftAssert();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file");
        }
        mobileSortingPage = new MobileSortingPage(driver);
        mobilePage = new MobilePage(driver);
        cartPage = new CartPage(driver);

        wishlistPage = new WishlistPage(driver); // Initialize WishlistPage

    }

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        String applicationUrl = config.getProperty("AUTLINK");
        if (applicationUrl == null) {
            throw new RuntimeException("URL is not defined in the config.properties file");
        }
        driver.get(applicationUrl);
        driver.manage().window().maximize();
    }

    @When("I navigate to the mobile page")
    public void i_navigate_to_the_mobile_page() {
        mobilePage.navigateToMobilePage();
    }

    @When("I sort the mobiles by name")
    public void i_sort_the_mobiles_by_name() {
        mobileSortingPage.toSortMobileByName();
    }

    @Then("the products should be sorted by name")
    public void the_products_should_be_sorted_by_name() {
        boolean isSorted = mobileSortingPage.isSortedByName();
        softAssert.assertTrue(isSorted, "The products are not sorted by name");
        softAssert.assertAll();
    }

    @When("I read the cost of Sony Xperia mobile on the list page")
    public void i_read_the_cost_of_sony_xperia_mobile_on_the_list_page() {
        listPagePrice = mobilePage.getSonyXperiaPriceOnListPage();
        System.out.println("Sony Xperia price on list page: " + listPagePrice);
    }

    @When("I click on the Sony Xperia mobile")
    public void i_click_on_the_sony_xperia_mobile() {
        mobilePage.clickOnSonyXperia();
    }

    @Then("I read the cost of Sony Xperia mobile on the detail page")
    public void i_read_the_cost_of_sony_xperia_mobile_on_the_detail_page() {
        detailPagePrice = mobilePage.getSonyXperiaPriceOnDetailPage();
        System.out.println("Sony Xperia price on detail page: " + detailPagePrice);
    }

    @Then("the prices should match")
    public void the_prices_should_match() {
        softAssert.assertEquals(listPagePrice, detailPagePrice, "Prices on list page and detail page do not match");
        softAssert.assertAll();
    }

    @When("I add Sony Xperia to the cart")
    public void i_add_sony_xperia_to_the_cart() {
        mobilePage.clickAddToCart(); // Ensure this method is defined in MobilePage
    }

    @When("I change the quantity of a mobile to {int}")
    public void i_change_the_quantity_of_a_mobile_to(Integer quantity) {
        cartPage.changeQuantityAndUpdate(quantity.toString()); // Convert integer to string if needed
    }

    @Then("I should see an error message for invalid quantity")
    public void i_should_see_an_error_message_for_invalid_quantity() {
        try {
            String errorMessage = cartPage.getErrorMessage();
            System.out.println("Error message: " + errorMessage);
            softAssert.assertTrue(errorMessage.contains(config.getProperty("errorMessage")), "Error message is not as expected");
        } catch (Exception e) {
            System.out.println("Failed to get error message: " + e.getMessage());
        }
    }

    @When("I click on the empty cart link")
    public void i_click_on_the_empty_cart_link() {
        cartPage.clickEmptyCartLink();
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        boolean isCartEmpty = cartPage.isCartEmpty();
        softAssert.assertTrue(isCartEmpty, "The cart is not empty");
        softAssert.assertAll();
    }
    
    @When("I add two mobiles to compare")
    public void i_add_two_mobiles_to_compare(){

        mobilePage.addFirstMobileToCompare();
        mobilePage.addSecondMobileToCompare();
    }

    @When("I click on the compare button")
    public void i_click_on_the_compare_button() {
        mobilePage.clickCompareButton();
    }

    @Then("I should see a pop-up window with the selected products")
    public void i_should_see_a_pop_up_window_with_the_selected_products() {
        boolean isDisplayed = mobilePage.isComparePopupDisplayed();
        boolean areProductsCorrect = mobilePage.verifySelectedProductsInComparePopup();
        softAssert.assertTrue(isDisplayed, "Compare popup is not displayed.");
        softAssert.assertTrue(areProductsCorrect, "Products in compare popup are not correct.");
        softAssert.assertAll();
    }

    @Then("I close the pop-up window")
    public void i_close_the_pop_up_window() {
        mobilePage.closeComparePopup();
    }

    @When("I click on the \"My Account\" link")
    public void i_click_on_the_my_account_link() {
    	mobilePage.clickMyAccountLink(); // Implement this method in WishlistPage
    }

    @When("I click on the \"Create Account\" link")
    public void i_click_on_the_create_account_link() {
        wishlistPage.clickCreateAccountLink(); // Implement this method in WishlistPage
    }

    @When("I fill in the new user information")
    public void i_fill_in_the_new_user_information_except_email() {
        String firstName = config.getProperty("firstName");
        String middleName = config.getProperty("middleName");
        String lastName = config.getProperty("lastName");
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        String confirmPassword = config.getProperty("confirmPassword");

        wishlistPage.fillRegistrationForm(firstName, middleName, lastName, email, password, confirmPassword);
    }
    
    @When("I click on \"Register\"")
    public void i_click_on_register() {
        wishlistPage.clickRegisterButton(); // Implement this method in WishlistPage
    }

    @Then("I should see the registration confirmation")
    public void i_should_see_the_registration_confirmation() {
        boolean isConfirmed = wishlistPage.isRegistrationConfirmed(); // Implement this method in WishlistPage
        softAssert.assertTrue(isConfirmed, "Registration was not successful");
        softAssert.assertAll();
    }

    @When("I go to the \"TV\" menu")
    public void i_go_to_the_tv_menu() {
        wishlistPage.navigateToTVMenu(); // Implement this method in WishlistPage
    }

    @When("I add a product to my wishlist")
    public void i_add_a_product_to_my_wishlist() {
        wishlistPage.addProductToWishlist(); // Implement this method in WishlistPage
    }

    @When("I click \"SHARE WISH LIST\"")
    public void i_click_share_wish_list() {
        wishlistPage.clickShareWishlist(); // Implement this method in WishlistPage
    }

    @When("I enter an email and a message on the next page")
    public void i_enter_an_email_and_a_message_on_the_next_page() {
        wishlistPage.enterEmailAndMessage(config.getProperty("receiverEmail"), config.getProperty("message")); // Implement this method in WishlistPage
    }

    @When("I click \"SHARE WISH LIST\" again")
    public void i_click_share_wish_list_again() {
        wishlistPage.clickShareWishlistAgain(); // Implement this method in WishlistPage
    }

    @Then("I should see that the wishlist has been shared")
    public void i_should_see_that_the_wishlist_has_been_shared() {
        boolean isShared = wishlistPage.isWishlistShared(); // Implement this method in WishlistPage
        softAssert.assertTrue(isShared, "Wishlist was not shared successfully");
        softAssert.assertAll();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
