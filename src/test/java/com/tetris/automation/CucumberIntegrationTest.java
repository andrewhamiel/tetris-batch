package com.tetris.automation;

import com.tetris.automation.config.SpringIntegrationTest;
import com.tetris.automation.stepdefs.StepDefinitions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
tags = "@Provided",
glue = {"com.tetris.automation.stepdefs", "com.tetris.automation.config"},
plugin = {"pretty", "html:target/cucumber-html-report"})
public class CucumberIntegrationTest extends SpringIntegrationTest{
    
}
