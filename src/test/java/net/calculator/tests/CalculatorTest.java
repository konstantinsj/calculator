package net.calculator.tests;

import net.calculator.helpers.HomePage;
import net.calculator.helpers.TestBase;
import org.junit.*;

public class CalculatorTest extends TestBase {

    HomePage home = new HomePage();

    @Test
    public void testBasicTest() {

        String x= String.valueOf(3);

        home.homePage();
        home.pressKey(x);

        Assert.assertEquals("Expected",x,home.getResult());
    }

    @Test
    public void testDivide() {

        home.homePage();
        home.pressKey("1");
        home.pressKey("'/'");
        home.pressKey("3");

        Assert.assertEquals("Expected",(1/3),home.getResult());

    }
}