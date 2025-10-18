package com.shubham.base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class TestBase {
public Properties prop;
     public TestBase () throws IOException {
        String filepath="C:\\Users\\Shubham.Kumar\\IdeaProjects\\SeleniumMavenPractice\\config.properties";
        FileInputStream file= new FileInputStream(filepath);
         prop= new Properties();
       prop.load(file);



    }
    public WebDriver launchBrowserDriver() throws IOException {
        WebDriver driver = null;

        String browser = prop.getProperty("browser");
        switch (browser) {
            case "Chrome":
                System.setProperty(
                        "webdriver.chrome.driver",
                        "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
                // Instantiate a ChromeDriver class.
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                break;
            case "Edge":
                System.setProperty(
                        "webdriver.chrome.driver",
                        "C:\\Program Files\\msedgedriver.exe");
                // Instantiate a ChromeDriver class.
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();

        }
        return driver;

    }
}
