package com.hamuk;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
    }

    @Test
    public void testLoginSuccess() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://hamuk.vercel.app/login");

        String title = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Login", title);

        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submit = driver.findElement(By.tagName("button"));

        email.sendKeys("erickmalcoaccha@gmail.com");
        password.sendKeys("uiy785EJouGEtR");
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getCurrentUrl().contains("profile"));
        assertTrue(driver.getCurrentUrl().contains("profile"));
    }

    @Test
    public void testLoginFailure() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://hamuk.vercel.app/login");

        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submit = driver.findElement(By.tagName("button"));

        email.sendKeys("pedro@yabadaba.com");
        password.sendKeys("uasdasd");
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorDiv = wait.until(d -> d.findElement(By.className("bg-red-500")));

        assertTrue(errorDiv.isDisplayed());
    }

    @Test
    public void testLoginFailureIncorrectEmail() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://hamuk.vercel.app/login");

        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submit = driver.findElement(By.tagName("button"));

        email.sendKeys("incorrectemail@example.com");
        password.sendKeys("uiy785EJouGEtR");
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorDiv = wait.until(d -> d.findElement(By.className("bg-red-500")));

        assertTrue(errorDiv.isDisplayed());
    }

    @Test
    public void testLoginFailureIncorrectPassword() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://hamuk.vercel.app/login");

        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submit = driver.findElement(By.tagName("button"));

        email.sendKeys("erickmalcoaccha@gmail.com");
        password.sendKeys("incorrectpassword");
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorDiv = wait.until(d -> d.findElement(By.className("bg-red-500")));

        assertTrue(errorDiv.isDisplayed());
    }

    @Test
    public void testLoginFailureEmptyFields() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://hamuk.vercel.app/login");

        WebElement submit = driver.findElement(By.tagName("button"));
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorDiv = wait.until(d -> d.findElement(By.className("text-red-500")));

        assertTrue(errorDiv.isDisplayed());
    }

    @Test
    public void testLoginFailureEmptyEmail() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://hamuk.vercel.app/login");

        WebElement password = driver.findElement(By.name("password"));
        WebElement submit = driver.findElement(By.tagName("button"));

        password.sendKeys("uiy785EJouGEtR");
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorDiv = wait.until(d -> d.findElement(By.className("text-red-500")));

        assertTrue(errorDiv.isDisplayed());
    }

    @Test
    public void testLoginFailureEmptyPassword() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://hamuk.vercel.app/login");

        WebElement email = driver.findElement(By.name("email"));
        WebElement submit = driver.findElement(By.tagName("button"));

        email.sendKeys("erickmalcoaccha@gmail.com");
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorDiv = wait.until(d -> d.findElement(By.className("text-red-500")));

        assertTrue(errorDiv.isDisplayed());
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
