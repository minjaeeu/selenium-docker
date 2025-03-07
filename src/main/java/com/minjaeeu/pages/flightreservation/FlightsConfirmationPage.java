package com.minjaeeu.pages.flightreservation;

import com.minjaeeu.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightsConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightsConfirmationPage.class);

    @FindBy(css = "#flights-confirmation-section .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmationNo;

    @FindBy(css = "#flights-confirmation-section .row:nth-child(2) .col:nth-child(2)")
    private WebElement taxValue;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPriceValue;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(flightConfirmationNo));
        return this.flightConfirmationNo.isDisplayed();
    }

    public FlightsConfirmationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getFlightNo() {
        String flightConfirmationNo = this.flightConfirmationNo.getText();
        log.info("Flight confirmation number: {}", flightConfirmationNo);
        return flightConfirmationNo;
    }

    public String getTaxValue() {
        String taxValue = this.taxValue.getText();
        log.info("Tax value: {}", taxValue);
        return taxValue;
    }

    public String getTotalPriceValue() {
        String totalPriceValue = this.totalPriceValue.getText();
        log.info("Total price value: {}", totalPriceValue);
        return totalPriceValue;
    }
}
