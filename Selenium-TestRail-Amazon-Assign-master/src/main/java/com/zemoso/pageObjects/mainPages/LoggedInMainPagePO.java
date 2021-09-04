package com.zemoso.pageObjects.mainPages;

import com.zemoso.pageObjects.YourAccountPO;
import com.zemoso.pageObjects.base.BasePagePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInMainPagePO extends BasePagePO {
    private By mainNav = By.id("nav-link-accountList");

    public LoggedInMainPagePO(WebDriver driver) {
        super(driver);
    }
    public YourAccountPO navigateToYourAccountPage(){
        driver.findElement(mainNav).click();
        return new YourAccountPO(driver);
    }
}
