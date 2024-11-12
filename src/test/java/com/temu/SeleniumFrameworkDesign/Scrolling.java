package com.temu.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scrolling {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		// need to initialize javascript object and casting driver to it
		// this we need to goto console and type = window.scrollBy(0,800
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)"); // takes 2 arguments but we can give only 1 and this will scroll

		// queryselector is css in javascript
		js.executeScript("document.querySelector(\".tableFixHead\").scrollTop=5000");

		List<WebElement> values = driver.findElements(By.xpath("//div[@class='tableFixHead']//td[4]"));
		int sum = 0;
		for (int i = 0; i < values.size(); i++) {
			Integer.parseInt(values.get(i).getText()); // converting string to integer
			// here we are adding
			sum = sum + Integer.parseInt(values.get(i).getText()); // converting string to integer

		}
		System.out.println(sum);
		int total=Integer.parseInt(driver.findElement(By.xpath("//div[@class='totalAmount']")).getText().split(":")[1].trim());
		Assert.assertEquals(sum, total);

	}

}
