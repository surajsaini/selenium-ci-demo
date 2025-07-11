package com.surajsaini.selenium_demo_CICD;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AirCanadaTest {

	WebDriver driver;

	// List of WebElement
	@FindBy(xpath = "//h1[text()=' Where can we take you? ']")

	WebElement homePageText;

	public String getText() {
		String text1 = homePageText.getText().trim();
		return text1;
	}

	@BeforeTest
	public void setup() {
		System.out.println("BeforeTest: Setup WebDriver");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-gpu");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

	@Test
	public void openAirCanadaWebsite() throws InterruptedException {
		System.out.println("Test: Open Air Canada Website");
		driver.get("https://www.aircanada.com");
		Assert.assertEquals(getText(), "Where can we take you?");
	}

	@AfterTest
	public void tearDown() {
		System.out.println("AfterTest: Closing WebDriver");
		driver.quit();
	}
}
