package com.ecommerce.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import java.util.logging.LogManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import junit.framework.Assert;
import org.junit.Assert;


public class Stepdefs {
	
	private static final Logger logger = LogManager.getLogger(Stepdefs.class);	
	
	WebDriver driver;
	String base_url = "https://www.amazon.in/";
	int implicit_wait_timeinsec = 20;
	Scenario scn;
	
	
    @Before
    public void setUp(Scenario scn){
    	this.scn = scn;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicit_wait_timeinsec, TimeUnit.SECONDS);
        logger.info("Browser invoked");
    }
    
    @After
    public void CleanUp()
    {
    	driver.quit();
    	scn.log("Browser Closed");
    }

	
	@Given("User opened browser")
    @Deprecated
	public void user_opened_browser() {
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(implicit_wait_timeinsec, TimeUnit.SECONDS);	
	}

	@And("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {
		driver.get(base_url);
		scn.log("navigated to url: " + base_url);
		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actual = driver.getTitle();
		//Assert.assertEquals(expected, actual);
        Assert.assertEquals("Page Title validation",expected,actual);
        scn.log("page title validation is successful:" + actual);

	}
	
	@When("User Search for product {string}")
	public void user_search_for_product(String productName) {
        WebDriverWait webDriverWait1 = new WebDriverWait(driver,20);
        WebElement elementSearchBox = webDriverWait1.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
        elementSearchBox.sendKeys(productName);
        scn.log("Product Searched: " + productName);
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        scn.log("Clicked on log button");
}
	
	
	@Then("Search Result page is displayed")
	public void search_result_page_is_displayed() {
        WebDriverWait webDriverWait = new WebDriverWait(driver,20);
        webDriverWait.until(ExpectedConditions.titleIs("Amazon.in : Mobile"));
     
        Assert.assertEquals("Page Title validation","Amazon.in : Mobile", driver.getTitle());
        scn.log("product page title validation is successfull:" + driver.getTitle());
    }
	
    @When("User click on any product")
    public void user_click_on_any_product() {
    	List <WebElement> productlist = driver.findElements(By.xpath("//a[@class='a-link-normal a-text-normal']"));
    	scn.log("list of product searched:" + productlist.size());
    	productlist.get(0).click();
    	scn.log("click on the link. Link text: " + productlist.get(0).getText());
    	
    }
    
    @Then("Product Description is displayed in new tab")
    public void product_description_is_displayed_in_new_tab() {
    	Set <String> handles = driver.getWindowHandles();
    	scn.log("list of windows found :" + handles.size());
        scn.log("Windows handles: " + handles.toString());
    	Iterator <String> it = handles.iterator();
    	String original = it.next();
    	String proddesc = it.next();
    	
    	driver.switchTo().window(proddesc);
    	scn.log("swithched to the new window");
    	
    	WebElement productTitle = driver.findElement(By.id("productTitle"));
    	Assert.assertEquals("Product tiltle validation", true, productTitle.isDisplayed());
    	scn.log("product title validation is successful: " + productTitle.getText());
    	
    	WebElement addtocart = driver.findElement(By.id("add-to-cart-button"));
    	Assert.assertEquals("add ti cart button validation", true,addtocart.isDisplayed() );
    	scn.log("add to cart button is displayed");
    	
    	WebElement buyButton = driver.findElement(By.id("buy-now-button"));
    	Assert.assertEquals("Buy button presence validation", true, buyButton.isDisplayed());
    	scn.log("buy button is displayed");

    	
    	driver.switchTo().window(original);
    	scn.log("switched back to the original window");
    	
    }
    
    

    





}
