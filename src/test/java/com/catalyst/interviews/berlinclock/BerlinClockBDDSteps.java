package com.catalyst.interviews.berlinclock;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BerlinClockBDDSteps {

    // i don't know what's the cucumber's way to inject this dependency
    // so i'm initializing it manually...
    private TimeConverter berlinClock = new BerlinClockTimeConverter();
    private String time;

    @Given("^the time is (.*)$")
    public void theTimeIs(String time) {
        this.time = time;
    }

    @Then("^the clock should look like:$")
    public void theClockShouldLookLike(String expectedBerlinClockOutput) {
        assertThat(berlinClock.convertTime(time), is(expectedBerlinClockOutput));
    }

}
