package com.ftel.isc.testcase.TestCase2;

import com.ftel.isc.by.ByTestCase_1;
import com.ftel.isc.page.TestCase_Page;
import com.ftel.isc.utils.Language;
import com.ftel.isc.utils.Utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestCase2 {
    //private final JTextArea textArea;
    // private final PrintStream standardOut;
    private static WebDriver driver;
    final int TIME_OUT = 20;

    @BeforeTest
    public static void beforeClass() throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/nhanne/Downloads/ToolTest/src/main/java/com/ftel/isc/driver/chromedriver");
        driver = new ChromeDriver();
        //System.setProperty("webdriver.gecko.driver", "/Users/nhanne/Desktop/ToolTest/src/main/java/com/ftel/isc/geckodriver");
        //driver = new FirefoxDriver();
        driver.get(Language.BASE_URL);
        driver.manage().window().maximize();

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("Users/nhanne/Desktop/screenshot.png"));
    }

    @Test(priority = 0, dataProvider = "dataProvider")
    public void testTittle(String tittle) throws InterruptedException {
        Thread.sleep(3000);
        String actualTitle = driver.getTitle();
        if (tittle.contentEquals(actualTitle)) {
            Assert.assertTrue(true);
        } else Assert.fail();
    }

    @Test(priority = 1)
    public void actionTestClick() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"box_internet\"]/div[1]/div/div[1]/div/div[10]/button")).click();
    }

    @Test(priority = 2)
    public void actionTestInfo() throws InterruptedException {

        TestCase_Page page = new TestCase_Page(driver);
        if (Utils.waitForElementDisplay(driver, ByTestCase_1.byFullName, 200, "1")) {
            page.typeDistrict();
            Thread.sleep(3000);

            page.typeByWardList();
            Thread.sleep(3000);

            page.typeTreet();
            Thread.sleep(3000);

            page.typeHouseNumber("123");

            Thread.sleep(6000);
        }
    }

    @Test(priority = 3, dataProvider = "dataMessage")
    public void setSubmitFrom(String name, String phone, String email) throws InterruptedException {
        Thread.sleep(3000);
        TestCase_Page page = new TestCase_Page(driver);
        try {
            page.typeFullName(name);
            page.typePhoneNumber(phone);
            page.typeEmail(email);
            page.typeSubmitForm();

            String textErrorPhone = driver.findElement(ByTestCase_1.byErrorPhone).getText();
            if (!textErrorPhone.equals("Số điện thoại hợp lệ phải có 10 số.")) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }

            Thread.sleep(6000);
            driver.findElement(ByTestCase_1.byFullName).sendKeys("");
            driver.findElement(ByTestCase_1.byPhoneNumber).sendKeys("");
            driver.findElement(ByTestCase_1.byEmail).sendKeys("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DataProvider(name = "dataProvider")
    public Object[][] getData() {
        return new Object[][]{
                {"FPT Telecom | Đăng Ký Online"},
                {"Error"}
        };
    }

    @DataProvider(name = "dataMessage")
    public Object[][] getData1() {
        return new Object[][]{
                {"NguyenNhan", "092400270", "nhan@gmail.com"},
        };
    }

    @AfterTest
    public static void afterClass() {
        driver.quit();
    }
}
