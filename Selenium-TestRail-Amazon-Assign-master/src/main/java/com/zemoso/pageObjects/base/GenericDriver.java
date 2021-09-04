package com.zemoso.pageObjects.base;

import org.openqa.selenium.WebDriver;

public class GenericDriver {
    protected WebDriver driver;

    public GenericDriver(WebDriver driver) {
        this.driver = driver;
    }
}
