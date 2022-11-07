import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AlekseySTest {

    //  TC_1_1  - Тест кейс:
    //  1. Открыть страницу https://openweathermap.org/
    //  2. Набрать в строке поиска город Paris
    //  3. Нажать пункт меню Search
    //  4. Из выпадающего списка выбрать Paris, FR
    //  5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "d:\\RedRover_school\\Soft\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );

        searchButton.click();
        Thread.sleep(1000);

        WebElement searchItem = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//span[text()='Paris, FR ']")
        );

        searchItem.click();

        WebElement h2CityNameHeader = driver.findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(1000);

        String actualResult = h2CityNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testDashboardLinkFromMainPage_WhenClick() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "d:\\RedRover_school\\Soft\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "Dashboard";

        driver.get(url);

        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);

        Boolean dynamicElement = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[@class ='owm-loader-container']/div[@class='owm-loader']")));

        WebElement dashboardButton = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//li/a[text() = 'Dashboard']"));

        dashboardButton.click();

        WebElement NameHeader = driver.findElement(By.xpath("//div/h1/b")
        );

        String actualResult = NameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testLinkFromMainPageToDashboardAndBackToMainPage_WhenClick() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "d:\\RedRover_school\\Soft\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "Сurrent weather and forecast - OpenWeatherMap";

        driver.get(url);

        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);

        Boolean dynamicElement = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[@class ='owm-loader-container']/div[@class='owm-loader']")));

        WebElement dashboardButton = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//li/a[text() = 'Dashboard']"));

        dashboardButton.click();

        WebElement NameHeader = driver.findElement(By.xpath("//div/h1/b")
        );

        String actualResult1 = NameHeader.getText();

        WebElement HomeButton = driver.findElement(
                By.xpath("//div/ol/li/a[@href = '/']"));

        HomeButton.click();

        dynamicElement = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[@class ='owm-loader-container']/div[@class='owm-loader']")));

        String actualResult = driver.getTitle();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
}
