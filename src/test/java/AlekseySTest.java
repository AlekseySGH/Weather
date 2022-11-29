import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.time.Duration;

public class AlekseySTest extends BaseTest {

    //  TC_1_1  - Тест кейс:
    //  1. Открыть страницу https://openweathermap.org/
    //  2. Набрать в строке поиска город Paris
    //  3. Нажать пункт меню Search
    //  4. Из выпадающего списка выбрать Paris, FR
    //  5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);

        WebDriverWait loaderWait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        loaderWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@class ='owm-loader-container']/div[@class='owm-loader']")));

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']"));

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']"));

        searchCityField.click();
        searchCityField.sendKeys(cityName);
        searchButton.click();

        WebDriverWait WebDriverWait20 = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        WebDriverWait20.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//span[text()='Paris, FR ']"))));

        WebElement searchItem = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//span[text()='Paris, FR ']"));

        searchItem.click();

        WebDriverWait20.until(ExpectedConditions.textToBePresentInElement(getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2")), expectedResult));

        WebElement h2CityNameHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2"));

        String actualResult = h2CityNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //  TC_1_2  - Тест кейс:
    //  1. Открыть страницу https://openweathermap.org/
    //  2. Кликнуть по кнопке Dashboard
    //  3. Убедится что переход на страницу Dashboard совершен

    @Test
    public void testDashboardLinkFromMainPage_WhenClick() {

        String url = "https://openweathermap.org/";
        String expectedResult = "Dashboard";

        getDriver().get(url);

        WebDriverWait loaderWait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        loaderWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@class ='owm-loader-container']/div[@class='owm-loader']")));

        WebElement dashboardButton = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//li/a[text() = 'Dashboard']"));

        dashboardButton.click();

        WebElement NameHeader = getDriver().findElement(By.xpath("//div/h1/b"));

        String actualResult = NameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //  TC_1_3  - Тест кейс:
    //  1. Открыть страницу https://openweathermap.org/
    //  2. Кликнуть по кнопке Dashboard
    //  3. Выполнить возврат на домашнюю страницу кликнув по ссылке
    //  4. Убедится что переход на домашнюю страницу совершен

    @Test
    public void testLinkFromMainPageToDashboardAndBackToMainPage_WhenClick() {

        String url = "https://openweathermap.org/";
        String expectedResult = "Сurrent weather and forecast - OpenWeatherMap";

        getDriver().get(url);

        WebDriverWait loaderWait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        loaderWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@class ='owm-loader-container']/div[@class='owm-loader']")));

        WebElement dashboardButton = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//li/a[text() = 'Dashboard']"));

        dashboardButton.click();

        WebElement HomeButton = getDriver().findElement(
                By.xpath("//div/ol/li/a[@href = '/']"));

        HomeButton.click();

        loaderWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@class ='owm-loader-container']/div[@class='owm-loader']")));

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }
}

