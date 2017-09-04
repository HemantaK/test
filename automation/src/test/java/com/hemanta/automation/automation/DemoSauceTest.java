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
    public void setUp(@Optional("desert1006") String username,
                      @Optional("9b32a925-85b9-48cd-ae76-ef2cc32cf8b1") String key,
                      @Optional("mac") String os,
                      @Optional("firefox") String browser,
                      @Optional("44") String browserVersion,
                      Method method) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", os);
        capabilities.setCapability("name", method.getName());
	capabilities.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
        this.driver = new RemoteWebDriver(
                new URL("http://" + username + ":" + key + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        System.out.println(capabilities);
        printSessionId(driver);
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


    private void printSessionId(WebDriver driver) {

        String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",
                (((RemoteWebDriver) driver).getSessionId()).toString(), "Test");
        System.out.println(message);
    }
	
	

}
