package africa.za.gambit.stepdefinitions.paf.selenium;

import africa.za.gambit.junit.cucumber.framework.base.TestBase;
import africa.za.gambit.junit.cucumber.framework.supporting.report.Report;
import africa.za.gambit.systems.paf.pageObjects.GoogleSearchPage;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j;

@Log4j
public class UrlNavigator extends TestBase {

    GoogleSearchPage page = new GoogleSearchPage();

    @Given("^I open the browser and navigate to google$")
    public void iOpenTheBrowserAndNavigateToGoogle() {
        page.navigateToUrl();
        Report.screenshot(scenario);
    }
}
