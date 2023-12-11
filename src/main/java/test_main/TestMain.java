package test_main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class TestMain {
public static void main (String[] args){
    System.setProperty("webdriver.chrome.driver","M:\\Java\\Features\\chromedriver.exe");
    WebDriver driver= new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://demo.nopcommerce.com/");
    System.out.println("Valid Data Login Scenario:");
    userLogin(driver, "leqaahani@example.com", "123456");
    System.out.println("Logging out");
    userLogOut(driver);
    // Scenario: Invalid Data Login Scenario
    System.out.println("Invalid Data Login Scenario:");
    userLogin(driver, "user@examples.com", "123456");

    // Close the browser
//    driver.quit();
}
    public static void userLogin(WebDriver driver, String email, String password) {
        // Click on login link
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();

        // Enter email
        WebElement emailInput = driver.findElement(By.id("Email"));
        emailInput.sendKeys(email);

        // Enter password
        WebElement passwordInput = driver.findElement(By.id("Password"));
        passwordInput.sendKeys(password);

        // Click on login button
        WebElement loginButton = driver.findElement(By.className("login-button"));
        loginButton.click();

        // Navigate back to the home page
    }
    public static void userLogOut(WebDriver driver){
        WebElement loginLink = driver.findElement(By.linkText("Log out"));
        loginLink.click();

    }
}
