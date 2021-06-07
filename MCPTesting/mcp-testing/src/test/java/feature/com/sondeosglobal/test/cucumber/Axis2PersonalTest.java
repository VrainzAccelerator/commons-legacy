package feature.com.sondeosglobal.test.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
dryRun = false,
strict = true,
features = {"src/test/resources/feature/axis2personal"},
glue = "feature",
tags = { "~@wip", "@executeThis" },
monochrome = true,
format = {
"pretty",
"html:target/cucumber",
"json:target_json/cucumber.json",
"junit:taget_junit/cucumber.xml"
}
)
public class Axis2PersonalTest {

}
