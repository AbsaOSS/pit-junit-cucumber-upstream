package africa.za.gambit.stepdefinitions.framework;

import africa.za.gambit.junit.cucumber.framework.base.TestBase;
import africa.za.gambit.junit.cucumber.framework.supporting.report.Report;
import io.cucumber.java.en.And;

public class MandatorySteps extends TestBase {

    @And("^The final test payload is logged out$")
    public void the_final_test_payload_is_logged_out() {
        Report.log(scenario, TestBase.getMandatoryReportStepValues());
    }

}
