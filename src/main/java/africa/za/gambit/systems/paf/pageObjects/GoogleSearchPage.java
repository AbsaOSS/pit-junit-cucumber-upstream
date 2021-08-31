package africa.za.gambit.systems.paf.pageObjects;

import africa.za.gambit.junit.cucumber.framework.base.TestBase;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage extends TestBase {

    public GoogleSearchPage() {
        PageFactory.initElements(driver, this);
    }

    public static final String searchBox = "name||q";
    public static final String searchButton = "name||btnK";

    public void navigateToUrl() {
        driver.get(environmentProperties.get("framework.frontEndUrl.google-search"));
    }

    public void enterText(String text) {
        String locatorType = searchBox.split("\\|\\|")[0];
        String locatorValue = searchBox.split("\\|\\|")[1];
        WebElement webElement = driver.findElement(By.name(locatorValue));
        webElement.sendKeys(text);
    }

    @SneakyThrows
    public void clickSearchButton() {
        String locatorType = searchButton.split("\\|\\|")[0];
        String locatorValue = searchButton.split("\\|\\|")[1];
        WebElement webElement = driver.findElement(By.name(locatorValue));
        webElement.click();
        Thread.sleep(1000);
    }

}