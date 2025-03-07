package com.minjaeeu.pages.flightreservation;

import com.minjaeeu.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightsSearchPage extends AbstractPage {

    @FindBy(id = "passengers")
    private WebElement passengersSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;


    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengersSelect));
        return this.passengersSelect.isDisplayed();
    }
    public FlightsSearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void selectPassengers(String noOfPassengers){
        Select passengers = new Select(this.passengersSelect);
        passengers.selectByValue(noOfPassengers);
    }

    public void clickSearchFlightsButton(){
        this.searchFlightsButton.click();
    }

}
