package com.ftel.isc.by;

import org.openqa.selenium.By;

public class ByTestCase_1 {
    public static By byFullName = By.id("fullname");
    public static By byPhoneNumber = By.id("phonenumber");
    public static By byEmail = By.name("email");
    public static By byDistrict = By.xpath("//*[@id=\"districtListDesktop\"]/div[1]/a");
    public static By byWardList = By.xpath("//*[@id=\"wardListDesktop\"]/div[1]/a");
    public static By byTreet = By.xpath("//*[@id=\"streetListDesktop\"]/div[1]/a");
    public static By byBirtDay = By.id("birthday");
    public static By byPassport = By.name("passport");
    public static By byRadio6Month = By.xpath("//*[@id=\"form-complete\"]/div/div[1]/div/div[11]/div[2]/div/label/input");
    public static By byPaymentCate = By.xpath("//*[@id=\"form-complete\"]/div/div[2]/div/div[17]/div/div/label");
    public static By byPayment = By.id("Complete-submitForm");

    //message error input phone number
    public static By byErrorPhone = By.xpath("//*[@id=\"form-check\"]/section/div[8]/div/span");
    public static By byErrorName = By.xpath("//*[@id=\"form-check\"]/section/div[7]/div/span");
    public static By textErrorPhone = By.xpath("//span[@class='message phonenumber']");
    public static By textErrorCMND = By.xpath("//span[@class='message passport']");
}
