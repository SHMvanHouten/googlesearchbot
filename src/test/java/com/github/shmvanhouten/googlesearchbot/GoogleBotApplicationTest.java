package com.github.shmvanhouten.googlesearchbot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
        webDriver.quit();
    }

    @Test
    public void itShouldGoToGoogle() throws Exception {

        String title = webDriver.getTitle();
        assertThat(title, is("Google"));
    }

    @Test
    public void itShouldEnterASearch()throws Exception{
        webDriver.findElement(By.id("lst-ib"))
                .sendKeys("Cucumber");
        webDriver.findElement(By.id("_fZl")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("r")));

        List<WebElement> elementList = webDriver.findElements(By.className("r"));
        String firstResult = elementList.get(0).getText();
        String secondResult = elementList.get(1).getText();
        String thirdResult = elementList.get(2).getText();

        System.out.println("search number 1: " + firstResult);
        System.out.println("search number 2: " + secondResult);
        System.out.println("search number 3: " + thirdResult);

        assertThat(firstResult, is("Cucumber"));
    }

    @Test
    public void itShouldClickOnTheFirstLink() throws Exception {
        webDriver.findElement(By.id("lst-ib"))
                .sendKeys("Cucumber");
        webDriver.findElement(By.id("_fZl")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("r")));

        webDriver.findElement(By.linkText("Cucumber")).click();

        assertThat(webDriver.getTitle(), is("Cucumber"));
    }
}