package com.minjaeeu.tests.fightreservation;

import com.minjaeeu.pages.flightreservation.*;
import com.minjaeeu.tests.AbstractTest;
import com.minjaeeu.util.Config;
import com.minjaeeu.util.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class FlightReservationTest extends AbstractTest {

    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setupTest(String noOfPassengers, String expectedPrice){
        //parameter setup
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void userRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver, wait);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails("Selenium", "Docker");
        registrationPage.enterUserCredentials("Selenium@docker.com", "Docker123");
        registrationPage.enterUserAddress("Selenium street", "Docker city", "30003");
        registrationPage.clickRegisterButton();

    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver, wait);
        Assert.assertTrue(registrationConfirmationPage.isAt());

        registrationConfirmationPage.clickOnGoFlightsSearchButton();

    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest() {
        FlightsSearchPage flightSearchPage = new FlightsSearchPage(driver, wait);
        Assert.assertTrue(flightSearchPage.isAt());

        flightSearchPage.selectPassengers(this.noOfPassengers);
        flightSearchPage.clickSearchFlightsButton();

    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightsSelectionTest(){
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver, wait);
        Assert.assertTrue(flightsSelectionPage.isAt());

        flightsSelectionPage.selectFlights();
        flightsSelectionPage.clickOnConfirmFlightButton();

    }

    @Test(dependsOnMethods = "flightsSelectionTest")
    public void flightsConfirmationTest(){
        FlightsConfirmationPage flightsConfirmationPage = new FlightsConfirmationPage(driver, wait);
        Assert.assertTrue(flightsConfirmationPage.isAt());

        String flightNo = flightsConfirmationPage.getFlightNo();
        String tax = flightsConfirmationPage.getTaxValue();
        String total = flightsConfirmationPage.getTotalPriceValue();

        Assert.assertEquals(total, this.expectedPrice);

    }


}
