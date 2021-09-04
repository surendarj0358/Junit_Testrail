package com.zemoso.pageObjects;

import com.zemoso.pageObjects.base.BasePagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPO extends BasePagePO {
    By proceedToBy  = By.name("proceedToRetailCheckout");

    public CartPO(WebDriver driver) {
        super(driver);
    }

    public AddAddressPO clickOnProceedToBuy(){
        driver.findElement(proceedToBy).click();
        return new AddAddressPO(driver);
    }
}
