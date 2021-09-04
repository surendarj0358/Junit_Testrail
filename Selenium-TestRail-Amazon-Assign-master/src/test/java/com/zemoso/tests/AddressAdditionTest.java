package com.zemoso.tests;

import com.zemoso.pageObjects.AddAddressPO;
import com.zemoso.pageObjects.mainPages.LoggedInMainPagePO;
import com.zemoso.pageObjects.YourAccountPO;
import com.zemoso.pageObjects.YourAddressesPO;
import config.InitialConfig;
import config.TestRailConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddressAdditionTest {
    @Test
    public void tc005_addAndVerifyAddress(){
        WebDriver driver  = SeleniumAmazonTest.driver;
        driver.get(InitialConfig.getBaseURL());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //main logged in search page
        LoggedInMainPagePO mainPageCO = new LoggedInMainPagePO(driver);
        //goto your account
        YourAccountPO accountPO = mainPageCO.navigateToYourAccountPage();
        //navigate to addresses page
        YourAddressesPO addressesPO = accountPO.navigateToAddressesPO();
        //navigate to page where you can add a new address
        AddAddressPO addressPO = addressesPO.navigateToAddNewAddressPage();
        //add a
        String companyName = "ZeMoSo Technologies Pvt Ltd";
        YourAddressesPO addressesPO1 = addressPO.addAddress(companyName, "9666738943",
                "500008", "802/803 MJR Magnifique", "Raidurgam");
        boolean isTextInPageAddresses = addressesPO1.isTextInPageAddresses(companyName);
        TestRailConfig.addTestResult(InitialConfig.getAddNewAddress(), isTextInPageAddresses);
        assert isTextInPageAddresses;

    }
}
