package OlympicsMedals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleFligths {
	/*
	 * Go to GoogleFligths web site to search for a ticket ' 
	 * MAKE sure that you are on the Google Flights web site.Use testNG 
	 * Make sure that Round trip is shown.Use testNG 
	 * Make sure 1 passenger selected .Use testNG 
	 * Choose premium economy 
	 * Go to New york Choose the date to leave and come back get a list with
	 * the name of the airplanes and their prices Price should be less than $1000
	 * 
	 */
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		
	}

	@Test
	public void googleFlights() throws InterruptedException {
		driver.get("https://www.google.com/flights#flt=/m/0vzm..2018-07-25*./m/0vzm.2018-07-29;c:USD;e:1;sd:1;t:h");
		String actualTitle = driver.findElement(By.xpath("(//span[.='Flights'])[2]")).getText();
		Assert.assertEquals(actualTitle, "Flights");
		System.out.println("WebSite Title: " + actualTitle);

		// Round Trip
		System.out.println("==========================================================");
		String roundTrip = driver
				.findElement(By.xpath("//div[@jsaction='ticket_type_selected:jsl._']/dropdown-menu/div/div/span[1]"))
				.getText();
		Assert.assertEquals(roundTrip, "Round trip");

		// 1 Passenger
		System.out.println("==========================================================");
		String onePassenger = driver.findElement(By.xpath("//div[@class='gws-flights-form__menu-label']")).getText();
		Assert.assertEquals(onePassenger, "1 passenger");
		System.out.println("Printing passenger: " + onePassenger);
		
		//SELECTING PREMIUM ECONOMY
		System.out.println("==========================================================");
		// clicking on economy
	    driver.findElement(By.xpath("(//span[@class='gws-flights-form__menu-label'])[2]")).click();
	    // clicking on Premium Economy
	    driver.findElement(By.xpath("//span[.='Premium Economy']")).click();;
	    String premiumEconomy = driver.findElement(By.xpath("//span[.='Premium Economy']")).getText();
        Assert.assertEquals(premiumEconomy, "Premium Economy");
	    System.out.println("Printing Premium Economy: "+premiumEconomy);
	    
	    // TYPING THE NAME OF THE FROMCITY
	    String cityFrom = "Dallas";
	    String cityTo = "Barcelona";
	    
	    driver.findElement(By.xpath("//div[@class='gws-flights-form__location-text gws-flights__flex-filler gws-flights__ellipsize gws-flights-form__input-target']")).click();
	   Thread.sleep(2200);
	   driver.findElement(By.xpath("//input[@placeholder='Where from?']")).sendKeys(cityFrom);
	   driver.findElement(By.xpath("//input[@placeholder='Where from?']")).sendKeys(cityFrom+Keys.ENTER);
	   
	   //typing the name of the CITYTO
	   Thread.sleep(1000);
	   driver.findElement(By.xpath("//span[@class='gws-flights-form__text-field-placeholder']")).click();
	   driver.findElement(By.xpath("//input[@placeholder='Where to?']")).sendKeys(cityTo);
	   driver.findElement(By.xpath("//input[@placeholder='Where to?']")).sendKeys(cityTo+Keys.ENTER);
	}
	   
	   // choosing the date
	   @Test
	public void choosingLeavingDate() {
      driver.get("https://www.google.com/flights#flt=/m/0vzm..2018-07-25*./m/0vzm.2018-07-29;c:USD;e:1;sd:1;t:h");   
      driver.findElement(By.xpath("(//div[@class='gws-flights__flex-filler gws-flights__ellipsize gws-flights-form__input-target'])[1]")).click();
	 
      // Leaving Date
      driver.findElement(By.xpath("//calendar-day[@data-day='2018-07-30']")).click(); 
	   }
      @Test
      public void choosingReturningBackDate() {
      driver.get("https://www.google.com/flights#flt=/m/0vzm..2018-07-25*./m/0vzm.2018-07-29;c:USD;e:1;sd:1;t:h");   
      driver.findElement(By.xpath("(//div[@class='gws-flights__flex-filler gws-flights__ellipsize gws-flights-form__input-target'])[2]")).click();
      // Returning Date
      driver.findElement(By.xpath("//calendar-day[@data-day='2018-08-30']")).click(); 
      driver.findElement(By.xpath("//g-raised-button")).click();
      
	}

	@Test
	public void tearDown() {
	//	driver.quit();
	}

}
