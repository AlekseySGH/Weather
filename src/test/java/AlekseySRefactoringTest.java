import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AlekseySRefactoringTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    final static By SEARCH_ITEM = By.xpath("//ul[@class='search-dropdown-menu']//span[text()='Paris, FR ']");
    final static By ACTUAL_CITY_NAME_HEADER = By.xpath("//div[@id='weather-widget']//h2");
    final static By DASHBOARD_BUTTON = By.xpath("//div[@id='desktop-menu']//li/a[text() = 'Dashboard']");
    final static By HOME_BUTTON = By.xpath("//div/ol/li/a[@href = '/']");
    final static By HEADER_NAME = By.xpath("//div/h1/b");

    private void LoaderWait() {
        getWait60().until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@class ='owm-loader-container']/div[@class='owm-loader']")));
    }

    private void WebDriverWait20ForClick(By by) {
        getWait20().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(by)));
    }
    private void WebDriverWait20ForPresentElement(By by, String text) {
        getWait20().until(ExpectedConditions.textToBePresentInElement(getDriver().findElement(by), text));
    }

    //  TC_1_1  - Тест кейс:
    //  1. Открыть страницу https://openweathermap.org/
    //  2. Набрать в строке поиска город Paris
    //  3. Нажать пункт меню Search
    //  4. Из выпадающего списка выбрать Paris, FR
    //  5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {

        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(BASE_URL);

        LoaderWait();

        WebElement searchCityField = getDriver().findElement(SEARCH_CITY_FIELD);
        WebElement searchButton = getDriver().findElement(SEARCH_BUTTON);

        searchCityField.click();
        searchCityField.sendKeys(cityName);
        searchButton.click();

        WebDriverWait20ForClick(SEARCH_ITEM);

        WebElement searchItem = getDriver().findElement(SEARCH_ITEM);

        searchItem.click();

        WebDriverWait20ForPresentElement(ACTUAL_CITY_NAME_HEADER, expectedResult);

        WebElement h2CityNameHeader = getDriver().findElement(ACTUAL_CITY_NAME_HEADER);

        String actualResult = h2CityNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //  TC_1_2  - Тест кейс:
    //  1. Открыть страницу https://openweathermap.org/
    //  2. Кликнуть по кнопке Dashboard
    //  3. Убедится что переход на страницу Dashboard совершен

    @Test
    public void testDashboardLinkFromMainPage_WhenClick() {

        String expectedResult = "Dashboard";

        getDriver().get(BASE_URL);

        LoaderWait();

        WebElement dashboardButton = getDriver().findElement(DASHBOARD_BUTTON);

        dashboardButton.click();

        WebElement headerName = getDriver().findElement(HEADER_NAME);

        String actualResult = headerName.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //  TC_1_3  - Тест кейс:
    //  1. Открыть страницу https://openweathermap.org/
    //  2. Кликнуть по кнопке Dashboard
    //  3. Выполнить возврат на домашнюю страницу кликнув по ссылке
    //  4. Убедится что переход на домашнюю страницу совершен

    @Test
    public void testLinkFromMainPageToDashboardAndBackToMainPage_WhenClick() {

        String expectedResult = "Сurrent weather and forecast - OpenWeatherMap";

        getDriver().get(BASE_URL);

        LoaderWait();

        WebElement dashboardButton = getDriver().findElement(DASHBOARD_BUTTON);

        dashboardButton.click();

        WebElement HomeButton = getDriver().findElement(HOME_BUTTON);

        HomeButton.click();

        LoaderWait();

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
