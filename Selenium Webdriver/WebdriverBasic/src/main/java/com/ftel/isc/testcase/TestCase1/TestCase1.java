package com.ftel.isc.testcase.TestCase1;

import com.ftel.isc.page.TestCase_Page;
import com.ftel.isc.by.ByTestCase_1;
import com.ftel.isc.testcase.TestCase2.TestCase2;
import com.ftel.isc.utils.Utils;
import com.ftel.isc.utils.Language;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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

import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class TestCase1 extends JFrame {
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
    public void actionTest() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"box_internet\"]/div[1]/div/div[1]/div/div[10]/button")).click();
        TestCase_Page page = new TestCase_Page(driver);
        if (Utils.waitForElementDisplay(driver, ByTestCase_1.byFullName, 200, "1")) {

            page.typeFullName("");
            page.typePhoneNumber("");
            page.typeEmail("");
            page.typeDistrict();
            Thread.sleep(3000);

            page.typeByWardList();
            Thread.sleep(3000);

            page.typeTreet();
            Thread.sleep(3000);

            page.typeHouseNumber("123");
            Thread.sleep(3000);

            page.typeSubmitForm();
            Thread.sleep(6000);

            String textErrorPhone = driver.findElement(ByTestCase_1.byErrorPhone).getText();
            String textErrorName = driver.findElement(ByTestCase_1.byErrorName).getText();
            if (textErrorPhone.equals("Vui lòng nhập Số điện thoại.") && textErrorName.equals("Vui lòng nhập Họ và tên.")) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }

            Thread.sleep(3000);
        }
    }

    @DataProvider(name = "dataProvider")
    public Object[][] getData() {
        return new Object[][]{
                {"FPT Telecom | Đăng Ký Online"}
        };
    }

    @AfterTest
    public static void afterClass() {
        driver.quit();
    }

}
