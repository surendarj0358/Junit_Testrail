package com.zemoso.pageObjects;

import com.zemoso.pageObjects.base.BasePagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class YourOrdersPO extends BasePagePO {

    private By orderFilter = By.cssSelector("select#orderFilter");
    public YourOrdersPO(WebDriver driver) {
        super(driver);
    }
    public void selectLastOneYearOrders(){
        Select ordersFilter = new Select(driver.findElement(orderFilter));
        ordersFilter.selectByVisibleText("2021");
    }

    public String getCurrentVisibleValueOfDurationOrders(){
        Select durationDropDown = new Select(driver.findElement(orderFilter));
        return durationDropDown.getFirstSelectedOption().getText();
    }
}
