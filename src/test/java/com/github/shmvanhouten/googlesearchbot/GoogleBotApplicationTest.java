package com.github.shmvanhouten.googlesearchbot;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleBotApplicationTest {
    private WebDriver webDriver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://www.google.nl");
    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }

    @Test
    public void itShouldGoToGoogle() throws Exception {

        String title = webDriver.getTitle();
        assertThat(title, is("Google"));
        webDriver.findElement(By.id("lst-ib")).sendKeys("Cucumber");
    }

    @Test
    public void itShouldEnterASearch()throws Exception{
        webDriver.findElement(By.id("lst-ib"))
                .sendKeys("Cucumber");
        webDriver.findElement(By.id("_fZl")).click();

        webDriver.manage().timeouts().implicitlyWait(3, SECONDS);

        List<WebElement> elementList = webDriver.findElements(By.className("r"));
        String firstResult = elementList.get(0).getText();
        String secondResult = elementList.get(1).getText();
        String thirdResult = elementList.get(2).getText();

        System.out.println("search number 1: " + firstResult);
        System.out.println("search number 2: " + secondResult);
        System.out.println("search number 3: " + thirdResult);

        assertThat(firstResult, is("Cucumber"));
    }
}