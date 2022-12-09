package com.tetris.automation.config;

import com.tetris.batch.TetrisJobRunner;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@TestPropertySource(properties = {"input.fileName=src/test/resources/cucumber/troll1Million.txt", "output.fileName=src/test/resources/cucumber/expected.txt"})
@SpringBootTest(classes = TetrisJobRunner.class)
public class SpringIntegrationTest {
    
}
