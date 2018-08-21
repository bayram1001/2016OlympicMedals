package OlympicsMedals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleTranslate {

	/*
	 * TEST CASE: Translating a word in all languages in Google Translate.

1. Navigate to Google Translate page at https://translate.google.com
2. Verify you are in the correct page.
3. Click English on the source field box.
4. Enter a word into source field to be translated.
5. Click a dropdown on the right column.
6. Select a language.
7. Capture a translated word.
8. Add the languages as keys and translated words as values to a Map.
9. Print the Map in console.
	 */
	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@Test
	public void googleTranslate() throws InterruptedException {
		String word= "excellent";
		driver.get("https://translate.google.com");
		
		String actualElement= driver.findElement(By.id("gt-appname")).getText();
		Assert.assertEquals(actualElement, "Translate");
		
		driver.findElement(By.id("sugg-item-en")).click();
		driver.findElement(By.cssSelector("#source")).sendKeys(word);
		driver.findElement(By.xpath("//*[@id='gt-tl-gms']")).click();
		
	List<WebElement>listOfLanguages = driver.findElements(By.xpath("//div[starts-with(@id,'goog-menuitem-group')]//div[@class='goog-menuitem-content']"));
		System.out.println("There are " + listOfLanguages.size() + " languages in Google Translate");
		
		List<String>  translatedWord = new ArrayList<>();
		SortedMap<String,String> map= new TreeMap<>();
		for (WebElement each : listOfLanguages) {
			each.click();
			Thread.sleep(300);
			 translatedWord.add(driver.findElement(By.id("result_box")).getText());
			 driver.findElement(By.xpath("//div[@id='gt-tl-gms']")).click();
			 
			
		}
		
		for (int i = 0; i < listOfLanguages.size(); i++) {
			map.put(listOfLanguages.get(i).getText(),translatedWord.get(i));
			
		}
		Set<Entry<String,String>> entries=map.entrySet();
		for(Entry<String,String> entry : entries) {
			System.out.println(word + " in " + entry.getKey() + " language is " +entry.getValue());
		}
		
		System.out.println(map);
		
		
		
		
		
		
		
	}
	@AfterClass
	public void tearDownClas() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}




}
