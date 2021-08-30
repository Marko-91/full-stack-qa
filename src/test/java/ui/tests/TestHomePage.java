package ui.tests;

import backend.Login;
import com.github.javafaker.Faker;
import globals.Globals;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tools.DelayManager;
import tools.WebPageHelper;
import ui.pages.Home;
import ui.pages.SignUp;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static globals.Globals.PATH;
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
        DelayManager.sleep(5000);

        log.info("Assert that user is redirected to dashboard");
        WebDriverWait wait = new WebDriverWait(webDriver,30);
        WebElement homeElement = WebPageHelper.getWebElement(webDriver, SignUp.welcome);
        assertThat("User dashboard should contain home", homeElement.getText().equals("Return to Login Form"));
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

    @BeforeMethod
    public void init() {
        System.out.println("init scripts");
        webDriver = new ChromeDriver();
        System.setProperty(Globals.driver, PATH);
        Globals.action = new Actions(Globals.webDriver);
        Globals.faker = new Faker();
        Globals.webDriver.get(Globals.endpoint);
        Globals.webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void close() {
        System.out.println("close web driver");
        Globals.webDriver.close();
    }
}
