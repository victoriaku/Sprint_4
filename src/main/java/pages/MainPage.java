package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    public static final String MAIN_URL = "https://qa-scooter.praktikum-services.ru/";

    //Кнопки "Заказать"
    public static final By HEADER_CREATE_ORDER_BUTTON =
            By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    public static final By BOTTOM_CREATE_ORDER_BUTTON =
            By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    //Кнопка "Статус заказа"
    public static final By ORDER_STATUS_BUTTON = By.xpath(".//button[text()='Статус заказа']");
    //Поле ввода номера заказа
    public static final By ORDER_NUMBER_INPUT = By.xpath(".//input[@placeholder='Введите номер заказа']");
    //Кнопка перехода на страницу "Статус заказа"
    public static final By GO_BUTTON = By.xpath(".//button[text()='Go!']");

    //Кнопки на логотипе "Яндекс.Самокат"
    public static final By LOGO_YANDEX_LINK = By.className("Header_LogoYandex__3TSOI");
    public static final By LOGO_SCOOTER_LINK = By.className("Header_LogoScooter__3lsAR");

    //Поиск элемента по тексту
    public static final String ELEMENT_BY_TEXT = ".//*[text()='%s']";

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //Перейти на главную страницу "Самокат"
    public void openMainPage(){
        driver.get(MAIN_URL);
    }

    //Нажать на кнопку "Статус заказа"
    public void clickOrderStatusButton(){
        driver.findElement(ORDER_STATUS_BUTTON).click();
    }

    //Ввести номер заказа в поле "Статус заказа"
    public void setOrderNumber(String number){
        WebElement element = driver.findElement(ORDER_NUMBER_INPUT);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(number);
    }

    //Нажать на кнопку "Go!", ведущую на страницу "Статус заказа"
    public void clickGoButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(GO_BUTTON));
        driver.findElement(GO_BUTTON).click();
    }

    //Показать ответ на вопрос в блоке "Вопросы о важном"
    public void clickAccordionItem(String question){
        WebElement element = driver.findElement(By.xpath(String.format(ELEMENT_BY_TEXT, question)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    //Проверить, есть ли на странице текст
    public boolean hasAnswerInAccordion(String answer){
        return !driver.findElements(By.xpath(String.format(ELEMENT_BY_TEXT, answer))).isEmpty();
    }

    //Нажать на кнопку "Заказать" вверху страницы
    public void clickHeaderCreateOrderButton(){
        driver.findElement(HEADER_CREATE_ORDER_BUTTON).click();
    }

    //Нажать на кнопку "Заказать" внизу страницы
    public void clickBottomCreateOrderButton(){
        WebElement element = driver.findElement(BOTTOM_CREATE_ORDER_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    //Нажать на логотип "Яндекс" вверху страницы
    public void clickYandexLogo(String yandexMainPageLink){
        driver.findElement(LOGO_YANDEX_LINK).click();
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.urlToBe(yandexMainPageLink));
        }
        catch (TimeoutException e){}
    }

    //Нажать на логотип "Самокат" вверху страницы
    public void clickScooterLogo(){
        driver.findElement(LOGO_SCOOTER_LINK).click();
    }
}
