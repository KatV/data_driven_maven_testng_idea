package com.w2a.base;

import com.w2a.utilities.ExcelReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

// Import log4j classes.

public class TestBase {

    /*
    * WebDriver - done
    * Properties - done
    * Logs - log4j jar, .log, log4j.properties, Logger
    * ExtentReports
    * DB
    * Excel
    * Mail
    * ReportNG, ExtentReports
    * Jenkins
    *
     */

    public static WebDriver driver;

    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    private static final Logger log = LogManager.getLogger(TestBase.class);
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "//src//test//resources//exel//testdata.xlsx");

    @BeforeSuite
    public void setUp() {

        if (driver == null) {
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis); // configuration file is loading
                log.debug("Config file loaded!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//properties//OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
                log.debug("OR file loaded!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (config.getProperty("browser").equals("firefox")) {

                //System.setProperty("webdriver.gecko.driver","geckodriver");
                driver = new FirefoxDriver();
                log.debug("Firefox Launched!!!");

            } else if (config.getProperty("browser").equals("chrome")) {

                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver");
                // best practice to avoid some issues with chrome
                ChromeOptions opt = new ChromeOptions();
                opt.addArguments("disable-infobars");
                opt.addArguments("--start-maximized");
                opt.addArguments("--disable-extensions");
                driver = new ChromeDriver(opt);
                log.debug("Chrome Launched!!!");

            } else if (config.getProperty("browser").equals("ie")) {

                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");
                //driver = new InternerExplorerDriver();
            }

            driver.get(config.getProperty("testsiteurl"));
            log.debug("Navigated to:" + config.getProperty("testsiteurl"));
            driver.manage().window().setSize(new Dimension(Integer.parseInt(config.getProperty("dimention.width")), Integer.parseInt(config.getProperty("dimention.height"))));
            log.debug("Screen dimension is:" + config.getProperty("dimention.width") + "x" + config.getProperty("dimention.height"));
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);

        }

    }

    public boolean isElementPresent(By by) {

        try{

            driver.findElement(by);
            return true;

        }catch(NoSuchElementException e){

            return false;
        }
    }

    @AfterSuite
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

        log.debug("Test execution completed!!!");
    }
}
