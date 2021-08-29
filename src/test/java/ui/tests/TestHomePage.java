package ui.tests;

import globals.Globals;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import tools.DelayManager;
import tools.WebPageHelper;
import ui.pages.Home;

import java.util.concurrent.TimeUnit;

import static globals.Globals.webDriver;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestHomePage {
    @Test
    public void testHomePage() {
        //TODO: Report bug links now working
        WebElement userNameInput =
                WebPageHelper.getWebElement(webDriver, Home.userNameInput);
        WebElement pwdInput =
                WebPageHelper.getWebElement(webDriver, Home.pwdInput);
        WebElement btnLogin =
                WebPageHelper.getWebElement(webDriver, Home.btnLogin);

        userNameInput.sendKeys(Globals.faker.name().firstName());
        DelayManager.sleep(1000);
        webDriver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);

        pwdInput.sendKeys(Globals.faker.harryPotter().house());
        DelayManager.sleep(1000);
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        btnLogin.click();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        DelayManager.sleep(1000);
    }

    @Test
    public void testFacebookLink() {
        WebElement facebookLogin =
                WebPageHelper.getWebElement(webDriver, Home.facebookLogin);
        facebookLogin.click();
        DelayManager.sleep(10000);
        assertThat("The facebook link should redirect successfully",
                Globals.webDriver.getCurrentUrl().equals("https://www.facebook.com/"));
    }

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
