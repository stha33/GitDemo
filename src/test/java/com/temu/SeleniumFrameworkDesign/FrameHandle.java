package com.temu.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameHandle {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://jqueryui.com/droppable/");
		driver.manage().window().maximize();
		
		
		//using switch for frame
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
	//	driver.findElement(By.id("draggable")).click();
		driver.switchTo().defaultContent();
		
		//drag and drop
		Actions a= new Actions(driver);
		WebElement source= driver.findElement(By.id("draggable"));
		WebElement target=driver.findElement(By.id("droppable"));
		
		a.dragAndDrop(source,target).build().perform();
		
		
	}

}
