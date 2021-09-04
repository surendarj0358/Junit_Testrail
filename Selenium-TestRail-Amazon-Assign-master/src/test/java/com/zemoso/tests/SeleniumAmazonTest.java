package com.zemoso.tests;

import config.InitialConfig;
import config.TestRailConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumAmazonTest {
    public static WebDriver driver;
    @BeforeSuite
    public void beforeSuite(){
        //we need to have properties in application.properties as required in below class
        new InitialConfig();
        Class<? extends WebDriver> driverClass = ChromeDriver.class;
        WebDriverManager.getInstance(driverClass).setup();
        try {
            driver = driverClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        amazonLogin(InitialConfig.getBaseURL(),
                InitialConfig.getEmail(), InitialConfig.getPassword());
    }

    @AfterSuite
    public void suiteTeardown(){
        TestRailConfig.finishUp();
        driver.close();
        driver.quit();
    }
    @Test
    public void tc001_testAddingItemToCart(){
        //this and the next method are not on based on POM pattern

        //find the search bar
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        //search for todays deals
        searchBox.sendKeys("Todays deals");
        searchBox.sendKeys(Keys.RETURN);
        //introducing an implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //select the third item from the search results -selector
        String searchResultSelector = "div.s-main-slot.s-result-list.s-search-results.sg-row " +
                "div[data-index='5'] img";
        waitTillSelectorVisible(searchResultSelector);
        List<WebElement> products =  driver.findElements(By
                .cssSelector(searchResultSelector));
        //we will find exactly one element
        assert products.size()==1:"Size was not 1";
        if(products.size() != 1){
            assert false;
            driver.close();
            return;
        }
        products.get(0).click();
        List<String> tabs2 = new ArrayList<> (driver.getWindowHandles());
        //switching to the newly opened tab of the search result
        driver.switchTo().window(tabs2.get(1));
        int expectedCartCount = 0;
        Select quantitySelect = new Select(driver.findElement(By.id("quantity")));
        expectedCartCount += Integer.parseInt(quantitySelect.getFirstSelectedOption().getText());
        expectedCartCount += getCurrentCartCount(driver);
        List<WebElement> stateBuybox = driver.findElements(
                By.cssSelector("div.a-section.a-spacing-small.a-text-center strong"));
        //if the size drop down is present enter the if condition
        if(stateBuybox.size()!=0){
            Select selectDropDown = new Select(driver.findElement(
                    By.id("native_dropdown_selected_size_name")));
            selectDropDown.selectByIndex(1);//select a size
            //wait for required changes in the page
            waitTillSelectorVisible("select#quantity");
        }
        //add the product to cart
        addToCart();
        //wait for the cart count to become one (fluently)
        WebElement cartCount = waitForCartCount(expectedCartCount +"");
        boolean result = Integer.parseInt(cartCount.getText()) == expectedCartCount;
        TestRailConfig.addTestResult(InitialConfig.getAddItemToCart(), result);
        assert result;
    }
    private int getCurrentCartCount(WebDriver driver){
        return Integer.parseInt(driver.findElement(By.id("nav-cart-count")).getText());
    }
    @Test
    public void tc002_searchForMobilesAndAddLastToCart(){
        //this method is also not based on POM
        int currentCartCount = getCurrentCartCount(driver);
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Mobiles");
        searchBox.sendKeys(Keys.RETURN);
        List<WebElement> mobiles = driver.findElements(By
                .cssSelector("div[data-component-type='s-search-result'].s-result-item"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //if mobiles are present in the search result
        if(mobiles.size() > 0){
            Actions actions = new Actions(driver);
            WebElement lastMobile = mobiles.get(mobiles.size() - 1);
            //scrolll to the last mobile and click
            actions.moveToElement(lastMobile);
            actions.perform();
            lastMobile.findElement(By.tagName("img")).click();
            List<String> tabs2 = new ArrayList<> (driver.getWindowHandles());
            //switch to the last opened tab
            driver.switchTo().window(tabs2.get(tabs2.size()-1));
            addToCart();
            ++currentCartCount;
            //wait for the cart count to become 2
            WebElement cartCount = waitForCartCount(currentCartCount+"");
            boolean res = Integer.parseInt(cartCount.getText()) == currentCartCount;
            TestRailConfig.addTestResult(InitialConfig.getAddLastMobToCart(),res);
            assert res;
        }
    }

    private void amazonLogin(String baseURL, String userName, String password) {
        driver.get(baseURL);
        driver.findElement(By.id("nav-link-accountList")).click();
        driver.findElement(By.id("ap_email")).sendKeys(userName);
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys(password);
        driver.findElement(By.id("signInSubmit")).click();
    }

    private void waitTillSelectorVisible(String selector) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        WebElement selectVis = driver.findElement(By.cssSelector(selector));
        wait.until(ExpectedConditions.elementToBeClickable(selectVis));
    }

    private WebElement waitForCartCount(String count) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        WebElement cartCount = driver.findElement(By.id("nav-cart-count"));
        wait.until(ExpectedConditions.textToBePresentInElement(cartCount, count));
        return cartCount;
    }

    private void addToCart() {
        driver.findElement(By.id("add-to-cart-button")).click();

    }

}

