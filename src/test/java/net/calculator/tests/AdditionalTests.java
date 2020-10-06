package net.calculator.tests;

import net.calculator.helpers.HomePage;
import net.calculator.helpers.TestBase;
import org.junit.Assert;
import org.junit.Test;

public class AdditionalTests extends TestBase {

    HomePage home = new HomePage();

    @Test
    public void testLongValues() {

        // we using keyboard input and checking long value
        home.homePage();
        home.input("2222222222222 * 33333333 =");
        Assert.assertEquals("74074073333325920000", home.getResult());
    }

    @Test
    public void testRandomArithmetic() {

        // generating 5 random numbers and performing arithmetic operations with them
        home.homePage();
        double[] random = genRandom();
        home.input(random[0] + "/" + random[1] + "+" + random[2] + "*" + random[3] + "-" + random[4]);
        double expectedCalc = (random[0] / random[1] + random[2] * random[3] - random[4]);
        Assert.assertEquals(truncateExpected(expectedCalc), Double.parseDouble(home.getResult()), 0.001);
    }

    @Test
    public void testBracketsAndBackButton() {

        //testing that operation in brackets performs before multiplication
        home.homePage();
        home.input("(2 + 2) × 2");
        Assert.assertEquals(8, Integer.parseInt(home.getResult()));

        //pressing Back button twice to delete last two characters
        home.pressKey("'bk'");
        home.pressKey("'bk'");
        Assert.assertEquals(4, Integer.parseInt(home.getResult()));
    }

    @Test
    public void testDecimal() {

        // we using buttons to input and checking long decimals
        home.homePage();
        home.pressKey("1");
        home.pressKey("'/'");
        home.pressKey("3");

        Assert.assertEquals((truncateExpected(1.0 / 3.0)), Double.parseDouble(home.getResult()), 0.0000000001);
    }
}
