package com.github.shmvanhouten.googlesearchbot;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class GoogleBotApplicationTest {
    private WebDriver webDriver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @Test
    public void itShouldGoToGoogle() throws Exception {
        webDriver.get("https://www.google.nl");
        String title = webDriver.getTitle();
        assertThat(title, is("Google"));
    }

}