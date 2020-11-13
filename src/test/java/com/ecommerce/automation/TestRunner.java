package com.ecommerce.automation;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
		features = "classpath:Feature",
		glue = "com.ecommerce.stepdefs",
		tags = "",
		plugin = {"pretty", 
				"html:target/html/",
	            "json:target/json/file.json",
     },
		dryRun=false
	)
public class TestRunner {

}
