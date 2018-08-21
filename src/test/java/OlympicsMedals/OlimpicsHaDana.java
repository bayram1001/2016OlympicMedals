package OlympicsMedals;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OlimpicsHaDana {

  WebDriver driver;

  @BeforeClass
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().fullscreen();
  }

  @Test(priority = 1)
  public void testCase1() throws InterruptedException {
    // ======================== step 1 =====================
    driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");

    // ======================== step 2 =====================
    List<WebElement> tableRank = driver.findElements(
        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
    tableRank.remove(tableRank.size() - 1);
    assertTrue(isSorted(tableRank));
    

    // ======================== step 3 =====================
    driver.findElement(
        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[2]"))
        .click();

    // ======================== step 4 =====================
    List<WebElement> tableStrRank = driver.findElements(
        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
    tableStrRank.remove(tableStrRank.size() - 1);
    assertTrue(isSorted(tableStrRank));

    // ======================== step 5 =====================
    List<WebElement> tableRank2 = driver.findElements(
        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
    tableRank2.remove(tableRank2.size() - 1);
    assertFalse(isSorted(tableRank2));
  }

  @Test(priority = 2)
  public void testCase2() {
    // ======================== step 1 =====================
    driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");

    // ======================== step 2 =====================
    String goldXPath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[3]";
    System.out.println(theMostMedals(goldXPath));

    // ======================== step 3 =====================
    String silverXPath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[4]";
    System.out.println(theMostMedals(silverXPath));

    // ======================== step 4 =====================
    String bronzeXPath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[5]";
    System.out.println(theMostMedals(bronzeXPath));

    // ======================== step 5 =====================
    String mostNumberXPath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[6]";
    System.out.println(theMostMedals(mostNumberXPath));

    // ======================== step 6 =====================
    assertEquals(theMostMedals(goldXPath), "United States");
    assertEquals(theMostMedals(silverXPath), "United States");
    assertEquals(theMostMedals(bronzeXPath), "United States");
    assertEquals(theMostMedals(mostNumberXPath), "United States");

  }

  @Test(priority = 3)
  public void testCase3() {
    // ======================== step 1 =====================
    driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");

    // ======================== step 2 =====================
    List<WebElement> medals18 = driver.findElements(
        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
    List<Integer> intMedals18 = changeIntType(medals18);
    List<WebElement> medals18Country = driver.findElements(
        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
    List<String> strMedals18Country = changeStringType(medals18Country);

    Set<Entry<String, Integer>> getValue = mapList(strMedals18Country, intMedals18).entrySet();
    List<String> cntry18 = new ArrayList<>();
    for (Entry<String, Integer> each : getValue) {
      if (each.getValue() == 18) {
        cntry18.add(each.getKey());
      }
    }

    // ======================== step 3 =====================
    assertEquals(cntry18, Arrays.asList("China", "France"));

  }

  @Test(priority = 4)
  public void testCase4() {
    // ======================== step 1 =====================
    driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");

    // ======================== step 2 =====================
    Map<String, String> mapRows = new HashMap<>();

    List<WebElement> rows = driver.findElements(
        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr"));
    rows.remove(rows.size() - 1);
    List<String> rowsNum = new ArrayList<>();

    List<WebElement> rowsCountry = driver.findElements(
        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
    List<String> strRowsCountry = changeStringType(rowsCountry);

    for (int i = 0; i < rows.size(); i++) {
      rowsNum.add(i + 1 + ", 2");
    }
    for (int i = 0; i < rowsNum.size(); i++) {
      mapRows.put(strRowsCountry.get(i), rowsNum.get(i));
    }

    // ======================== step 3 =====================
    assertEquals(getMapValue(mapRows, "China"), "3, 2");
  }

  @Test(priority = 5)
  public void testCase5() {
    // ======================== step 1 =====================
    driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");

    // ======================== step 2 =====================
    List<WebElement> medals18 = driver.findElements(
        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
    List<Integer> intMedals18 = changeIntType(medals18);
    List<WebElement> medals18Country = driver.findElements(
        By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
    List<String> strMedals18Country = changeStringType(medals18Country);

    Set<Entry<String, Integer>> getValues = mapList(strMedals18Country, intMedals18).entrySet();
    List<String> cntry18 = new ArrayList<>();
    int sum = 0;
    for (Entry<String, Integer> each : getValues) {
      sum = each.getValue();
      for (Entry<String, Integer> each2 : getValues) {
        if (!(each.getKey().equals(each2.getKey())) && each2.getValue() + sum == 18)
          cntry18.add(each2.getKey());
      }
      sum = 0;
    }

    // ======================== step 3 =====================
    assertEquals(cntry18, Arrays.asList("Australia", "Italy"));

  }

  // this method returns country rows
  public String getMapValue(Map<String, String> mapList, String key) {

    Set<Entry<String, String>> getValues = mapList.entrySet();
    for (Entry<String, String> each : getValues) {
      if (each.getKey().equals(key)) {
        return each.getValue();
      }
    }
    return null;
  }

  // this method creates Map
  public Map<String, Integer> mapList(List<String> strList, List<Integer> intList) {

    Map<String, Integer> mapMedals = new HashMap<>();

    for (int i = 0; i < intList.size(); i++) {
      mapMedals.put(strList.get(i), intList.get(i));

    }
    return mapMedals;
  }

  // this method returns the most medals
  public String theMostMedals(String xPath) {
    WebElement medal = driver.findElement(By.xpath(xPath));

    if (medal.getAttribute("title").equals("Sort descending"))
      medal.click();

    String theMostMedalsCountry = driver
        .findElement(By.xpath(
            "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[1]/th/a"))
        .getText();

    return theMostMedalsCountry;
  }

  // this method returns sorted or not
  public boolean isSorted(List<WebElement> list) {

    List<Integer> intRank = changeIntType(list);
    List<String> strRank = changeStringType(list);

    if (!(intRank.isEmpty())) {
      List<Integer> intRank2 = new ArrayList<>(intRank);
      Collections.sort(intRank2);
      return intRank2.equals(intRank);
    } else {
      List<String> strRank2 = new ArrayList<>(strRank);
      Collections.sort(strRank2);
      return strRank2.equals(strRank);
    }

  }

  // this method adds country names to ArrayList
  public List<String> changeStringType(List<WebElement> strList) {
    List<String> strCountryList = new ArrayList<>();
    for (WebElement each : strList) {
      if (Character.isAlphabetic(each.getText().charAt(0))) {
        strCountryList.add(each.getText());
      }
    }
    return strCountryList;
  }

  // this method converts String numbers to int and adds to ArrayList
  public List<Integer> changeIntType(List<WebElement> numList) {
    numList.remove(numList.size() - 1);
    List<Integer> intNumList = new ArrayList<>();
    for (WebElement each : numList) {
      if (Character.isDigit(each.getText().charAt(0))) {
        intNumList.add(Integer.parseInt(each.getText()));
      }
    }
    return intNumList;
  }

  @AfterClass
  public void tearDown() throws InterruptedException {
    Thread.sleep(2000);
    driver.quit();
  }
}