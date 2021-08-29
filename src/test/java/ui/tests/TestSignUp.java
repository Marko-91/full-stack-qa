package ui.tests;

import globals.Globals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tools.DelayManager;
import tools.WebPageHelper;
import ui.pages.Home;
import ui.pages.SignUp;

import static globals.Globals.webDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static tools.UserHelper.getRandomEmail;

public class TestSignUp {

    @Test
    public void testSignUpE2E() {
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

        String firstName = Globals.faker.name().firstName();
        inputFirstName.sendKeys(Globals.faker.name().username());
        inputFirstName2.sendKeys(firstName);
        inputLastName.sendKeys(Globals.faker.name().lastName());
        inputEmail.sendKeys(getRandomEmail(firstName));
        inputPwd.sendKeys(Globals.faker.name().username());
        inputMobile.sendKeys(Globals.faker.phoneNumber().cellPhone());
        buttonSignIn.click();

        WebDriverWait wait = new WebDriverWait(webDriver,30);
        WebElement welcomeMessage =
                WebPageHelper.getWebElementByTag(webDriver, "h3");
        Boolean isWelcomeMessagePresent =
                wait.until(ExpectedConditions.textToBePresentInElement(welcomeMessage,
                        "User has been successfully registered."));

        Reporter.log("Asserting...");
        Assert.assertTrue(isWelcomeMessagePresent);
    }

    @Test
    public void testLoginE2E() {
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

        WebDriverWait wait = new WebDriverWait(webDriver,30);
        WebElement welcomeMessage =
                WebPageHelper.getWebElementByTag(webDriver, "h3");
        Boolean isWelcomeMessagePresent =
                wait.until(ExpectedConditions.textToBePresentInElement(welcomeMessage,
                        "User has been successfully registered."));

        Reporter.log("Asserting...");
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

        WebElement home = webDriver.findElement(By.xpath("//a[contains(text(),'HOME')]"));
        assertThat("User dashboard should contain home", home.getText().equals("HOME"));
    }
}
