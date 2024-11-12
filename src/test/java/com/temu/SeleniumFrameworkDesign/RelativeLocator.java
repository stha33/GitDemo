package com.temu.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class RelativeLocator {
public static void main(String[] args) throws IOException {
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://rahulshettyacademy.com/angularpractice/");
	
	WebElement nameEditBox=driver.findElement(By.xpath("//input[@name='name']"));
	System.out.println(driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText());
	
	WebElement IceCreamLabel=driver.findElement(By.xpath("//label[text()='Check me out if you Love IceCreams!']"));
	driver.findElement(with(By.tagName("input")).toLeftOf(IceCreamLabel)).click();
	

	File src=IceCreamLabel.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new File("C:\\Users\\dell\\Desktop\\Screenshot\\screenshot.png"));

	
	nameEditBox.getRect().getDimension().getHeight();
	
}
}
