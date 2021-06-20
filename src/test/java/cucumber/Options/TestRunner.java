package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {".//features/placeValidations.feature"},
        plugin = {"json:target/jsonReports/cucumber-report.json"},
        glue = {"stepdefinitions"},
        dryRun = false
        //tags = "@AddPlace or @DeletePlace"
)
public class TestRunner {
}
