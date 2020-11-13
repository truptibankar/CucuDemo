package com.ecommerce.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
        webDriverWait.until(ExpectedConditions.titleIs("Amazon.in : Mobile"));
        
        Assert.assertEquals("Page Title validation","Amazon.in : Mobile", driver.getTitle());
    }
	
    @When("User click on any product")
    public void user_click_on_any_product() {
    	List <WebElement> productlist = driver.findElements(By.xpath("//a[@class='a-link-normal a-text-normal']"));
    	productlist.get(0).click();
    	
    }
    
    @Then("Product Description is displayed in new tab")
    public void product_description_is_displayed_in_new_tab() {
    	Set <String> handles = driver.getWindowHandles();
    	Iterator <String> it = handles.iterator();
    	String original = it.next();
    	String proddesc = it.next();
    	
    	driver.switchTo().window(proddesc);
    	
    	WebElement productTitle = driver.findElement(By.id("productTitle"));
    	Assert.assertEquals("Product tiltle validation", true, productTitle.isDisplayed());
    	
    	WebElement addtocart = driver.findElement(By.id("add-to-cart-button"));
    	Assert.assertEquals("add ti cart button validation", true,addtocart.isDisplayed() );
    	
    	driver.switchTo().window(original);
    	
    	
    	
    }






}
