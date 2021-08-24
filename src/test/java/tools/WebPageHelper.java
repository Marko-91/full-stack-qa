package tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebPageHelper {

    public static WebElement getWebElement(WebDriver webDriver, String xpath) {
        return webDriver.findElement(By.xpath(xpath));
    }

    public static WebElement getWebElementByTag(WebDriver webDriver, String tagName) {
        return webDriver.findElement(By.tagName(tagName));
    }

    public static void clickWebElement(WebDriver webDriver, String xpath) {
        getWebElement(webDriver, xpath)
                .click();
    }

    public static void hoverWebElement(WebDriver webDriver, Actions action, String xpath) {
        action.moveToElement(getWebElement(webDriver, xpath))
                .build()
                .perform();
    }
}
