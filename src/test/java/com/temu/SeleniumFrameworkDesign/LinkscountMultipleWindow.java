package com.temu.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LinkscountMultipleWindow {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		int countOfLink = driver.findElements(By.tagName("a")).size();
		System.out.println(countOfLink);
		Assert.assertEquals(driver.findElements(By.tagName("a")).size(), 27);
		// count in footer section
		WebElement footerdriver = driver.findElement(By.id("gf-BIG")); // limiting webdriver scope
		System.out.println(footerdriver.findElements(By.tagName("a")).size());

		// count on footer of 1st column discount coupons
		WebElement coloumndriver = driver.findElement(By.xpath("//tbody/tr/td[1]/ul"));
		coloumndriver.findElements(By.tagName("a")).size();

		// click each link in the column
		for (int i = 1; i < coloumndriver.findElements(By.tagName("a")).size(); i++) {
			// keyboard function
			String clickonLinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
			// passing keyboard function to send keys instead of click.
			coloumndriver.findElements(By.tagName("a")).get(i).sendKeys(clickonLinkTab);
			Thread.sleep(5);

		}
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> a = windows.iterator();

		// this means if it has next window open
		while (a.hasNext()) {
			driver.switchTo().window(a.next());
			 System.out.println(driver.getTitle());
		}

	}
}
