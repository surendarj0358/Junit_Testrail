package com.zemoso.pageObjects;

import com.zemoso.pageObjects.base.BasePagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class YourAddressesPO extends BasePagePO {
    By addNewAddress = By.cssSelector("a#ya-myab-address-add-link h2.a-color-tertiary");

    public YourAddressesPO(WebDriver driver) {
        super(driver);
    }
    public AddAddressPO navigateToAddNewAddressPage(){
        driver.findElement(addNewAddress).click();
        return new AddAddressPO(driver);
    }

    public boolean isTextInPageAddresses(String text){
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return !list.isEmpty();
    }

}
