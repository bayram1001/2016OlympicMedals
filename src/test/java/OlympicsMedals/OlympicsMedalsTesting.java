package OlympicsMedals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OlympicsMedalsTesting {

	WebDriver driver;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	// Test Case 1: SORT TEST

	@Test(priority = 0)
	public void sorting() {
		// 1. Go to website
		// https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.o do that you
		// need to capture all the cells in the Rank column and check
		// if they are in ascending order (highlighted in the picture). Use TestNG
		// assertions.
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
		// 2. Verify that by default the Medal table is sorted by rank.
		List<WebElement> rows = new ArrayList<>();
		List<String> expRows = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		List<String> actRows = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			rows.addAll(driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
							+ "]/td[1]")));

		}

		for (WebElement each : rows) {
			actRows.add(each.getText());

		}

		Assert.assertEquals(actRows, expRows);
		// 3. Click link NOC.
		driver.findElement(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[2]"))
				.click();
		// 4. Now verify that the table is now sorted by the country names.
		List<WebElement> countries = new ArrayList<>();
		List<String> expCountries = Arrays.asList(" Australia (AUS)", " China (CHN)", " France (FRA)", " Germany (GER)",
				" Great Britain (GBR)", " Italy (ITA)", " Japan (JPN)", " Russia (RUS)", " South Korea (KOR)",
				" United States (USA)");
		List<String> actCountries = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			countries.addAll(driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
							+ "]/th[1]")));
		}
		for (WebElement eachCountry : countries) {
			actCountries.add(eachCountry.getText());

		}
		Assert.assertEquals(actCountries, expCountries);
		// 5. Verify that Rank column is not in ascending order anymore. Use TestNG
		// assertions.
		List<WebElement> newRows = new ArrayList<>();
		List<String> expNewRows = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		List<String> actNewRows = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			newRows.addAll(driver.findElements(
					By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
							+ "]/td[1]")));

		}

		for (WebElement eachNewRow : rows) {
			actRows.add(eachNewRow.getText());

		}

		Assert.assertNotEquals(actNewRows, expNewRows);
	}

	// Test Case 2: THE MOST
	// 6. Write TestNG test for your methods.
	@Test(priority = 1)
	public void mostNumber() {
		// 1. Go to website https://en.wikipedia.org/wiki/2016_Summer_Olympics.
		driver.get("https:en.wikipedia.org/wiki/2016_Summer_Olympics");
	}

	// 2. Write a method that returns the name of the country with the most number
	// of gold medals.
	@Test(priority = 2)
	public void MostGoldMedals() {
		List<WebElement> countryList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/th/a"));
		List<WebElement> GoldMedalsList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/td[2]"));

		findMost(countryList, GoldMedalsList);
	}

	// 3. Write a method that returns the name of the country with the most number
	// of silver medals.
	@Test(priority = 3)
	public void MostSilverMedals() {
		List<WebElement> countryList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/th/a"));
		List<WebElement> SilverMedalsList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/td[3]"));

		findMost(countryList, SilverMedalsList);
	}

	// 4. Write a method that returns the name of the country with the most number
	// of bronze medals.
	@Test(priority = 4)
	public void MostBronzeMedals() {
		List<WebElement> countryList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/th/a"));
		List<WebElement> BronzMedalsList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/td[4]"));

		findMost(countryList, BronzMedalsList);
	}

	// 5. Write a method that returns the name of the country with the most number
	// of medals.
	@Test(priority = 5)
	public void TotalMedals() {
		List<WebElement> countryList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/th/a"));
		List<WebElement> TotalMedalsList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/td[5]"));
	

		findMost(countryList, TotalMedalsList);

	}

	// Test Case 3: COUNTRY BY MEDAL
	// 1. Go to website https://en.wikipedia.org/wiki/2016_Summer_Olympics.
	@Test(priority = 6)
	public void silverCountriesMedal() {
		driver.get("https:en.wikipedia.org/wiki/2016_Summer_Olympics");
		List<WebElement> countryList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/th/a"));
		List<WebElement> SilverMedalsList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/td[3]"));

		// 2. Write a method that returns a list of countries whose silver medal count
		// is equal to 18.
		// 3. Write TestNG test for your method.
		System.out.println("List of countries whose silver medal count is equal to 18 = "+getCountryByMedalCount(countryList, SilverMedalsList, 18));

	}

	// Test Case 4: GET INDEX
	// 3. Write TestNG test for your method (use Japan as test input).
	@Test(priority = 7)
	public void Index() {
		// 1. Go to website https://en.wikipedia.org/wiki/2016_Summer_Olympics.
		driver.get("https:en.wikipedia.org/wiki/2016_Summer_Olympics");
		// 2. Write a method that takes country name and returns the row and column
		// number. You decide the datatype of the return.
		String expectedCountry = "Japan";
		System.out.println("Japan's row and column = ["+getCountryRowColumn(expectedCountry)+"]");
		Assert.assertEquals(getCountryRowColumn(expectedCountry), "6 2");

	}

	// Test Case 5: GET SUM
	// 3. Write TestNG test for your method.

	@Test(priority = 8)
	public void getSumOfBronzMedals() {
		// 1. Go to website https://en.wikipedia.org/wiki/2016_Summer_Olympics.
		driver.get("https:en.wikipedia.org/wiki/2016_Summer_Olympics");
		
		// 2. Write a method that returns a list of two countries whose sum of bronze
		// medals is 18.
		List<WebElement> countryList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/th/a"));
		List<WebElement> BronzMedalsList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/td[4]"));
		int expectedint = 18;

		for (int i = 0; i < countryList.size(); i++) {

			for (int j = 0; j < BronzMedalsList.size(); j++) {

				if (Integer.parseInt(BronzMedalsList.get(i).getText())
						+ Integer.parseInt(BronzMedalsList.get(j).getText()) == expectedint) {

					if (countryList.get(i).getText().equals(countryList.get(j).getText())) {
						continue;
					} else {
						String firstCountry = countryList.get(i).getText();
						String SecondCountry = countryList.get(j).getText();
						System.out.println("List of two countries whose sum of bronze medals is 18 = ["+firstCountry + "--" + SecondCountry+"]");
						break;

					}
				}

			}
		}
	}

	public String findMost(List<WebElement> countryLst, List<WebElement> medalCountLst) {
		// remove the last row
		medalCountLst.remove(medalCountLst.size() - 1);

		Map<String, Integer> map = getMap(countryLst, medalCountLst);

		Set<Entry<String, Integer>> setMap = map.entrySet();

		String result = "";
		int max = 0;
		for (Entry<String, Integer> each : setMap) {
			if (each.getValue() > max) {
				max = each.getValue();
				result = each.getKey();
			}

		}
		return result;
	}

	public Map<String, Integer> getMap(List<WebElement> list1, List<WebElement> list2) {
		Map<String, Integer> map = new HashMap<>();

		for (int i = 0; i < list1.size(); i++) {
			map.put(list1.get(i).getText(), Integer.valueOf(list2.get(i).getText()));
		}

		return map;
	}

	public List<String> getCountryByMedalCount(List<WebElement> countryList, List<WebElement> medalCountList, int a) {
		Map<String, Integer> map = getMap(countryList, medalCountList);
		List<String> result = new ArrayList<>();
		Set<Entry<String, Integer>> setMap = map.entrySet();
		for (Entry<String, Integer> each : setMap) {
			if (each.getValue() == a) {
				result.add(each.getKey());
			}
		}
		return result;
	}

	public String getCountryRowColumn(String countryName) {
		int row = driver.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr")).size();
		int column = driver.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../thead/tr/th"))
				.size();

		String str[][] = new String[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				str[i][j] = driver.findElement(By.xpath("(//caption[.='2016 Summer Olympics medal table']//../tbody/tr["
						+ (i + 1) + "]/*)[" + (j + 1) + "]")).getText();
				if (str[i][j].contains(countryName)) {
					return (i + 1) + " " + (j + 1);
				}
			}
		}

		return null;
	}

}