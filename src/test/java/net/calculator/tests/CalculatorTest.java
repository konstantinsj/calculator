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

        Assert.assertEquals("Expected",(truncateExpected(1.0/3.0)),home.getResult(), 0.0000000001);

    }

    //we need to truncate all expected results same way as calculator does it
    public static double truncateExpected(double value) {
        double scale = Math.pow(10, 10);
        return Math.round(value * scale) / scale;
    }
}