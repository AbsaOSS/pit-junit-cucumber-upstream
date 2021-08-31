package africa.za.gambit.base;

import africa.za.gambit.junit.cucumber.framework.base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j;

@Log4j
public class Hook {

    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {
        TestBase.scenario = scenario;
        if (TestBase.seleniumTrigger) {
            TestBase.initialiseSelenium();
        }
        log.info("----- Scenario: START ----- ");
        log.info("Scenario Name: " + scenario.getName());
    }

    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        // Close selenium driver
        if (TestBase.seleniumTrigger) {
            TestBase.closeSelenium();
        }
        log.info("Scenario Result: " + scenario.getStatus());
        log.info("----- Scenario: END ----- ");
    }

}
