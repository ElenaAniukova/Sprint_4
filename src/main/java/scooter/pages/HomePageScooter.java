package scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class HomePageScooter {

    //ЛОКАТОРЫ И МЕТОДЫ ДЛЯ ТЕСТИРОВАНИЯ ВЫПАДАЮЩЕГО СПИСКА В РАЗДЕЛЕ «ВОПРОСЫ О ВАЖНОМ»

    // Надпись вопросы о важном
    private final By importantQuestions = By.xpath("//div[@class='Home_SubHeader__zwi_E' and text()='Вопросы о важном']");

    // Метод возвращает локаторы блоков с вопросом. Индекс от 0 до 7.
    private By getQuestionLocator(int index) {
        return By.id("accordion__heading-" + index);
    }

    // Метод возвращает локаторы блоков с ответами - результат нажатия. Индекс от 0 до 7.
    private By getAnswerLocator(int index) {
        return By.xpath(".//div[@id='accordion__panel-" + index + "']//p");
    }

    // Создаем экземпляр веб-драйвера
    private final WebDriver driver;
    // Создаем экземпляр явного ожидания
    private final WebDriverWait wait;

    // Конструктор класса
    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    // Прокрутка до списка с вопросами
    public void clickFaqQuestion(int index) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(importantQuestions));

        // Получаем локатор
        By questionLocator = getQuestionLocator(index);

        // Дожидаемся, что элемент видим и кликабелен
        wait.until(ExpectedConditions.elementToBeClickable(questionLocator));
        // Нажимаем на него
        driver.findElement(questionLocator).click();
    }

    public String getAnswerText(int index) {
        // Получаем локатор
        By answerLocator = getAnswerLocator(index);

        // Дожидаемся, что элемент ответа виден
        wait.until(ExpectedConditions.elementToBeClickable(answerLocator));

        // Находим элемент и возвращаем его текст
        return driver.findElement(answerLocator).getText();
    }


    // ЛОКАТОРЫ И МЕТОДЫ ДЛЯ ТЕСТИРОВАНИЯ ФОРМЫ ЗАКАЗА

    // Кнопка Заказать наверху
    private final By orderTopButton = By.xpath("//button[text()='Заказать']");

    // Кнопка Заказать внизу
    private final By orderBottomButton = By.xpath("//button[text()='Заказать' and contains(@class, 'Button_Middle')]");


    // Кликаем по кнопке orderTopButton
    public void clickOrderTopButton() {
        // Ожидаем, пока кнопка станет кликабельной, и кликаем
        wait.until(ExpectedConditions.elementToBeClickable(orderTopButton)).click();

    }

    // Находим и кликаем по кнопке orderBottomButton
    public void clickOrderBottomButton() {
        // Ожидаем, пока элемент станет видимым, и сохраняем его
        WebElement bottomButton = wait.until(ExpectedConditions.visibilityOfElementLocated(orderBottomButton));
        // Прокручиваем страницу до нижней кнопки заказать
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                bottomButton);
        // Кликаем по ней
        bottomButton.click();
    }

    // Метод для переключения в тесте между кнопками
    public void clickOrderButton(String buttonType) {
        if ("top".equalsIgnoreCase(buttonType)) {
            clickOrderTopButton();
        } else if ("bottom".equalsIgnoreCase(buttonType)) {
            clickOrderBottomButton();
        }
    }
}






