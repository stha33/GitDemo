package com.temu.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Calender {

	public static void main(String[] args) {

		String month="6";
		String day="24";
		String year="2027";
		String expectedList []= {month,day,year};
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		//System.out.println(driver.findElement(By.xpath("//div[@class='react-date-picker__inputGroup']")).getText());
		driver.findElement(By.xpath("//div[@class='react-date-picker__inputGroup']")).click();
		
		driver.findElement(By.xpath("//button[@type='button']/span")).click();
		
		driver.findElement(By.xpath("//button[@class='react-calendar__navigation__label']")).click();
		driver.findElement(By.xpath("//button[text()='"+year+"']")).click();
		driver.findElement(By.xpath("//div[@class='react-calendar__year-view__months']/button["+month+"]")).click();
		driver.findElement(By.xpath("//abbr[contains(@aria-label,'"+day+"')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='react-date-picker__inputGroup']")));
		
		//storing all three elements with common factor in list and then iterating
		List <WebElement> actualList=driver.findElements(By.xpath("//input[@inputmode='numeric']"));
		for(int i=0;i<actualList.size();i++) {
			
			actualList.get(i).getAttribute("value");
			Assert.assertEquals(actualList.get(i).getAttribute("value"), expectedList[i]);
		
			
			
		}
		driver.close();
		
	}

}
