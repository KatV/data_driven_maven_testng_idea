package com.w2a.testcases;


import com.w2a.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {

    private static final Logger log = LogManager.getLogger(BankManagerLoginTest.class);

    @Test
    public void loginAsBankManager() throws InterruptedException {

        log.debug("Inside login test");
        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();

        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "Login is not successful!");

        log.debug("Login test successfully executed!");

    }
}
