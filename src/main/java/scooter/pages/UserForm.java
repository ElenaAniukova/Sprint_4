package scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserForm {
    // Имя
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");

    // Фамилия
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");

    // Адрес
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Станция метро
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");

    // Телефон
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка далее
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // Экземпляр драйвера для управления браузером
    private final WebDriver driver;

    // Явное ожидание для поиска элементов на странице
    private final WebDriverWait wait;

    // Конструктор класса
    public UserForm(WebDriver driver) {
        this.driver = driver;
        // Устанавливаем время ожидания
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    //Метод для ввода имени пользователя в соответствующее поле
    public void setUsername(String username) {
        driver.findElement(nameField).sendKeys(username);
    }
    // Метод для ввода фамилии пользователя
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }
    // Метод для ввода адреса доставки
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    // Метод для выбора станции метро из выпадающего списка
    public void setMetro(String metroStationPart, String metroStationFull) {
        // Ожидаем, пока поле ввода метро станет кликабельным, и кликаем
        wait.until(ExpectedConditions.elementToBeClickable(metroField)).click();
        // Вводим часть названия станции для фильтрации
        driver.findElement(metroField).sendKeys(metroStationPart);
        // Создаем динамический локатор для выбора полного названия станции
        By stationOption = By.xpath(
                "//li[contains(@class,'select-search__row') and contains(normalize-space(.),'" + metroStationFull + "')]");
        // Ожидаем, пока нужная станция появится в списке, и кликаем по ней
        wait.until(ExpectedConditions.visibilityOfElementLocated(stationOption)).click();
    }
    // Метод для ввода номера телефона
    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }
    // Метод для клика по кнопке "Далее" для перехода ко другому шагу оформления
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Проверка открытия страницы для заполнения данных покупателя
    public void ensureDateInputVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }
    // Комплексный метод для заполнения всей формы данных пользователя и перехода к следующему окну
    public void fillUserForm(String username, String surname, String address, String metroStationPart, String metroStationFull, String phone) {
        ensureDateInputVisible();
        setUsername(username);
        setSurname(surname);
        setAddress(address);
        setMetro(metroStationPart, metroStationFull);
        setPhone(phone);
        clickNextButton();
    }

}








