package com.agoda;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class StepDefinitions {
    String firstName = "Srikanth Reddy";
    String lastName = "Karam";
    WebElement signupButton;
    WebDriver driver;
    @Before
    public void setUp(){
        driver  = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Given("the registration page is open")
    public void the_registration_page_is_open() {
        driver.get("https://www.agoda.com/");
        driver.findElement(By.xpath("//span[contains(@class,'Typographystyled__TypographyStyled-sc-j18mtu-0 gSVfcd kite-js-Typography')]")).click();
    }
    @When("a valid email {string} is entered")
    public void a_valid_email_is_entered(String email) {
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Universal login']")));
        WebElement emailField = driver.findElement(By.xpath("//input[@id='email']"));
        emailField.sendKeys(email);
    }
    @And("a new password {string} is entered")
    public void a_new_password_is_entered(String password){
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        confirmPasswordField.sendKeys(password);

    }
    @And("all the required fields are entered")
    public void required_fields_are_entered() throws InterruptedException {
        Thread.sleep(2000);
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys(firstName);
        Thread.sleep(2000);
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.sendKeys(lastName);
    }

    @And("the sign up button is clicked")
    public void theSignUpButtonIsClicked() throws InterruptedException{
        Thread.sleep(2000);
        signupButton = driver.findElement(By.xpath("//span[normalize-space()='Sign up']"));
        signupButton.click();
    }
    @Then("redirection to the account confirmation page occurs")
    public void redirected_to_accountConfirmation_page()throws InterruptedException{
        String pageTitle = driver.getTitle();
        Assert.assertEquals("",pageTitle);
        Thread.sleep(2000);

    }
    @And("username is displayed")
    public void username_is_displayed() throws InterruptedException{
        String expectedUsername = firstName+" "+lastName.toUpperCase().charAt(0)+".";
        String displayedUsername = driver.findElement(By.xpath("//p[@class='Typographystyled__TypographyStyled-sc-j18mtu-0 Hkrzy kite-js-Typography ']")).getText();
        Assert.assertEquals(expectedUsername,displayedUsername);
        Thread.sleep(20000);

    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
