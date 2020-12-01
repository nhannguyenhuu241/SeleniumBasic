package com.ftel.isc.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCase_Page {

    WebDriver driver;

    By byFullName = By.id("fullname");
    By byPhoneNumber = By.id("phonenumber");
    By byEmail = By.name("email");
    By byDistrict = By.xpath("//*[@id=\"districtListDesktop\"]/div[1]/a");
    By byWardList = By.xpath("//*[@id=\"wardListDesktop\"]/div[1]/a");
    By byTreet = By.xpath("//*[@id=\"streetListDesktop\"]/div[1]/a");
    By byHouseNumber = By.id("housenumber");
    By bySubmitForm = By.id("submitForm");
    By byBirtDay = By.id("birthday");
    By byPassport = By.name("passport");
    By byRadio6Month = By.xpath("//*[@id=\"form-complete\"]/div/div[1]/div/div[11]/div[2]/div/label");
    By byPaymentCate = By.xpath("//*[@id=\"form-complete\"]/div/div[2]/div/div[17]/div/div/label");
    By byPayment = By.id("Complete-submitForm");
    By textErrorPhone = By.xpath("//span[@class='message phonenumber']");
    By textErrorCMND = By.xpath("//span[@class='message passport']");


    public TestCase_Page(WebDriver driver) {
        this.driver = driver;
    }

    public void typeFullName(String name) {
        driver.findElement(byFullName).sendKeys(name);
    }

    public void typeFullNameClaer() {
        driver.findElement(byFullName).clear();
    }

    public void typePhoneNumber(String phone) {
        driver.findElement(byPhoneNumber).sendKeys(phone);
    }

    public void typePhoneNumberClear() {
        driver.findElement(byPhoneNumber).clear();
    }

    public void typeEmail(String email) {
        driver.findElement(byEmail).sendKeys(email);
    }

    public void typeEmailClear() {
        driver.findElement(byEmail).clear();
    }

    public void typeDistrict() {
        driver.findElement(byDistrict).click();
    }

    public void typeByWardList() {
        driver.findElement(byWardList).click();
    }

    public void typeTreet() {
        driver.findElement(byTreet).click();
    }

    public void typeHouseNumber(String value) {
        driver.findElement(byHouseNumber).sendKeys(value);
    }

    public void typeSubmitForm() {
        driver.findElement(bySubmitForm).click();
    }

    public void typeBirtDay(String value) {
        driver.findElement(byBirtDay).sendKeys(value);
    }

    public void typeBirtDayClear() {
        driver.findElement(byBirtDay).clear();
    }

    public void typePassport(String value) {
        driver.findElement(byPassport).sendKeys(value);
    }

    public void typePassportClear() {
        driver.findElement(byPassport).clear();
    }


    public void typeRadio6Month() {
        driver.findElement(byRadio6Month).click();
    }

    public void typePaymentCate() {
        driver.findElement(byPaymentCate).click();
    }

    public void typePayment() {
        driver.findElement(byPayment).click();
    }

    public String getErrorPhone(){
        return driver.findElement(textErrorPhone).getText();
    }

    public boolean typeStatusErrorPhone(){
        return driver.findElement(textErrorPhone).isDisplayed();
    }

    public String typeErrorCMND(){
        return driver.findElement(textErrorCMND).getText();
    }

    public boolean typeStatusErrorCMND(){
        return driver.findElement(textErrorCMND).isDisplayed();
    }


}
