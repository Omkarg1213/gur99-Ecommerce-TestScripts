package guru99.EcommerceProject.MobileSortingPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MobileSortingPage {
    WebDriver driver;
    By mobile = By.xpath("(//a[@class='level0 '])[1]");
    By sort = By.xpath("(//select[@title])[1]");
    By productNames = By.xpath("//h2[@class=\"product-name\"]");

    public MobileSortingPage(WebDriver driver) {
        this.driver = driver;
    } 

    public void navigateToMobilePage() {
		 driver.findElement(mobile).click();
	}
    
    public void toSortMobileByName() {
        driver.findElement(mobile).click();
        WebElement sortDropdown = driver.findElement(sort);
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Name");
    }
    
    public boolean isSortedByName() {
        List<WebElement> productElements = driver.findElements(productNames);
        List<String> productNamesList = new ArrayList<>();
        for (WebElement productElement : productElements) {
            productNamesList.add(productElement.getText().trim());
        }
        
        System.out.println("Unsorted product names:");
        for (String name : productNamesList) {
            System.out.println(name);
        }
        
        List<String> sortedProductNamesList = new ArrayList<>(productNamesList);
        Collections.sort(sortedProductNamesList);
        
        System.out.println("Sorted product names:");
        for (String name : sortedProductNamesList) {
            System.out.println(name);
        }
        
        return productNamesList.equals(sortedProductNamesList);
    }

}
<<<<<<< HEAD

=======
>>>>>>> origin/master
