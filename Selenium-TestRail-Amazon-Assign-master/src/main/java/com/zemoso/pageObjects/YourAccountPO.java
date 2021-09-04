package com.zemoso.pageObjects;

import com.zemoso.pageObjects.base.BasePagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourAccountPO extends BasePagePO {
    private By orders = By.cssSelector("div[data-card-identifier='YourOrders']");
    private By addresses = By.cssSelector("div[data-card-identifier='AddressesAnd1Click']");
    public YourAccountPO(WebDriver driver) {
        super(driver);
    }

    public YourOrdersPO navigateToOrders(){
        driver.findElement(orders).click();
        return new YourOrdersPO(driver);
    }

    public YourAddressesPO navigateToAddressesPO(){
        driver.findElement(addresses).click();
        return new YourAddressesPO(driver);
    }

}
