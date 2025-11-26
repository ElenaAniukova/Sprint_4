package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// Абстрактный базовый класс для тестовых классов
public abstract class BaseTest {

    // Поле для хранения экземпляра WebDriver
    protected static WebDriver driver;
    // Поле с адресом тестируемого веб-приложения
    protected static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    // Метод, который выполняется один раз перед запуском всех тестов в классе
    @BeforeClass
    public static void setupDriver() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    // Метод, который выполняется один раз после завершения всех тестов в классе.
    @AfterClass
    public static void teardownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}


