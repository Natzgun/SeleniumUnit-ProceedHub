package com.hamuk;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegisterTest {
    private WebDriver driver;
    private static String registeredEmail;

    @BeforeEach
    public void setup() { driver = new FirefoxDriver(); }

    @Test
    @Order(1)
    public void testRegisterSuccess() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(500));
        driver.get("https://hamuk.vercel.app/register");

        String title = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Registro", title);

        WebElement username = driver.findElement(By.name("username"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement career = driver.findElement(By.name("career"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submit = driver.findElement(By.tagName("button"));

        registeredEmail = "mcahauanan" + System.currentTimeMillis() + "@unsa.edu.pe";

        username.sendKeys("Melany");
        email.sendKeys(registeredEmail);
        career.sendKeys("Ciencia de la Computacion");
        password.sendKeys("123456");
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getCurrentUrl().contains("scholarship"));
        assertTrue(driver.getCurrentUrl().contains("scholarship"));
    }

    @Test
    @Order(2)
    public void testRegisterWithExistingEmail() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://hamuk.vercel.app/register");

        String title = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Registro", title);

        WebElement username = driver.findElement(By.name("username"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement career = driver.findElement(By.name("career"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submit = driver.findElement(By.tagName("button"));

        String existingEmail = registeredEmail;

        username.sendKeys("Melany");
        email.sendKeys(existingEmail);
        career.sendKeys("Ciencia de la Computacion");
        password.sendKeys("123456");
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorDiv = wait.until(d -> d.findElement(By.className("bg-red-500")));

        assertTrue(errorDiv.isDisplayed());
    }

    @Test
    @Order(3)
    public void testLoginWithRegisteredEmail() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://hamuk.vercel.app/login");

        String title = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Login", title);

        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submit = driver.findElement(By.tagName("button"));

        String existingEmail = registeredEmail;

        email.sendKeys(registeredEmail);
        password.sendKeys("123456");
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getCurrentUrl().contains("scholarship"));
        assertTrue(driver.getCurrentUrl().contains("scholarship"));
    }

    @Test
    @Order(4)
    public void testRegisterWithEmptyFields() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://hamuk.vercel.app/register");

        String title = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Registro", title);

        WebElement submit = driver.findElement(By.tagName("button"));

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
