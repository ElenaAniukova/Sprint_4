package scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ConfirmationPopup {
    // Создаем экземпляр веб-драйвера
    private final WebDriver driver;
    // Создаем экземпляр явного ожидания
    private final WebDriverWait wait;

    // Конструктор класса
    public ConfirmationPopup(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    // Локатор для кнопки "Да" в окне подтверждения
    private final By buttonYes = By.xpath("//button[text()='Да']");

    // Метод выполняет клик по кнопке "Да", подтверждая заказ
    public void confirmOrder() {
        // Проверяем, что появилось окно подтверждения и кликаем по кнопке Да
        wait.until(ExpectedConditions.elementToBeClickable(buttonYes)).click();
        }
    // Проверяем, осталось ли окно подтверждения видимым после клика для фиксации бага
    public boolean isConfirmationWindowStillVisible() {
        try {
            // Ждем, пока элемент станет видимым
            wait.until(ExpectedConditions.visibilityOfElementLocated(buttonYes));
            return true; // Кнопка все еще видна - баг зафиксирован
        } catch (TimeoutException e) { //
            return false; // Кнопка исчезла - баг отсутствует
        }
    }
    }


