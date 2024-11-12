package com.temu.SeleniumFrameworkDesign;

import java.util.Arrays;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SslCheck {
	
	public static void main(String[] args) {
		
		//class called chromeoptions that sets behavior for the browser
		ChromeOptions option= new ChromeOptions();
		option.setAcceptInsecureCerts(true);
		 /*//for firefox and all other browser
		FirefoxOptions op= new FirefoxOptions();
		op.setAcceptInsecureCerts(true); */
		
		//using proxy. create object and do step by step
		Proxy proxy = new Proxy();
		proxy.setHttpProxy("ipaddress:4444");
		option.setCapability("proxy",proxy);
		
		//disabling popup when testing.
		option.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
	
		
		WebDriverManager.chromedriver().setup();
		//passing above options as an argument in chromedriver
		WebDriver driver=new ChromeDriver(option);
		driver.get("https://expired.badssl.com/");
		System.out.println(driver.getTitle());
		
		
		
	}

}
