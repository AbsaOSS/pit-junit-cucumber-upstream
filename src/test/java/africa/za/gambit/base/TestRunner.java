package africa.za.gambit.base;

import africa.za.gambit.junit.cucumber.framework.supporting.listener.Initialize;
import africa.za.gambit.junit.cucumber.framework.supporting.listener.Processor;
import africa.za.gambit.junit.cucumber.framework.supporting.listener.TearDown;
import africa.za.gambit.junit.cucumber.framework.supporting.runner.CustomRunner;
import io.cucumber.junit.CucumberOptions;
import lombok.extern.log4j.Log4j;
import org.junit.runner.RunWith;

@Log4j
@RunWith(CustomRunner.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/java/africa/za/gambit/features",
        glue = {"africa.za.gambit.base","africa.za.gambit.stepdefinitions"},
        plugin = {"json:target/cucumber-report/cucumber.json"}
)
public class TestRunner {

    private static String executionDateTime;
    private static final String DATEFORMAT = "dd-MM-yyyy_hh-mm-ss";

    @Initialize
    public static void initialize() {
        log.info("----- Initialize: START ----- ");
        executionDateTime = Processor.getDateAsString(DATEFORMAT);
        Processor.initializeFramework();
        log.info("----- Initialize: END ----- ");
    }

    @TearDown
    public static void teardown() {
        log.warn("----- Teardown: START ----- ");
        Processor.initializeTeardown(executionDateTime);
        log.info("----- Teardown: END ----- ");
    }

}
