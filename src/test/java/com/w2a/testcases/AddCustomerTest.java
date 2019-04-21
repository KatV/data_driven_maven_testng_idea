package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "getData")
    public void AddCustomer(String firstName, String lastName, String postCode, String alerttext) throws InterruptedException {

        // fill fields with data from excel file
        driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
        driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
        driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postCode);
        driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alerttext));
        alert.accept();

    }

    @DataProvider
    public Object[][] getData(){

        String sheetName = "AddCustomerTest";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rows-1][cols];

        for (int rowNum=2; rowNum <= rows; rowNum++){
            for (int colNum=0; colNum < cols; colNum++){
                //data[0][0]
                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum); //-2
            }
        }
        return data;
    }
}