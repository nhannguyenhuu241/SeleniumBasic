package com.ftel.isc.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Utils {

    public static boolean waitForElementDisplay(WebDriver driver, By by,
                                                int waitInSecond, String message) {
        for (int i = 0; i < waitInSecond / 2 + 1; i++) {
            try {
                if (driver.findElement(by).isDisplayed()) {
                    return true;
                }
                Thread.sleep(2 * 1000);
            } catch (Exception e) {
                System.out.print(".");
            }
        }
        return false;
    }

}
