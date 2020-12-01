package com.ftel.isc.testcase.TestCase3;

import com.ftel.isc.by.ByTestCase_1;
import com.ftel.isc.page.TestCase_Page;
import com.ftel.isc.utils.Language;
import com.ftel.isc.utils.Utils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestCase3 {
    private static WebDriver driver;
    final int TIME_OUT = 20;


    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\PC\\Desktop\\ToolTest\\src\\main\\java\\com\\ftel\\isc\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        java.util.Set<String> handes = driver.getWindowHandles();
        String winHanddel = driver.getWindowHandle();
        handes.remove(winHanddel);
        //System.setProperty("webdriver.gecko.driver", "/Users/nhanne/Desktop/ToolTest/src/main/java/com/ftel/isc/geckodriver");
        //driver = new FirefoxDriver();
        driver.get(Language.BASE_URL);
        driver.manage().window().maximize();
        String actualTitle = driver.getTitle();

        String expectedTitle = "FPT Telecom | Đăng Ký Online";
        if (expectedTitle.contentEquals(actualTitle)) {
            System.out.println("Title Passed !");
        } else System.out.println("Title Failed");
    }

    @Test
    public void actionTest() {
        driver.findElement(By.xpath("//*[@id=\"box_internet\"]/div[1]/div/div[1]/div/div[10]/button")).click();
        TestCase_Page page = new TestCase_Page(driver);
        if (Utils.waitForElementDisplay(driver, ByTestCase_1.byFullName, 200, "1")) {

            page.typeFullName("");
            page.typePhoneNumber("");
            page.typeEmail("nguyennhan.it2412@gmail.com");
            page.typeDistrict();
            waitForTimeOut();
            ByWard(page);
        }
    }

    private void ByWard(TestCase_Page page) {
        if (Utils.waitForElementDisplay(driver, ByTestCase_1.byWardList, 200, "2")) {
            // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            page.typeByWardList();
            waitForTimeOut();
            ByTreet(page);
        }
    }

    private void ByTreet(TestCase_Page page) {
        if (Utils.waitForElementDisplay(driver, ByTestCase_1.byTreet, 200, "3")) {
            // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            page.typeTreet();
            page.typeHouseNumber("123");
            page.typeSubmitForm();
            waitForTimeOut();
            ByMessage(page);
        }
    }

    private void ByMessage(TestCase_Page page) {
        if (Utils.waitForElementDisplay(driver, ByTestCase_1.byErrorPhone, 100, "4")) {
            String textErrorPhone = driver.findElement(ByTestCase_1.byErrorPhone).getText();
            String textErrorName = driver.findElement(ByTestCase_1.byErrorName).getText();
            if (textErrorPhone.equals("Vui lòng nhập Số điện thoại.") && textErrorName.equals("Vui lòng nhập Họ và tên.")) {
                System.out.println("\nTest Case Passed");
            } else {
                System.out.println("\nTest Case Failed");
            }
        }
    }

    public void waitForTimeOut() {
        driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
    }


    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
