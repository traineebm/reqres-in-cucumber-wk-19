package in.reqres;

import cucumber.api.CucumberOptions;
import in.reqres.testbase.TestBase;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/java/resources/featurefile",tags = "@SANITY"
)
public class ReqresRunner extends TestBase {
}
