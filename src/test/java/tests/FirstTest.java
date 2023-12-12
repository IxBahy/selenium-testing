package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class FirstTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "M:\\Java\\Features\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testRegisterUser() {
        System.out.println("Testing register User:");
        driver.navigate().to("https://demo.nopcommerce.com/register");
        assertTrue(registerUser(driver));
    }

    @Test
    public void testValidLogin() {
        System.out.println("Valid Data Login Scenario:");
        userLogin(driver, "john.doe@example.com", "123456");
        assertTrue(isUserLoggedIn(driver));
    }

    @Test
    public void testInvalidLogin() {
        System.out.println("Invalid Data Login Scenario:");
        userLogin(driver, "user@examples.com", "123456");
        assertFalse(isUserLoggedIn(driver));
    }

    @Test
    public void testValidPasswordReset() {
        System.out.println("Valid Password Reset Scenario:");
        resetPassword(driver, "john.doe@example.com", true);
    }

    @Test
    public void testInvalidPasswordReset() {
        System.out.println("Invalid Password Reset Scenario:");
        resetPassword(driver, "user@example.com", false);
    }

    @Test
    public void testAddToCart() {
        System.out.println("Testing add to cart:");
        userLogin(driver, "john.doe@example.com", "123456");
        driver.navigate().to("https://demo.nopcommerce.com/build-your-own-computer");
        assertTrue(addToCart(driver));
    }


    private void userLogin(WebDriver driver, String email, String password) {
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();

        WebElement emailInput = driver.findElement(By.id("Email"));
        emailInput.sendKeys(email);

        WebElement passwordInput = driver.findElement(By.id("Password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.className("login-button"));
        loginButton.click();
    }

    private void resetPassword(WebDriver driver, String email, boolean shouldReset) {
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();

        WebElement forgotPasswordLink = driver.findElement(By.xpath("//a[text()='Forgot password?']"));
        forgotPasswordLink.click();

        WebElement emailInput = driver.findElement(By.id("Email"));
        emailInput.sendKeys(email);

        WebElement recoverButton = driver.findElement(By.xpath("//button[@name='send-email']"));
        recoverButton.click();

        WebElement notificationDiv = driver.findElement(By.cssSelector("div.bar-notification"));
        WebElement successMessage = notificationDiv.findElement(By.tagName("p"));

        if (shouldReset) {
            assertTrue(successMessage.getText().contains("Email with instructions has been sent to you."));
            System.out.println("Password reset was successful");
        } else {
            assertTrue(successMessage.getText().contains("Email not found."));
            System.out.println("Password reset was not successful");
        }
    }

    private boolean isUserLoggedIn(WebDriver driver) {
        try {
            driver.findElement(By.xpath("//a[@class='ico-logout']"));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private boolean addToCart(WebDriver driver) {
        WebElement ramSelect = driver.findElement(By.xpath("//select[@name='product_attribute_2']"));
        WebElement option = ramSelect.findElement(By.xpath("//option[@value='3']"));
        option.click();
        WebElement hddInput = driver.findElement(By.id("product_attribute_3_6"));
        hddInput.click();
        WebElement addButton = driver.findElement(By.id("add-to-cart-button-1"));
        addButton.click();
        driver.navigate().to("https://demo.nopcommerce.com/cart");

        WebElement table = driver.findElement(By.tagName("tbody"));
        int numberOfRows = table.findElements(By.tagName("tr")).size();
        return numberOfRows > 0;
    }

    private static boolean registerUser(WebDriver driver) {
        try {
            WebElement genderRadioButton = driver.findElement(By.id("gender-male")); // Replace with actual gender radio button ID
            genderRadioButton.click();

            WebElement firstNameInput = driver.findElement(By.id("FirstName")); // Replace with actual first name input ID
            firstNameInput.sendKeys("John");

            WebElement lastNameInput = driver.findElement(By.id("LastName")); // Replace with actual last name input ID
            lastNameInput.sendKeys("Doe");

            WebElement dayInput = driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")).findElement(By.xpath("//option[@value='1']"));
            dayInput.click();// Replace with actual date of birth input ID
            WebElement monthInput = driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")).findElement(By.xpath("//option[@value='1']")); // Replace with actual date of birth input ID
            monthInput.click();
            WebElement yearInput = driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")).findElement(By.xpath("//option[@value='2003']")); // Replace with actual date of birth input ID
            yearInput.click();

            WebElement emailInput = driver.findElement(By.id("Email")); // Replace with actual email input ID
            emailInput.sendKeys("john.doe@example.com");

            WebElement company = driver.findElement(By.id("Company")); // Replace with actual password input ID
            company.sendKeys("a3a3a3a3a3");


            WebElement passwordInput = driver.findElement(By.id("Password")); // Replace with actual password input ID
            passwordInput.sendKeys("123456");

            WebElement confirmPasswordInput = driver.findElement(By.id("ConfirmPassword")); // Replace with confirm password input ID
            confirmPasswordInput.sendKeys("123456");

            WebElement registerButton = driver.findElement(By.id("register-button")); // Replace with actual register button ID
            registerButton.click();

        } catch (Exception e) {
            System.out.println("exp"+e);
            return false;
        }
        return true;
    }
}

