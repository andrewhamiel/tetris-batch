package com.tetris.automation.stepdefs;

import java.io.File;

import com.tetris.automation.config.SpringIntegrationTest;
import com.tetris.batch.config.TetrisConfig;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinitions {
    private File inputFile;
    private File actualOutputFile;
    
    @Given("I am a {string} user with valid input")
    public void setInputFile(String userString){
        inputFile = new File("src/test/resources/cucumber/" + userString + ".txt");
        actualOutputFile = new File("src/test/resources/cucumber/" + userString + "-output-actual.txt");
        ReflectionTestUtils.setField(TetrisConfig.class, "input.fileName", inputFile);
        ReflectionTestUtils.setField(TetrisConfig.class, "output.fileName", actualOutputFile);
    }

    @When("I invoke the Tetris application")
    public void runBatchJob(){
        
    }

    @Then("I expect the output to match the expected results")
    public void validateSuccessfulOutput(){
        //Do same file compare as in unit tests
    }

    @Then("I expect the following {string} to be thrown")
    public void validateOutputWithException(String exception){
        
    }
}
