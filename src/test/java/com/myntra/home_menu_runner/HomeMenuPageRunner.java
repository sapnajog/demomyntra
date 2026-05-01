package com.myntra.home_menu_runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features", glue = { "com.myntra.stepdefinitions",
		"com.myntra.Hooks" }, tags = "not @Ignore", plugin = { "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" })
public class HomeMenuPageRunner extends AbstractTestNGCucumberTests {

}
