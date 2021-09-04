package com.zemoso.pageObjects;

import com.zemoso.pageObjects.base.GenericDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChooseDeliveryOptionsPO extends GenericDriver {
    private By deliveryDay = By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div[1]/form/div[2]/div[1]/div[2]/div[1]/div/div/div[1]/div/div/div[2]/span/span");
    private By continueButton = By.cssSelector("input.a-button-text[value='Continue']:first-of-type");
    public ChooseDeliveryOptionsPO(WebDriver driver) {
        super(driver);
    }
    public PaymentOptionsPO clickContinue(){
        driver.findElement(continueButton).click();
        return new PaymentOptionsPO(driver);
    }
    public String getFirstDeliveredItemDate(){
        return driver.findElement(deliveryDay).getText();
    }
}
