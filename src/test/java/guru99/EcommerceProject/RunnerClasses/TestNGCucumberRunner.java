package guru99.EcommerceProject.RunnerClasses;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/Features/verify_mobile_sorting.feature",
    glue = "guru99.EcommerceProject.TestSteps"
   // plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

