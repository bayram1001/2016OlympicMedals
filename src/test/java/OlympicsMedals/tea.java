package OlympicsMedals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import io.github.bonigarcia.wdm.WebDriverManager;

public class tea {
	
	//TestCase:
	//Navigate to http://www.practiceselenium.com
	//Verify that title says "Welcome"
	//Go to Menu and verify the title "Menu"
	//Go to check out and verify the title "Check Out"
	//Enter customer info and submit it
	//Go to Let's Talk Tea link and verify the title "Let's Talk Tea"
	//Enter message information and submit it
	
WebDriver driver;
Faker faker = new Faker();
String email = faker.internet().emailAddress();
String name = faker.name().fullName();
String address = faker.address().fullAddress();
String cardNumber = faker.finance().creditCard();
String verificationCode = faker.number().numberBetween(001, 999) + "";


	@BeforeClass
	public void setUpMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("http://www.practiceselenium.com");
	}
	
	@Test (priority=1)
	public void verifyTitle() {
		String expected = "Welcome";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}
	
	@Test (priority=2)
	public void menu() {
		driver.findElement(By.xpath("//a[@data-title='Menu']")).click();
		String expected = "Menu";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}
		
		@Test (priority=3)
		public void checkOut() {
		driver.findElement(By.xpath("//span[@class='button-content wsb-button-content']")).click();
		String expected = "Check Out";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
}
	
	@Test (priority=4)
	public void enterInfo() {
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.xpath("//div/input[@id='name']")).sendKeys(name);
		driver.findElement(By.id("address")).sendKeys(address);
		Select cardType = new Select(driver.findElement(By.xpath("//select[@id='card_type']")));
		cardType.selectByIndex(faker.number().numberBetween(1, cardType.getOptions().size()-1));
		driver.findElement(By.id("card_number")).sendKeys(cardNumber);
		driver.findElement(By.id("cardholder_name")).sendKeys(name);
		driver.findElement(By.id("verification_code")).sendKeys(verificationCode);
		driver.findElement(By.xpath("//button[.='Place Order']")).click();
	}
	
	
	 @AfterTest
		public void tearDown() {
			driver.close();
		}
	
}
