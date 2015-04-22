package com.hemanta.automation.automation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Unit test for simple App.
 */
public class DemoSauceTest {

	private WebDriver driver;

    /**
     * Creates a new {@link RemoteWebDriver} instance to be used to run WebDriver tests using Sauce.
     *
     * @param username the Sauce username
     * @param key the Sauce access key
     * @param os the operating system to be used
     * @param browser the name of the browser to be used
     * @param browserVersion the version of the browser to be used
     * @param method the test method being executed
     * @throws Exception thrown if any errors occur in the creation of the WebDriver instance
     */
   

	@Parameters({"username", "key", "os", "browser", "browserVersion"})
    @BeforeMethod
    public void setUp(@Optional("kumar_hemanta") String username,
                      @Optional("540440a3-f43f-4e65-a507-270454fddb8d") String key,
                      @Optional("mac") String os,
                      @Optional("firefox") String browser,
                      @Optional("17") String browserVersion,
                      Method method) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", os);
        capabilities.setCapability("name", method.getName());
        this.driver = new RemoteWebDriver(
                new URL("http://" + username + ":" + key + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
    }

    @Test
    public void webDriver() throws Exception {
        driver.get("http://www.amazon.com/");
        AssertJUnit.assertEquals(driver.getTitle(), "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
    }

 

	@AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

}
