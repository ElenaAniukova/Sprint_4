package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scooter.pages.ConfirmationPopup;
import scooter.pages.HomePageScooter;
import scooter.pages.RentalForm;
import scooter.pages.UserForm;

import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest{
    // Поля класса для хранения параметров теста
    private final String buttonType;
    private final String nameField;
    private final String surnameField;
    private final String addressField;
    private final String metroStationPart;
    private final String metroStationFull;
    private final String phoneField;
    private final String scooterDeliveryDateInput;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String courierComment;


    // Конструктор класса, который принимает набор данных для каждого запуска теста
    public OrderTest(String buttonType, String nameField, String surnameField, String addressField, String metroStationPart, String metroStationFull, String phoneField,
                     String scooterDeliveryDateInput, String rentalPeriod, String scooterColor, String courierComment) {
        this.buttonType = buttonType;
        this.nameField = nameField;
        this.surnameField = surnameField;
        this.addressField = addressField;
        this.metroStationPart = metroStationPart;
        this.metroStationFull = metroStationFull;
        this.phoneField = phoneField;
        this.scooterDeliveryDateInput = scooterDeliveryDateInput;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.courierComment = courierComment;
    }

    // Предоставляем тестовые данные для оформления заказа
    @Parameterized.Parameters()
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"top", "Мария", "Семенова", "Москва, Красная Площадь, 5", "Ново", "Новокосино", "12345678911", "7-е ноября 2025 г.", "сутки", "black",
                        "Буду дома после 18.00"},
                {"bottom", "Максим", "Сергеев", "Санкт-Петербург, Желтая Площадь, 5", "Сокол", "Сокольники", "12345678912", "17-е декабря 2025 г.", "двое суток", "grey",
                        "Звонок сломан, стучите"},
        };
    }

              // Метод, проверяющий полный позитивный сценарий оформления заказа
              @Test
              public void checkFullPositiveOrderFlow() {
                  // Открываем веб-приложение
                   driver.get(BASE_URL);
                  // Создаем объект для взаимодействия с главной страницей
                  HomePageScooter objHomePageScooter = new HomePageScooter(driver);
                  // Создаем объект для взаимодействия со страницей данных пользователя
                  UserForm objUserForm = new UserForm(driver);
                  // Создаем объект для взаимодействия со страницей Про аренду
                  RentalForm objRentalForm = new RentalForm(driver);
                  // Создаем объект для взаимодействия со всплывающим окном Хотите оформить заказ?
                  ConfirmationPopup objConfirmationPopup = new ConfirmationPopup(driver);

                  //Кликаем по кнопке "top" или "bottom"
                  objHomePageScooter.clickOrderButton(buttonType);
                  // Заполняем форму данными пользователя
                  objUserForm.fillUserForm (nameField, surnameField, addressField, metroStationPart, metroStationFull, phoneField);
                  // Заполняем форму данными об аренде
                  objRentalForm.fillRentalForm(scooterDeliveryDateInput, rentalPeriod, scooterColor, courierComment);
                  // Ожидаем, что окно подтверждения закроется и появится другое окно
                  assertFalse("Критический баг: Всплывающее окно с сообщением об успешном создании заказа не появилось (окно подтверждения зависло).",
                          objConfirmationPopup.isConfirmationWindowStillVisible());
              }
    }







