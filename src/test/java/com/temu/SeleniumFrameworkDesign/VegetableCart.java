package com.temu.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VegetableCart {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// implicit wait globally
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// storing names in an array
		String[] productsName = { "Brocolli", "Cauliflower", "Cucumber" };
		// calling below method by making the method static
		addItems(driver, productsName); // basically addItems is utility

		// we can create an object to call the method aswell(this way method should not
		// be static)
		/*
		 * VegetableCart veg= new VegetableCart(); veg.addItems(driver, productsName);
		 */

		driver.findElement(By.xpath("//img[@alt='Cart']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@placeholder='Enter promo code']"))));
		driver.findElement(By.xpath("//input[@class='promoCode']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.xpath("//button[@class='promoBtn']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[@class='promoInfo']"))));
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='promoInfo']")).getText(), "Code applied ..!");

	}

	public static void addItems(WebDriver driver, String[] productsName) {
		int j = 0;
		// storing names of all product in webelement
		List<WebElement> products = driver.findElements(By.xpath("//h4[@class='product-name']"));

		for (int i = 0; i < products.size(); i++) {
			// storing names from the site in array of names
			String name[] = products.get(i).getText().split("-");
			// System.out.println(name);
			// since we need just the name, trimming it
			String formattedName = name[0].trim();
			// System.out.println(formattedName);

			// converting array to arraylist
			List<String> productsNameList = Arrays.asList(productsName);

			if (productsNameList.contains(formattedName)) {
				j++;
				// clicking on add to cart
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (j == productsName.length) {
					break;
				}

			}

		}

	}
}
