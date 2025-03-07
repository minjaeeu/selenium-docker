package com.minjaeeu.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.minjaeeu.listener.TestListener;
import com.minjaeeu.util.Config;
import com.minjaeeu.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({TestListener.class})
public abstract class AbstractTest {

    public static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    protected WebDriver driver;
    protected WebDriverWait wait;



    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String finalUrl = String.format(urlFormat, hubHost);
        log.info("grid url is {}", finalUrl);
        return new RemoteWebDriver(new URL(finalUrl), capabilities);
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @BeforeSuite
    public void setUpConfig(){
        Config.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        if (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))) {
            this.driver = getRemoteDriver();
        } else {
            this.driver = getLocalDriver();
        }
        ctx.setAttribute(Constants.DRIVER, this.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Config.get(Constants.TIMEOUT))));
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }



//    @AfterMethod
//    public void sleep(){
//        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10));
//    }

}