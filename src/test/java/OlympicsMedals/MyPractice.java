package OlympicsMedals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyPractice {

	WebDriver driver;

	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Test(priority = 0)
	public void sorting() {

		// 1. go to https://en.wikipedia.org/wiki/2012_Summer_Olympics#Medal_table
		driver.get("https://en.wikipedia.org/wiki/2012_Summer_Olympics#Medal_table");

		// 2. Verify that by default the Medal table is sorted by rank.
		List<WebElement> rows = new ArrayList<>();
		List<String> expectedRows = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		List<String> actualRows = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			rows.addAll(driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
							+ "]/td[1]")));

		}
		for (WebElement each : rows) {
			actualRows.add(each.getText());
		}
		Assert.assertEquals(actualRows, expectedRows);
		System.out.println("===============================================================");
		System.out.println("Actual Rows are: " + actualRows);

		// 3. Click link NOC.
		driver.findElement(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[2]"))
				.click();

		// 4. Now verify that the table is now sorted by the country names.
		List<WebElement> sortedCountries = new ArrayList<>();
		List<String> expectedCountries = Arrays.asList(" Australia (AUS)‡", " China (CHN)‡", " France (FRA)‡",
				" Germany (GER)‡", " Great Britain (GBR)*", " Hungary (HUN)‡", " Italy (ITA)", " Russia (RUS)‡",
				" South Korea (KOR)‡", " United States (USA)‡");
		List<String> actualCountries = new ArrayList<>();

		for (int i = 0; i < 11; i++) {
			sortedCountries.addAll(driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
							+ "]/th[1]")));
		}
		for (WebElement eachCountry : sortedCountries) {
			actualCountries.add(eachCountry.getText());
		}
		Assert.assertEquals(actualCountries, expectedCountries);
		System.out.println("===============================================================");
		System.out.println("Actual Countries are: " + actualCountries);

		// 5. Verify that Rank column is not in ascending order anymore. Use TestNG
		// assertions.
		List<WebElement> newAscendingRows = new ArrayList<>();
		List<String> epextedNewRows = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		List<String> actualNewRows = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			newAscendingRows.addAll(driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
							+ "]/td[1]")));
		}
		for (WebElement eachNewRow : newAscendingRows) {
			actualNewRows.add(eachNewRow.getText());
		}
		Assert.assertNotEquals(expectedRows, actualNewRows);
		System.out.println("===============================================================");
		System.out.println("New Actual Rows after clicking on NOC :" + actualNewRows);

	}

	@Test(priority = 1)
	public void mostNumber() {

		// 6.go back to the same website
		driver.get("https://en.wikipedia.org/wiki/2012_Summer_Olympics#Medal_table");

		// 7. Write a method that returns the name of the country with the most number of gold medals.
		List<WebElement> countryList = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr//a"));
		countryList.remove(countryList.size() - 1);// not to get the 11th row
		List<WebElement> mostGoldMedal = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[2]"));
		mostGoldMedal.remove(mostGoldMedal.size() - 1); // not to get the 11th row
		System.out.println("===============================================================");
		System.out.println(countryList.size() + " ," + mostGoldMedal.size());
		System.out.println("The most gold medal country is :" +

				findMostOfEverything(countryList, mostGoldMedal));

	}
	
	@Test (priority = 2)
	public void countryRowAndColumn() {
		// 8 Go to website https://en.wikipedia.org/wiki/2012_Summer_Olympics.
				driver.get("https:en.wikipedia.org/wiki/2012_Summer_Olympics");
				
// 9. Write a method that takes country name and returns the row and column number for that country. 
				//You decide the data type of the return.
				String expectedCountry = "Japan";
				System.out.println("Japan's row and column = ["+getCountryRowColumn(expectedCountry)+"]");
				Assert.assertEquals(getCountryRowColumn(expectedCountry), "6 2");		

	}

	public String findMostOfEverything(List<WebElement> abc, List<WebElement> def) {

		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < abc.size(); i++) {
			map.put(abc.get(i).getText(), Integer.valueOf(def.get(i).getText()));
		}
		Set<Entry<String, Integer>> setMap = map.entrySet();

		String result = "";
		int max = 0;
		for (Entry<String, Integer> each : setMap) {
			if (each.getValue() > max) {
				max = each.getValue();
				result = each.getKey();
			}
		}
		return result + " , " + max;
	}
	public String getCountryRowColumn(String countryName) {
		int row = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//../tbody/tr")).size();
		int column = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//../thead/tr/th"))
				.size();

		String str[][] = new String[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				str[i][j] = driver.findElement(By.xpath("(//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//../tbody/tr["
						+ (i + 1) + "]/*)[" + (j + 1) + "]")).getText();
				if (str[i][j].contains(countryName)) {
					return (i + 1) + " " + (j + 1);
				}
			}
			}
		return null;
	}
}


