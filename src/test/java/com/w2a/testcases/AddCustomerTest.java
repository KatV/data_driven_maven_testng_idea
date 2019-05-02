package com.w2a.testcases;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {

    @Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
    public void AddCustomer(String firstName, String lastName, String postCode, String alerttext) throws InterruptedException {

        // fill fields with data from excel file
        click("addCustBtn");
        type("firstname", firstName);
        type("lastname", lastName);
        type("postcode", postCode);
        click("addBtn");

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alerttext));
        alert.accept();

    }

}
