package ui.tests;

import backend.Login;
import globals.Globals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import tools.DelayManager;
import tools.WebPageHelper;
import ui.pages.Home;
import ui.pages.SignUp;

import java.util.logging.Logger;

import static globals.Globals.webDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static tools.UserHelper.getRandomEmail;

public class TestSignUp {
    private static final Logger log = Logger.getLogger(Login.class.getName());

    /**
     * This tests validates sign up end-to-end.
     *
     * Test steps:
     * 1. Get sign up page
     * 2. Input sign up parameters
     * 3. Assert that user is created
     * 4. Assert that welcome message is present
     */
    @Test
    public void testSignUpE2E() {
        log.info("Get sign up page");
        Globals.webDriver.get(Globals.endpoint + "signup");
        DelayManager.sleep(5000);

        WebElement inputFirstName =
                    WebPageHelper.getWebElement(webDriver, SignUp.inputFirstName);
        WebElement inputFirstName2 =
                WebPageHelper.getWebElement(webDriver, SignUp.inputFirstName2);
        WebElement inputLastName =
                WebPageHelper.getWebElement(webDriver, SignUp.inputLastName);
        WebElement inputEmail =
                WebPageHelper.getWebElement(webDriver, SignUp.inputEmail);
        WebElement inputPwd =
                WebPageHelper.getWebElement(webDriver, SignUp.inputPwd);
        WebElement inputMobile =
                WebPageHelper.getWebElement(webDriver, SignUp.inputMobile);
        WebElement buttonSignIn =
                WebPageHelper.getWebElement(webDriver, SignUp.buttonSignIn);


        DelayManager.sleep(5000);

        log.info("Input sign up parameters");
        String firstName = Globals.faker.name().firstName();
        inputFirstName.sendKeys(Globals.faker.name().username());
        inputFirstName2.sendKeys(firstName);
        inputLastName.sendKeys(Globals.faker.name().lastName());
        inputEmail.sendKeys(getRandomEmail(firstName));
        inputPwd.sendKeys(Globals.faker.name().username());
        inputMobile.sendKeys(Globals.faker.phoneNumber().cellPhone());
        buttonSignIn.click();

        log.info("Assert that user is created");
        WebDriverWait wait = new WebDriverWait(webDriver,30);
        WebElement welcomeMessage =
                WebPageHelper.getWebElementByTag(webDriver, "h3");
        Boolean isWelcomeMessagePresent =
                wait.until(ExpectedConditions.textToBePresentInElement(welcomeMessage,
                        "User has been successfully registered."));

        log.info("Assert that welcome message is present");
        Assert.assertTrue(isWelcomeMessagePresent);
    }

    /**
     * This tests validates log in end-to-end.
     *
     * Test steps:
     * 1. Get sign up page
     * 2. Input sign up parameters
     * 3. Assert that user is created
     * 4. Assert that welcome message is present
     * 5. Assert that user is redirected to dashboard
     */
    @Test
    public void testLoginE2E() {
        log.info("Get sign up page");
        Globals.webDriver.get(Globals.endpoint + "signup");
        DelayManager.sleep(5000);

        WebElement inputFirstName =
                WebPageHelper.getWebElement(webDriver, SignUp.inputFirstName);
        WebElement inputFirstName2 =
                WebPageHelper.getWebElement(webDriver, SignUp.inputFirstName2);
        WebElement inputLastName =
                WebPageHelper.getWebElement(webDriver, SignUp.inputLastName);
        WebElement inputEmail =
                WebPageHelper.getWebElement(webDriver, SignUp.inputEmail);
        WebElement inputPwd =
                WebPageHelper.getWebElement(webDriver, SignUp.inputPwd);
        WebElement inputMobile =
                WebPageHelper.getWebElement(webDriver, SignUp.inputMobile);
        WebElement buttonSignIn =
                WebPageHelper.getWebElement(webDriver, SignUp.buttonSignIn);


        DelayManager.sleep(5000);

        log.info("Input sign up parameters");
        String firstName = Globals.faker.name().firstName();
        inputFirstName.sendKeys(Globals.faker.name().username());
        inputFirstName2.sendKeys(firstName);
        inputLastName.sendKeys(Globals.faker.name().lastName());
        inputEmail.sendKeys(getRandomEmail(firstName));
        String userName = Globals.faker.name().username();
        String pwd = Globals.faker.name().username();
        inputPwd.sendKeys(Globals.faker.name().username());
        inputMobile.sendKeys(Globals.faker.phoneNumber().cellPhone());
        buttonSignIn.click();

        log.info("Assert that user is created");
        WebDriverWait wait = new WebDriverWait(webDriver,30);
        WebElement welcomeMessage =
                WebPageHelper.getWebElementByTag(webDriver, "h3");
        Boolean isWelcomeMessagePresent =
                wait.until(ExpectedConditions.textToBePresentInElement(welcomeMessage,
                        "User has been successfully registered."));

        log.info("Assert that welcome message is present");
        Assert.assertTrue(isWelcomeMessagePresent);
        Globals.webDriver.get(Globals.endpoint + "login");
        WebElement userNameLogin =
                WebPageHelper.getWebElement(webDriver, SignUp.loginUserName);
        WebElement userNamePwd =
                WebPageHelper.getWebElement(webDriver, SignUp.loginPwd);

        userNameLogin.sendKeys(userName);
        userNamePwd.sendKeys(pwd);
        WebElement loginLoginBtn = WebPageHelper.getWebElement(webDriver, Home.loginLoginBtw);
        loginLoginBtn.click();
        DelayManager.sleep(5000);

        log.info("Assert that user is redirected to dashboard");
        WebElement homeElement = webDriver.findElement(By.xpath("//a[contains(text(),'HOME')]"));
        Boolean home =
                wait.until(ExpectedConditions.textToBePresentInElement(homeElement,
                        "HOME"));
        assertThat("User dashboard should contain home", home);
    }
}
