package com.zemoso.pageObjects;

import com.zemoso.pageObjects.base.BasePagePO;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class AddAddressPO extends BasePagePO {
    By fullName = By.id("address-ui-widgets-enterAddressFullName");
    By phoneNumber = By.id("address-ui-widgets-enterAddressPhoneNumber");
    By postalCode = By.id("address-ui-widgets-enterAddressPostalCode");
    By addressLine1 = By.id("address-ui-widgets-enterAddressLine1");
    By addressLine2 = By.id("address-ui-widgets-enterAddressLine2");
    By city = By.id("address-ui-widgets-enterAddressCity");
    By selectOfficeHours = By.id("address-ui-widgets-addr-details-address-type-and-business-hours");
    By clickSelectionSelectTag = By.cssSelector("a#dropdown1_2");
    By formSubmitButton = By.cssSelector("input.a-button-input[type='submit']");
    By firstAddressButtonSelector = By.cssSelector("a[data-action='page-spinner-show']");

    public AddAddressPO(WebDriver driver) {
        super(driver);
    }


    public YourAddressesPO addAddress(String fullName, String phoneNumber,
                                      String postalCode, String addressLine1,
                                      String addressLine2){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(this.fullName).sendKeys(fullName);
        driver.findElement(this.phoneNumber).sendKeys(phoneNumber);
        driver.findElement(this.postalCode).sendKeys(postalCode);
        waitForAutoCityAddInCityDD();
        WebElement addressLineEle = driver.findElement(this.addressLine1);
        addressLineEle.sendKeys(addressLine1);

        driver.findElement(this.addressLine2).sendKeys(addressLine2);

        driver.findElement(this.selectOfficeHours).click();
        driver.findElement(this.clickSelectionSelectTag).click();
        driver.findElement(this.formSubmitButton).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return new YourAddressesPO(driver);
    }
    public ChooseDeliveryOptionsPO clickonMostRecentlyUsedAddress(){
        Optional<WebElement> first = driver.findElements(firstAddressButtonSelector)
                .stream().findFirst();
        if(first.isPresent()){
            first.get().click();
            return new ChooseDeliveryOptionsPO(driver);
        }
        else {
            return null;
        }
    }
    private void waitForAutoCityAddInCityDD() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        WebElement selectEle = driver.findElement(this.city);
        wait.until(ExpectedConditions.textToBePresentInElementValue(selectEle, "HYDERABAD"));
    }

}
