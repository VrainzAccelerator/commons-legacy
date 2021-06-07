package com.vrainz.mcpmid.cucumber.ooredoo;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
dryRun = false,
strict = true,
features = {"src/test/resources/features/ooredoo"},
glue = "com.vrainz.mcpmid.cucumber",
tags = { "~@wip", "@executeThis" },
monochrome = true,
format = {
"pretty",
"html:target/cucumber",
"json:target_json/cucumber.json",
"junit:taget_junit/cucumber.xml"
}
)
public class OoredooTest {

}
