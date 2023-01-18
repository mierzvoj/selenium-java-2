package test.java.webdemo.seleniumDemo;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestClass {


    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        System.setProperty("webdriver.gecko.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://duckduckgo.pl/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testTofindFirstThirdRes() {
        WebElement p = driver.findElement(By.name("q"));

        p.sendKeys("cars");
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
        p.submit();
        WebElement firstResult = driver.findElement(By.xpath("//article[@id='r1-0']/div[2]/h2/a/span"));
        firstResult.click();
        driver.get("https://duckduckgo.com/?q=cars&t=h_&ia=web");
        WebElement secondResult = driver.findElement(By.xpath("//article[@id='r1-2']/div[2]/h2/a/span"));
        secondResult.click();
        assertNotNull(firstResult);
    }

    @Test
    public void testNoClick() throws InterruptedException {
        WebElement p = driver.findElement(By.name("q"));

        p.sendKeys("cars");
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
        p.submit();
        WebElement firstResult = driver.findElement(By.xpath("//article[@id='r1-0']/div[2]/h2/a/span"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstResult);
        Thread.sleep(1500);
        assertNotNull(firstResult);
    }


    @Test
    public void testClassFind() {
        WebElement p = driver.findElement(By.className("content-info"));

        assertNotNull(p);
    }

    @Test
    public void testTagLookUp() {
        WebElement p = driver.findElement(By.tagName("div"));

        assertNotNull(p);
    }

    @Test
    public void testIdLookUp() {
        WebElement p = driver.findElement(By.id("iframe_hidden"));

        assertNotNull(p);
    }


    @Test
    public void testNameSearch() {
        WebElement p = driver.findElement(By.name("q"));

        assertNotNull(p);
    }


}
