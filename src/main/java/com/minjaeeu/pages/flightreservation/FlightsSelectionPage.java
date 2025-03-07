package com.minjaeeu.pages.flightreservation;

import com.minjaeeu.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightsSelectionPage extends AbstractPage {

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightOptions;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightButton;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(confirmFlightButton));
        return this.confirmFlightButton.isDisplayed();
    }

    public FlightsSelectionPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void selectFlights(){
        int randomDeparture = ThreadLocalRandom.current().nextInt(0, departureFlightOptions.size());
        int randomArrival = ThreadLocalRandom.current().nextInt(0, arrivalFlightOptions.size());
        this.departureFlightOptions.get(randomDeparture).click();
        this.arrivalFlightOptions.get(randomArrival).click();
    }

    public void clickOnConfirmFlightButton(){
        this.confirmFlightButton.click();
    }

}
