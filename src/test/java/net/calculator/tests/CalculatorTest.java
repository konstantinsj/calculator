package net.calculator.tests;

import net.calculator.helpers.HomePage;
import net.calculator.helpers.TestBase;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest extends TestBase {

    HomePage home = new HomePage();

    @Test
    public void testMultiplicationTable() {

        // checking 10x10 multiplication table
        home.homePage();
        for (int i = 1; i <= 10; i++) {
            for (int b = 1; b <= 10; b++) {

                home.input(i + "*" + b);
                Assert.assertEquals(i * b, Integer.parseInt(home.getResult()));
                home.pressKey("'C'");
            }
        }
    }

    @Test
    public void testNegativeAndHistory() {

        //multiplication of negative number
        home.homePage();
        home.input("-5 * 3 =");
        Assert.assertEquals(-15, Integer.parseInt(home.getResult()));

        //subtraction of negative number
        home.pressKey("'C'");
        home.input("3 - 5");
        Assert.assertEquals(-2, Integer.parseInt(home.getResult()));

        //checking history
        Assert.assertEquals("-5 × 3 = -15", home.getHistory());
    }

    @Test
    public void testMemory() {

        //writing result in memory, reading it and checking MR/MC label
        home.homePage();
        home.input("33 * 3 =");
        home.pressKey("'M+'");
        home.pressKey("'C'");
        Assert.assertEquals("0", home.getResult());
        Assert.assertEquals("MR", home.readMRClabel());

        home.pressKey("'MR'");
        Assert.assertEquals("99", home.getResult());
        Assert.assertEquals("MC", home.readMRClabel());
    }

    @Test
    public void testRandomDivideBy0() {

        // generating random number using calculator and diving in to 0
        home.homePage();
        home.pressKey("'RND'");

        //checking that random number don't give any error by itself before diving
        Assert.assertNotEquals("Error", home.getResult());

        home.pressKey("'/'");
        home.pressKey("0");
        Assert.assertEquals("Error", home.getResult());
    }
}