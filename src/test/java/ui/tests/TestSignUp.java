package ui.tests;

import globals.Globals;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import tools.DelayManager;
import tools.WebPageHelper;
import ui.pages.SignUp;

import static globals.Globals.webDriver;
import static tools.EmailHelper.getRandomEmail;

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
        inputFirstName.sendKeys(firstName);
        inputFirstName2.sendKeys(firstName);
        inputLastName.sendKeys(Globals.faker.name().lastName());
        inputEmail.sendKeys(getRandomEmail(firstName));
        inputPwd.sendKeys(Globals.faker.harryPotter().quote());
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
}
