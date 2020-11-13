package com.ecommerce.stepdefs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import junit.framework.Assert;
import org.junit.Assert;


public class Stepdefs {
	
	WebDriver driver;
	String base_url = "https://www.amazon.in/";
	int implicit_wait_timeinsec = 20;
	
	
	@Given("User opened browser")
	public void user_opened_browser() {
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(implicit_wait_timeinsec, TimeUnit.SECONDS);	
	}

	@And("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {
		driver.get(base_url);
		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actual = driver.getTitle();
		//Assert.assertEquals(expected, actual);
        Assert.assertEquals("Page Title validation",expected,actual);

	}
	
	@When("User Search for product {string}")
	public void user_search_for_product(String productName) {
        WebDriverWait webDriverWait1 = new WebDriverWait(driver,20);
        WebElement elementSearchBox = webDriverWait1.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
        elementSearchBox.sendKeys(productName);
        driver.findElement(By.xpath("//input[@value='Go']")).click();
}
	
	
	@Then("Search Result page is displayed")
	public void search_result_page_is_displayed() {
        WebDriverWait webDriverWait = new WebDriverWait(driver,20);
        webDriverWait.until(ExpectedConditions.titleIs("Amazon.in : Laptop"));
        
        Assert.assertEquals("Page Title validation","Amazon.in : Laptop", driver.getTitle());



		
	}




}
