package com.shubham.elements;

import com.shubham.base.TestBase;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ElementLocator extends TestBase {
    WebDriver driver;
    public ElementLocator(WebDriver driver) throws IOException {
        this.driver=driver;
    }
}

