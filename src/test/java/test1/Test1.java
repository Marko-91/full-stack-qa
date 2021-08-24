package test1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marko.stankovic\\personal-projects\\drivers\\chrome\\chromedriver.exe");
        WebDriver wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.get("https://www.google.com/");
        Thread.sleep(1000);
        wd.quit();
    }
}
