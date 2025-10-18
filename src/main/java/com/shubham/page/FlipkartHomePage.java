package com.shubham.page;

import com.shubham.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class FlipkartHomePage extends TestBase {
    WebDriver driver;

//    public FLipkartHomePage() throws IOException {
//        super();
//    }
    public FlipkartHomePage(WebDriver driver) throws IOException {
        this.driver=driver;
    }

    //    public FLipkartHomePage(WebDriver driver){
//        PageFactory.initElements(this);
//    }
    //S1-Get the title of flipkart homepage
    public void getTitle() throws InterruptedException {
        Thread.sleep(3000);
        WebElement name=driver.findElement(By.xpath("//picture[@title='Flipkart']//img"));
        System.out.println("Flipkart title is:" + name.getDomAttribute("title"));
        //driver.findElement(By.xpath("//a[@aria-label='Kilos']")).click();
    }
}
