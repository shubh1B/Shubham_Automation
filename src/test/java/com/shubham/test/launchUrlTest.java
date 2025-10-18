package com.shubham.test;

import com.shubham.page.FlipkartHomePage;
import com.shubham.util.ExcelDataProvider;
import com.shubham.util.appData;
import org.testng.annotations.*;
import com.shubham.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.shubham.util.Util;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class launchUrlTest extends TestBase {
    WebDriver driver;
    Util util;
    private static final Logger logger = LogManager.getLogger(launchUrlTest.class);

    //calls the Base class constructor
    public launchUrlTest() throws IOException {
        super();
    }

    //Initiallize the driver in base class & pass to util class
    @BeforeTest
    public void initiallizeDriver() throws IOException {
        this.driver = launchBrowserDriver();
        logger.info("Starting test...");
        Util util = new Util(driver);
        this.util = util;
        logger.debug("Chrome browser launched");

    }

    @Test(priority = 2, dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
    public void launchUrl(appData dt) throws IOException, InterruptedException {
        // Launch Website
        util.launchUrl();
        System.out.println(dt.itema);
        logger.info("amazon app launched");
        String classname = this.getClass().getSimpleName();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println(classname);
        util.takeScreenshot(classname, methodName);
        FlipkartHomePage fLipkartHomePage=new FlipkartHomePage(driver);
        fLipkartHomePage.getTitle();
        ;

    }

    //Closes the browser after execution
    @AfterTest
    public void closeDriver() {
        if (this.driver != null) {
            driver.close();
        }
    }
}
