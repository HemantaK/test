package com.hemanta.automation.web;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import bsh.Capabilities;

public class DemoWebTest {
	@Test
	public void testDemo(){
		WebDriver driver;
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		driver = new HtmlUnitDriver(cap);
		driver.get("https://cacert.org/");
		System.out.println(driver.getTitle());
		driver.close();
		
	}
	@Test
	public void testRemoteDriver() throws MalformedURLException{
		
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
		driver.get("http://google.com");
		
		driver.close();
	}

}
