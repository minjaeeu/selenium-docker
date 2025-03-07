package com.minjaeeu.pages.flightreservation;

import com.minjaeeu.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchButton;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearchButton));
        return this.goToFlightsSearchButton.isDisplayed();
    }

    public RegistrationConfirmationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOnGoFlightsSearchButton() {
        this.goToFlightsSearchButton.click();
    }

}
