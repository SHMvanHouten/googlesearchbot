package com.github.shmvanhouten.googlesearchbot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PhantomJsGoogleBotTest {
    private WebDriver webDriver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\documenten\\downloads\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        webDriver = new PhantomJSDriver(capabilities);
        webDriver.get("https://www.google.nl");
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }

    @Test
    public void itShouldFindGoogle() throws Exception {
        assertThat(webDriver.getTitle(), is("Google"));
    }

    @Test
    public void itShouldFindGoogleAndSearchForCucumberSilently() throws Exception {

        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("lst-ib")));

        webDriver.findElement(By.id("lst-ib"))
                .sendKeys("Cucumber");
        webDriver.findElement(By.id("_fZl")).click();

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
}
