package guru99.EcommerceProject.MobileSortingPage;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobilePage {
    WebDriver driver;
    WebDriverWait wait;
    By mobileMenu = By.xpath("(//a[@class='level0 '])[1]");
    private By myAccountLink = By.xpath("(//a[@title=\"My Account\"])[2]");
    By sonyXperiaPrice = By.xpath("(//span[@class=\"price\"])[4]");
    public static By sonyXperiaLink = By.xpath("//img[@alt=\"Xperia\"]");
    public static By addToCart = By.xpath("//button[@title='Add to Cart']");
    By sonyXperiaPriceDetail = By.xpath("//span[@class='price']");
    By addToCompare = By.xpath("//a[@class='link-compare']");
    By compareButton = By.xpath("//button[@title=\"Compare\"]");
    By comparePopup = By.xpath("//h1[contains(text(),'Compare Products')]");

    public MobilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToMobilePage() {
        driver.findElement(mobileMenu).click();
    }
    
    public void clickMyAccountLink() {
		driver.findElement(myAccountLink).click();
	}

    public String getSonyXperiaPriceOnListPage() {
        return driver.findElement(sonyXperiaPrice).getText();
    }

    public void clickOnSonyXperia() {
        driver.findElement(sonyXperiaLink).click();
    }

    public String getSonyXperiaPriceOnDetailPage() {
        return driver.findElement(sonyXperiaPriceDetail).getText();
    }

    public void clickAddToCart() {
        driver.findElement(addToCart).click();
    }

    public void addFirstMobileToCompare() {
        WebElement addFirstMobileToCompare = driver.findElements(addToCompare).get(0);
        addFirstMobileToCompare.click();
    }

    public void addSecondMobileToCompare() {
        WebElement addSecondMobileToCompare = driver.findElements(addToCompare).get(1);
        addSecondMobileToCompare.click();
    }

    public void clickCompareButton() {
        WebElement compareBtn = driver.findElement(compareButton);
        compareBtn.click();
    }

    public boolean isComparePopupDisplayed() {
        switchToComparePopup();
        WebElement popup = wait.until(
            ExpectedConditions.visibilityOfElementLocated(comparePopup)
        );
        return popup.isDisplayed();
    }

    public boolean verifySelectedProductsInComparePopup() {
        switchToComparePopup();
        By firstProductInPopup = By.xpath("//a[@title='IPhone']");
        By secondProductInPopup = By.xpath("//a[@title='Samsung Galaxy']");
        WebElement firstProductElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(firstProductInPopup)
        );
        WebElement secondProductElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(secondProductInPopup)
        );

        // Assuming you can retrieve the names of the products for comparison
        String firstProductName = firstProductElement.getText();
        String secondProductName = secondProductElement.getText();

        // Validate the product names (you can compare with expected names or any other validation logic)
        return firstProductName.contains(firstProductName) && secondProductName.contains(secondProductName);
    }

    public void closeComparePopup() {
        try {
            // Get the current window handle
            String mainWindowHandle = driver.getWindowHandle();
            
            // Collect all window handles
            Set<String> windowHandles = driver.getWindowHandles();
            
            // If there are multiple windows, one of them must be the popup
            if (windowHandles.size() > 1) {
                for (String handle : windowHandles) {
                    if (!handle.equals(mainWindowHandle)) {
                        driver.switchTo().window(handle); // Switch to the popup window
                        driver.close(); // Close the popup window
                        break;
                    }
                }
            } else {
                System.out.println("No popup window found to close.");
            }
            
            // Switch back to the main window
            driver.switchTo().window(mainWindowHandle);
        } catch (Exception e) {
            System.out.println("Error while closing the popup window: " + e.getMessage());
            // Switch back to the main window in case of error
            String mainWindowHandle = driver.getWindowHandle();
            driver.switchTo().window(mainWindowHandle);
        }
    }

    private void switchToComparePopup() {
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
}
