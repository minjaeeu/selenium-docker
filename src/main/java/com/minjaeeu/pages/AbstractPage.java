package com.minjaeeu.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

public AbstractPage(WebDriver driver, WebDriverWait wait){
    this.driver = driver;
    this.wait = wait;
    PageFactory.initElements(driver, this);

}

// confirmation that a page is indeed loaded/active
public abstract boolean isAt();

}
