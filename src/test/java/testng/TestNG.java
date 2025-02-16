package testng;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import example.StaticProvider;

public class TestNG {

    String name = "AfterOffice";

    @BeforeClass
    public void setUpClass() {
        System.out.println("Ini untuk setup class");

        // scenario login
        // set API, set credential
        // set URL website
    }

    @Test
    public void checkoutBarang() {
        // checkout barang

        /*
         * login
         * checkout
         */
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("Before Method");
    }

    @Test
    public void scenarioTest1() {
        Assert.assertEquals(name, "AfterOffice");
        System.out.println("scenario 1");
    }

    @Test
    public void scenarioTest2() {
        Assert.assertEquals(name, "AfterOffice");
        System.out.println("scenario 2");
    }

    @Test
    public void scenarioTest3() {
        Assert.assertEquals(name, "AfterOffice");
        System.out.println("scenario 3");
    }

    @Test(dataProvider = "dataproviderPositive", dataProviderClass = StaticProvider.class)
    public void dataTestScenario(String name, int age) {
        System.out.println("Nama : " + name + " Umur : " + age);
    }

    @AfterMethod
    public void afterUp() {
        System.out.println("After Method");
    }

    @AfterClass
    public void setUpAfterClass() {
        System.out.println("Ini adalah setup after class");
    }

    @DataProvider(name = "dataprovider")
    public Object[][] dataTest() {
        return new Object[][] {
                { "Rudy", 10 },
                { "Sari", 20 },
                { "Joko", 25 }
        };
    }
}