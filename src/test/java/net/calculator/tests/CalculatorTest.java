package net.calculator.tests;

import io.qameta.allure.Step;
import net.calculator.helpers.HomePage;
import net.calculator.helpers.TestBase;
import org.junit.*;

import java.util.concurrent.ThreadLocalRandom;

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

        // we using button to input here
        home.homePage();
        home.pressKey("1");
        home.pressKey("'/'");
        home.pressKey("3");

        Assert.assertEquals("Expected",(truncateExpected(1.0/3.0)),Double.parseDouble(home.getResult()), 0.0000000001);
    }

    @Test
    public void testLongValues() {

        home.homePage();
        home.input("2222222222222 * 33333333 =");
        Assert.assertEquals( "74074073333325920000",home.getResult());
    }

    @Test
    public void testRandomArithmetic() {

        home.homePage();
        double randomNum1 = ThreadLocalRandom.current().nextInt(0, 9999);
        double randomNum2 = ThreadLocalRandom.current().nextInt(0, 9999);
        double randomNum3 = ThreadLocalRandom.current().nextInt(0, 9999);
        double randomNum4 = ThreadLocalRandom.current().nextInt(0, 9999);
        double randomNum5 = ThreadLocalRandom.current().nextInt(0, 9999);

        home.input(randomNum1 + "/" +randomNum2 + "+" +randomNum3 + "*" +randomNum4 +"-" +randomNum5);

        double ExpectedCalc = (randomNum1/randomNum2+randomNum3*randomNum4-randomNum5);
        Assert.assertEquals(truncateExpected(ExpectedCalc), Double.parseDouble(home.getResult()), 0.000001);
    }
    @Test
    public void testBracketsAndBackButton() {

        home.homePage();
        home.input("(2 + 2) × 2");
        Assert.assertEquals(8, Integer.parseInt(home.getResult()));

        //pressing Back button twice to delete last two characters
        home.pressKey("'bk'");home.pressKey("'bk'");
        Assert.assertEquals(4, Integer.parseInt(home.getResult()));
    }
    @Test
    public void testRandomDivideBy0() {

        home.homePage();
        home.pressKey("'RND'");

        //checking that random number don't give any error
        Assert.assertNotEquals("Error", home.getResult());

        home.pressKey("'/'");
        home.pressKey("0");
        Assert.assertEquals("Error", home.getResult());
    }

    @Test
    public void testMultiplicationTable() {

        home.homePage();
        for (int i =1; i <= 10; i++){
            //cycles through the first number to multiply

            for (int b=1; b <=10; b++){
                //cycles through second number to multiply
                home.input(i+"*" +b);
                Assert.assertEquals( i*b, Integer.parseInt(home.getResult()));
                home.pressKey("'C'");
            }
        }
    }
}