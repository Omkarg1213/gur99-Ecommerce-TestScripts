package guru99.EcommerceProject.MobileSortingPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WishlistPage {
	WebDriver driver;
    private By createAccountLink = By.xpath("//a[@title=\"Create an Account\"]");
    private By firstNameField = By.id("firstname"); 
    private By middleNameField = By.id("middlename");
    private By lastNameField = By.id("lastname");
    private By email_address = By.xpath("//input[@id='email_address']");
    private By passwordField = By.id("password");
    private By confirmPasswordField = By.id("confirmation"); 
    private By checkboxField = By.id("is_subscribed");
    private By registerButton = By.xpath("//button[@title='Register']");
    private By registrationConfirmationMessage = By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]");
    private By tvMenuLink = By.linkText("TV");
    private By productLink = By.xpath("//a[contains(text(), 'Product Name')]"); // Adjust XPath for specific product
    private By addToWishlistButton = By.linkText("Add to Wishlist");
    private By shareWishlistButton = By.xpath("//span[contains(text(),'Share Wishlist')]");
    private By emailField = By.xpath("//textarea[@id='email_address']");
    private By messageField = By.xpath("//textarea[@id='message']");
    private By shareWishlistButtonAgain = By.xpath("//span[contains(text(),'Share Wishlist')]");
    private By wishlistSharedMessage = By.xpath("//span[contains(text(),'Your Wishlist has been shared.')]");

	public WishlistPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickCreateAccountLink() {
		driver.findElement(createAccountLink).click();
		
	}

	public void fillRegistrationForm(String firstName, String middleName, String lastName, String email,
			String password, String confirmPassword) {
		driver.findElement(firstNameField).sendKeys(firstName);
		driver.findElement(middleNameField).sendKeys(middleName);
		driver.findElement(lastNameField).sendKeys(lastName);
		driver.findElement(email_address).sendKeys(email);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
		driver.findElement(checkboxField).click();
	}

	public void clickRegisterButton() {
		driver.findElement(registerButton).click();
	}

	public boolean isRegistrationConfirmed() {
		WebElement registrationConfirmation = driver.findElement(registrationConfirmationMessage);
		return registrationConfirmation.isDisplayed();
	}

	public void navigateToTVMenu() {
		driver.findElement(tvMenuLink).click();
		
	}

	public void addProductToWishlist() {
		driver.findElements(addToWishlistButton).get(0).click();
		
	}

	public void clickShareWishlist() {
		driver.findElement(shareWishlistButton).click();
	}

	public void enterEmailAndMessage(String email, String message) {
		driver.findElement(emailField).sendKeys(email);;
		driver.findElement(messageField).sendKeys(message);
	}

	public void clickShareWishlistAgain() {
		driver.findElement(shareWishlistButtonAgain).click();
	}

	public boolean isWishlistShared() {
		WebElement isWishlistSharedElement = driver.findElement(wishlistSharedMessage);
		return isWishlistSharedElement.isDisplayed();
	}

	
}
