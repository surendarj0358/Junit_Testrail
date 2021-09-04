package com.zemoso.pageObjects;

import com.zemoso.pageObjects.base.GenericDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentOptionsPO extends GenericDriver {
    By addPayMethod = By.xpath("//*[contains(text(),'Add Debit/Credit/ATM Card')]");
    By addCard = By.partialLinkText("Add a credit or debit");
    public PaymentOptionsPO(WebDriver driver) {
        super(driver);
    }
    public void addPaymentMethod(){
        driver.findElement(addPayMethod).click();
        driver.findElement(addCard).click();
    }

}
