package testng;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import example.StaticProvider;

public class ParallelTestNG2 {

    String name = "AfterOffice";

    @Test
    public void scenarioTest1() {
        Assert.assertEquals(name, "AfterOffice");
        System.out.println("scenario 1");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest2() {
        Assert.assertEquals(name, "AfterOffice");
        System.out.println("scenario 2");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest3() {
        Assert.assertEquals(name, "AfterOffice");
        System.out.println("scenario 3");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest4() {
        Assert.assertEquals(name, "AfterOffice");
        System.out.println("scenario 2");
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void scenarioTest5() {
        Assert.assertEquals(name, "AfterOffice");
        System.out.println("scenario 3");
        System.out.println(Thread.currentThread().getId());
    }

    @Test(dataProvider = "dataproviderPositive", dataProviderClass = StaticProvider.class)
    public void dataTestScenario(String name, int age) {
        System.out.println("Nama : " + name + " Umur : " + age);
    }
}