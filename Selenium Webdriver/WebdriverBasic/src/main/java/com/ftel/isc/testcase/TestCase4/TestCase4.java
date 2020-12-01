package com.ftel.isc.testcase.TestCase4;

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
import java.lang.reflect.Method;

public class TestCase4 {

    private static WebDriver driver;
    final int TIME_OUT = 3000;

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


    @AfterTest
    public static void afterClass() {
        driver.quit();
    }

    @Test(priority = 1, dataProvider = "dataProvider")
    public void testTittle(String tittle) throws InterruptedException {
        Thread.sleep(TIME_OUT);
        String actualTitle = driver.getTitle();
        if (tittle.contentEquals(actualTitle)) {
            Assert.assertFalse(false);
        } else Assert.fail("Sai tiêu đề");
        driver.findElement(By.xpath("//*[@id=\"box_internet\"]/div[1]/div/div[1]/div/div[10]/button")).click();
    }

    @Test(priority = 2)
    public void addAddress() throws InterruptedException {
        Thread.sleep(3000);
        TestCase_Page page = new TestCase_Page(driver);
        page.typeDistrict();
        Thread.sleep(TIME_OUT);

        page.typeByWardList();
        Thread.sleep(TIME_OUT);

        page.typeTreet();
        Thread.sleep(TIME_OUT);

        page.typeHouseNumber("123");

    }

    @Test(priority = 3, dataProvider = "dataProvider")
    public void actionTestScreen1(String name, String phone, String email) throws InterruptedException {
        Thread.sleep(3000);
        //driver.findElement(By.xpath("//*[@id=\"box_internet\"]/div[1]/div/div[1]/div/div[10]/button")).click();
        TestCase_Page page = new TestCase_Page(driver);
        if (Utils.waitForElementDisplay(driver, ByTestCase_1.byFullName, 200, "1")) {
            page.typeFullName(name);
            page.typePhoneNumber(phone);
            page.typeEmail(email);
            Thread.sleep(TIME_OUT);

            page.typeSubmitForm();

            Thread.sleep(TIME_OUT);

            page.typeFullNameClaer();
            page.typePhoneNumberClear();
            page.typeEmailClear();

            Thread.sleep(TIME_OUT);
            if (Utils.waitForElementDisplay(driver, ByTestCase_1.textErrorPhone, 100, "trang 3")) {
                if (page.getErrorPhone().equals("Số điện thoại hợp lệ phải có 10 số.")) {
                    Assert.assertTrue(true, "Số điện thoại thấp hoặc hơn 10 số");
                } else if (page.getErrorPhone().equals("Nhập sai số điện thoại, xin Quý khách vui lòng nhập lại. Số điện thoại hợp lệ phải có 10 số.")) {
                    Assert.assertTrue(true, "Sai định dạng số điện thoại");
                } else {
                    Assert.fail("Lỗi không xác định");
                }
            } else {
                Assert.assertTrue(true);
            }
        }
    }

    @Test(priority = 4, dataProvider = "dataProvider")
    public void actionTestScreen2(String passport) throws InterruptedException {
        Thread.sleep(10000);
        TestCase_Page page = new TestCase_Page(driver);
        if (Utils.waitForElementDisplay(driver, ByTestCase_1.byBirtDay, 500, "trang 3")) {
            page.typeBirtDay("26/10/1997");
            Thread.sleep(TIME_OUT);
            page.typePassport(passport);
            Thread.sleep(TIME_OUT);
            // page.typeRadio6Month();
            page.typePaymentCate();
            Thread.sleep(TIME_OUT);
            page.typePayment();
            Thread.sleep(TIME_OUT);
            page.typePassportClear();
            page.typeBirtDayClear();
            if (page.typeStatusErrorCMND()) {
                if (page.typeErrorCMND().equals("CMND từ 9 - 12 số")) {
                    Assert.assertFalse(false, "Sai định dạng");
                } else {
                    Assert.assertFalse(false, "Sai cấu trúc có ký tự đặc biệt");
                }
            } else {
                Assert.assertTrue(true, "Đúng định dạng");
            }
        }
    }

    @DataProvider(name = "dataProvider")
    public Object[][] getData(Method m) {
        if (m.getName().equalsIgnoreCase("testTittle")) {
            return new Object[][]{
                    {"FPT Telecom | Đăng Ký Online"},
                    {"Error"}
            };
        } else if (m.getName().equalsIgnoreCase("actionTestScreen1")) {
            return new Object[][]{
                    {"Nguyennhan", "09240027000", "nhan@gmail.com"},
                    {"Nguyennhan", "092400128@", "nguyennhan@gmail.com"},
                    {"Nguyennhan", "09240020", "nhan@gmail.com"}
            };
        } else {
            return new Object[][]{
                    {"3418188645444333"},
                    {"12312332@"},
                    {"341818"}
            };
        }
    }

}
