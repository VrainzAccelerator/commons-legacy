package feature.com.sondeosglobal.test.cucumber.digicel.salvador;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
dryRun = false,
strict = true,
features = {"src/test/resources/feature/digicelSalvador"},
glue = "feature.com.sondeosglobal.test.cucumber.digicel.salvador",
tags = { "~@wip", "@executeThis" },
monochrome = true,
format = {
"pretty",
"html:target/cucumber",
"json:target_json/cucumber.json",
"junit:taget_junit/cucumber.xml"
}
)
public class DigicelSalvadorTest {
	
}
