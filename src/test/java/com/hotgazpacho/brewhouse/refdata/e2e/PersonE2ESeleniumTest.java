package com.hotgazpacho.brewhouse.refdata.e2e;

import com.hotgazpacho.brewhouse.refdata.util.selenium.SeleniumTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SeleniumTest(driver = PhantomJSDriver.class, baseUrl = "http://localhost:9000")
public class PersonE2ESeleniumTest {

    @Autowired
    private WebDriver driver;

    @LocalServerPort
    private int port;

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void helloPageHasTextHelloWorld(){
        driver.get(String.format("http://127.0.0.1:%s/api/hello", port));
        assertThat(driver.findElement(By.tagName("body")).getText(), containsString("Hello World!"));
    }
}
