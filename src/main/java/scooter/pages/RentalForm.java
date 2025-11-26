package scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class RentalForm {

    //поле Когда привести самокат
    private final By scooterDeliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //выбираем следующий месяц
    private final By nextMonthButton = By.xpath("//button[@aria-label='Next Month']");

    //поле Срок аренды
    private final By rentalPeriod = By.className("Dropdown-placeholder");

    //поле Комментарий для курьера
    private final By courierComment = By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Локатор для кнопки "Заказать"
    private final By orderBottomButton = By.xpath("//button[text()='Заказать' and contains(@class, 'Button_Middle')]");

    // Экземпляр драйвера для управления браузером
    private final WebDriver driver;

    // Явное ожидание для поиска элементов на странице
    private final WebDriverWait wait;

    // Конструктор класса
    public RentalForm(WebDriver driver) {
        this.driver = driver;
        // Устанавливаем время ожидания
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Метод для заполнения поля Когда привести самокат
    public void setScooterDeliveryDate(String deliveryDate){
        // Ждем когда поле Когда привести самокат становится кликабельным и нажимаем на него
        wait.until(ExpectedConditions.elementToBeClickable(scooterDeliveryDateInput)).click();
        // Для поиска даты используем цикл, кнопку Следующий месяц можно нажать максимум 12 раз
        // Вводим это условие, чтобы не произошло зависания системы
        final int MAX_CLICKS = 12;
        boolean dateFound = false;

        for (int i = 0; i < MAX_CLICKS; i++) {
            // Создаем локатор для предполагаемой даты
            By scooterDeliveryDateOption = By.xpath("//div[contains(@class,'react-datepicker__day') and contains(@aria-label,'" + deliveryDate + "')]");

            // Если элемент найден в текущем месяце, кликаем по нему
            if (driver.findElements(scooterDeliveryDateOption).size() > 0) {
                wait.until(ExpectedConditions.elementToBeClickable(scooterDeliveryDateOption)).click();
                dateFound = true;
                break;
            } else {
                // Кликаем на кнопку Следующий месяц
                wait.until(ExpectedConditions.elementToBeClickable(nextMonthButton)).click();
            }
            }
            // Выводим ошибку, если дата так и не была найдена
        if (!dateFound) {
            throw new RuntimeException("Ошибка: Дата '" + deliveryDate + "' не найдена в течение " + MAX_CLICKS + " месяцев.");
        }
    }

    // Метод для заполнения поля Срок аренды
    public void setRentalPeriod(String rentalPeriodOption) {
        // Ждем пока поле Срок аренды становится кликабельным и нажимаем на него
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriod)).click();
        // Создаем локатор для предполагаемой опции аренды
        By rentalPeriodOptions = By.xpath("//div[contains(@class,'Dropdown-option') and text()='" + rentalPeriodOption + "']");
        // Прокручиваем выпадающий список до нужной опции
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(rentalPeriodOptions));
        // Ждем пока элемент станет кликабельным и кликаем на него
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOptions)).click();
    }

    // Метод для заполнения поля Цвет самоката
    public void setScooterColor(String scooterColor) {
        // Создаем локатор для предполагаемого цвета
        By scooterColorOption = By.xpath("//*[@id='" + scooterColor + "']");
        wait.until(ExpectedConditions.elementToBeClickable(scooterColorOption)).click();
    }

    // Метод для заполнения поля Комментарий для курьера
    public void setComment(String comment) {
        wait.until(ExpectedConditions.elementToBeClickable(courierComment)).sendKeys(comment);
    }

    // Метод для нажатия кнопки Заказать
    public void placeOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderBottomButton)).click();
    }

    // Проверка открытия страницы для заполнения данных покупателя
    public void isDateInputVisibilityVisible() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(scooterDeliveryDateInput));
    }
    // Метод для заполнения полей формы и нажатия кнопки Заказать
    public void fillRentalForm (String deliveryDate, String rentalPeriodOption, String scooterColor, String comment){
        isDateInputVisibilityVisible();
        setScooterDeliveryDate(deliveryDate);
        setRentalPeriod(rentalPeriodOption);
        setScooterColor(scooterColor);
        setComment(comment);
        placeOrder();

    }
    }





