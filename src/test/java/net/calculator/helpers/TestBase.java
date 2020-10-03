package net.calculator.helpers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TestBase {
    static WebDriver driver;
    static String chromeDriverPath = "chromedriver.exe";

    WebDriverWait wait = new WebDriverWait(driver, 10);
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(driver);

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath );
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
//      options.addArguments("--headless");
//      driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() {driver.close();
    }

    //we need to truncate all expected results same way as calculator does it
    public static double truncateExpected(double value) {
        double scale = Math.pow(10, 10);
        return Math.round(value * scale) / scale;
    }

    // this method generates 5 random numbers
    public double[] genRandom() {
        double[] number = new double[5];
        int count = 0;

        while (count < number.length) {
            number[count++] = ThreadLocalRandom.current().nextInt(0, 9999);
        }
        return number;
    }
}