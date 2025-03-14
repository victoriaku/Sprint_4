package pages;

import org.openqa.selenium.*;

public class CreateOrderPage {

    private WebDriver driver;

    //Поля формы "Для кого самокат"
    public static final By NAME_INPUT = By.xpath(".//input[@placeholder='* Имя']");
    public static final By SURNAME_INPUT = By.xpath(".//input[@placeholder='* Фамилия']");
    public static final By ADDRESS_INPUT = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    public static final By METRO_STATION_FIELD = By.className("select-search__value");
    public static final By PHONE_NUMBER_INPUT =
            By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее" на форме "Для кого самокат"
    public static final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");

    //Поля формы "Про аренду"
    public static final By DELIVERY_DATE_INPUT = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    public static final By RENTAL_PERIOD_DROPDOWN = By.xpath(".//div[@class='Dropdown-control']");
    public static final By COMMENT_INPUT = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать" на форме "Про аренду"
    public static final By CREATE_ORDER_BUTTON =
            By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    //Кнопка "Да" в окне "Хотите оформить заказ?"
    public static final By CONFIRM_ORDER_BUTTON = By.xpath(".//button[text()='Да']");

    public static final By ORDER_PLACED_TEXT = By.xpath(".//*[text()='Заказ оформлен']");

    public CreateOrderPage(WebDriver driver){
        this.driver = driver;
    }

    //Ввести данные в поле "Имя"
    public void setName(String name){
        driver.findElement(NAME_INPUT).sendKeys(name);
    }

    //Ввести данные в поле "Фамилия"
    public void setSurname(String surname){
        driver.findElement(SURNAME_INPUT).sendKeys(surname);
    }

    //Ввести данные в поле "Адрес"
    public void setAddress(String address){
        driver.findElement(ADDRESS_INPUT).sendKeys(address);
    }

    //Выбрать станцию метро из саджеста
    public void chooseMetroStation(String metroStation){
        driver.findElement(METRO_STATION_FIELD).click();
        WebElement element = driver.findElement(By.xpath(String.format(".//*[text()='%s']/parent::button", metroStation)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    //Ввести данные в поле "Телефон"
    public void setPhoneNumber(String phoneNumber){
        driver.findElement(PHONE_NUMBER_INPUT).sendKeys(phoneNumber);
    }

    //Заполнить поля формы "Для кого самокат"
    public void fillFormAboutClient(String name, String surname, String address,
                                    String metroStation, String phoneNumber){
        setName(name);
        setSurname(surname);
        setAddress(address);
        chooseMetroStation(metroStation);
        setPhoneNumber(phoneNumber);
    }

    //Нажать на кнопку "Далее"
    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }

    //Ввести дату в поле "Когда привезти самокат"
    public void setDeliveryDate(String deliveryDate){
        WebElement element = driver.findElement(DELIVERY_DATE_INPUT);
        element.sendKeys(deliveryDate);
        element.sendKeys(Keys.ENTER);
    }

    //Выбрать срок аренды
    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(RENTAL_PERIOD_DROPDOWN).click();
        driver.findElement(By.xpath(String.format(".//*[text()='%s']", rentalPeriod))).click();
    }

    //Выбрать цвет самоката
    private void setColor(String color) {
        driver.findElement(By.id(color)).click();
    }

    //Ввести данные в поле "Комментарий"
    private void setComment(String comment) {
        driver.findElement(COMMENT_INPUT).sendKeys(comment);
    }

    //Заполнить поля формы "Про аренду"
    public void fillFormAboutRent(String deliveryDate, String rentalPeriod, String color, String comment) {
        setDeliveryDate(deliveryDate);
        setRentalPeriod(rentalPeriod);
        setColor(color);
        setComment(comment);
    }

    //Нажать на кнопку "Заказать" на форме "Про аренду"
    public void clickCreateOrderButton(){
        driver.findElement(CREATE_ORDER_BUTTON).click();
    }

    //Нажать на кнопку "Да" в окне "Хотите оформить заказ?"
    public void clickConfirmOrderButton(){
        driver.findElement(CONFIRM_ORDER_BUTTON).click();
    }

    //Есть ли на странице текст "Заказ оформлен"
    public boolean hasOrderPlacedText(){
        return !driver.findElements(ORDER_PLACED_TEXT).isEmpty();
    }
}
