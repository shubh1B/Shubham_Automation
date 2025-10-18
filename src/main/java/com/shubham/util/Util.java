package com.shubham.util;

import com.shubham.base.TestBase;
import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Util extends TestBase {

    WebDriver driver;

    //Receives the driver from base & test class & set it to driver of util class
    public Util(WebDriver driver) throws IOException {
        super();
        this.driver = driver;

    }
//    public com.shubham.Util() throws IOException {
//        super();
//    }

    public void launchUrl() {
        driver.get(prop.getProperty("url"));

    }

    //Method to take screenshot
    public void takeScreenshot(String classname, String methodname) {

        try {
            // Open a website
            System.out.println(classname);

            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Define the location and filename for the screenshot
            File destination = new File("Downloads\\" + classname + "\\" + methodname + ".png");
            // Copy the screenshot to the destination location
            FileUtils.copyFile(screenshot, destination);
            System.out.println("Screenshot saved at: " + destination.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    //write data to excel sheet
    public void writeDataInExcel(String filepath, String sheetname, int row, int col, String val) {
        ExcelWriter.writeToCell(filepath, sheetname, row, col, val);
    }
}
