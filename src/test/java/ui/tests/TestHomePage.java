package ui.tests;

import globals.Globals;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import tools.DelayManager;
import tools.WebPageHelper;
import ui.pages.Home;

import java.util.concurrent.TimeUnit;

import static globals.Globals.webDriver;

public class TestHomePage {
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
        webDriver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);

        pwdInput.sendKeys(Globals.faker.harryPotter().house());
        DelayManager.sleep(1000);
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        btnLogin.click();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        DelayManager.sleep(1000);
    }
}
