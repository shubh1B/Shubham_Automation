package com.shubham.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day1 {
    public static void main (String args[]) throws InterruptedException {
        System.setProperty( "webdriver.chrome.driver",
                "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver= new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
        String title=driver.getTitle();
        System.out.println(title);
        String url=driver.getCurrentUrl();
        System.out.println(url);
       //Thread.sleep(4000);
//        WebElement login=driver.findElement(By.name("login"));
//        login.click();W

        //driver.findElement(By.linkText("Forgotten password?")).click();
        //driver.findElement(By.partialLinkText("Forgotten")).click();
       // driver.findElement(By.tagName("a")).click();






    }
}
