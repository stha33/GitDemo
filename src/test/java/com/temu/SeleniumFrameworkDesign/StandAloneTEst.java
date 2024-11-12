package com.temu.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTEst {
	
public static void main(String[] args) {
	
	String productName="ZARA COAT 3";
	
//	System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
//	WebDriver driver = new ChromeDriver();
//instead of above
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	driver.manage().window().maximize();
	driver.get("https://rahulshettyacademy.com/client");
	
	driver.findElement(By.id("userEmail")).sendKeys("sam@m.com");
	driver.findElement(By.id("userPassword")).sendKeys("Rahul123");
	driver.findElement(By.id("login")).click();
	//expicit wait at global level
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	
	List<WebElement> products= driver.findElements(By.cssSelector(".mb-3 "));
	
	WebElement prod=products.stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	prod.findElement(By.xpath("(//button[@class='btn w-10 rounded'][normalize-space()='Add To Cart'])[1]")).click();
	
	//using wait from above global level
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

	
	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	
	List <WebElement> cartProducts=  driver.findElements(By.cssSelector(".cartSection h3"));
	
	Boolean match= cartProducts.stream().anyMatch(cartProduct->
	cartProduct.getText().equalsIgnoreCase(productName));
	
	Assert.assertTrue(match);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='ta-results list-group ng-star-inserted']")));
	driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
	driver.findElement(By.cssSelector(".action__submit")).click();
	String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.close();
	
	
}
}