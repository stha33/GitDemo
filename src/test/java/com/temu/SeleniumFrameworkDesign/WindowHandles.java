package com.temu.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandles {

	public static void main(String[] args) {

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/loginpagePractise/#");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[@class='blinkingText']")).click();
		//getting parent and child id
		Set <String> windows=driver.getWindowHandles();
		//iterating the parent and child id
		Iterator <String> it=windows.iterator();
		//first getting the parent id and then moving to child (happens step by step)
		String parentId=it.next();
		String childId=it.next();

		
		driver.switchTo().window(childId);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inner-box']/h1")));
		//driver.findElement(By.xpath("//p[@class='im-para red']/strong")).getText();
		String text= driver.findElement(By.xpath("//div[@class='col-md-8']/p[2]")).getText();
		String finalSplitted= text.split("at")[1].split("with")[0].trim();
		System.out.println(finalSplitted);
		
		//going back to parent window
		driver.switchTo().window(parentId);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-column")));
		driver.findElement(By.id("username")).sendKeys(finalSplitted);
		
		
		
/*		String splitted []=text.split("@");
		//[0]=Please email us at mentor
		//[1]=rahulshettyacademy.com with below template to receive response
		String []splittedagain=splitted[1].split("with");
		//[0]=rahulshettyacademy.com
		//[1]=below template to receive response
		System.out.println(splittedagain[0].trim());    */

		//String text= driver.findElement(By.xpath("//p[@class='im-para red']/strong")).getText();
		//System.out.println(text);
		//driver.findElement(By.xpath("//p[@class='im-para red']/strong")).getText();
		
		
		
		
	}

}
