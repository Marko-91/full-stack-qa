package globals;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.awt.AWTException;
import java.awt.Robot;

public class Globals {
    public static WebDriver webDriver;
    public static String driver = "webdriver.chrome.driver";
    public static String PATH = "C:\\Users\\marko.stankovic\\personal-projects\\drivers\\chrome\\chromedriver.exe";
    public static String endpoint = "http://localhost:8080/";
    public static Actions action;
    public static Robot robot;
    public static Faker faker;

    @BeforeSuite
    public void init() throws AWTException {
        System.out.println("init scripts");
        webDriver = new ChromeDriver();
        System.setProperty(driver, PATH);
        action = new Actions(Globals.webDriver);
        robot = new Robot();
        faker = new Faker();
        Globals.webDriver.get(Globals.endpoint);
        Globals.webDriver.manage().window().maximize();
    }

    @AfterSuite
    public void close() {
        System.out.println("close web driver");
        Globals.webDriver.close();
    }
}
