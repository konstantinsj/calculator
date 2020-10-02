package net.calculator.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends TestBase {

    //locators
    private String pageUrl = "https://www.calculator.net";
    private By result = By.id("sciOutPut");
    private By history = By.id("scihistory");

    public void homePage() {
        driver.get(pageUrl);
    }

    public String getResult() {
        return driver.findElement(result).getAttribute("innerText").replace('\u00A0',' ').trim();
    }

    public String getHistory() {
        return driver.findElement(history).getAttribute("innerText").replace('\u00A0',' ').trim();
    }

    public void input(String inputData) {
        new Actions(driver).sendKeys(inputData).perform();
    }

    public void pressKey(String key) {
        driver.findElement(By.xpath("//span[@onclick=\"r(" +key +")\"]")).click();
    }
}