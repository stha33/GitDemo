package com.temu.SeleniumFrameworkDesign;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Brokenlinks {
public static void main(String[] args) throws IOException {
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
	//broken url or link
	//step 1 is to get all urls using selenium
	//there are java methods that will call urls and gets you status code
	//if status code is >400, then url is broken or not working
	
	
	
	String url= driver.findElement(By.xpath("//a[text()='Broken Link']")).getAttribute("href");
	List<WebElement> footerLinks= driver.findElements(By.xpath("//li[@class='gf-li']/a"));
	
	//Soft assertion creating an object
	SoftAssert a = new SoftAssert();
	for(int i=0;i<footerLinks.size();i++)
	{
		String attribute=footerLinks.get(i).getAttribute("href");
		//creating object of URL
		URL ur= new URL(attribute);
		HttpURLConnection conn = (HttpURLConnection)ur.openConnection();
		conn.setRequestMethod("HEAD");
		conn.connect();
		int responseCode=conn.getResponseCode();
		
		//instead of if condition we can write in short
		//Assert.assertTrue(responseCode>400, "broken link is : "+footerLinks.get(i).getText()+"response code is: "+responseCode);
		if(responseCode>400) {
			footerLinks.get(i).getText();
			
			System.out.println("broken link is : "+footerLinks.get(i).getText()+"response code is: "+responseCode);

		Assert.assertTrue(false);	
		}
		
		
		
		//for soft assertion instead of assert we can pass the a in it
		a.assertTrue(responseCode>400, "broken link is : "+footerLinks.get(i).getText()+"response code is: "+responseCode);

		
		
	}
	//this is compulsory for sofr assertion out of for loop
	  //a.assertAll();
  	
	
	
}
}
