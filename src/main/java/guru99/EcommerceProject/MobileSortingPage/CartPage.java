package guru99.EcommerceProject.MobileSortingPage;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    By quantityInput = By.xpath("//input[@title='Qty']");
    By updateButton = By.xpath("//button[@title='Update']");
    By errorMessage = By.xpath("//p[contains(text(),'* The maximum quantity allowed for purchase is 500')]");
    By emptyCartLink = By.xpath("//button[@title='Empty Cart']");
    By emptyCartMessage = By.xpath("//div[@class='cart-empty']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
    }

    public void changeQuantityAndUpdate(String quantity) {
//        WebElement addToCartElement = driver.findElement(MobilePage.addToCart);
//        addToCartElement.click();
        WebElement quantityInputElement = driver.findElement(quantityInput);
        quantityInputElement.clear();
        quantityInputElement.sendKeys(quantity);
        driver.findElement(updateButton).click();
    }

    public String getErrorMessage() {
        WebElement errorMessageElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(errorMessage)
        );
        return errorMessageElement.getText();
    }

    public void clickEmptyCartLink() {
        WebElement link = driver.findElement(emptyCartLink);
        link.click();
    }

    public boolean isCartEmpty() {
        return driver.findElement(emptyCartMessage).isDisplayed();
    }
}
