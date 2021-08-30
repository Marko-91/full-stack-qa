package ui.tests;

import backend.Login;
import globals.Globals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import tools.DelayManager;
import tools.WebPageHelper;
import ui.pages.Home;

import java.util.logging.Logger;

import static globals.Globals.webDriver;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestHomePage {
    private static final Logger log = Logger.getLogger(Login.class.getName());

    /**
     * This test validates home page login.
     */
    @Test
    public void testHomePage() {
        WebElement userNameInput =
                WebPageHelper.getWebElement(webDriver, Home.userNameInput);
        WebElement pwdInput =
                WebPageHelper.getWebElement(webDriver, Home.pwdInput);
        WebElement btnLogin =
                WebPageHelper.getWebElement(webDriver, Home.btnLogin);

        userNameInput.sendKeys(Globals.faker.name().firstName());
        DelayManager.sleep(1000);

        pwdInput.sendKeys(Globals.faker.harryPotter().house());
        DelayManager.sleep(1000);

        btnLogin.click();
        DelayManager.sleep(1000);

        log.info("Assert that user is redirected to dashboard");
        WebDriverWait wait = new WebDriverWait(webDriver,30);
        WebElement homeElement = webDriver.findElement(By.xpath("//a[contains(text(),'HOME')]"));
        Boolean home =
                wait.until(ExpectedConditions.textToBePresentInElement(homeElement,
                        "HOME"));
        assertThat("User dashboard should contain home", home);
    }

    /**
     * This test validates facebook link.
     */
    @Test
    public void testFacebookLink() {
        WebElement facebookLogin =
                WebPageHelper.getWebElement(webDriver, Home.facebookLogin);
        facebookLogin.click();
        DelayManager.sleep(10000);
        assertThat("The facebook link should redirect successfully",
                Globals.webDriver.getCurrentUrl().equals("https://www.facebook.com/"));
    }

    /**
     * This test validates twitter link.
     */
    @Test
    public void testTwitterLink() {
        WebElement facebookLogin =
                WebPageHelper.getWebElement(webDriver, Home.twitterLogin);
        facebookLogin.click();
        DelayManager.sleep(10000);
        assertThat("The facebook link should redirect successfully",
                Globals.webDriver.getCurrentUrl().equals("https://twitter.com/"));
    }
}
